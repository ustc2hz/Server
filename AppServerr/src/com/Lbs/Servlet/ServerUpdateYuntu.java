package com.Lbs.Servlet;

import com.Lbs.test.DataToYuntu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServerUpdateYuntu
 */
public class ServerUpdateYuntu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServerUpdateYuntu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DataToYuntu dty = null;
		try {
			dty=new DataToYuntu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String data = new String(request.getParameter("data").getBytes("iso-8859-1"),"utf-8");		
		if(data!=null)
		{		
			System.out.println(data);
			try {
				int a = dty.update(data);
				if(a==1)
				{
					System.out.println("a is :"+a);
					PrintWriter out = response.getWriter();
					out.write("1");
					out.flush();
					out.close();
				}else
				{
					System.out.println("a is :"+a);
					PrintWriter out = response.getWriter();
					out.write("0");
					out.flush();
					out.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
