package com.Lbs.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.Lbs.model.Driver;
import com.Lbs.orm.DriverOperate;

public class DriverUpdateServlet extends HttpServlet {

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
		String message = new String(request.getParameter("driver").getBytes(
				"iso-8859-1"), "utf-8");
		System.out.println(message);
		// 获取信息
		ObjectMapper objectMapper = new ObjectMapper();
		Driver driver = objectMapper.readValue(message, Driver.class);// 获取driver类
		DriverOperate driverOperate = new DriverOperate();

		PrintWriter out = response.getWriter();
		flag = driverOperate.updateDriver(driver);
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
