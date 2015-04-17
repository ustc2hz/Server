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

import ustc.sse.water.lbs.server.model.Order;
import ustc.sse.water.lbs.server.model.OrderShowList;
import ustc.sse.water.lbs.server.orm.DriverOperate;
import ustc.sse.water.lbs.server.orm.OrderOperate;


/**
 * 
 * Servlet类. <br>
 * 驾驶员对订单的操作
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
public class DriverOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String result = "fail";
		// 获取订单操作模式和驾驶员id
		int handleType = Integer.valueOf(request.getParameter("type"));
		int driverId = Integer.valueOf(request.getParameter("driverId"));
		
		PrintWriter out = response.getWriter();
		DriverOperate driverOperate = new DriverOperate();
		
		switch (handleType) {
		case 1: // 查询所有订单
			OrderShowList osl = driverOperate.getDriverOrders(driverId);
			ObjectMapper objectMapper = new ObjectMapper();
			if(osl.getDriverShow().size() != 0) {
				result = objectMapper.writeValueAsString(osl);
			}
			break;
		}
		
		out.print(result);
		out.flush();
		out.close();

	}
}
