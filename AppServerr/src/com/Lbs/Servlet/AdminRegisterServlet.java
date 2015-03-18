package com.Lbs.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Lbs.model.Admin;
import com.Lbs.orm.AdminOperate;
/**
 * 管理员注册Servlet
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
		
		System.out.println(user.getAdminName());
		System.out.println(user.getAdminPassword());
		
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
