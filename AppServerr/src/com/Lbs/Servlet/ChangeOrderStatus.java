package com.Lbs.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.Lbs.orm.OrderOperate;

public class ChangeOrderStatus extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * 管理员成功获取新订单后，更改订单状态
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		OrderOperate op = new OrderOperate();
		ObjectMapper objectMapper = new ObjectMapper();
		String result = "success";
		
		// 取出Jackson封装的信息
		String ids = request.getParameter("changeIds");
		List<String> idList = objectMapper.readValue(ids, List.class);
		System.out.println(idList.toString());
		
		boolean flag = op.updateOrderStatusToOne(idList);
		if(!flag) result = "fail"; 
		
		out.print(result);
		out.flush();
		out.close();
	}

}
