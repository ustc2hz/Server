<%@page import="cn.LBS.orm.driverOperate"%>
<%@page import="com.sun.jndi.url.iiopname.iiopnameURLContextFactory"%>
<%@page import="cn.LBS.model.Admin"%>
<%@page import="cn.LBS.orm.orderOperate"%>
<%@page import="cn.LBS.model.OrderList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="java.util.*,cn.LBS.*,java.sql.Connection,java.sql.DriverManager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" language="javascript">
	function deleteOrder() 
	{
		var id = document.getElementsByName('deleteAll');
  	    var value = new Array();
		for(var i = 0; i < id.length; i++){
    		if(id[i].checked)
    		value.push(id[i].value);
   		}  
   		if(value.length == 0){//证明没有选中
    		 alert("没有选中要删除的订单");
    		 return false;
    	}else{
    		 	if (!confirm("确认要删除吗？")) {//不删除
        	    	 return  false;
        		 }else{//确认要删除
       		  		window.location.href="/LBSManagerWeb/order/Order_delete?id="+value+","; 
        		}
     		}
	}
	function newOrder()
	{
   		window.location.href="/LBSManagerWeb/order/OrderInfoo.jsp"; 
	}
  	 function test()
	 {
     	var page = document.getElementById("goPage").value;    
   	   	window.location.href="/LBSManagerWeb/order/OrderInfoo.jsp?curPage="+page; 
	 }
</script>
</head>
 <body bgcolor="#6ebff7">
<div>
<%@include file="../files/header.jsp"%>
<div style="width:901px; height:300px;background:#31a7f8;margin-left:auto; margin-right:auto;"  >
<table align="center">
	<tr>
		<td ><h1 align="center">查看预定信息列表</h1></td>
	</tr>
</table>
<table width="522"  border="0"  align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="260" align="center">
        <input type="button" name="button2" id="button2" value="刷新列表"  onclick="return newOrder()"/>
    </td>
    <td width="260" align="center">
    	<input type="button" id="delete" name="delete" value="删除" onclick="return deleteOrder()"/> </td>
  </tr>
</table>
<%!public static final int PAGESIZE = 6; 
	int pageCount; 
	int curPage = 1; 
	int size=0;
	%>
 <table border="1" id="tb" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="30" align="center">选择</td>
      <td width="100" align="center">车牌号</td>
      <td width="100" align="center">订单日期</td>
      <td width="50" align="center">预定个数</td>
      <td width="250" align="center">订单信息</td>
      <td width="50" align="center">金额</td>
      <td width="50" align="center">订单状态</td>
    </tr>
    <%
			 // 获取已处理订单信息
     		Admin admin = new Admin();
     		admin = (Admin)application.getAttribute("existAdmin");
    		List<OrderList> list = new orderOperate().findOrderAllByAdmin(admin);
    		if(list == null||list.size() < 1) {
    			out.print("没有数据");
    		}else {
    			// 遍历所有数据
    			size = list.size(); //得到总数
    			pageCount = (size%PAGESIZE==0)?(size/PAGESIZE):(size/PAGESIZE+1); //总页数
    			String tmp = request.getParameter("curPage"); 
    			if(tmp==null){ 
    				tmp="1"; 
    			} 
    			curPage = Integer.parseInt(tmp); //当前页数
    			if(curPage >= pageCount) 
    				curPage = pageCount; 
    			if(curPage < 1){
    				curPage = 1;
    			}
       			int count = 0+(curPage-1)*PAGESIZE; 
    			for(int i = count ;i<list.size();i++) {
    				if(i >= PAGESIZE*curPage)
    					break;  
    				OrderList order = list.get(i);
    				String driverName = new driverOperate().finddriverName(order.getDriver().getDriverId());
    				count++;
    %> 
	    <tr>
	    	<td align="center"><input type="checkbox" id="cb1" name="deleteAll" value=<%=order.getOrderId()%>/></td>
	   		<td align="center"><%=driverName%></td>
      		<td align="center"><%=order.getOrderDate()%> </td>
      		<td align="center"><%=order.getDriverNum()%></td>
      		<td align="center"><%=order.getOrderInfo()%></td>
      		<td align="center"><%=order.getOrderPrice()%></td>
      		<td align="center">已处理</td>
    	</tr>
    <%
   		 }
	}
	%>  
</table>
<div align="center">
<a href = "/LBSManagerWeb/order/OrderInfoo.jsp?curPage=1" >首页</a>
<a href = "/LBSManagerWeb/order/OrderInfoo.jsp?curPage=<%=curPage-1%>" >上一页</a>
<a href = "/LBSManagerWeb/order/OrderInfoo.jsp?curPage=<%=curPage+1%>" >下一页</a>
<a href = "/LBSManagerWeb/order/OrderInfoo.jsp?curPage=<%=pageCount%>" >尾页</a>
第<%=curPage%>页/共<%=pageCount%>页 共有<%=size%>个订单
<input id="goPage" type="text" size="3"><a href="javascript:test()" style="text-decoration:none">跳转</a>
</div>
</div>
<%@include file="../files/bottom.jsp" %>
<%@include file="/message/DeleteOrdermessage.jsp" %>
</div>
</body>
</html>