package com.railway.management.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	public static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {
			try {

				Configuration cfg = new Configuration();
				cfg.configure("hibernate.cfg.xml");
				sessionFactory = cfg.buildSessionFactory();
			}

			catch (Exception e) {
				System.out.println("Something Went Wrong");
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
}
