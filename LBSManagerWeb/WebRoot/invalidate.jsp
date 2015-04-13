<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 手动销毁
	session.invalidate();
	response.sendRedirect("/LBSManagerWeb/admin/login.jsp");
%>
