package ustc.sse.water.lbs.server.orm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ustc.sse.water.lbs.server.model.AdminOrderShow;
import ustc.sse.water.lbs.server.model.Driver;
import ustc.sse.water.lbs.server.model.DriverOrderShow;
import ustc.sse.water.lbs.server.model.Order;
import ustc.sse.water.lbs.server.model.OrderShowList;
import ustc.sse.water.lbs.server.utils.HibernateUtil;

/**
 * 
 * 驾驶员的数据库操作类. <br>
 * 对Driver数据库表的操作
 * 
 * <p>
 * Copyright: Copyright (c) 2015-3-26 下午9:44:39
 * <p>
 * Company: 中国科学技术大学软件学院
 * <p>
 * 
 * @author 韩琼 周晶鑫改
 * @version 2.0.0
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
				d = (Driver) drivers.next();
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

	/**
	 * 通过驾驶员姓名查找驾驶员 
	 * @param driver 驾驶员
	 * @return Driver
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
			List orders = session.createQuery("from Order where driver.driverId=? and orderStatus>?")
					.setParameter(0, driverId).setParameter(1, 0).list();
			session.getTransaction().commit();
			if(orders.size() != 0 && orders != null) {
				// 取出有用值
				for(int i=0; i<orders.size(); i++) {
					Order order = (Order)orders.get(i);
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
			case 3: // 更换车牌号
				driver.setDriverName(msg.get(2));
				break;
			case 1:// 修改电话
				driver.setDriverPhone(msg.get(2));
				break;
			case 2: // 修改密码
				String oldPwd = msg.get(2); // 取出旧密码
				if(oldPwd.equals(driver.getDriverPassword())) {
					driver.setDriverPassword(msg.get(3));	
				} else {
					return false;
				}
				
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
