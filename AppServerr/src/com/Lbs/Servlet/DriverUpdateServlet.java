package com.Lbs.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.Lbs.model.Driver;
import com.Lbs.orm.DriverOperate;
/**
 * 修改驾驶员信息的Servlet
 */
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
		PrintWriter out = response.getWriter();
		
		DriverOperate dOperate = new DriverOperate();
		String result = "fail";
		
		// 获取信息
		String messages = new String(request.getParameter("messages").getBytes(
				"iso-8859-1"), "utf-8");
		ObjectMapper objectMapper = new ObjectMapper();
		List<String> msgList = objectMapper.readValue(messages, List.class);
		boolean flag = dOperate.updateDriverInfo(msgList);
		if (flag) {// 修改成功
			result = "success";
		} else if("2".equals(msgList.get(0))){
			result = "oldError";
		}
		
		out.print(result); // 返回给APP
		out.flush();
		out.close();
	}

}
