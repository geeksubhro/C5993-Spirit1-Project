package com.railway.management.authentication;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.railway.management.entity.User;
import com.railway.management.entity.Person;

public class UserAuthentication {

    private static boolean isPersonExists(Session session, String userId, String password) {
        // Check if a person with the given ID and password exists
        Person existingPerson = (Person) session
                .createQuery("FROM Person WHERE userId = :userId AND password = :password")
                .setParameter("userId", userId).setParameter("password", password).uniqueResult();

        return existingPerson != null;
    }

    public static User authenticateUser(String userId, String password) {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

        try (SessionFactory factory = configuration.buildSessionFactory()) {
            try (Session session = factory.openSession()) {
                // Check if a person with the given ID and password exists
                if (isPersonExists(session, userId, password)) {
                    // Check if the person with the given ID is a user
                    User user = (User) session.createQuery("FROM User WHERE person.userId = :userId")
                            .setParameter("userId", userId).uniqueResult();

                    if (user != null) {
                        return user; // Person is a user
                    } else {
                        System.out.println("Person is not a user.");
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
