package cn.LBS.orm;

import org.hibernate.Session;

import cn.LBS.model.Driver;
import cn.LBS.utils.HibernateUtil;

/*
 * 对管理员的操作
 */
public class driverOperate {
	private Session session;

	/*
	 * 查找驾驶员车牌号
	 */
	public String finddriverName(Integer driverId) {
		String driverName = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Driver driver = new Driver();
			driver = (Driver) session.get(Driver.class, driverId);
			session.getTransaction().commit();
			driverName = driver.getDriverName();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}
		return driverName;
	}
}
