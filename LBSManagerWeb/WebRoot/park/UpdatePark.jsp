<%@page import="cn.LBS.orm.parkOperate"%>
<%@page import="cn.LBS.model.ParkInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>停车场地址</title>
<script type="text/javascript" language="javascript">
	 function test()
	 {
     	var page = document.getElementById("goPage").value;    
   	   	window.location.href="/LBSManagerWeb/park/UpdatePark.jsp?curPage="+page; 
	 }
</script>
</head>
<body bgcolor="#6ebff7">
<div>
<%@include file="../files/header.jsp"%>
<div style="width:901px; height:400px;background:#31a7f8;margin-left:auto; margin-right:auto;">
<table align="center">
	<tr>
		<td ><h1 align="center">附近停车场信息</h1></td>
	</tr>
</table>
<%!public static final int PAGESIZE = 6; 
	int pageCount; 
	int curPage = 1; 
	int size=0;
%>
<table width="662"  align="center" cellpadding="0" cellspacing="0" border="1">
	 <tr>
      <td width="172" align="center">停车场名字</td>
      <td width="80" align="center">提前5分钟</td>
      <td width="80" align="center">提前10分钟</td>
      <td width="80" align="center">提前15分钟</td>
      <td width="80" align="center">预定0.5小时</td>
      <td width="80" align="center">预定1小时</td>
      <td width="90" align="center">预定1小时以上</td>
    </tr>
     <%
    		List<ParkInfo> list = new parkOperate().show();
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
    				ParkInfo parkinfo = list.get(i);
    				count++;
    %> 
     <tr>
	    <td align="center"><%=parkinfo.getParkName()%></td>
	   	<td align="center"><%=parkinfo.getOrderTen()%></td>
      	<td align="center"><%=parkinfo.getOrderTri()%> </td>
      	<td align="center"><%=parkinfo.getOrderTwe()%></td>
      	<td align="center"><%=parkinfo.getPayHalPay()%></td>
      	<td align="center"><%=parkinfo.getPayOneHour()%></td>
      	<td align="center"><%=parkinfo.getPayMorePay()%></td>
    </tr>
    <%
    		}
    	}
    %>
</table>
<div align="center">
<a href = "/LBSManagerWeb/park/UpdatePark.jsp?curPage=1" >首页</a>
<a href = "/LBSManagerWeb/park/UpdatePark.jsp?curPage=<%=curPage-1%>" >上一页</a>
<a href = "/LBSManagerWeb/park/UpdatePark.jsp?curPage=<%=curPage+1%>" >下一页</a>
<a href = "/LBSManagerWeb/park/UpdatePark.jsp?curPage=<%=pageCount%>" >尾页</a>
第<%=curPage%>页/共<%=pageCount%>页 共有<%=size%>个停车场
<input id="goPage" type="text" size="3"><a href="javascript:test()" style="text-decoration:none">跳转</a>
</div>
</div>
<%@include file="../files/bottom.jsp"%>
</div>
</body>
</html>