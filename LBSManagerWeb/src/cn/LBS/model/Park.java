package cn.LBS.model;

/*
 * 停车场车位信息类
 */
public class Park {
	private Admin admin;// 驾驶员类
	private Integer adminId;// 停车场ID
	private String parkPublic;// 开放停车位
	private String parkRemain;// 剩余停车位

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getParkPublic() {
		return parkPublic;
	}

	public void setParkPublic(String parkPublic) {
		this.parkPublic = parkPublic;
	}

	public String getParkRemain() {
		return parkRemain;
	}

	public void setParkRemain(String parkRemain) {
		this.parkRemain = parkRemain;
	}

}
