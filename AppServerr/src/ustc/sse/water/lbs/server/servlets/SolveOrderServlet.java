package ustc.sse.water.lbs.server.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import ustc.sse.water.lbs.server.model.Order;
import ustc.sse.water.lbs.server.orm.OrderOperate;

/**
 * 
 * Activity类. <br>
 * 处理APP发送的订单
 * <p>
 * Copyright: Copyright (c) 2015-3-26 下午9:44:39
 * <p>
 * Company: 中国科学技术大学软件学院
 * <p>
 * 
 * @author 周晶鑫
 * @version 1.0.0
 */
public class SolveOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 接收APP发送的车位订单
	 * 解析订单信息，将信息存入数据库中
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String result = "error"; // 默认为“error”
		String message = new String(request.getParameter("order").getBytes("iso-8859-1"),"utf-8");
		// 使用Jackson解析Order
		ObjectMapper objectMapper = new ObjectMapper();
		Order order = objectMapper.readValue(message,Order.class);
		
		OrderOperate orderKeep = new OrderOperate();
		if(orderKeep.insertOrder(order)) {
			result = "success";
		}
		
		PrintWriter out = response.getWriter();
		out.print(result); // 返回给APP
		out.flush();
		out.close();
	}

}
