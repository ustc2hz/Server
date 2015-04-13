package cn.LBS.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.LBS.test.DataToYuntu;

import com.opensymphony.xwork2.ActionSupport;

/*
 * 对停车场信息的修改信息，在云图上进行的处理。
 */
@SuppressWarnings("serial")
public class UpdateYuntuAction extends ActionSupport {
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();

	@Override
	public String execute() throws Exception {
		DataToYuntu dty = null;
		try {
			dty = new DataToYuntu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "upsendfail";
		}
		String data = new String();
		if (request.getParameter("data") != null) {
			data = new String(request.getParameter("data").getBytes(
					"iso-8859-1"), "utf-8");
		} else if (request.getAttribute("data") != null) {
			data = new String(request.getAttribute("data").toString());
		}
		if (data != null) {
			System.out.println(data);
			try {
				int a = dty.update(data);
				if (a == 1) {
					System.out.println("a is :" + a);
					System.out.println("修改云图数据发送成功");
					return "upsendsuccess";
				} else {
					System.out.println("a is :" + a);
					System.out.println("修改云图数据发送云图失败");
					this.addFieldError("updateYuntu", "修改云图数据失败,无需修改数据，重新提交即可");
					return "upsendfail";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "upsendfail";
			}
		}
		return "upsendfail";
	}
}
