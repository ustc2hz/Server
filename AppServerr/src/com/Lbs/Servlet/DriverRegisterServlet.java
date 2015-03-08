package com.Lbs.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Lbs.model.Driver;
import com.Lbs.orm.DriverOperate;

public class DriverRegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		Driver driver = new Driver();
		driver.setDriverName(new String(request.getParameter("username").getBytes("iso-8859-1"),"utf-8"));
		driver.setDriverPassword(request.getParameter("password"));
		boolean flag = false;
		// 注册时,根据Driver的name进行查找即可
		DriverOperate driverOperate = new DriverOperate();

		PrintWriter out = response.getWriter();
		String msg = "";

		// 用户不存在
		flag = driverOperate.insertDriver(driver);
		if (flag) {// 增加成功
			msg = "success";
			out.print(msg);
			System.out.println("增加成功");
		} else {
			msg = "fail";
			out.print(msg);
			System.out.println("增加失败");
		}
	}

}
