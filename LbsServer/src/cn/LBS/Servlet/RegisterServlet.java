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

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setUserpassword(request.getParameter("password"));

		UserDao dao = new UserDaoJdbcImpl();
		User existUser = dao.find(user);

		PrintWriter out = response.getWriter();
		String msg = "";
		if (existUser == null) {
			dao.add(user);
			msg = "success";
			out.println(msg);
		} else {
			msg = "fail";
			out.println(msg);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		doGet(request, response);
	}

}
