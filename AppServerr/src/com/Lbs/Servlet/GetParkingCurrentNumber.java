package com.Lbs.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.dialect.Ingres10Dialect;

import com.Lbs.model.Admin;
import com.Lbs.orm.AdminOperate;
/**
 * 获取停车场剩余车位数
 */
public class GetParkingCurrentNumber extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String managerId = request.getParameter("managerId");
		AdminOperate adminOperate = new AdminOperate();
		int number = adminOperate.getCurrentNumber(Integer.parseInt(managerId));
		out.print(String.valueOf(number));
		out.flush();
		out.close();
	}

}
