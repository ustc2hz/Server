package cn.LBS.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.LBS.model.Admin;
import cn.LBS.orm.adminOperate;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/*
 * 对Admin相关信息的请求进行处理。
 */
@SuppressWarnings("serial")
public class AdminAction extends ActionSupport implements ModelDriven<Admin> {
	private ServletContext application = ServletActionContext
			.getServletContext();// application存放登陆用户的用户名
	private HttpServletResponse response = ServletActionContext.getResponse();// 用户的响应
	private HttpServletRequest request = ServletActionContext.getRequest();// 用户的请求
	private adminOperate adminOperate = new adminOperate();// Admin的操作方法
	private Admin admin = new Admin();// Admin类
	private String message;// 请求处理结果信息

	/*
	 * 用户的登陆，接受用户的登陆请求
	 */
	public String login() throws Exception {
		System.out.println("用户名：" + admin.getAdminName());
		System.out.println("密码：：" + admin.getAdminPassword());

		if (adminOperate.findAdmin(admin) != null) {// 查找不为空，用户存在
			Admin existAdmin = new Admin();
			existAdmin.setAdminName(admin.getAdminName());
			existAdmin.setAdminPassword(admin.getAdminPassword());
			application.setAttribute("existAdmin", existAdmin);// 查找用户成功，用application来记录username的值，在主页中显示
			// 判断记住用户名和密码
			if ("yes".equals(request.getParameter("remember"))) {// 勾选了记住用户的用户名和密码，下次可以直接登陆
				Cookie usernameCookie = new Cookie("username",
						existAdmin.getAdminName());
				usernameCookie.setMaxAge(60 * 60 * 24);// 保持时间为1天
				usernameCookie.setPath("/LBSManagerWeb");
				response.addCookie(usernameCookie);

				Cookie passwordCookie = new Cookie("password",
						existAdmin.getAdminPassword());
				passwordCookie.setMaxAge(60 * 60 * 24 * 30);
				passwordCookie.setPath("/LBSManagerWeb");
				response.addCookie(passwordCookie);
			}
			this.message = "true";
			application.setAttribute("welcome", "true");
			return "loginfail";
		} else {// 查找失败，用户不存在
			this.addFieldError("NameOrPassword", "用户名或是密码错误!");
			return "loginfail";
		}
	}

	/*
	 * 用户注册
	 */
	public String register() {
		if (adminOperate.findAdminByAdName(admin) != null) {// 用户名已经存在
			this.addFieldError("CunAdmin", "用户名已经存在");
			return "registerfail";
		} else {// 用户名不存在
			if (adminOperate.insertAdmin(admin)) {// 真，即插入成功
				application.setAttribute("register", "true");
				return "registerfail";// 注册成功，返回到登陆界面
			} else {
				this.addFieldError("registerFail", "注册失败");
				return "registerfail";// 注册失败，返回到注册界面
			}
		}
	}

	/*
	 * 修改用户密码
	 */
	public String update() throws IOException {
		Admin admin1 = (Admin) application.getAttribute("existAdmin");
		String old = request.getParameter("OldPassword");
		String newString = admin1.getAdminPassword();
		System.out.println("admin的电话：" + admin.getAdminPhone());
		if (old.equals(newString)) {// 旧密码输入正确
			admin.setAdminPassword(request.getParameter("NewPassword"));
			boolean flag = adminOperate.updateAdmin(admin,
					admin1.getAdminName());// 修改密码
			if (flag) {// 密码修改成功
				application.setAttribute("updatePW", "true");
				return "fail";
			} else {
				this.addFieldError("PWfail", "密码修改失败");
				return "fail";
			}
		} else {// 旧密码输入不正确
			this.addFieldError("PWerror", "密码输入错误");
			return "fail";
		}
	}

	/*
	 * 删除用户，接收用户注销的请求
	 */
	public String delete() {
		Admin admin1 = (Admin) application.getAttribute("existAdmin");
		Integer adminId = adminOperate.findAdminIdByAdName(admin1
				.getAdminName());
		System.out.println("adminid：" + adminId);// 为空
		if (adminOperate.deleteAdmin(adminId)) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				System.out.println("Cookies的长度：" + cookies.length);
				for (int i = 0; i < cookies.length; i++) {
					System.out.println("cookiesName:" + cookies[i].getName());
					if (cookies[i].getName().equals("username")) {
						Cookie cookie = new Cookie(cookies[i].getName(), "");
						cookie.setMaxAge(0);
						cookie.setPath("/LBSManagerWeb");
						response.addCookie(cookie);
					}
					if (cookies[i].getName().equals("password")) {
						Cookie cookiee = new Cookie(cookies[i].getName(), "");
						cookiee.setMaxAge(0);
						cookiee.setPath("/LBSManagerWeb");
						response.addCookie(cookiee);
					}
				}
			}
			System.out.println("删除管理员成功");
			return "deleteSuc";
		} else {
			System.out.println("删除管理员失败");
			this.addFieldError("deleteMsg", "删除用户失败");
			return "deleteFail";
		}
	}

	@Override
	public Admin getModel() {
		// TODO Auto-generated method stub
		return admin;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
