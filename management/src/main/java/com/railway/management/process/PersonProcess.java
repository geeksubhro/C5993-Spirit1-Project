package com.railway.management.process;

import com.railway.management.dao.Impl.PersonDAOImpl;
import com.railway.management.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class PersonProcess {

	public static void resetPassword(String username, String newPassword) {

		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		try (Session session = sessionFactory.openSession()) {
			PersonDAOImpl personDAO = new PersonDAOImpl(sessionFactory);

			// Step 1: Get the Person entity based on the provided username
			Person person = personDAO.getPersonByUsername(username);

			if (person != null) {
				// Step 2: Update the password
				person.setPassword(newPassword);

				// Step 3: Save the updated Person entity
				Transaction transaction = session.beginTransaction();
				personDAO.updatePerson(person);
				transaction.commit();

				System.out.println("Password reset successfully for user: " + username);
			} else {
				System.out.println("Person not found for the provided username: " + username);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// Example usage:
		resetPassword("exampleUsername", "newPassword");
	}
}
