package com.Lbs.model;
/**
 * 记录每个停车场的剩余车位数
 */
public class ParkingBook {
	
	private int parkId; // 停车场id 
	private int managerId; // 管理员
	private int parkNum; // 剩余车位数
	
	public int getParkId() {
		return parkId;
	}
	public void setParkId(int parkId) {
		this.parkId = parkId;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public int getParkNum() {
		return parkNum;
	}
	public void setParkNum(int parkNum) {
		this.parkNum = parkNum;
	}
	
}
