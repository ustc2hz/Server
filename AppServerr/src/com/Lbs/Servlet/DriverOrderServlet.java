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
 * 驾驶员对订单的操作
 */
public class DriverOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// String result = "error";
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
		// 编号为1 增加订单 编号为2 删除单个订单 编号为3 修改订单 编号为4 查询所有订单
		switch (id) {
		case 1: // 增加订单(驾驶员端增加订单)
			result = orderOperate.insertOrder(order);
			if (result) {
				out.print("success");
			} else {
				out.print("fail");
			}
			break;
		case 2:// 删除单个订单
			//result = orderOperate.deleteOrder(order);
			if (result) {
				out.print("success");
			} else {
				out.print("fail");
			}
			break;
		case 3:// 删除所有订单
			String drivername = request.getParameter("DriverName");// 获取驾驶员的name
			//result = orderOperate.DriverdeleteOrders(drivername);
			if (result) {
				out.print("success");
			} else {
				out.print("fail");
			}
			break;
		case 4: // 修改订单
			//result = orderOperate.updateOrder(order);
			if (result) {
				out.print("success");
			} else {
				out.print("fail");
			}
			break;
		case 5:// 查询所有订单
			List<Order> list = new ArrayList<Order>();
			String dname = request.getParameter("dName");// 获取驾驶员的name
			//list = orderOperate.DriverfindOrder(dname);
			out.println(list.toString());
			break;
		default:
			System.out.println("操作不存在");
			break;
		}
		out.flush();
		out.close();
		// if (id == 1) {
		//
		// if (result) {
		// out.print("success");
		// } else {
		// out.print("error");
		// }
		// } else if (id == 2) {
		//
		// if (result) {
		// out.print("success");
		// } else {
		// out.print("error");
		// }
		// } else if (id == 3) {
		// result = orderOperate.DriverupdateOrder(order);
		// if (result) {
		// out.print("success");
		// } else {
		// out.print("error");
		// }
		// } else if (id == 4) {
		//
		// out.println(list.toString());
		// }

	}
}
