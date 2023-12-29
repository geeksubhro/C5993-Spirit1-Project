package com.railway.management;

import org.hibernate.SessionFactory;
import com.railway.management.config.HibernateUtil;
import com.railway.management.controller.UserChoice;

public class App {
	public static void main(String[] args) {
		createSession();
//		TestDataInput.AddData();
//		TestDataShowAll.show();
		UserChoice.menu();
	}

	public static void createSession() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		if (sessionFactory != null) {
			System.out.println("SessionFactory Initiallized");
		} else {
			System.out.println("Something Went Wrong");

		}

	}
}
