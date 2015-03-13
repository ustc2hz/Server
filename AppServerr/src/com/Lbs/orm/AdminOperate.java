package com.Lbs.orm;

import java.util.Iterator;

import org.hibernate.Session;

import com.Lbs.model.Admin;
import com.Lbs.util.HibernateUtil;

public class AdminOperate {

	private Session session;

	/*
	 * 增加管理员
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
			// TODO: handle exception
			e.printStackTrace();
			flag = false;
		} finally {
			session.close();
		}
		return flag;
	}

	/*
	 * 查找管理员(登陆时)
	 */
	public Admin findAdmin(Admin admin) {
		Admin a = new Admin();
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
			// TODO: handle exception
			e.printStackTrace();
			admin = null;
		} finally {
			session.close();
		}
		return admin;
	}

	/*
	 * 查找管理员(根据管理员name来查找，修改密码时)
	 */
	public Admin findAdminByAdName(Admin admin) {
		Admin a = new Admin();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			String hql = "from Admin admin where admin.adminName = ?";
			Iterator admins = session.createQuery(hql)
					.setString(0, admin.getAdminName()).iterate();
			if (admins.hasNext()) {
				a = (Admin) admins.next();
			} else {
				a = null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			a = null;
		} finally {
			session.close();
		}
		return a;
	}

	/*
	 * 修改管理员信息
	 */
	public boolean updateAdmin(Admin admin) {
		boolean flag = false;
		Admin a = new Admin();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			a = findAdminByAdName(admin);
			if (a != null) {// 查找的管理员信息存在
				a.setAdminPassword(admin.getAdminPassword());
			} else {// 查找的管理员不存在
				flag = false;
				System.out.println("查找的管理员不存在");
			}
			session.update(a);
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
	 * 删除管理员
	 */
	public boolean deleteAdmin(Admin admin) {
		boolean flag = false;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(admin);
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
