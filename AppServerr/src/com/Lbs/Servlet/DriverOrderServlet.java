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
import com.Lbs.orm.DriverOperate;
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
		
		String result = "fail";
		// 获取订单操作模式和驾驶员id
		int handleType = Integer.valueOf(request.getParameter("type"));
		int driverId = Integer.valueOf(request.getParameter("driverId"));
		System.out.println("-->>id"+driverId);
		
		PrintWriter out = response.getWriter();
		DriverOperate driverOperate = new DriverOperate();
		
		switch (handleType) {
		case 1: // 查询所有订单
			OrderShowList osl = driverOperate.getDriverOrders(driverId);
			ObjectMapper objectMapper = new ObjectMapper();
			if(osl.getDriverShow().size() != 0) {
				result = objectMapper.writeValueAsString(osl);
			}
			break;
		case 2:// 删除单个订单
			break;
		case 3:// 删除所有订单
			break;
		}
		
		out.print(result);
		out.flush();
		out.close();

	}
}
