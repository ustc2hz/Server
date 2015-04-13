package cn.LBS.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.LBS.model.Admin;
import cn.LBS.model.OrderList;
import cn.LBS.orm.orderOperate;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/*
 * 接收订单相关的请求，并进行处理
 */
@SuppressWarnings("serial")
public class OrderAction extends ActionSupport implements
		ModelDriven<OrderList> {
	private ServletContext application = ServletActionContext
			.getServletContext();// 获取登陆用户的用户名
	private HttpServletRequest request = ServletActionContext.getRequest();// 用户请求
	private orderOperate orderOperate = new orderOperate();// 响应用户请求
	private OrderList order;// 订单类
	private Admin admin;// 停车场管理员类
	// 订单统计时需要获取表单中的数据，需要如下信息
	private String OrderNum;// 订单总个数
	private String OrderPrice;// 订单总收益
	private String OrderYMNum;// 某年某月的订单个数
	private String OrderYMPrice;// 某年某月的订单总收益

	/*
	 * 显示所有的历史订单，根据管理员的ID和订单的状态
	 */
	public String showAll() {
		admin = (Admin) application.getAttribute("existAdmin");
		List list = new ArrayList();// 查找订单
		list = orderOperate.findOrderAllByAdmin(admin);// 判断结果
		if (list == null || list.size() == 0) { // 没有查到订单
			System.out.println("订单信息为空");
			return "OrderAllNo";
		} else {
			request.setAttribute("list", list);
			System.out.println("订单信息不为空");
			return "OrderAllYes";
		}
	}

	/*
	 * 完成订单状态的修改 ，状态从1-2。
	 */
	public String updateFinish() {
		String idsString = request.getParameter("id");
		String[] resultId = idsString.split("/,");
		if (orderOperate.updateOrderStatus(resultId)) {
			System.out.println("订单状态修改成功");
			application.setAttribute("handleOrder", "true");
		} else {
			System.out.println("订单状态修改失败");
		}
		return "updfail";// 返回updfail，由配置中信息页面负责跳转
	}

	/*
	 * 完成订单的删除
	 */
	public String delete() {
		String idsString = request.getParameter("id");
		String[] resultId = idsString.split("/,");
		if (orderOperate.deleteOrder(resultId)) {
			System.out.println("删除订单成功");
			application.setAttribute("deleteOrder", true);
			return "delfail";
		} else {
			System.out.println("删除订单失败");
			return "delfail";
		}
	}

	/*
	 * 完成订单的查询
	 */
	public String Count() {
		admin = (Admin) application.getAttribute("existAdmin");
		Integer orderNum = orderOperate.CountOrderNum(admin.getAdminName());
		Integer orderPrice = orderOperate.CountOrderPrice(admin.getAdminName());
		String year = request.getParameter("Year");
		String month = request.getParameter("Month");
		Integer[] num = orderOperate.CountOrderNumOfYM(admin.getAdminName(),
				year, month);
		if (orderNum == null) {
			orderNum = 0;
			orderPrice = 0;
		}
		if (num == null) {
			num[0] = 0;
			num[1] = 0;
		}
		setOrderNum(orderNum.toString());
		setOrderPrice(orderPrice.toString());
		setOrderYMNum(num[0].toString());
		setOrderYMPrice(num[1].toString());
		return "countsuccess";
	}

	@Override
	public OrderList getModel() {
		// TODO Auto-generated method stub
		return order;
	}

	public String getOrderNum() {
		return OrderNum;
	}

	public void setOrderNum(String orderNum) {
		OrderNum = orderNum;
	}

	public String getOrderPrice() {
		return OrderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		OrderPrice = orderPrice;
	}

	public String getOrderYMNum() {
		return OrderYMNum;
	}

	public void setOrderYMNum(String orderYMNum) {
		OrderYMNum = orderYMNum;
	}

	public String getOrderYMPrice() {
		return OrderYMPrice;
	}

	public void setOrderYMPrice(String orderYMPrice) {
		OrderYMPrice = orderYMPrice;
	}
}