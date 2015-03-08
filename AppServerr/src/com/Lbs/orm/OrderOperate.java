package com.Lbs.orm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.Lbs.model.AdminOrderShow;
import com.Lbs.model.Order;
import com.Lbs.model.OrderShowList;
import com.Lbs.util.HibernateUtil;

/*
 * 管理员与驾驶员对订单的操作
 */
public class OrderOperate {
	private Session session;

	/**
	 * 将订单存入数据库中
	 * @param order 订单
	 * @return boolean
	 */
	public boolean insertOrder(Order order) {
		boolean flag = false;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(order);
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
	 * 管理员获取订单状态为“0”的订单
	 * @param adminId 管理员Id
	 * @return OrderShowList
	 */
	public OrderShowList getAdminOrderShow(int adminId) {
		OrderShowList orderLists = new OrderShowList();
		List<AdminOrderShow> list = new ArrayList<AdminOrderShow>();
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Iterator orders = session.createQuery("from Order where admin.adminId=? and orderStatus=?")
					.setParameter(0, adminId).setParameter(1, 0).iterate();
			session.getTransaction().commit();
			
			for(; orders.hasNext();) {
				Order order = (Order)orders.next();
				AdminOrderShow aos = new AdminOrderShow();
				aos.setOrderId(order.getOrderId());
				aos.setDriverLicence(order.getDriver().getDriverName());
				aos.setDriverPhone(order.getDriver().getDriverPhone());
				aos.setOrderDate(order.getOrderDate());
				aos.setOrderDetail(order.getOrderInfo());
				aos.setOrderPrice(order.getOrderPrice());
				aos.setOrderStatus(order.getOrderStatus());
				aos.setParkNumber(order.getDriverNum());
				list.add(aos);
			}
			orderLists.setAdminShow(list);
			
		} catch (Exception e) {
			e.printStackTrace();
			orderLists = null;
			session.close();
		}
		
		return orderLists;
	}
	
	/**
	 * 修改订单状态为1
	 * @param ids 需要修改的订单id
	 * @return boolean
	 */
	public boolean updateOrderStatusToOne(List<String> ids) {
		boolean flag = false;
		Integer[] orderIds = new Integer[ids.size()];
		// 将String类型的id转化为int类型
		for(int i=0; i<ids.size(); i++) {
			String oId = ids.get(i);
			orderIds[i] = Integer.parseInt(oId);
		}
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Iterator orders = session.createCriteria(Order.class)
					.add(Restrictions.in("orderId", orderIds)).list().iterator();
			int count = 0;
		    // 遍历
		    while(orders.hasNext()) {
		    	Order order = (Order)orders.next();
		    	order.setOrderStatus(1);
		    	if(++count%20 == 0) {
		    		session.flush();
		    		session.clear();
		    	}
		    }
		    session.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		
		return flag;
	}
	
	/**
	 * 检查订单是否被管理员接收到
	 * @param uuid 订单的标识
	 * @return boolean
	 */
	public boolean checkOrderReceived(String uuid) {
		boolean flag = false;
		int state = 0;
		try {
			String hql = "select orderStatus from Order where uuid = ?";
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Iterator status = session.createQuery(hql).setParameter(0, uuid).iterate();
			while(status.hasNext()) {
				state = (Integer) status.next();
			}
			if(state == 1) {
				flag = true;
			} 
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
			flag = false;
		}
		
		return flag;
	}
	
	/**
	 * 删除没有被管理员接收到的订单
	 * @param uuid 订单标识
	 */
	public int deleteUnreceivedOrder(String uuid) {
		int count = 0;
		try {
			String hql = "delete Order where uuid = ?";
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			count = session.createQuery(hql).setParameter(0, uuid).executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
		}
		return count;
	}
	
}
