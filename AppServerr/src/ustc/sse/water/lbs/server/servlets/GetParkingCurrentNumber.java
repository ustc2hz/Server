package ustc.sse.water.lbs.server.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.dialect.Ingres10Dialect;

import ustc.sse.water.lbs.server.model.Admin;
import ustc.sse.water.lbs.server.orm.AdminOperate;

/**
 * 
 * Servlet类. <br>
 * 获取停车场剩余车位数
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
public class GetParkingCurrentNumber extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String managerId = request.getParameter("managerId");
		AdminOperate adminOperate = new AdminOperate();
		int number = adminOperate.getCurrentNumber(Integer.parseInt(managerId));
		
		out.print(String.valueOf(number));
		out.flush();
		out.close();
	}

}
