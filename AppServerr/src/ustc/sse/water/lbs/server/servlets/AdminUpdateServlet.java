package ustc.sse.water.lbs.server.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import ustc.sse.water.lbs.server.model.Admin;
import ustc.sse.water.lbs.server.orm.AdminOperate;


/**
 * 
 * Servlet类. <br>
 * 修改停车场管理员密码的Servlet
 * 
 * <p>
 * Copyright: Copyright (c) 2015-3-26 下午9:44:39
 * <p>
 * Company: 中国科学技术大学软件学院
 * <p>
 * 
 * @author 韩琼 周晶鑫改
 * @version 2.0.0
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
