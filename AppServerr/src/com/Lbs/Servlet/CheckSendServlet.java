package com.Lbs.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Lbs.orm.OrderOperate;
/**
 * 驾驶员APP检查管理员APP是否接收到订单
 */
public class CheckSendServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 驾驶员APP3s后检查管理员是否接收到订单信息
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String orderUUID = request.getParameter("uuid");
		String result = "error";
		OrderOperate op = new OrderOperate();
		PrintWriter out = response.getWriter();
		
		if(op.checkOrderReceived(orderUUID)) {
			result = "success";
		} else {
			// 删除数据库中的记录
			op.deleteUnreceivedOrder(orderUUID);
		}
		out.print(result);
		out.flush();
		out.close();
	}

}
