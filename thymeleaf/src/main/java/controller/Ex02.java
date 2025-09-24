package controller;

import java.util.Map;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/ex02")
public class Ex02 {
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@GetMapping("/01")
	public String _01(HttpSession session, Model model) {
		//
		// Request Scope(Model Attributes)
		//
		UserVo vo1 = new UserVo(1L, "둘리1");
		model.addAttribute("vo", vo1);
		
		//
		// Session Scope
		//
		UserVo vo2 = new UserVo(2L, "둘리2");
		session.setAttribute("vo", vo2);

		//
		// Application Scope1(ServletContext)
		//
		UserVo vo3 = new UserVo(3L, "둘리3");
		servletContext.setAttribute("vo", vo3);
		
		// The 'request','session','servletContext' and 'response' expression utility objects are no longer available by default for template expressions and their use is not recommended.
		// In cases where they are really needed, they should be manually added as context variables.
		model.addAttribute("servletContext", servletContext);

		//
		// Application Scope2(ApplicationContext)
		//
		UserVo vo4 = new UserVo(4L, "둘리4");
		
		MutablePropertyValues propertyValues = new MutablePropertyValues(new ObjectMapper().convertValue(vo4, new TypeReference<Map<String, Object>>(){}));
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(UserVo.class);
		beanDefinition.setPropertyValues(propertyValues);
		BeanDefinitionRegistry registry = (BeanDefinitionRegistry)applicationContext.getAutowireCapableBeanFactory();
		registry.registerBeanDefinition("vo", beanDefinition);
		
		return "ex02/01";
	}
}
