<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
 <body>  
 	<script type="text/javascript">
	var register = ${applicationScope.register};
	if(register != ""){
		alert("注册成功,去登陆！");
		window.location.href='/LBSManagerWeb/admin/login.jsp';
	}
 </script>
<%
	application.removeAttribute("register");
%>
</body>  
</html>  