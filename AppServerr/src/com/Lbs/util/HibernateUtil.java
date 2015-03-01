package com.Lbs.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * ʵ�ֶ�Hibernate�ĳ�ʼ�� ��Ч�Ĺ�����Session��������Session�Ķ��̹߳����������
 */
public class HibernateUtil {
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	private static SessionFactory sessionFactory = null; // SessionFactory����
	// ����Hibernate�����ļ�
	static {
		try {
			Configuration cfg = new Configuration().configure();
			ServiceRegistry sr = new StandardServiceRegistryBuilder()
					.applySettings(cfg.getProperties()).build();
			sessionFactory = cfg.buildSessionFactory(sr);
			// sessionFactory = cfg.buildSessionFactory();
		} catch (Exception e) {
			System.out.println("�����Ự����ʧ��");
			e.printStackTrace();
		}
	}

	// ��ȡsession
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

	/**
	 * �ؽ��Ự����
	 */
	public static void rebuildSessionFactory() {
		try {
			Configuration cfg = new Configuration().configure();
			ServiceRegistry sr = new StandardServiceRegistryBuilder()
					.applySettings(cfg.getProperties()).build();
			sessionFactory = cfg.buildSessionFactory(sr);
			// sessionFactory = cfg.buildSessionFactory();
		} catch (Exception e) {
			System.out.println("�����Ự����ʧ��");
			e.printStackTrace();
		}
	}

	// ��ȡSessionFactory����
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	// �ر�Session
	public static void closeSession() throws HibernateException {
		Session session = (Session) threadLocal.get();
		threadLocal.set(null);
		if (session != null) {
			session.close(); // �ر�
		}
	}

}
