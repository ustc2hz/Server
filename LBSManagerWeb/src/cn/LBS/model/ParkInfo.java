package cn.LBS.model;

/*
 * 停车场信息类
 */
public class ParkInfo {
	private Integer adminId;// 管理员ID
	public String parkName;// 停车场名字
	public String parkAddress;// 停车场地址
	public String adminPhone;// 停车场电话
	public String orderTen;// 预定四十分钟收费价格
	public String orderTri;// 预定五十分钟收费价格
	public String orderTwe;// 预定六十分钟收费价格
	public String payHalPay;// 停车半个小时收费标准
	public String payMorePay;// 停车多于一个小时收费标准
	public String payOneHour;// 停车一小时收费标准
	public String parkSum;// 停车场个数
	private Integer parkId;// 云图中停车场的id
	private Admin admin;// 停车场管理员类

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getParkAddress() {
		return parkAddress;
	}

	public void setParkAddress(String parkAddress) {
		this.parkAddress = parkAddress;
	}

	public String getOrderTen() {
		return orderTen;
	}

	public void setOrderTen(String orderTen) {
		this.orderTen = orderTen;
	}

	public String getOrderTri() {
		return orderTri;
	}

	public void setOrderTri(String orderTri) {
		this.orderTri = orderTri;
	}

	public String getOrderTwe() {
		return orderTwe;
	}

	public void setOrderTwe(String orderTwe) {
		this.orderTwe = orderTwe;
	}

	public String getPayHalPay() {
		return payHalPay;
	}

	public void setPayHalPay(String payHalPay) {
		this.payHalPay = payHalPay;
	}

	public String getPayMorePay() {
		return payMorePay;
	}

	public void setPayMorePay(String payMorePay) {
		this.payMorePay = payMorePay;
	}

	public String getPayOneHour() {
		return payOneHour;
	}

	public void setPayOneHour(String payOneHour) {
		this.payOneHour = payOneHour;
	}

	public Integer getParkId() {
		return parkId;
	}

	public void setParkId(Integer parkId) {
		this.parkId = parkId;
	}

	public String getParkSum() {
		return parkSum;
	}

	public void setParkSum(String parkSum) {
		this.parkSum = parkSum;
	}

}
