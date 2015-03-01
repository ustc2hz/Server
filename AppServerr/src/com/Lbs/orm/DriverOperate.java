package com.Lbs.orm;

import java.util.Iterator;

import org.hibernate.Session;

import com.Lbs.model.Driver;
import com.Lbs.util.HibernateUtil;

public class DriverOperate {
	private Session session;

	/*
	 * 增加驾驶员信息
	 */
	public boolean insertDriver(Driver driver) {
		boolean flag = false;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(driver);
			session.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			flag = false;
		} finally {
			session.close();
		}
		return flag;
	}

	/*
	 * 查找驾驶员(登陆时)需要把驾驶员的name与password进行查找
	 */
	public Driver findDriver(Driver driver) {
		Driver d = new Driver();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from Driver driver where driver.driverName = ? and driver.driverPassword = ?";
			Iterator drivers = session.createQuery(hql)
					.setString(0, driver.getDriverName())
					.setString(1, driver.getDriverPassword()).iterate();
			if (drivers.hasNext()) {
				d = (Driver) drivers.next();
			} else {
				d = null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			d = null;
		} finally {
			session.close();
		}
		return d;
	}

	/*
	 * 查找驾驶员(根据驾驶员name来查找，修改密码时)
	 */
	public Driver findDriverBydName(Driver driver) {
		Driver d = new Driver();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from Driver driver where driver.driverName = ?";
			Iterator drivers = session.createQuery(hql)
					.setString(0, driver.getDriverName()).iterate();
			if (drivers.hasNext()) {
				d = (Driver) drivers.next();
			} else {
				d = null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			d = null;
		} finally {
			session.close();
		}
		return d;
	}

	/*
	 * 修改驾驶员信息（修改密码）
	 */
	public boolean updateDriver(Driver driver) {
		boolean flag = false;
		Driver d = new Driver();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			d = findDriverBydName(driver);
			if (d != null) {// 查找的驾驶员信息存在
				d.setDriverPassword(driver.getDriverPassword());
				d.setDriverPhone(driver.getDriverPhone());
			}
			session.update(d);
			session.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			flag = false;
		} finally {
			session.close();
		}
		return flag;
	}

	/*
	 * 删除驾驶员信息
	 */
	public boolean deleteDriver(Driver driver) {
		boolean flag = false;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(driver);
			session.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			flag = false;
		} finally {
			session.close();
		}
		return flag;
	}
}
