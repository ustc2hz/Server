package cn.LBS.model;

/*
 * 订单信息类
 */
public class OrderList {
	private Integer orderId;// 订单编号
	private String parkName;// 预定停车场名字
	private String parkAddress;// 预定停车场地址
	private Integer driverNum;// 预定停车位个数
	private String orderDate;// 订单日期
	private String orderInfo;// 订单详细信息
	private String orderPrice;// 订单总金额
	private Integer orderStatus;// 订单状态
	private Admin admin;// 管理员类
	private Driver driver;// 驾驶员类

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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

	public Integer getDriverNum() {
		return driverNum;
	}

	public void setDriverNum(Integer driverNum) {
		this.driverNum = driverNum;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}

	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
}