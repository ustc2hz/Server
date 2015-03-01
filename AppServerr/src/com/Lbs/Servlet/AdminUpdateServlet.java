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

public class AdminUpdateServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		boolean flag = false;
		String result = "";
		String message = new String(request.getParameter("admin").getBytes(
				"iso-8859-1"), "utf-8");
		System.out.println(message);
		// 获取信息
		ObjectMapper objectMapper = new ObjectMapper();
		Admin admin = objectMapper.readValue(message, Admin.class);// 获取admin类
		AdminOperate adminOperate = new AdminOperate();

		PrintWriter out = response.getWriter();
		flag = adminOperate.updateAdmin(admin);
		if (flag) {// 修改成功
			result = "success";
			out.print(result); // 返回给APP
		} else {
			result = "fail";
			out.print(result); // 返回给APP
		}
		out.flush();
		out.close();
	}

}
