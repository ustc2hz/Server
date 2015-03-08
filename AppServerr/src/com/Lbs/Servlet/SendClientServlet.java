package com.Lbs.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.Lbs.model.AdminOrderShow;
import com.Lbs.model.Order;
import com.Lbs.model.OrderShowList;
import com.Lbs.orm.OrderOperate;
/**
 * 将新订单发送给管理员APP
 */
public class SendClientServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 管理员APP每2s轮询访问，查看是否有新订单
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		// 获取管理员id
		String adminId = request.getParameter("adminId");
		ObjectMapper objectMapper = new ObjectMapper();
		String jacksonString = "old";
		System.out.println("访问");
		
		OrderOperate op = new OrderOperate();
		OrderShowList os = op.getAdminOrderShow(Integer.parseInt(adminId));
		if(os.getAdminShow().size() != 0) {
			// 将OrderShowList对象生成Jackson字符串
			jacksonString = objectMapper.writeValueAsString(os);
		} else {
			jacksonString = "old";
		}
		
		out.print(jacksonString);
		out.flush();
		out.close();
	}

}
