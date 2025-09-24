<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${'Hello World: JSP(JSTL/EL) View' }</h1>
	
	
	<div style="padding-top: 100px">
		<a href="${pageContext.request.contextPath }/ex01">return to index</a>
	</div>	
</body>
</html>