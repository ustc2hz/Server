<%@page import="com.sun.jndi.cosnaming.IiopUrl.Address"%>
<%@page import="cn.LBS.orm.orderOperate"%>
<%@page import="cn.LBS.orm.adminOperate"%>
<%@page import="cn.LBS.model.ParkInfo"%>
<%@page import="java.util.List"%>
<%@page import="cn.LBS.orm.parkOperate"%>
<%@page import="cn.LBS.model.Admin"%>
<%@page import="cn.LBS.model.Park"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>停车场信息</title>
<style type="text/css">
		body{margin:0;height:100%;width:100%;position:absolute;}
		#mapContainer{height:100%;width:100%;}
		#btnDiv{
			position:absolute;
			bottom: 0;
			left:10px;
			height:150px;
			font-size:12px;
		}
		#btnDiv input{
			height:23px;
			outline:none;
			border:1px solid #ddd;
			padding-left:5px;
			border-radius:3px;
		}
	</style>
<script type="text/javascript" language="javascript">
	var flag=0;
	function $(id)
	{
    	return document.getElementById(id);
    }
	function controlDIV()
	{
   	 	if(flag==0)
    	{
       	 	$("my").style.visibility="hidden";
      	 	$("bt_display").value="显示";
       	 	flag=1;
   		}
    	else
    	{
       	   $("my").style.visibility="";
       	   $("bt_display").value="隐藏";
       	   flag=0;    
    	}
    }
	function ValidateNum()
	{
		var publicNum = document.getElementById("parkPublic").value;
		var parkNum = document.getElementById("ParkTotalNum").value;
		if(publicNum > parkNum){
			 alert("发布车位数不能大于总车位数!");
			 return false;
		}else{
			return true;
		}
		var parkaddress = document.getElementById('address').value;
		if(parkaddress == ""){
			 alert("没有选中地址");
			return false;
		}
	}
 </script>
 </head>
 <body bgcolor="#6ebff7">
<div>
<%@include file="../files/header.jsp"%>
<div style="width:901px; height:400px;background:#31a7f8;margin-left:auto; margin-right:auto;">
<form action="/LBSManagerWeb/park/ParkInfo_update"  method="post" onsubmit="return ValidateNum()">
<table align="center"> 
	<tr>
		<td ><h1 align="center">车位信息</h1></td>
	</tr>
</table>
<table align="center">
	<tr>
		<td align="center" style="color:red; font-size: 20;"> 
  			<s:fielderror fieldName="updatemsg"></s:fielderror>
  			<s:fielderror fieldName="deletemsg"></s:fielderror>
  			<s:fielderror fieldName="sendYuntu"></s:fielderror>
  			<s:fielderror fieldName="updateYuntu"></s:fielderror>
  		</td>
	</tr>
</table>
<table  border="1"  align="center" cellpadding="0" cellspacing="0" bgcolor="#31a7f8">
  <tr>
  	<td align="center" colspan="3" style="color:red; font-size: 20;">
  		<s:fielderror fieldName="msg"></s:fielderror>
  	 </td>
    <td align="right" colspan="1">
    	<input type="submit" name="update" id="button1" value="提交修改"/>
    </td>
  </tr>
  <%!
  	Admin admin = new Admin();
	Park park = new Park();
	ParkInfo parkinfo = new ParkInfo();
	parkOperate operate = new parkOperate();
	orderOperate orderoperate = new orderOperate();
	String oldphone ="";
	Integer count = null;
	Integer remainCount = null;
	String flag = null;
	String[] address = {"",""};
  %>
  <%	
	admin = (Admin)application.getAttribute("existAdmin");
	List list1= operate.findparkInfobyadName(admin.getAdminName());
	List list2= operate.findparkbyadName(admin.getAdminName());
	count = orderoperate.CountOrderParkNum(admin.getAdminName());
	if(list1 != null){
		parkinfo = (ParkInfo)list1.get(0);
		if( parkinfo.getParkAddress() == null){
			flag = "0";//表示创建
		}else{//表示修改
			flag = "1";
			String orgStr = parkinfo.getParkAddress();
			address = orgStr.split(",");
			System.out.println("经度："+address[0]);
			System.out.println("纬度："+address[1]);
		}
	}
	System.out.println("jsp中flag"+flag);
	if(list2 != null){
		park = (Park)list2.get(0);
		System.out.println(park.getParkPublic());
		if(park.getParkPublic() == null){
			remainCount = 0;
		}else{
			remainCount = Integer.valueOf(park.getParkPublic())- count ;
		}
	}
  %>
  <tr>
    <td>总车位：</td>
    <td><input type="text" name="parkSum" id="ParkTotalNum" width="10px" value = <%=(parkinfo.getParkSum())==null?0:parkinfo.getParkSum()%>  onkeypress="return (/[\d.]/.test(String.fromCharCode(event.keyCode)))" /></td>
    <td>停车场名称： </td>
    <td><input type="text" name="parkName" id="ParkName" width="20px" value = <%=(parkinfo.getParkName())==null? 0:parkinfo.getParkName()%> /></td>
  </tr>
  <tr>
  	<td>发布车位数:</td>
    <td> <input name="parkOrder" id="parkPublic" type="text"  size="10" value= <%=(park.getParkPublic())==null?0:park.getParkPublic()%> onkeypress="return (/[\d.]/.test(String.fromCharCode(event.keyCode)))" /></td>
    <td>未预定车位数：</td>
    <td> <input name="parkRemain" type="text"  size="10" readonly="readonly" value= <%=remainCount%> /></td>
  </tr>
  
  <tr>
  		<td align="center" colspan="4">预定收费标准</td>
  </tr>
  <tr>
    <td>预定时间：</td>
    <td >20分钟</td>
    <td align="right"> 收费价格：</td>
    <td> <input type="text" name="orderTen" id="TenMins" width="5px" value = <%=(parkinfo.getOrderTen())==null?0:parkinfo.getOrderTen()%>  onkeypress="return (/[\d.]/.test(String.fromCharCode(event.keyCode)))" />  元</td>
  </tr>
  <tr>
    <td >预定时间：</td>
    <td  >40分钟       </td>
    <td align="right">收费价格：</td>
    <td><input type="text" name="orderTri" id="FifMins" width="5px" value= <%=(parkinfo.getOrderTri())==null?0:parkinfo.getOrderTri()%>  onkeypress="return (/[\d.]/.test(String.fromCharCode(event.keyCode)))" /> 元	</td>
  </tr>
  <tr>
    <td >预定时间：</td>
    <td >60分钟  </td>
    <td align="right">收费价格: </td>
    <td><input type="text" name="orderTwe" id="TweMins" width="5px" value= <%=(parkinfo.getOrderTwe())==null? 0:parkinfo.getOrderTwe()%>  onkeypress="return (/[\d.]/.test(String.fromCharCode(event.keyCode)))" /> 元</td>
  </tr>
   <tr>
  		<td align="center" colspan="4">停车收费标准</td>
  </tr>
  <tr>
  	<td >停车时长：</td>
    <td >0.5小时</td>
    <td align="right">收费价格：</td>
    <td> <input type="text" name="payHalPay" id="HalfHour" width="5px" value= <%=(parkinfo.getPayHalPay())== null?0:parkinfo.getPayHalPay()%>  onkeypress="return (/[\d.]/.test(String.fromCharCode(event.keyCode)))" /> 元</td>
  </tr>
  <tr>
  	<td >停车时长：</td>
    <td >1小时</td>
    <td align="right">收费价格：</td>
    <td> <input type="text" name="payOneHour" id="OneHour" width="5px" value= <%=(parkinfo.getPayOneHour())==null?0:parkinfo.getPayOneHour() %> onkeypress="return (/[\d.]/.test(String.fromCharCode(event.keyCode)))"/> 元</td>
  </tr>
  <tr>
  	<td >停车时长：</td>
    <td >1小时以上</td>
    <td align="right">收费价格：</td>
    <td><input type="text" name="payMorePay" id="MoreHour" width="5px" value= <%=(parkinfo.getPayMorePay())==null?0:parkinfo.getPayMorePay() %> onkeypress="return (/[\d.]/.test(String.fromCharCode(event.keyCode)))"/> 元</td>
  </tr>
  <tr>
  	<td colspan="4">停车场电话：<input type="text" name="adminPhone" readonly="readonly" id="phone" width="10px" value = <%=parkinfo.getAdminPhone()%> /> </td>
  </tr>
  <tr>
  	<td colspan="4">停车场地址：<input name="parkAddress" id="address" type="text" value = <%=(parkinfo.getParkAddress())==null?0:parkinfo.getParkAddress()%> /> <input type="button" id="bt_display" value="隐藏" onclick="controlDIV()"/> </td>
  </tr>
  <tr>
  	<td><input type="hidden" id="flag" name="flag" value=<%=flag%> /></td>
  </tr>
</table>
<div id="my">
	<div>
			<br>地图经纬度坐标：(<b>鼠标左键在地图上单击获取经纬度坐标</b>)
			<br>X：<input type="text" id="lngX" value=<%=address[0]%> />&nbsp;Y：<input type="text" id="latY" value=<%=address[1]%> />
			<input type="button" value="标记当前位置" onClick="javascript:addMarker()"/>  
	</div>
	<div id="mapContainer"></div>
	<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=7c15f6dc0831a55ab415d01d89c6e2d4"></script>
	<script type="text/javascript">
		
			var a = document.getElementById('lngX').value;
			var b = document.getElementById('latY').value;	
			if(a == "/"){
				a = 120.633318;
				document.getElementById('lngX').value = a;
			}
			if(b == "/"){
				b = 31.28295;
				document.getElementById('latY').value = b;
			}
		var map = new AMap.Map('mapContainer', {
			view: new AMap.View2D({
				center: new AMap.LngLat(a, b),
				zoom: 12
			})
		});		
		AMap.event.addListener(map,'click',getLnglat);	
		//鼠标在地图上点击，获取经纬度坐标
		function getLnglat(e){  
			document.getElementById('lngX').value = e.lnglat.getLng();  
			document.getElementById('latY').value = e.lnglat.getLat();  
			document.getElementById('address').value = e.lnglat.getLng().toString()+","+ e.lnglat.getLat().toString();
		} 
		
		//添加点标记
		function addMarker(){
			var LngLatX = document.getElementById('lngX').value; //获取Lng值
			var LngLatY = document.getElementById('latY').value; //获取Lat值
			marker = new AMap.Marker({				  
				icon: "http://webapi.amap.com/images/marker_sprite.png",
				position:new AMap.LngLat(LngLatX,LngLatY)
			});
			
			marker.setMap(map);  //在地图上添加点
			map.setFitView(); //调整到合理视野
		}
	</script>
</div>
</form>
</div>
<%@include file="../files/bottom.jsp" %>
</div>
</body>
</html>