package com.bit2025.emaillist.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.bit2025.emaillist.repository.EmailRepository;
import com.bit2025.emaillist.repository.EmaillogRepository;
import com.bit2025.emaillist.vo.EmailVo;

@Service
public class EmaillistService {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Autowired
	private EmailRepository emailRepository;

	@Autowired
	private EmaillogRepository emaillogRepository;

	public List<EmailVo> getEmails() {
		return emailRepository.findAll();
	}

	public void addEmail(EmailVo emailVo) {
		// TX:begin ////////////////////////
		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			int count = emaillogRepository.update();
			if(count == 0) {
			emaillogRepository.insert();
		}
		// TX:end(success) ////////////////////////
		emailRepository.insert(emailVo);
		
		transactionManager.commit(txStatus);
		// TX:end(fail) ////////////////////////
		} catch(Throwable e) {
			transactionManager.rollback(txStatus);
			
			throw new RuntimeException(e);
		}
	}

	public void deleteEmail(Long id) {
		// 트랜잭션 동기화 작업: 초기화
		TransactionSynchronizationManager.initSynchronization();
		Connection conn = DataSourceUtils.getConnection(dataSource);
		
		try {
			// TX:begin ////////////////////////
			conn.setAutoCommit(false);
			
			EmailVo vo = emailRepository.findById(id);
			emailRepository.deleteById(id);
			emaillogRepository.update(vo.getRegDate());
			
			// TX:end(success) ////////////////////////
			conn.commit();
		} catch(Throwable e) {
			try {
			// TX:end(fail) ////////////////////////
				conn.rollback();
			} catch (SQLException e1) {
			}
			
			throw new RuntimeException(e);
			
		}	finally {
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}
}