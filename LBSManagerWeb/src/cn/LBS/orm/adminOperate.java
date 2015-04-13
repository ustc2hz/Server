package cn.LBS.orm;

import java.util.Iterator;

import org.hibernate.Session;

import cn.LBS.model.Admin;
import cn.LBS.model.Park;
import cn.LBS.model.ParkInfo;
import cn.LBS.utils.HibernateUtil;

/*
 * 对管理员信息的操作
 */
public class adminOperate {
	private Session session;

	/*
	 * 增加管理员
	 */
	public boolean insertAdmin(Admin admin) {
		boolean flag = false;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			ParkInfo parkInfo = new ParkInfo();
			Park park = new Park();
			parkInfo.setAdminPhone(admin.getAdminPhone());
			admin.setPark(park);
			park.setAdmin(admin);
			admin.setParkInfo(parkInfo);
			parkInfo.setAdmin(admin);
			session.save(admin);
			session.save(park);
			session.save(parkInfo);
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

	/*
	 * 查找管理员 根据用户名和密码，登录时查找管理员。
	 */
	public Admin findAdmin(Admin admin) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from Admin admin where admin.adminName = ? and admin.adminPassword = ?";
			Iterator admins = session.createQuery(hql)
					.setString(0, admin.getAdminName())
					.setString(1, admin.getAdminPassword()).iterate();
			if (admins.hasNext()) {
				admin = (Admin) admins.next();
			} else {
				admin = null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			admin = null;
		} finally {
			session.close();
		}
		return admin;
	}

	/*
	 * 查找管理员 根据管理员name来查找，用户注册时，判断用户名是否重复。
	 */
	public Admin findAdminByAdName(Admin admin) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from Admin admin where admin.adminName = ?";
			Iterator admins = session.createQuery(hql)
					.setString(0, admin.getAdminName()).iterate();
			if (admins.hasNext()) {
				admin = (Admin) admins.next();
			} else {
				admin = null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			admin = null;
		} finally {
			session.close();
		}
		return admin;
	}

	/*
	 * 查找管理员 根据管理员name来查找，用户注册时，判断用户名是否重复。
	 */
	public Integer findAdminIdByAdName(String adminname) {
		Integer id = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from Admin admin where admin.adminName = ?";
			Iterator admins = session.createQuery(hql).setString(0, adminname)
					.iterate();
			if (admins.hasNext()) {
				Admin admin = (Admin) admins.next();
				id = admin.getAdminId();
			} else {
				id = null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			session.close();
		}
		return id;
	}

	/*
	 * 修改管理员信息 进入修改管理员信息，证明这个用户已经存在了，就不需要判断是否存在这个管理员了。 修改park和adminPhoen的电话
	 */
	public boolean updateAdmin(Admin admin, String adminname) {
		boolean flag = false;
		System.out.println("admin的名字：" + admin.getAdminName());
		System.out.println("admin的密码：" + admin.getAdminPassword());
		System.out.println("admin的电话：" + admin.getAdminPhone());
		Integer id = findAdminIdByAdName(adminname);
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "update Admin admin set admin.adminPassword = ? , admin.adminPhone = ? where admin.adminName = ?";
			session.createQuery(hql).setString(0, admin.getAdminPassword())
					.setString(1, admin.getAdminPhone())
					.setString(2, adminname).executeUpdate(); // 修改admin表格的密码和电话
			String hql1 = "update ParkInfo p set p.adminPhone = ? where p.adminId = ?";
			session.createQuery(hql1).setString(0, admin.getAdminPhone())
					.setInteger(1, id).executeUpdate(); // 修改parkinfo的电话
			session.getTransaction().commit();
			flag = true;
			System.out.println("密码修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		} finally {
			session.close();
		}
		return flag;
	}

	/*
	 * 删除管理员
	 */
	public boolean deleteAdmin(Integer adminid) {
		boolean flag = false;
		System.out.println("adminid：" + adminid);// 为空
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Admin admin = (Admin) session.load(Admin.class, adminid);
			String Sql = "delete from lbs_order where admin_id = :adminid ;";
			session.createSQLQuery(Sql).setInteger("adminid", adminid)
					.executeUpdate();// 删除admin相关的订单
			session.delete(admin);// 删除admin
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
}
