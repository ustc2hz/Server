package com.Lbs.orm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import com.Lbs.model.AdminOrderShow;
import com.Lbs.model.Driver;
import com.Lbs.model.DriverOrderShow;
import com.Lbs.model.Order;
import com.Lbs.model.OrderShowList;
import com.Lbs.util.HibernateUtil;
/**
 * 对Driver数据库表的操作
 */
public class DriverOperate {
	private Session session;

	/**
	 * 添加Driver信息
	 * @param driver 驾驶员
	 * @return boolean
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
			e.printStackTrace();
			flag = false;
		} finally {
			session.close();
		}
		return flag;
	}

	/**
	 * 查找Driver
	 * @param driver 驾驶员
	 * @return Driver
	 */
	public Driver findDriver(Driver driver) {
		Driver d = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from Driver driver where driver.driverName = ? and driver.driverPassword = ?";
			Iterator drivers = session.createQuery(hql)
					.setParameter(0, driver.getDriverName())
					.setParameter(1, driver.getDriverPassword()).iterate();
			if (drivers.hasNext()) {
				System.out.println("进入此处");
				d = (Driver) drivers.next();
				System.out.println(d.getDriverName());
			} 
			session.getTransaction().commit();
		} catch (Exception e) {
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
			e.printStackTrace();
			d = null;
		} finally {
			session.close();
		}
		return d;
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
	
	/**
	 * 获取驾驶员的状态为1和2的订单
	 * @param driverId 驾驶员的Id
	 * @return OrderShowList
	 */
	public OrderShowList getDriverOrders(int driverId) {
		OrderShowList orderLists = new OrderShowList();
		List<DriverOrderShow> list = new ArrayList<DriverOrderShow>();
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Iterator orders = session.createQuery("from Order where driver.driverId=? and orderStatus>?")
					.setParameter(0, driverId).setParameter(1, 0).iterate();
			session.getTransaction().commit();
			// 取出有用值
			for(; orders.hasNext();) {
				Order order = (Order)orders.next();
				DriverOrderShow dos = new DriverOrderShow();
				dos.setParkName(order.getParkName());
				dos.setParkAddress(order.getParkAddress());
				dos.setParkNumber(order.getDriverNum());
				dos.setOrderDate(order.getOrderDate());
				dos.setOrderDetail(order.getOrderInfo());
				dos.setOrderPrice(order.getOrderPrice());
				dos.setAdminPhone(order.getAdmin().getParkPhone());
				dos.setOrderStatus(order.getOrderStatus());
				list.add(dos);
			}
			orderLists.setDriverShow(list);
			
		} catch (Exception e) {
			e.printStackTrace();
			orderLists = null;
		} finally {
			session.close();
		}
		
		return orderLists;
	}
	
	/**
	 * 分模式修改Driver的信息
	 * @param msg 修改信息
	 * @return boolean
	 */
	public boolean updateDriverInfo(List<String> msg) {
		boolean flag = false; // 默认修改失败
		int mode = Integer.parseInt(msg.get(0)); // 取出修改模式
		int dId = Integer.parseInt(msg.get(1)); // 取出驾驶员Id
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Driver driver = (Driver)session.get(Driver.class, dId);
			
			switch(mode) {
			case 3: // 修改全部
				driver.setDriverName(msg.get(2));
				driver.setDriverPhone(msg.get(3));
				driver.setDriverPassword(msg.get(4));
				break;
			case 1:// 修改电话
				driver.setDriverPhone(msg.get(2));
				break;
			case 2: // 修改密码
				driver.setDriverPassword(msg.get(2));
				break;
			}
			
			session.flush();
			session.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		} finally {
			session.close();
		}
		
		return flag;
	}
}
