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

import com.Lbs.model.Driver;
import com.Lbs.orm.DriverOperate;
/**
 * 管理员登录的Servlet
 */
public class DriverLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		Driver driver = new Driver();
		driver.setDriverName(new String(request.getParameter("username").getBytes("iso-8859-1"),"utf-8"));
		driver.setDriverPassword(request.getParameter("password"));
		
		// 查找数据库
		Driver existDriver = new DriverOperate().findDriver(driver);
		ObjectMapper objectMapper = new ObjectMapper();
		String msg = "fail";
		if (existDriver != null) {
			List<String> list = new ArrayList<String>();
			list.add(String.valueOf(existDriver.getDriverId()));
			list.add(existDriver.getDriverName());
			list.add(existDriver.getDriverPhone());
			msg = objectMapper.writeValueAsString(list);
		}
		
		out.print(msg);
		out.flush();
		out.close();
	}

}
