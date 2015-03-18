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
import com.Lbs.model.OrderShowList;
import com.Lbs.orm.AdminOperate;
import com.Lbs.orm.OrderOperate;

/**
 * 管理员的订单操作
 * 1.获取正在进行的订单
 * 2.获取已经完成的订单
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
		String result = "fail";
		PrintWriter out = response.getWriter();
		
		// 获取订单操作
		int type = Integer.valueOf(request.getParameter("type"));
		int adminId = Integer.valueOf(request.getParameter("adminId"));
		System.out.println(adminId);
		
		AdminOperate adminOperate = new AdminOperate();
		OrderShowList osl = null;
		
		// 编号为1 查询正在进行的订单，编号2查询已经完成的订单
		switch (type) {
		case 1: // 查询正在进行的订单
			osl = adminOperate.getAdminOrders(adminId, 1);
			break;
		case 2: // 查询已经完成的订单
			osl = adminOperate.getAdminOrders(adminId, 2);
			break;
		}
		
		if(osl.getAdminShow().size() != 0) {
			ObjectMapper objectMapper = new ObjectMapper();
			result = objectMapper.writeValueAsString(osl);
		}else {
			result = "empty";
		}
		
		out.print(result);
		out.flush();
		out.close();
	}
}
