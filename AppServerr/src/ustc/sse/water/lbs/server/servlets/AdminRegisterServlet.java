package ustc.sse.water.lbs.server.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ustc.sse.water.lbs.server.model.Admin;
import ustc.sse.water.lbs.server.orm.AdminOperate;

/**
 * 
 * Servlet类. <br>
 * 管理员注册Servlet
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
public class AdminRegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		Admin user = new Admin();
		user.setAdminName(new String(request.getParameter("username").getBytes("iso-8859-1"),"utf-8"));
		user.setAdminPassword(request.getParameter("password"));
		
		// 注册时,根据Admin的name进行查找即可
		AdminOperate adminOperate = new AdminOperate();

		PrintWriter out = response.getWriter();
		String msg = "fail";

		// 用户不存在,可以添加用户
		boolean flag = adminOperate.insertAdmin(user);
		if (flag) {// 增加成功
			msg = "success";
		} 
		
		out.print(msg);
		out.flush();
		out.close();
	}

}
