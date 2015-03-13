package com.Lbs.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.Lbs.model.Admin;
import com.Lbs.orm.AdminOperate;
/**
 * 管理员登录的Servlet
 */
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
		user.setAdminName(new String(request.getParameter("username").getBytes("iso-8859-1"),"utf-8"));
		user.setAdminPassword(request.getParameter("password"));
		System.out.println("管理员名字：" + user.getAdminName());
		System.out.println("管理员密码：" + user.getAdminPassword());
		Admin existUser = (new AdminOperate()).findAdmin(user);

		PrintWriter out = response.getWriter();
		String msg = "fail";
		if (existUser != null) {
			// 查找 --- 登陆成功
			ObjectMapper objectMapper = new ObjectMapper();
			msg = objectMapper.writeValueAsString(existUser);
		} 
		
		out.print(msg);
		out.flush();
		out.close();
	}

}
