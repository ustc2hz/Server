package cn.LBS.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/*
 * 实现对Hibernate的初始化 有效的管理了Session，避免了Session的多线程共享数据问题
 */
public class HibernateUtil {
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	private static SessionFactory sessionFactory = null;// SessionFactory对象
	// 加载Hibernate配置文件
	static {
		try {
			Configuration cfg = new Configuration().configure();
			ServiceRegistry sr = new StandardServiceRegistryBuilder()
					.applySettings(cfg.getProperties()).build();
			sessionFactory = cfg.buildSessionFactory(sr);
			// sessionFactory = cfg.buildSessionFactory();
		} catch (Exception e) {
			System.out.println("创建会话工厂失败");
			e.printStackTrace();
		}
	}

	// 获取session
	public static Session getSession() throws HibernateException {
		Session session = (Session) threadLocal.get();
		if (session == null || !session.isOpen()) {
			if (sessionFactory == null) {
				rebuildSessionFactory();
			}
			session = (sessionFactory != null) ? sessionFactory.openSession()
					: null;
			threadLocal.set(session);
		}
		return session;
	}

	/*
	 * 重建会话工厂
	 */
	public static void rebuildSessionFactory() {
		try {
			Configuration cfg = new Configuration().configure();
			ServiceRegistry sr = new StandardServiceRegistryBuilder()
					.applySettings(cfg.getProperties()).build();
			sessionFactory = cfg.buildSessionFactory(sr);
		} catch (Exception e) {
			System.out.println("创建会话工厂失败");
			e.printStackTrace();
		}
	}

	// 获取SessionFactory对象
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	// 关闭Session
	public static void closeSession() throws HibernateException {
		Session session = (Session) threadLocal.get();
		threadLocal.set(null);
		if (session != null) {
			session.close(); // 关闭
		}
	}
}
