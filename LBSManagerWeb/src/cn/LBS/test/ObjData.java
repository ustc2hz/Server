package cn.LBS.test;

/*
 * 云图所需停车场信息类
 */
public class ObjData {

	public String _name;// 停车场名字
	public String _address;// 停车场地址
	public String phone;// 停车场电话
	public String orderTen;// 预定四十分钟
	public String orderTri;// 预定五十分钟
	public String orderTwe;// 预定六十分钟
	public String payHalPay;// 停车半个小时
	public String payMorePay;// 停车多于一个小时
	public String payOneHour;// 停车一个小时
	public String parkSum;// 停车场总车位数

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
		// TODO Auto-generated method stub
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
