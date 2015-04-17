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

import ustc.sse.water.lbs.server.model.Driver;
import ustc.sse.water.lbs.server.orm.DriverOperate;

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
		// 获取参数
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
