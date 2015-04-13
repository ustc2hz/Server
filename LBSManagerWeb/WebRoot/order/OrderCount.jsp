<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="#6ebff7">
<div>
<%@include file="../files/header.jsp"%>
<div style="width:901px; height:300px;background:#31a7f8;margin-left:auto; margin-right:auto;"  >
<form action="/LBSManagerWeb/order/Order_Count" method="post">
<table align="center" >
	<tr>
		<td ><h1 align="center">统计信息</h1></td>
	</tr>
</table>
<table align="center" border="1" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" colspan="5">统计总收益</td>
	</tr>
	<tr>
		<td colspan="2"><s:textfield name="OrderNum" label="总订单数:" value="%{OrderNum}"/> </td>
		<td colspan="2"><s:textfield name="OrderPrice" label="总订单金额" value="%{OrderPrice}"/> </td>
	</tr>
	<tr>
		<td align="center" colspan="4">统计某年月收益</td>
	</tr>
	<tr>
		<td>输入年： </td>
		<td><select name="Year">
				<option value="2014" >2014</option>
				<option value="2015">2015</option>
				<option value="2016">2016</option>
				<option value="2017">2017</option>
				<option value="2018">2018</option>
			</select>年
		</td>
		<td>输入月： </td>
		<td><select name="Month" >
				<option value="01">1</option>
				<option value="02">2</option>
				<option value="03">3</option>
				<option value="04">4</option>
				<option value="05">5</option>
				<option value="06">6</option>
				<option value="07">7</option>
				<option value="08">8</option>
				<option value="09">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
			</select>月
		</td>
	</tr>
	<tr>
		<td colspan="2"><s:textfield name="OrderYMNum" label="总订单数" value="%{OrderYMNum}"/> </td>
		<td colspan="2"><s:textfield name="OrderYMPrice" label="总订单金额" value="%{OrderYMPrice}"/></td>
	</tr>
	<tr>
		<td colspan="4" align="right" ><input type="submit" id="buttonid" name="button" value="查询" /></td>
	</tr>
</table>
</form>
</div>
<%@include file="../files/bottom.jsp" %>
</div>
</body>
</html>