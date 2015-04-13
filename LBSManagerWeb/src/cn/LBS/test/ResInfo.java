package cn.LBS.test;

/*
 * 云图返回信息类
 */
public class ResInfo {
	private String info = new String();
	private int status = -1;
	private int _id = -1;

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

}
