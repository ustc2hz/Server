<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
</head>
<body bgcolor="#6ebff7">
<div>
<%@include file="/header.jsp" %>
<div>
<table width="901" height="500" border="1" bgcolor="#31a7f8" align="center" bordercolor="white">
  <tr>
    <td width="888" valign="top">
    <table width="654" height="377" align="center" cellspacing="10">
    <tr>
    	<td height="20"></td>
    </tr>
      <tr>
        <td width="205" height="130"><a href="admin/UserInfo.jsp"><img src="image/9.jpg" width="139" height="128" /></a></td>
        <td width="203"><a href="park/ParkInfo.jsp"><img src="image/1.jpg" width="139" height="128" /></a></td>
        <td width="198"><a href="order/OrderInfoo.jsp"><img src="image/11.jpg" width="139" height="128" /></a></td>
      </tr>
      <tr>
      	<td height="21" valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="admin/UserInfo.jsp" style="text-decoration:none">个人信息</a></td>
        <td height="21" valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="park/ParkInfo.jsp" style="text-decoration:none">车位信息</a></td>
        <td height="21" valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="order/OrderInfoo.jsp" style="text-decoration:none">预订信息</a></td>
      </tr>
      <tr>
        <td height="131"><a href="order/OrderHandle.jsp"><img src="image/10.jpg" width="139" height="128" /></a></td>
        <td><a href="park/UpdatePark.jsp"><img src="image/6.jpg" width="139" height="128" /></a></td>
        <td><a href="order/OrderCount.jsp"><img src="image/12.png" width="139" height="128" /></a></td>
      </tr>
      <tr>
      		<td height="21" valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="order/OrderHandle.jsp" style="text-decoration:none">预定处理</a></td>
        	<td height="21" valign="top"> &nbsp;&nbsp;&nbsp;&nbsp;<a href="park/UpdatePark.jsp" style="text-decoration:none">周围停车场信息</a></td>
            <td height="21" valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="order/OrderCount.jsp" style="text-decoration:none">订单统计</a></td>
      </tr>
    </table></td>
  </tr>
</table>
	<s:actionmessage/>
</div>
<%@include file="/files/bottom.jsp" %>
</div>
</body>
</html>