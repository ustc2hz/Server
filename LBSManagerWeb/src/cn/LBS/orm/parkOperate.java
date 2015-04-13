package cn.LBS.orm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import cn.LBS.model.Park;
import cn.LBS.model.ParkInfo;
import cn.LBS.utils.HibernateUtil;

/*
 * 对停车场信息的操作
 */
public class parkOperate {
	private Session session;
	private List list = new ArrayList();// 存储停车场信息列表

	public List<ParkInfo> show() {
		List<ParkInfo> list = new ArrayList<ParkInfo>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from ParkInfo p";
			list = session.createQuery(hql).list();
			session.getTransaction().commit();
			if (list != null) {
				System.out.println("获取所有停车场信息成功");
			} else {
				list = null;
				System.out.println("获取所有停车场信息失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			list = null;
		} finally {
			session.close();
		}
		System.out.println("停车场list的长度:" + list.size());
		if (list != null) {
			Iterator<ParkInfo> iterator = list.iterator();
			while (iterator.hasNext()) {
				ParkInfo p = iterator.next();
				if ((p.getParkAddress()) == null) {
					iterator.remove();
				}
			}
		}
		return list;
	}

	// 根据管理员的name,查询停车场信息
	public List findparkInfobyadName(String adminname) {
		System.out.println("adminName:" + adminname);
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from ParkInfo parkinfo left outer join fetch parkinfo.admin a where a.adminName = ? ";
			list = session.createQuery(hql).setString(0, adminname).list();
			session.getTransaction().commit();
			if (list.size() != 0) {
				System.out.println("获取停车场信息成功");
			} else {
				list = null;
				System.out.println("获取停车场信息失败");
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
	 * 根据管理员的名字，查询停车场车位信息
	 */
	public List findparkbyadName(String adminname) {
		System.out.println("adminName:" + adminname);
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from Park park left outer join fetch park.admin a where a.adminName = ?";
			list = session.createQuery(hql).setString(0, adminname).list();
			session.getTransaction().commit();
			if (list.size() != 0) {
				System.out.println("获取停车场车位信息成功");
			} else {
				list = null;
				System.out.println("获取停车场车位信息失败");
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
	 * 修改停车场信息，停车场车位信息
	 */
	public boolean update(ParkInfo parkInfo, Park park, String adminname) {
		boolean flag = false;
		Integer id = null;
		id = new adminOperate().findAdminIdByAdName(adminname);// 查找adminName对应的adminId
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql1 = "update ParkInfo parkinfo set parkinfo.parkName = ?, parkinfo.parkAddress = ?, parkinfo.orderTen = ?, parkinfo.orderTri = ?, parkinfo.orderTwe = ?, parkinfo.payHalPay = ?,parkinfo.payMorePay = ?, parkinfo.payOneHour = ?, parkinfo.parkSum = ?, parkinfo.adminPhone = ? where parkinfo.adminId = ? ";
			session.createQuery(hql1).setString(0, parkInfo.getParkName())
					.setString(1, parkInfo.getParkAddress())
					.setString(2, parkInfo.getOrderTen())
					.setString(3, parkInfo.getOrderTri())
					.setString(4, parkInfo.getOrderTwe())
					.setString(5, parkInfo.getPayHalPay())
					.setString(6, parkInfo.getPayMorePay())
					.setString(7, parkInfo.getPayOneHour())
					.setString(8, parkInfo.getParkSum())
					.setString(9, parkInfo.getAdminPhone()).setInteger(10, id)
					.executeUpdate(); // 修改停车场信息
			String hql2 = "update Park park set park.parkPublic = ?, park.parkRemain = ? where park.adminId = ?";
			session.createQuery(hql2).setString(0, park.getParkPublic())
					.setString(1, park.getParkRemain()).setInteger(2, id)
					.executeUpdate();// 修改停车场车位信息
			session.getTransaction().commit();
			flag = true;
			System.out.println("停车场信息修改成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}
		return flag;
	}

	/*
	 * 增加停车场编号（从云图获取的停车场Id存入到对象停车场管理员的停车场信息中）
	 */
	public boolean insertparkId(Integer adminID, Integer parkID) {
		boolean flag = false;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			ParkInfo parkInfo = (ParkInfo) session
					.load(ParkInfo.class, adminID);
			parkInfo.setParkId(parkID);
			session.save(parkInfo);
			session.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}
		return flag;
	}

	/*
	 * 查询停车场ID 根据管理员的ID获取
	 */
	public Integer findparkId(Integer adminID) {
		Integer parkID = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			ParkInfo parkInfo = (ParkInfo) session
					.load(ParkInfo.class, adminID);
			parkID = parkInfo.getParkId();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}
		return parkID;
	}
}
