package com.Lbs.orm;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.Lbs.model.Order;
import com.Lbs.util.HibernateUtil;

/*
 * 管理员与驾驶员对订单的操作
 */
public class OrderOperate {
	private Session session;

	/*
	 * 插入订单（驾驶员端发送过来的订单信息）
	 */
	public boolean insertOrder(Order order) {
		boolean flag = false;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(order);
			session.getTransaction().commit();
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
	 * 删除单个订单 驾驶员和管理员都根据订单编号来删除订单
	 */
	public boolean deleteOrder(Order order) {
		boolean flag = false;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "delete Order order where order.orderId = ?";
			session.createQuery(hql)
					.setString(0, String.valueOf(order.getOrderId()))
					.executeUpdate();
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
	 * 删除所有订单 驾驶员端删除所有订单，根据驾驶员Dname来查询所有订单并删除
	 */
	public boolean DriverdeleteOrders(String drivername) {
		boolean flag = false;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "delete Order where Order.driver.driverName = ?";
			session.createQuery(hql).setString(0, drivername).executeUpdate();
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
	 * 删除所有订单 管理员端删除所有订单，根据停车场管理员的name来查找和删除订单。
	 */
	public boolean AdmindeleteOrders(String adminName) {
		boolean flag = false;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			// 先根据管理员的name来查找管理员的ID

			String hql = "delete Order where Order.park.pName = ? and Order.park.pAddress = ?";
			// 根据管理员ID来删除订单
			// int num = session.createQuery(hql).setString(0, parkName)
			// .setString(1, parkAddress).executeUpdate();
			session.getTransaction().commit();
			// if (num != 0) {
			// flag = true;
			// System.out.println("管理员端删除所有订单操作成功");
			// } else {
			// flag = false;
			// System.out.println("管理员端删除所有订单操作失败�");
			// }
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
	 * 修改订单 驾驶员和管理员都通过OrderId来修改订单
	 */
	public boolean updateOrder(Order order) {
		boolean flag = false;
		Order o = new Order();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			String hql = "update Order set Order.oStatus = ?,Order.dNum = ?,Order.oDate = ?,Order.oInfo = ?,Order.oPrice = ?,Order.driver.dPhone=?,Order.park.pName=?,Order.park.pAddress=?,Order.driver.dName=? where Order.oId = ?";
			// int num = session.createQuery(hql)
			// .setString(0, String.valueOf(order.getoStatus()))
			// .setString(1, String.valueOf(order.getdNum()))
			// .setString(2, order.getoDate())
			// .setString(3, order.getoInfo())
			// .setString(4, order.getoPrice())
			// .setString(5, order.getDriver().getdPhone())
			// .setString(6, order.getPark().getpName())
			// .setString(7, order.getPark().getpAddress())
			// .setString(8, order.getDriver().getUsername())
			// .setString(9, String.valueOf(order.getoId()))
			// .executeUpdate();
			session.getTransaction().commit();
			// if (num != 0) {
			// flag = true;
			// System.out.println("删除订单操作成功");
			// } else {
			// flag = false;
			// System.out.println("删除订单操作失败");
			// }
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
	 * 查找订单 管理员端查找需要处理的订单(根据管理员的停车场的name和Address以及订单状态为0的订单)
	 */
	public List<Order> AdminfindOrder(String pName, String pAddress) {
		List<Order> list = new ArrayList<Order>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			// 根据订单中的驾驶员的name来修改订单
			String hql = "from Order where Order.park.pName=? and Order.park.pAddress=? and Order.oStatus = 0";
			list = session.createQuery(hql).setString(0, pName)
					.setString(1, pAddress).list();
			session.getTransaction().commit();
			if (list != null) {
				System.out.println("管理员端获取订单成功");
			} else {
				list = null;
				System.out.println("管理员端获取订单失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			list = null;
		} finally {
			session.close();
		}
		return list;
	}

	/*
	 * 查找订单 管理员端查找所有订单(根据管理员停车场的name和address来进行查找)
	 */
	public List<Order> AdminfindOrders(String parkName, String parkAddress) {
		List<Order> list = new ArrayList<Order>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			// 根据订单中的驾驶员的name来修改订单
			String hql = "from Order where Order.park.pName=? and Order.park.pAddress=?";
			list = session.createQuery(hql).setString(0, parkName)
					.setString(1, parkAddress).list();
			session.getTransaction().commit();
			if (list != null) {
				System.out.println("管理员端获取订单成功");
			} else {
				list = null;
				System.out.println("管理员端获取订单失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			list = null;
		} finally {
			session.close();
		}
		return list;
	}

	/*
	 * 查找订单 驾驶员端查找订单(历史记录)根据驾驶员的name来查询所有订单
	 */
	public List<Order> DriverfindOrder(String dname) {
		List<Order> list = new ArrayList<Order>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			// 根据订单中的驾驶员的name来修改订单
			String hql = "from Order where Order.driver.username=?";
			list = session.createQuery(hql).setString(0, dname).list();
			session.getTransaction().commit();
			if (list != null) {
				System.out.println("驾驶员端获取所有订单成功");
			} else {
				list = null;
				System.out.println("驾驶员端获取所有订单失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			list = null;
		} finally {
			session.close();
		}
		return list;
	}
}
// /*
// * ɾ�������Ա��ɾ�����ݶ����е�orderId����ѯ��ɾ��)
// */
// public boolean AdmindeleteOrder(Order order) {
// boolean flag = false;
// try {
// session = HibernateUtil.getSession();
// session.beginTransaction();
// String hql = "delete Order where Order.oId = ?";
// int num = session.createQuery(hql)
// .setString(0, String.valueOf(order.getoId()))
// .executeUpdate();
// session.getTransaction().commit();
// if (num != 0) {
// flag = true;
// System.out.println("����Ա��ɾ������ɹ�");
// } else {
// flag = false;
// System.out.println("����Ա��ɾ�����ʧ��");
// }
// } catch (Exception e) {
// // TODO: handle exception
// e.printStackTrace();
// flag = false;
// } finally {
// session.close();
// }
// return flag;
// }
// /*
// * �޸Ķ��� ��ʻԱ���޸Ķ���(��ݶ�����OrderId����ѯ���޸�)
// */
// public boolean DriverupdateOrder(Order order) {
// boolean flag = false;
// Order o = new Order();
// try {
// session = HibernateUtil.getSession();
// session.beginTransaction();
//
// String hql =
// "update Order set Order.oStatus = ?,Order.dNum = ?,Order.oDate = ?,Order.oInfo = ?,Order.oPrice = ?,Order.driver.dPhone=?,Order.park.pName=?,Order.park.pAddress=?,Order.driver.dName=? where Order.oId = ?";
// int num = session.createQuery(hql)
// .setString(0, String.valueOf(order.getoStatus()))
// .setString(1, String.valueOf(order.getdNum()))
// .setString(2, order.getoDate())
// .setString(3, order.getoInfo())
// .setString(4, order.getoPrice())
// .setString(5, order.getDriver().getdPhone())
// .setString(6, order.getPark().getpName())
// .setString(7, order.getPark().getpAddress())
// .setString(8, order.getDriver().getUsername())
// .setString(9, String.valueOf(order.getoId()))
// .executeUpdate();
// session.getTransaction().commit();
// if (num != 0) {
// flag = true;
// System.out.println("��ʻԱ��ɾ������ɹ�");
// } else {
// flag = false;
// System.out.println("��ʻԱ��ɾ�����ʧ��");
// }
// } catch (Exception e) {
// // TODO: handle exception
// e.printStackTrace();
// flag = false;
// } finally {
// session.close();
// }
// return flag;
// }
//
// /*
// * �޸Ķ��� ����Ա���޸Ķ�������ݶ����еĹ���Աͣ���������ֺ͵�ַ���в�ѯ���޸ģ�
// */
// public boolean AdminupdateOrder(Order order) {
// boolean flag = false;
// Order o = new Order();
// try {
// session = HibernateUtil.getSession();
// session.beginTransaction();
// // ��ݶ����еļ�ʻԱ��name���޸Ķ���
// String hql =
// "update Order set Order.oStatus = ?,Order.dNum = ?,Order.oDate = ?,Order.oInfo = ?,Order.oPrice = ?,Order.driver.dPhone=?,Order.driver.dName=? where Order.park.pName=? and Order.park.pAddress=?";
// int num = session.createQuery(hql)
// .setString(0, String.valueOf(order.getoStatus()))
// .setString(1, String.valueOf(order.getdNum()))
// .setString(2, order.getoDate())
// .setString(3, order.getoInfo())
// .setString(4, order.getoPrice())
// .setString(5, order.getDriver().getdPhone())
// .setString(6, order.getDriver().getUsername())
// .setString(7, order.getPark().getpName())
// .setString(8, order.getPark().getpAddress())
// .executeUpdate();
// session.getTransaction().commit();
// if (num != 0) {
// flag = true;
// System.out.println("����Ա��ɾ������ɹ�");
// } else {
// flag = false;
// System.out.println("����Ա��ɾ�����ʧ��");
// }
// } catch (Exception e) {
// // TODO: handle exception
// e.printStackTrace();
// flag = false;
// } finally {
// session.close();
// }
// return flag;
// }
