package com.Lbs.Servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.Lbs.model.ParkDetailObject;
import com.Lbs.model.ParkingBook;
import com.Lbs.orm.AdminOperate;
import com.Lbs.test.DataToYuntu;

public class ServerToYuntu extends HttpServlet {

	int managerId = 0;
	
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
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DataToYuntu dty = null;
		try {
			dty=new DataToYuntu();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String data=new String();
		String name=new String();
		String newdata=new String();
		
		
		if(request.getParameter("data")!=null&&request.getParameter("name")!=null)
		{
		 data = new String(request.getParameter("data").getBytes("iso-8859-1"),"utf-8");
		 name = new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8");
		
		 newdata=AppnewData(data,name);
		}
		else if(request.getAttribute("data")!=null&&request.getAttribute("name")!=null)
		{
			data=new String(request.getAttribute("data").toString());
			name=new String(request.getAttribute("name").toString());
			newdata=WebnewData(data,name);
			
		}
		
		if(newdata!=null)
		{		
			System.out.println(newdata);
			try {
				int a = dty.create(newdata);
				if(a==1)
				{
					System.out.println("a is :"+a);
					PrintWriter out = response.getWriter();
					out.write(dty.id+"");
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
				e.printStackTrace();
			}
		}
		
		
	}

	public String AppnewData(String data,String name)
	{
		ObjectMapper objectMapper = new ObjectMapper();
		AdminOperate adminOperate = new AdminOperate();
		ParkDetailObject parkData = null;
		try {
			parkData = objectMapper.readValue(data, ParkDetailObject.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		managerId = adminOperate.findAdminIdByName(name, parkData.getPhone()); // 找到管理员id
		ParkingBook pb = new ParkingBook();
		pb.setManagerId(managerId);
		pb.setParkNum(Integer.parseInt(parkData.getParkSum()));
		adminOperate.saveParkNumber(pb); // 将车位数量存入数据库
		
		 StringBuilder sb = new StringBuilder();
		 String tempData=data.substring(0, data.length()-1);
		 sb.append(tempData);
		 sb.append(",\"managerId\":\"");
		 sb.append(String.valueOf(managerId));
		 sb.append("\",\"parkType\":\"app\"}");
		return sb.toString();
	}
	public String WebnewData(String data,String name)
	{
		ObjectMapper objectMapper = new ObjectMapper();
		ParkDetailObject parkData = null;
		try {
			parkData = objectMapper.readValue(data, ParkDetailObject.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		managerId = new AdminOperate().findAdminIdByName(name,parkData.getPhone()); // 找到管理员id
		 StringBuilder sb = new StringBuilder();
		 String tempData=data.substring(0, data.length()-1);
		 sb.append(tempData);
		 sb.append(",\"managerId\":\"");
		 sb.append(String.valueOf(managerId));
		 sb.append("\",\"parkType\":\"web\"}");
		return sb.toString();
	}
	
	
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
	}

}
