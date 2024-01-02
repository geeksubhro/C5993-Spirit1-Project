package com.railway.management;

import org.hibernate.SessionFactory;
import com.railway.management.config.HibernateUtil;
import com.railway.management.controller.UserChoice;
import com.railway.management.dataInput.TicketDataInput;
import com.railway.management.exceptions.NeedNewMenuException;

public class App {
	public static void main(String[] args) throws NeedNewMenuException {
		createSession();
//		TestDataInput.AddData();
//		TestDataShowAll.show();
//			UserChoice.menu();
			TicketDataInput.bookTicket();

	}

	public static void createSession() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		if (sessionFactory != null) {
			System.out.println("App Started Succesfully\n\n");
		} else {
			System.out.println("Something Went Wrong");

		}

	}
}
