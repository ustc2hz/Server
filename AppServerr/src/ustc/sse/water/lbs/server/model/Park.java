package ustc.sse.water.lbs.server.model;
/**
 * 
 * Model类. <br>
 * 停车场.
 * <p>
 * Copyright: Copyright (c) 2015-3-26 下午9:44:39
 * <p>
 * Company: 中国科学技术大学软件学院
 * <p>
 * 
 * @author 韩琼
 * @version 1.0.0
 */
public class Park {
	
	private Admin admin; // 停车场管理员
	private Integer adminId; // 管理员id
	private String parkPublic; // 提供车位数
	private String parkRemain; // 剩余车位数

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
