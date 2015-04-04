package com.Lbs.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		Admin user = new Admin();
		user.setAdminName(new String(request.getParameter("username").getBytes("iso-8859-1"),"utf-8"));
		user.setAdminPassword(request.getParameter("password"));
		
		Admin existUser = new AdminOperate().findAdmin(user);
		
		String msg = "fail";
		if (existUser != null) {
			List<String> list = new ArrayList<String>();
			list.add(String.valueOf(existUser.getAdminId()));
			list.add(existUser.getAdminName());
			list.add(existUser.getParkPhone());
			ObjectMapper objectMapper = new ObjectMapper();
			msg = objectMapper.writeValueAsString(list);
		} 
		
		out.print(msg);
		out.flush();
		out.close();
	}

}
