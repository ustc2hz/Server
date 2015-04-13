<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
 <script type="text/javascript">
	function  ValidateFromone()
	{
		var oldpassword=document.getElementById("OldPassword").value;
		if(oldpassword == "")
		{
			alert("旧密码不能为空!");
			return false;
		}
		var password=document.getElementById("NewPassword").value;
		var repassword=document.getElementById("AgainPassword").value;
		if(password == "")
		{ 
			alert("新密码不能为空!");
			return false;
		}
		if(password != repassword)
		{
			alert("两次输入的密码不一致!");
			return false;
		}
		var validatepassword=document.getElementById("NewPassword").value;
		if((validatepassword.match("^[a-zA-Z]{1}[a-zA-Z0-9]{4,19}$"))== null)
		{
			alert("密码必须是5-20位以字母开头的字母和数字的组合！");
			return false;
		}
		//判断电话的输入
		var uphone = document.getElementById("adminPhone").value;
		 if(!(/^1[3|5][0-9]\d{4,8}$/.test(uphone))){ 
		        alert("不是完整的11位手机号或者正确的手机号前七位"); 
		        return false; 
		    } 
	}
 </script>
</head>
<body bgcolor="#6ebff7">
<div>
<%@include file="../files/header.jsp"%>
<div style="width:901px; height:280px;background:#31a7f8;margin-left:213; margin-right:auto;">
<form action="/LBSManagerWeb/admin/admin_update" method="post" onsubmit="return ValidateFromone();">
<table align="center">
	<tr>
		<td ><h1 align="center">用户修改信息</h1></td>
	</tr>
	<tr>
		<td style="color:red;" align="center"> <s:fielderror fieldName="deleteMsg"></s:fielderror> </td><!-- 提示用户注销失败信息 -->
	</tr>
</table>
<table align="center">
	<tr>
		<td align="center" style="color:red; font-size: 20;"> 
  			<s:fielderror fieldName="PWsuccess"></s:fielderror>
  			<s:fielderror fieldName="PWfail"></s:fielderror>
  			<s:fielderror fieldName="PWerror"></s:fielderror>
  		</td>
	</tr>
</table>
<table align="center" width="473" border="1"  cellPadding="0"  bordercolor="white" cellspacing="0">
  <tr>
    <td width="244" height="78" rowspan="6" align="center"><img src="../image/9.jpg" width="180" height="130" /></td>
  </tr>
  <tr>
  	<td style="color:black;" colspan="2" align="center" height="45"><font size="6" style="font-weight:BOLD">修改密码</font></td>
  </tr>
  <tr>
    <td  width=100>旧 密 码:</td>
    <td  width=120>
    <input type="password" style="WIDTH: 130px" name="OldPassword" id="OldPassword"> </td>
  </tr>
  <tr>
    <td  width=100>新 密 码:</td>
    <td  width=120><input type="password" style="WIDTH: 130px" name="NewPassword" id="NewPassword"/></td>
  </tr>
  <tr>
    <td  width=100 >确认密码:</td>
    <td  width=120><input type="password" style="WIDTH: 130px" name="AgainPassword" id="AgainPassword"/></td>
  </tr>
  <tr>
  	<td  width=100 >电话号码:</td>
  	<td  width=120><input type="text" style="WIDTH: 130px" name="adminPhone" id="adminPhone"/></td>
  </tr>
  <tr>
  	<td align="center" ><input type="button" name="button1" id="button1" value="注销用户" onclick="window.location.href='/LBSManagerWeb/admin/admin_delete'"/></td>
    <td align="center" colspan="2">
    	<input type="submit" name="submit" id="button1" value="提交"  />  
    </td>
  </tr>
</table>
</form>
</div>
<%@include file="../files/bottom.jsp" %>
<%@include file="/message/updatePWmessage.jsp" %>
</div>
</body>
</html>