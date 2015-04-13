package cn.LBS.orm;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.LBS.model.Admin;
import cn.LBS.model.OrderList;
import cn.LBS.utils.HibernateUtil;

/*
 * 对订单的操作
 */
public class orderOperate {
	private Session session;

	/*
	 * 查询未处理的订单 根据停车场管理员,订单状态为1，1：表示已查看订单
	 */
	public List<OrderList> findOrderByAdmin(Admin admin) {
		List<OrderList> list = new ArrayList<OrderList>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			System.out.println("admin的name:" + admin.getAdminName());
			String hql = "from OrderList o left outer join fetch o.admin a where o.orderStatus = 1 and a.adminName = ?";
			list = session.createQuery(hql).setString(0, admin.getAdminName())
					.list();
			session.getTransaction().commit();
			if (list != null) {
				System.out.println("驾驶员端获取未处理订单成功");
			} else {
				list = null;
				System.out.println("驾驶员端获取未处理订单失败");
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
	 * 查询所有的历史订单 根据停车场管理员,订单状态为2,2：表示订单完成了
	 */
	public List<OrderList> findOrderAllByAdmin(Admin admin) {
		List<OrderList> list = new ArrayList<OrderList>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from OrderList o left outer join fetch o.admin a where o.orderStatus = 2 and a.adminName = ?";
			list = session.createQuery(hql).setString(0, admin.getAdminName())
					.list();
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

	/*
	 * 修改订单的状态，根据获取的订单Id数组
	 */
	public boolean updateOrderStatus(String[] ids) {
		boolean flag = false;
		int updateEntity = 0;
		int count = 0;
		System.out.println("id:" + ids[0]);
		Integer id = Integer.valueOf(ids[0]);
		System.out.println("id:" + id);
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			for (int i = 0; i < ids.length; i++) {// 查询出表中所有的id并修改订单状态
				Transaction tx = session.beginTransaction();
				String hql = "update OrderList o set o.orderStatus = ? where o.orderId =?";
				updateEntity = session.createQuery(hql).setInteger(0, 2)
						.setInteger(1, Integer.valueOf(ids[i])).executeUpdate();
				if (++count % 20 == 0) {
					session.flush();
					session.clear();
				}
				tx.commit();
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}
		return flag;
	}

	/*
	 * 删除订单，根据获取的订单Id数组
	 */
	public boolean deleteOrder(String[] ids) {
		boolean flag = false;
		int updateEntity = 0;
		int count = 0;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			for (int i = 0; i < ids.length; i++) { // 查询出表中所有的id并删除订单
				Transaction tx = session.beginTransaction();
				String hql = "delete OrderList o where o.orderId =?";
				updateEntity = session.createQuery(hql)
						.setInteger(0, Integer.valueOf(ids[i])).executeUpdate();
				if (++count % 20 == 0) {
					session.flush();
					session.clear();
				}
				tx.commit();
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}
		return flag;
	}

	/*
	 * 统计订单中预定的停车位数
	 */
	public Integer CountOrderParkNum(String adminname) {
		Integer count = null;
		Integer adminId = new adminOperate().findAdminIdByAdName(adminname);
		System.out.println("adminId：" + adminId);
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String sql = "select sum(lbs_order.driver_number) from lbs_order where lbs_order.order_status = 0 and lbs_order.admin_id = :adminid ;";
			List accountList = session.createSQLQuery(sql)
					.setInteger("adminid", adminId).list();
			session.getTransaction().commit();
			if (accountList.get(0) == null) {
				count = 0;
			} else {
				count = Integer.valueOf(accountList.get(0).toString());
			}
			System.out.println("预定车位数：" + count);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}
		return count;
	}

	/*
	 * 统计订单个数
	 */
	public Integer CountOrderNum(String adminname) {
		Integer count = null;
		Integer adminId = new adminOperate().findAdminIdByAdName(adminname);
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String sql = "select count(lbs_order.order_id) from lbs_order where lbs_order.order_status = 2 and lbs_order.admin_id = :adminid ;";
			List accountList = session.createSQLQuery(sql)
					.setInteger("adminid", adminId).list();
			session.getTransaction().commit();
			if (accountList.get(0) == null) {
				count = 0;
			} else {
				count = Integer.valueOf(accountList.get(0).toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}
		return count;
	}

	/*
	 * 统计订单总收益
	 */
	public Integer CountOrderPrice(String adminname) {
		Integer count = 0;
		Integer adminId = new adminOperate().findAdminIdByAdName(adminname);
		List<String> list = new ArrayList<String>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String sql = "select lbs_order.order_price from lbs_order where lbs_order.order_status = 2 and lbs_order.admin_id = :adminid ;";
			list = session.createSQLQuery(sql).setInteger("adminid", adminId)
					.list();
			session.getTransaction().commit();
			if (list == null) {
				count = 0;
			} else {
				for (int i = 0; i < list.size(); i++) {
					count = Integer.valueOf(list.get(i).toString());
					count += count;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}
		return count;
	}

	/*
	 * 统计某年某月的订单数
	 */
	public Integer[] CountOrderNumOfYM(String adminname, String Year,
			String Month) {
		Integer[] num = new Integer[2];
		Integer adminId = new adminOperate().findAdminIdByAdName(adminname);
		System.out.println("年：" + Year + ",月：" + Month);
		List<OrderList> list = new ArrayList<OrderList>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String sql = "select * from lbs_order where lbs_order.order_date like :date and lbs_order.order_status = 2 and lbs_order.admin_id = :id ;";
			list = session.createSQLQuery(sql).addEntity(OrderList.class)
					.setString("date", Year + "-" + Month + "%")
					.setInteger("id", adminId).list();
			session.getTransaction().commit();
			if (list == null) {
				num = null;
			} else {
				num[0] = list.size();
				num[1] = 0;
				for (int i = 0; i < list.size(); i++) {
					System.out.println("list[i]的内容："
							+ list.get(i).getClass().getName());
					OrderList order = list.get(i);
					num[1] = Integer.valueOf(list.get(i).getOrderPrice()
							.toString());
					num[1] += num[1];
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}
		return num;
	}
}