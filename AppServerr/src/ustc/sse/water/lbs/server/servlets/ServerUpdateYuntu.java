package ustc.sse.water.lbs.server.servlets;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ustc.sse.water.lbs.server.cloud.DataToYuntu;

/**
 * 
 * Servlet类. <br>
 * Servlet implementation class ServerUpdateYuntu
 * 
 * <p>
 * Copyright: Copyright (c) 2015-3-26 下午9:44:39
 * <p>
 * Company: 中国科学技术大学软件学院
 * <p>
 * 
 * @author 黄志恒
 * @version 2.0.0
 */
public class ServerUpdateYuntu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServerUpdateYuntu() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DataToYuntu dty = null;
		try {
			dty = new DataToYuntu();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String data = new String(request.getParameter("data").getBytes(
				"iso-8859-1"), "utf-8");
		if (data != null) {
			System.out.println(data);
			try {
				int a = dty.update(data);
				if (a == 1) {
					PrintWriter out = response.getWriter();
					out.write("1");
					out.flush();
					out.close();
				} else {
					PrintWriter out = response.getWriter();
					out.write("0");
					out.flush();
					out.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
