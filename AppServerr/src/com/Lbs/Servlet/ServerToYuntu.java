package com.Lbs.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.Lbs.test.DataToYuntu;
import com.Lbs.test.ObjData;

public class ServerToYuntu extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ServerToYuntu() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		com.Lbs.test.DataToYuntu dty = null;
		try {
			dty = new DataToYuntu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		com.Lbs.test.ObjData obd;
		ObjectMapper objectMapper = new ObjectMapper();

		String data = new String(request.getParameter("data").getBytes(
				"iso-8859-1"), "utf-8");
		obd = objectMapper.readValue(data, ObjData.class);// 获取停车场的信息
		String adminname = request.getParameter("adminname");
		System.out.println(data);
		if (data != null) {
			// objectMapper = new ObjectMapper();
			// obd = objectMapper.readValue(data, ObjData.class);
			// System.out.println(obd.toString());
			dty.tt = data;
			try {
				int a = dty.create();
				if (a == 1) {
					System.out.println("a is :" + a);
					PrintWriter out = response.getWriter();
					out.write("1");
					out.flush();
					out.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
