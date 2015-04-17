package ustc.sse.water.lbs.server.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ustc.sse.water.lbs.server.orm.AdminOperate;

/**
 * 
 * Servlet类. <br>
 * 修改订单状态为2:已完成
 * 
 * <p>
 * Copyright: Copyright (c) 2015-3-26 下午9:44:39
 * <p>
 * Company: 中国科学技术大学软件学院
 * <p>
 * 
 * @author 周晶鑫
 * @version 1.0.0
 */
public class OrderStatusToTwoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String result = "fail";
		
		// 获取订单id
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		
		AdminOperate aOperate = new AdminOperate(); 
		if(aOperate.updateStatusTwo(orderId)) {
			result = "success";
		}
		
		out.print(result);
		out.flush();
		out.close();
	}

}
