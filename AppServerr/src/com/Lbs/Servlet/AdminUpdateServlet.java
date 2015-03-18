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
 * 修改停车场管理员密码的Servlet
 */
public class AdminUpdateServlet extends HttpServlet {

	private static final int UPDATE_SUCCESS = 1; // 修改密码成功
	private static final int UPDATE_OLD_ERROE = 2; // 旧密码不匹配
	private static final int UPDATE_ERROR = 3; // 修改密码出错

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

		String result = "fail"; // 默认失败

		// 获取参数
		int adminId = Integer.parseInt(request.getParameter("adminId"));
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		System.out.println(adminId);
		System.out.println(oldPwd);
		System.out.println(newPwd);

		// 操作数据库
		AdminOperate adminOperate = new AdminOperate();
		switch (adminOperate.updateAdminPwd(adminId, oldPwd, newPwd)) {
		case UPDATE_SUCCESS: // 修改成功
			result = "success";
			break;
		case UPDATE_OLD_ERROE: // 旧密码错误
			result = "oldError";
			break;
		case UPDATE_ERROR: // 修改失败
			result = "fail";
			break;
		}

		out.print(result);
		out.flush();
		out.close();
	}

}
