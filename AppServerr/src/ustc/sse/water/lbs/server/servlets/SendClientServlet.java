package ustc.sse.water.lbs.server.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import ustc.sse.water.lbs.server.model.AdminOrderShow;
import ustc.sse.water.lbs.server.model.Order;
import ustc.sse.water.lbs.server.model.OrderShowList;
import ustc.sse.water.lbs.server.orm.OrderOperate;


/**
 * 
 * Activity类. <br>
 * 将新订单发送给管理员APP.
 * <p>
 * Copyright: Copyright (c) 2015-3-26 下午9:44:39
 * <p>
 * Company: 中国科学技术大学软件学院
 * <p>
 * 
 * @author 周晶鑫
 * @version 1.0.0
 */
public class SendClientServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 管理员APP每2s轮询访问，查看是否有新订单
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		// 获取管理员id
		String adminId = request.getParameter("adminId");
		
		String jacksonString = "old";
		
		OrderOperate op = new OrderOperate();
		OrderShowList os = op.getAdminOrderShow(Integer.parseInt(adminId));
		if(os.getAdminShow().size() != 0) {
			ObjectMapper objectMapper = new ObjectMapper();
			// 将OrderShowList对象生成Jackson字符串
			jacksonString = objectMapper.writeValueAsString(os);
		} else {
			jacksonString = "old";
		}
		
		out.print(jacksonString);
		out.flush();
		out.close();
	}

}
