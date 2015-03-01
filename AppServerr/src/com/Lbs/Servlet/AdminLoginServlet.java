package com.Lbs.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Lbs.model.Admin;
import com.Lbs.orm.AdminOperate;

public class AdminLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		Admin user = new Admin();
		user.setAdminName(request.getParameter("username"));
		user.setAdminPassword(request.getParameter("password"));
		System.out.println("管理员名字：" + request.getParameter("username"));
		System.out.println("管理员密码：" + request.getParameter("password"));
		Admin existUser = (new AdminOperate()).findAdmin(user);

		PrintWriter out = response.getWriter();
		String msg = "";
		if (existUser == null) {
			// 没有查到 --- 登陆失败
			msg = "fail";
			out.write(msg);
			System.out.println("登陆失败");
		} else {
			// 查找 --- 登陆成功
			msg = "success";
			out.write(msg);
			// out.print(msg);
			System.out.println("登陆成功");
		}
		out.flush();
		out.close();
	}

}
