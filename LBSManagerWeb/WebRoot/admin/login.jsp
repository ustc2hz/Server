<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<head>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<title>管理员登陆注册界面</title>
<STYLE type=text/css>
BODY{
		FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
	}
TD{
	FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
  }
</STYLE>
<script type="text/javascript">
	function  ValidateFrom()
	{
		var username=document.getElementById("txtName").value;
		if(username == "")
		{
			alert("用户名不能为空!");
			return false;
		}
		var password=document.getElementById("txtPwd").value;
		if(password == "")
		{ 
			alert("密码不能为空!");
			return false;
		}
		if((password.match("^[a-zA-Z]{1}[a-zA-Z0-9]{4,19}$"))== null)
		{
			alert("密码格式不正确！");
			return false;
		}
	}
</script>
</HEAD>
<BODY bgcolor="31a7f8">
	<FORM id=form1 name=form1 method="post" action="/LBSManagerWeb/admin/admin_login" onsubmit="return ValidateFrom();">
	<DIV>
		<TABLE cellSpacing=0 cellPadding=0 width=900 align=center border=0>
  			<TBODY>
  				<TR>
   					 <TD style="HEIGHT: 105px"><IMG src="login_1.jpg"  border=0></TD>
   				</TR>
  				<TR>
    				<TD background="login_2.jpg" height=300>
     				 <TABLE height=300 cellPadding=0 width=900 border=0>
        				<TBODY>
       						 <TR>
        					  <TD colSpan=2 height=35></TD>
        					 </TR>
       						 <TR>
         						 <TD width=360></TD>
         					 <TD> <TABLE cellSpacing="-2" cellPadding="0" border=0 align="center">
              <TBODY>
              <tr>
              		<td style="color:red;" colspan="2">
              		<s:fielderror fieldName="NameOrPassword"></s:fielderror> </td><!-- 提示用户名或是密码错误信息 -->
              </tr>
              <TR>
                <TD style="HEIGHT: 28px" width=80>登 陆 名：</TD>
                <TD style="HEIGHT: 28px" width=100>
                	<INPUT id=txtName style="WIDTH: 130px" name=adminName value="${cookie.username.value }">
                </TD>
                <td style="color:red;"></td>
              </TR>
              <TR>
                <TD style="HEIGHT: 28px">登陆密码：</TD>
                <TD style="HEIGHT: 28px">
                	<INPUT id=txtPwd style="WIDTH: 130px" type=password name=adminPassword value="${cookie.password.value }"></TD>      
              </TR>
              <TR>
                <TD style="HEIGHT: 8px" align="right"><input type="checkbox" name="remember" value="yes" /></TD>
                <TD style="HEIGHT: 8px">记住用户名和密码<br></TD> 
              </TR>
              <TR Align="center">
              	<td></td>
              	<Td>
                	<input id=btn2 type="submit" name="submit" value="登陆"/>
                 	<input id=btn2 type="button" name="button2" value="注册" onclick="javascript:window.location.href='register.jsp'"/>
                 </Td>
                <TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
              </TR>
            </TBODY>
          </TABLE>
       </TD>
     </TR>
   </TBODY>
 </TABLE>
 </TD>
 </TR>
  <TR>
    <TD><IMG src="login_3.jpg" border=0></TD>
  </TR>
</TBODY>
 </TABLE>
 </DIV>
 <%@include file="/message/Welcomemessage.jsp" %>
</FORM>
</BODY>
</HTML>
