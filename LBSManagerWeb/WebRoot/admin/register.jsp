<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HEAD id=Head1>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<title>停车场管理员登陆注册系统</title>
<script type="text/javascript">
	function  ValidateFrom()
	{
		var username=document.getElementById("adminName").value;
		if(username == "")
		{
			alert("用户名不能为空!");
			return false;
		}
		var password=document.getElementById("txtPwd").value;
		var repassword=document.getElementById("txtcode").value;
		if(password == "")
		{ 
			alert("密码不能为空!");
			return false;
		}
		if(password != repassword)
		{
			alert("两次输入的密码不一致!");
			return false;
		}
		var validatepassword=document.getElementById("txtPwd").value;
		if((validatepassword.match("^[a-zA-Z]{1}[a-zA-Z0-9]{4,19}$"))== null)
		{
			alert("密码必须是5-20位以字母开头的字母和数字的组合！");
			return false;
		}
		var userphone = document.getElementById("phone").value;
		 if(!(/^1[3|5][0-9]\d{4,8}$/.test(userphone))){ 
		        alert("不是完整的11位手机号或者正确的手机号前七位"); 
		        return false; 
		    } 
	}
</script>
<STYLE type=text/css>
BODY{
		FONT-SIZE: 14px; COLOR: #ffffff; FONT-FAMILY: 宋体
	}
TD{
	FONT-SIZE: 14px; COLOR: #ffffff; FONT-FAMILY: 宋体
  }
</STYLE>
</HEAD>
<BODY bgcolor="31a7f8">
	<FORM id=form1 name=form1 action="/LBSManagerWeb/admin/admin_register" method="post" onsubmit="return ValidateFrom()">
	<DIV>
		<TABLE cellSpacing=0 cellPadding=0 width=900 align=center border=0>
  			<TBODY>
  				<TR>
   					 <TD style="HEIGHT: 105px"><IMG src="login_1.jpg"  border=0></TD>
   				</TR>
  				<TR>
    				<TD background=login_4.jpg height=300>
     				 <TABLE height=300 cellPadding=0 width=900 border=0>
        	<TBODY>
       	 <TR>
          <TD colSpan=2 height=35></TD></TR>
        <TR>
          <TD width=360></TD>
          <TD>
            <TABLE cellSpacing="-2" cellPadding="0" border=0 align="center">
              <TBODY>
              <tr >
              <!-- 提示用户验证码输入错误 或是用户注册成功-->
              	<td style="color:red;" colspan="2"> <s:fielderror fieldName="CunAdmin"></s:fielderror> <!-- 用户名已经存在 --> 
              										<s:fielderror fieldName="YZfail"></s:fielderror>	<!-- 验证码输入错误 -->
              										<s:fielderror fieldName="registerFail"></s:fielderror>	<!-- 注册失败 -->
              	</td>
              </tr>
              <TR>
                <TD style="HEIGHT: 28px" width=80> 用 户 名:</TD>
                <TD style="HEIGHT: 28px" width=100>
                	<INPUT id=txtName style="WIDTH: 130px" name=adminName>
                </TD>
                
              </TR>
              <TR>
                <TD style="HEIGHT: 28px"> 密    码:</TD>
                <TD style="HEIGHT: 28px"><INPUT id=txtPwd style="WIDTH: 130px" type=password name=adminPassword></TD>
                
              </TR>
              <TR>
                <TD style="HEIGHT: 28px">确认密码:</TD>
                <TD style="HEIGHT: 28px"><INPUT id=txtcode style="WIDTH: 130px" name=txtcode type=password></TD>
              </TR>
              <TR>
                <TD style="HEIGHT: 28px"> 电话号码:</TD>
                <TD style="HEIGHT: 28px"><INPUT type=text id=phone style="WIDTH: 130px" name=adminPhone></TD>
              </TR>
              <TR Align="center">
              		<Td><input id=btn1 type="button" name="button1" value="返回登陆" onclick="javascript:window.location.href='login.jsp'"></TD>
              		<Td><input id=btn2 type="submit" name="button2" value="注册"></TD>
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
    <TD><IMG src="login_3.jpg" border=0> </TD>
  </TR>
 </TBODY>
 </TABLE>
 </DIV>
  <%@include file="/message/Registermessage.jsp" %>
</FORM>
</BODY>
