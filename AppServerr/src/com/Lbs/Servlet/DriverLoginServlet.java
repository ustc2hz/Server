package com.Lbs.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Lbs.model.Driver;
import com.Lbs.orm.DriverOperate;

public class DriverLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		Driver driver = new Driver();
		driver.setDriverName(new String(request.getParameter("username").getBytes("iso-8859-1"),"utf-8"));
		driver.setDriverPassword(request.getParameter("password"));

		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("password"));

		Driver existDriver = (new DriverOperate()).findDriver(driver);

		PrintWriter out = response.getWriter();
		String msg = "";
		if (existDriver == null) {
			// 没有查到 --- 登陆失败
			msg = "fail";
			out.print(msg);
			System.out.println("登陆失败");
		} else {
			// 查找 --- 登陆成功
			msg = "success";
			out.print(msg);
			System.out.println("登陆成功");
		}
		out.flush();
		out.close();
	}

}
