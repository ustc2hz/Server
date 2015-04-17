package ustc.sse.water.lbs.server.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ustc.sse.water.lbs.server.orm.OrderOperate;

/**
 * 
 * Servlet类. <br>
 * 驾驶员APP检查管理员APP是否接收到订单
 * 
 * <p>
 * Copyright: Copyright (c) 2015-3-26 下午9:44:39
 * <p>
 * Company: 中国科学技术大学软件学院
 * <p>
 * 
 * @author 韩琼 周晶鑫改
 * @version 2.0.0
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
		// 获取参数
		String orderUUID = request.getParameter("uuid");
		int managerId = Integer.parseInt(request.getParameter("managerId"));
		int bookNumber = Integer.parseInt(request.getParameter("bookNum"));
		String result = "error";
		OrderOperate op = new OrderOperate();
		PrintWriter out = response.getWriter();
		
		if(op.checkOrderReceived(orderUUID,managerId,bookNumber)) {
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
