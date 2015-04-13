package cn.LBS.model;

/*
 * 云图所需停车场信息类
 */
public class YuntuData {
	private String _location;// 坐标经纬度
	private String _name; // 停车场名
	private String orderTen;// 预定四十分钟收费标准
	private String orderTri;// 预定五十分钟收费标准
	private String orderTwe;// 预定六十分钟收费标准
	private String parkSum;// 停车场停车位个数
	private String payHalPay;// 停车半个小时
	private String payMorePay;// 停车多于一个小时
	private String payOneHour;// 停车一个小时

	public String get_location() {
		return _location;
	}

	public void set_location(String _location) {
		this._location = _location;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
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

	public String getParkSum() {
		return parkSum;
	}

	public void setParkSum(String parkSum) {
		this.parkSum = parkSum;
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

}
