package cn.LBS.model;

/*
 * 停车场管理员类
 */
public class Admin {
	private Integer adminId;// 停车场管理员ID
	private String adminName;// 停车场管理员的用户名
	private String adminPassword;// 停车场管理员的登陆密码
	private String adminPhone;// 停车场的电话
	private ParkInfo parkInfo;// 停车场信息类
	private Park park;// 停车场停车位信息类

	// private String parkPhone;
	public Integer getAdminId() {
		return adminId;
	}

	public String getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public ParkInfo getParkInfo() {
		return parkInfo;
	}

	public void setParkInfo(ParkInfo parkInfo) {
		this.parkInfo = parkInfo;
	}

	public Park getPark() {
		return park;
	}

	public void setPark(Park park) {
		this.park = park;
	}

}
