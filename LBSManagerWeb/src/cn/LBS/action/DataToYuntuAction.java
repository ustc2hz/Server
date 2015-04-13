package cn.LBS.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.LBS.orm.adminOperate;
import cn.LBS.orm.parkOperate;
import cn.LBS.test.DataToYuntu;

import com.opensymphony.xwork2.ActionSupport;

/*
 * 对停车场信息发送给云图的请求进行处理
 */
@SuppressWarnings("serial")
public class DataToYuntuAction extends ActionSupport {
	private ServletContext application = ServletActionContext
			.getServletContext();// 获取登陆用户的用户名
	private HttpServletRequest request = ServletActionContext.getRequest();// 用户请求
	private HttpServletResponse response = ServletActionContext.getResponse();// 响应请求
	private int managerId = 0;// 管理员的ID

	/*
	 * 接收请求后，默认执行此方法。将停车场信息发送给云图(non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws ServletException, IOException {
		DataToYuntu dty = null;
		try {
			dty = new DataToYuntu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "sendfail";
		}
		Integer adminid = Integer.valueOf(request.getAttribute("adminID")
				.toString());
		String data = new String();
		String name = new String();
		String newdata = new String();
		if (request.getParameter("data") != null
				&& request.getParameter("name") != null) {
			data = new String(request.getParameter("data").getBytes(
					"iso-8859-1"), "utf-8");
			name = new String(request.getParameter("name").getBytes(
					"iso-8859-1"), "utf-8");
			System.out.println("data--->>>>>" + data);
			System.out.println("managerName:---->" + name);
			newdata = AppnewData(data, name);
			System.out.println("--->>>>>newdata" + newdata);
		} else if (request.getAttribute("data") != null) {
			data = new String(request.getAttribute("data").toString());
			name = new String(request.getAttribute("name").toString());
			System.out.println("name----->" + name);
			newdata = WebnewData(data, name);
		}
		if (newdata != null) {
			System.out.println(newdata);
			try {
				int a = dty.create(newdata);
				Integer id = dty.id;// 获取云图的id。
				boolean flag = new parkOperate().insertparkId(adminid, id); // 将云图中停车场的ID插入到表格中
				if (a == 1 && flag) {
					System.out.println("a is :" + a);
					System.out.println("发送云图获取的:" + dty.id);
					System.out.println("发送云图成功");
					return "sendsuccess";
				} else {
					System.out.println("a is :" + a);
					System.out.println("发送云图失败");
					this.addFieldError("sendYuntu", "发送云图数据失败，无需修改信息，重新提交即可");
					return "sendfail";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "sendfail";
			}
		}
		return "sendfail";
	}

	/*
	 * 对App端停车场信息进行封装，转变成云图所能接收的字符串
	 */
	public String AppnewData(String data, String name) {
		managerId = new adminOperate().findAdminIdByAdName(name); // 找到管理员id
		System.out.println("--->>>>>managerId" + String.valueOf(managerId));
		StringBuilder sb = new StringBuilder();
		String tempData = data.substring(0, data.length() - 1);
		sb.append(tempData);
		sb.append(",\"managerId\":\"");
		sb.append(String.valueOf(managerId));
		sb.append("\",\"parkType\",\"app\"}");
		return sb.toString();
	}

	/*
	 * 对Web端停车场信息进行封装，转变成云图所能接收的字符串
	 */
	public String WebnewData(String data, String name) {
		managerId = new adminOperate().findAdminIdByAdName(name); // 找到管理员id
		System.out.println("name----->" + managerId);
		System.out.println("--->>>>>managerId" + String.valueOf(managerId));
		StringBuilder sb = new StringBuilder();
		String tempData = data.substring(0, data.length() - 1);
		sb.append(tempData);
		sb.append(",\"managerId\":\"");
		sb.append(String.valueOf(managerId));
		sb.append("\",\"parkType\":\"web\"}");
		System.out.println("name----->" + sb.toString());
		return sb.toString();
	}
}