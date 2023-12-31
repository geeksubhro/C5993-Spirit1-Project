package com.railway.management.process;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.railway.management.dao.PersonDAO;
import com.railway.management.dao.Impl.PersonDAOImpl;

public class MailProcess {
	
	public static String getEmailByUsername(String username) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		PersonDAO p = new PersonDAOImpl(sessionFactory);
		String email;
		try {
			email = p.getEmailByUsername(username);
			return email;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
}
