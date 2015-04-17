package ustc.sse.water.lbs.server.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
 * 管理员登录的Servlet
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
public class AdminLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		// 获取请求中的参数
		Admin user = new Admin();
		user.setAdminName(new String(request.getParameter("username").getBytes("iso-8859-1"),"utf-8"));
		user.setAdminPassword(request.getParameter("password"));
		// 找到数据库中符合要求的管理员
		Admin existUser = new AdminOperate().findAdmin(user);
		
		String msg = "fail";
		if (existUser != null) { // 存在符合要求的管理员
			List<String> list = new ArrayList<String>();
			list.add(String.valueOf(existUser.getAdminId()));
			list.add(existUser.getAdminName());
			list.add(existUser.getParkPhone());
			ObjectMapper objectMapper = new ObjectMapper();
			msg = objectMapper.writeValueAsString(list);
		} 
		
		out.print(msg);
		out.flush();
		out.close();
	}

}
