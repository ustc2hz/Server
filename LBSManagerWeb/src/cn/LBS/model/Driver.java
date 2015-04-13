package cn.LBS.model;

/*
 * 驾驶员类
 */
public class Driver {
	private Integer driverId;// 驾驶员ID
	private String driverName;// 驾驶员用户名
	private String driverPassword;// 驾驶员登陆密码
	private String driverPhone;// 驾驶员电话

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverPassword() {
		return driverPassword;
	}

	public void setDriverPassword(String driverPassword) {
		this.driverPassword = driverPassword;
	}

	public String getDriverPhone() {
		return driverPhone;
	}

	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}

}
