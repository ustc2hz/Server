package cn.LBS.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import cn.LBS.model.Admin;
import cn.LBS.model.Park;
import cn.LBS.model.ParkInfo;
import cn.LBS.model.YuntuData;
import cn.LBS.orm.adminOperate;
import cn.LBS.orm.parkOperate;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/*
 * 停车场信息相关的请求进行处理
 */
@SuppressWarnings("serial")
public class ParkInfoAction extends ActionSupport implements
		ModelDriven<ParkInfo> {
	public static ObjectMapper objectMapper = new ObjectMapper();// 对停车场信息的封装类
	private ServletContext application = ServletActionContext
			.getServletContext();// 获取登录用户的用户名
	private HttpServletRequest request = ServletActionContext.getRequest();// 用户请求
	private parkOperate parkoperate = new parkOperate();// 停车场信息的处理类
	private ParkInfo parkinfo = new ParkInfo();// 停车场信息类
	private Admin admin = new Admin();// 停车场管理员类
	private Park park = new Park();// 停车场类

	/*
	 * 停车场信息的修改
	 */
	public String update() throws JsonGenerationException,
			JsonMappingException, IOException {
		String flagg = request.getParameter("flag");
		System.out.println("创建或是修改：" + flagg);
		boolean flag = false;
		park.setParkPublic(request.getParameter("parkOrder"));// 获取表单信息
		park.setParkRemain(request.getParameter("parkRemain"));
		admin = (Admin) application.getAttribute("existAdmin");// 获取停车场管理员
		YuntuData ydata = new YuntuData();// 封装YuntuData
		ydata.set_location(parkinfo.getParkAddress());
		ydata.set_name(parkinfo.getParkName());
		ydata.setOrderTen(parkinfo.getOrderTen());
		ydata.setOrderTri(parkinfo.getOrderTri());
		ydata.setOrderTwe(parkinfo.getOrderTwe());
		ydata.setParkSum(parkinfo.getParkSum());
		ydata.setPayHalPay(parkinfo.getPayHalPay());
		ydata.setPayMorePay(parkinfo.getPayMorePay());
		ydata.setPayOneHour(parkinfo.getPayOneHour());
		// 1. 修改停车场的信息，根据停车场管理员端的name。返回布尔值。 2. 发送给云图
		Integer adminid = new adminOperate().findAdminIdByAdName(admin
				.getAdminName());
		flag = parkoperate.update(parkinfo, park, admin.getAdminName());
		if (flagg.equals("1"))// 表示修改
		{
			Integer id = parkoperate.findparkId(adminid);// 需要获取云图的id哈
			System.out.println("修改云图的Id：" + id);
			if (flag) {
				System.out.println("停车场数据库信息修改成功");
				String data = objectMapper.writeValueAsString(ydata);// data中放置_id;
				data = data.substring(0, data.length() - 1);
				request.setCharacterEncoding("utf-8");
				data = data + "," + "\"" + "_id" + "\"" + ":" + "\"" + id
						+ "\"" + "}";
				System.out.println("parkinfoAction的data:" + data);
				request.setAttribute("data", data);
				return "updatesuccess";
			} else {
				System.out.println("停车场数据库信息修改失败");
				this.addFieldError("updatemsg", "数据库信息修改失败,需重新提交");
				return "updatefail";
			}
		} else {// 表示创建
			if (flag) {
				System.out.println("停车场的数据库信息创建成功");
				String data = objectMapper.writeValueAsString(ydata);
				System.out.println("parkinfoAction的data:" + data);
				request.setCharacterEncoding("utf-8");
				request.setAttribute("data", data);
				request.setAttribute("name", admin.getAdminName());
				System.out.println("adminId：" + adminid);
				request.setAttribute("adminID", adminid);// 需要发送id使得得到的parkId存到对应位置
				return "createsuccess";
			} else {
				System.out.println("停车场的数据库信息创建失败");
				this.addFieldError("deletemsg", "停车场的信息创建失败,需重再次提交");
				return "createfail";
			}
		}
	}

	@Override
	public ParkInfo getModel() {
		// TODO Auto-generated method stub
		return parkinfo;
	}
}