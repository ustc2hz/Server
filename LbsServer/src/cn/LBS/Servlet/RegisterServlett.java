package cn.LBS.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.LBS.Dao.UserDao;
import cn.LBS.Dao.Impl.UserDaoJdbcImpl;
import cn.LBS.Model.User;

public class RegisterServlett extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setUserpassword(request.getParameter("password"));
		System.out.println(user.getUsername());
		System.out.println(user.getUserpassword());

		UserDao dao = new UserDaoJdbcImpl();
		User existUser = dao.find(user);

		PrintWriter out = response.getWriter();
		String msg = "";
		if (existUser == null) {
			// 用户不存在
			dao.add(user);
			msg = "success";
			out.println(msg);
			out.println("成功");
		} else {
			// 用户存在
			msg = "fail";
			out.println(msg);
			out.println("失败");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		doGet(request, response);
	}

}
