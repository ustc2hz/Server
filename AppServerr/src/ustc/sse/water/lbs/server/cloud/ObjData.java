package ustc.sse.water.lbs.server.cloud;
/**
 * 
 * 云图类. <br>
 * 停车场信息
 * 
 * <p>
 * Copyright: Copyright (c) 2015-3-26 下午9:44:39
 * <p>
 * Company: 中国科学技术大学软件学院
 * <p>
 * 
 * @author 黄志恒
 * @version 2.0.0
 */
public class ObjData {

	public String _name; // 停车场名
	public String _address; // 停车场坐标
	public String phone; // 停车场电话
	public String orderTen; 
	public String orderTri;
	public String orderTwe;
	public String payHalPay;// 停车半个小时
	public String payMorePay;// 停车多于一个小时
	public String payOneHour;
	public String parkSum; // 提供的车位数

	public ObjData(String name, String address, String phone, String orderTen,
			String orderTri, String orderTwe, String payHalPay,
			String payOneHour, String payMorePay, String parkSum) {
		this._name = name;
		this._address = address;
		this.phone = phone;
		this.orderTen = orderTen;
		this.orderTri = orderTri;
		this.orderTwe = orderTwe;
		this.payHalPay = payHalPay;
		this.payOneHour = payOneHour;
		this.payMorePay = payMorePay;
		this.parkSum = parkSum;
	}

	public ObjData() {

	}

	@Override
	public String toString() {
		return this._name + "#" + this._address + "#" + this.phone + "#"
				+ this.orderTen + "#" + this.orderTri + "#" + this.orderTwe
				+ "#" + this.payHalPay + "#" + this.payOneHour + "#"
				+ this.payMorePay + "#" + this.parkSum;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_address() {
		return _address;
	}

	public void set_address(String _address) {
		this._address = _address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getParkSum() {
		return parkSum;
	}

	public void setParkSum(String parkSum) {
		this.parkSum = parkSum;
	}

}
