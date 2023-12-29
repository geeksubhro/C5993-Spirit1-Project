package com.railway.management.authentication;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.railway.management.entity.Employee;
import com.railway.management.entity.Person;

public class EmployeeAuthentication {

    private static boolean isPersonExists(Session session, String userId, String password) {
        // Check if a person with the given ID and password exists
        Person existingPerson = (Person) session
                .createQuery("FROM Person WHERE userId = :userId AND password = :password")
                .setParameter("userId", userId).setParameter("password", password).uniqueResult();

        return existingPerson != null;
    }

    public static Employee authenticateEmployee(String userId, String password) {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

        try (SessionFactory factory = configuration.buildSessionFactory()) {
            try (Session session = factory.openSession()) {
                // Check if a person with the given ID and password exists
                if (isPersonExists(session, userId, password)) {
                    // Check if the person with the given ID is an employee
                    Employee employee = (Employee) session.createQuery("FROM Employee WHERE person.userId = :userId")
                            .setParameter("userId", userId).uniqueResult();

                    if (employee != null) {
                        return employee; // Person is an employee
                    } else {
                        System.out.println("Person is not an employee.");
                        return null;
                    }
                } else {
                    System.out.println("Invalid credentials. Person does not exist.");
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
