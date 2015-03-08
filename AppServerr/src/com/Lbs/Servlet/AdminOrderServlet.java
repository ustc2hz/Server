package com.Lbs.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.Lbs.model.Order;
import com.Lbs.orm.OrderOperate;

/*
 * 管理员端对订单的操作
 */
public class AdminOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		boolean result = false;
		// 获取订单操作
		Integer id = Integer.valueOf(request.getParameter("flag"));
		// 获取订单信息
		String message = new String(request.getParameter("order").getBytes(
				"iso-8859-1"), "utf-8");
		System.out.println(message);
		OrderOperate orderOperate = new OrderOperate();

		ObjectMapper objectMapper = new ObjectMapper();
		Order order = objectMapper.readValue(message, Order.class);
		PrintWriter out = response.getWriter();
		// 编号为1 删除单个订单 编号为2删除所有订单 编号3:修改订单 编号为4: 查找需要处理的订单 编号为5: 查询所有订单
		switch (id) {
		case 1: // 删除单个订单
			//result = orderOperate.deleteOrder(order);
			if (result) {
				out.print("success");
			} else {
				out.print("fail");
			}
			break;
		case 2: // 删除所有订单信息
			String pName = request.getParameter("parkName");// 获取停车场的名字
			String pAddress = request.getParameter("parkAddress");// 获取停车场的地址ַ
			// result = orderOperate.AdmindeleteOrders(pName, pAddress);
			if (result) {
				out.print("success");
			} else {
				out.print("fail");
			}
			break;
		case 3: // 修改订单
			// result = orderOperate.updateOrder(order);
			if (result) {
				out.print("success");
			} else {
				out.print("fail");
			}
			break;
		case 4: // 查询所需要处理的订单
			List<Order> list = new ArrayList<Order>();
			String pname = request.getParameter("parkname");// 获取管理员的停车场Name
			String paddress = request.getParameter("parkaddress");// 获取管理员停车场的Address
			 //list = orderOperate.AdminfindOrder(pname, paddress);
			out.print(list.toString());
			break;
		case 5: // 查询所有的订单
			List<Order> l = new ArrayList<Order>();
			String parkname = request.getParameter("parkname");// 获取管理员的停车场Name
			String parkaddress = request.getParameter("parkaddress");// 获取管理员停车场的Address
			//list = orderOperate.AdminfindOrders(parkname, parkaddress);
			out.print(l.toString());
			break;
		default:
			System.out.println("操作不存在");
			break;
		}
		out.flush();
		out.close();
	}
}
