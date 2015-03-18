package com.Lbs.orm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.Lbs.model.Admin;
import com.Lbs.model.AdminOrderShow;
import com.Lbs.model.DriverOrderShow;
import com.Lbs.model.Order;
import com.Lbs.model.OrderShowList;
import com.Lbs.util.HibernateUtil;
/**
 * 停车场管理的数据库操作类
 * 增加管理员，修改管理员密码，查询管理员
 * 获取管理员的订单信息
 */
public class AdminOperate {

	private Session session; // Hibernate操作的Session

	/**
	 * 新增管理员
	 * @param admin 停车场管理员
	 * @return boolean
	 */
	public boolean insertAdmin(Admin admin) {
		boolean flag = false;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(admin);
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
	 * 查询指定管理员是否存在
	 * @param admin 管理员
	 * @return Admin 如果存在，则返回该管理员；否则，返回null
	 */
	public Admin findAdmin(Admin admin) {
		Admin manager = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from Admin admin where admin.adminName = ? and admin.adminPassword = ?";
			Iterator admins = session.createQuery(hql)
					.setString(0, admin.getAdminName())
					.setString(1, admin.getAdminPassword()).iterate();
			if (admins.hasNext()) {
				manager = (Admin) admins.next();
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager = null;
		} finally {
			session.close();
		}
		return manager;
	}

	/**
	 * 修改停车场管理员的密码
	 * @param adminId 管理员id
	 * @param oldPwd 旧密码
	 * @param newPwd 新密码
	 * @return int 1为修改正常完成，2为旧密码错误，3为修改出错
	 */
	public int updateAdminPwd(int adminId, String oldPwd, String newPwd) {
		int resultType = 3;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Admin admin = (Admin)session.get(Admin.class, adminId);
			session.getTransaction().commit();
			if(oldPwd.equals(admin.getAdminPassword())) {
				// 旧密码匹配
				session.beginTransaction();
				admin.setAdminPassword(newPwd);
				session.flush();
				session.getTransaction().commit();
				resultType = 1;
			} else {
				resultType = 2;
			}
		} catch (Exception e) {
			resultType = 3;
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return resultType;
	}

	/**
	 * 获取管理员订单
	 * @param adminId 管理员id
	 * @param type 1为正在进行的订单，2为已经完成的订单
	 * @return OrderShowList
	 */
	public OrderShowList getAdminOrders(int adminId, int type) {
		OrderShowList osl = new OrderShowList();
		List<AdminOrderShow> list = new ArrayList<AdminOrderShow>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Iterator orders = session.createQuery("from Order where admin.adminId=? and orderStatus=?")
					.setParameter(0, adminId).setParameter(1, type).iterate();
			session.getTransaction().commit();
			// 取出有用值
			for(; orders.hasNext();) {
				Order order = (Order)orders.next();
				AdminOrderShow aos = new AdminOrderShow();
				
				aos.setOrderId(order.getOrderId());
				aos.setDriverLicence(order.getDriver().getDriverName());
				aos.setDriverPhone(order.getDriver().getDriverPhone());
				aos.setParkNumber(order.getDriverNum());
				aos.setOrderDate(order.getOrderDate());
				aos.setOrderDetail(order.getOrderInfo());
				aos.setOrderPrice(order.getOrderPrice());
				aos.setOrderStatus(order.getOrderStatus());
				
				list.add(aos);
			}
			osl.setAdminShow(list);
		} catch (Exception e) {	
			e.printStackTrace();
			osl = null;
		} finally {
			session.close();
		}
		
		return osl;
	}
	
	/**
	 * 修改管理员订单状态为2
	 * @param orderId 订单id
	 * @return boolean 
	 */
	public boolean updateStatusTwo(int orderId) {
		boolean flag = false;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			// 修改状态为2
			Order order = (Order)session.get(Order.class, orderId);
			order.setOrderStatus(2);
			session.flush();
			session.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return flag;
	}
	
}
