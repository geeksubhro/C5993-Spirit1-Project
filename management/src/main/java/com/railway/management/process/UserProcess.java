package com.railway.management.process;

import com.railway.management.dao.PersonDAO;
import com.railway.management.dao.TrainDAO;
import com.railway.management.dao.UserDAO;
import com.railway.management.dao.Impl.PersonDAOImpl;
import com.railway.management.dao.Impl.TrainDAOImpl;
import com.railway.management.dao.Impl.UserDAOImpl;
import com.railway.management.entity.Person;
import com.railway.management.entity.Ticket;
import com.railway.management.entity.Train;
import com.railway.management.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserProcess {

	private static boolean isPersonExists(Session session, String userId, Long phone, String email) {
		Person existingPerson = (Person) session
				.createQuery("FROM Person WHERE userId = :userId OR phone = :phone OR email = :email")
				.setParameter("userId", userId).setParameter("phone", phone).setParameter("email", email)
				.uniqueResult();

		return existingPerson != null;
	}

	private static void savePerson(Session session, Person person) {
		PersonDAO personDAO = new PersonDAOImpl(session.getSessionFactory());
		personDAO.savePerson(person);
	}

	private static void saveUser(Session session, User user) {
		UserDAO userDAO = new UserDAOImpl(session.getSessionFactory());
		userDAO.saveUser(user);
	}

	public static void addUser(String name, String email, int age, String address, Long phone, String userId,
			String password, List<Ticket> tickets) {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

		try (SessionFactory factory = configuration.buildSessionFactory()) {
			try (Session session = factory.openSession()) {
				Person person = new Person();
				person.setName(name);
				person.setEmail(email);
				person.setAge(age);
				person.setAddress(address);
				person.setPhone(phone);
				person.setUserId(userId);
				person.setPassword(password);

				if (!isPersonExists(session, person.getUserId(), person.getPhone(), person.getEmail())) {
					Transaction transaction = session.beginTransaction();

					savePerson(session, person);

					User user = new User(person, tickets);
					saveUser(session, user);

					// Commit the transaction
					transaction.commit();
					System.out.println("User Created......");
				} else {
					System.out.println("Person with the same ID, phone, or email already exists. Skipping...");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    public static void addUser(String name, String email, int age, String address, Long phone, String userId,
            String password) {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

        try (SessionFactory factory = configuration.buildSessionFactory()) {
            try (Session session = factory.openSession()) {
                Person person = new Person();
                person.setName(name);
                person.setEmail(email);
                person.setAge(age);
                person.setAddress(address);
                person.setPhone(phone);
                person.setUserId(userId);
                person.setPassword(password);

                if (!isPersonExists(session, person.getUserId(), person.getPhone(), person.getEmail())) {
                    Transaction transaction = session.beginTransaction();

                    savePerson(session, person);

                    User user = new User(person, new ArrayList<>()); // Pass null or an empty list for tickets
                    saveUser(session, user);

                    // Commit the transaction
                    transaction.commit();
                } else {
                    System.out.println("Person with the same ID, phone, or email already exists. Skipping...");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	public static void showUsers() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		UserDAO userDAO = new UserDAOImpl(sessionFactory);

		List<User> users = userDAO.getAllUsers();

		if (users != null && !users.isEmpty()) {
			System.out.println("Printing all stored Users:");
			for (User user : users) {
				System.out.println(user);
			}
		} else {
			System.out.println("No Users found.");
		}
	}
	public static void updateUserDetails() {
	    showUsers();
	    Scanner scanner = new Scanner(System.in);
	    System.out.print("Enter the user_id you want to update: ");
	    int userId = scanner.nextInt();

	    Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

	    try (SessionFactory factory = configuration.buildSessionFactory()) {
	        try (Session session = factory.openSession()) {
	            UserDAO userDAO = new UserDAOImpl(session.getSessionFactory());
	            User user = userDAO.getUserById(userId);

	            if (user != null) {
	                System.out.println("User details for user_id " + userId + ":");
	                System.out.println(user);

	                System.out.println("Select the details you want to update:");
	                System.out.println("1. Person Details");

	                System.out.print("Enter the option number to update: ");
	                int option = scanner.nextInt();
	                scanner.nextLine(); // Consume the newline character

	                switch (option) {
	                    case 1:
	                        updatePersonDetails(user.getPerson().getId(), session);
	                        break;
	                    default:
	                        System.out.println("Invalid option. No changes made.");
	                        return;
	                }

	                Transaction transaction = session.beginTransaction();
	                userDAO.updateUser(user);
	                transaction.commit();

	                System.out.println("User details updated successfully.");
	            } else {
	                System.out.println("User not found for the provided user_id.");
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        scanner.close();
	    }
	}

	private static void updatePersonDetails(int personId, Session session) {
	    PersonDAO personDAO = new PersonDAOImpl(session.getSessionFactory());
	    Person person = personDAO.getPersonById(personId);

	    if (person != null) {
	        System.out.println("Person details for person_id " + personId + ":");
	        System.out.println(person);

	        System.out.println("Select the details you want to update:");
	        System.out.println("1. Name");
	        System.out.println("2. Email");
	        System.out.println("3. Age");
	        System.out.println("4. Address");
	        System.out.println("5. Phone");
	        System.out.println("6. User ID");
	        System.out.println("7. Password");

	        try (Scanner scanner = new Scanner(System.in)) {
	            System.out.print("Enter the option number to update: ");
	            int option = scanner.nextInt();
	            scanner.nextLine(); // Consume the newline character

	            switch (option) {
	                case 1:
	                    System.out.print("Enter new Name: ");
	                    String newName = scanner.nextLine();
	                    person.setName(newName);
	                    break;
	                case 2:
	                    System.out.print("Enter new Email: ");
	                    String newEmail = scanner.nextLine();
	                    person.setEmail(newEmail);
	                    break;
	                case 3:
	                    System.out.print("Enter new Age: ");
	                    int newAge = scanner.nextInt();
	                    person.setAge(newAge);
	                    break;
	                case 4:
	                    System.out.print("Enter new Address: ");
	                    String newAddress = scanner.nextLine();
	                    person.setAddress(newAddress);
	                    break;
	                case 5:
	                    System.out.print("Enter new Phone: ");
	                    Long newPhone = scanner.nextLong();
	                    person.setPhone(newPhone);
	                    break;
	                case 6:
	                    System.out.print("Enter new User ID: ");
	                    String newUserId = scanner.nextLine();
	                    person.setUserId(newUserId);
	                    break;
	                case 7:
	                    System.out.print("Enter new Password: ");
	                    String newPassword = scanner.nextLine();
	                    person.setPassword(newPassword);
	                    break;
	                default:
	                    System.out.println("Invalid option. No changes made.");
	                    return;
	            }
	        }
	        Transaction transaction = session.beginTransaction();
	        personDAO.updatePerson(person);
	        transaction.commit();

	        System.out.println("Person details updated successfully.");
	    } else {
	        System.out.println("Person not found for the provided person_id.");
	    }
	}

	public static User getUserById(int id) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		UserDAO userDAO = new UserDAOImpl(sessionFactory);
		User user= userDAO.getUserById(id);
		return user;
	}

	public static void changeUserPassword(User user, String newPassword) {
	    SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	    UserDAO userDAO = new UserDAOImpl(sessionFactory);
	    userDAO.changeUserPassword(user, newPassword);

	    System.out.println("User password changed successfully.");
	}

	public static void changeUserPassword(User user, Scanner scanner) {
		System.out.println("Enter new password: ");
		String newPassword = scanner.next();
		UserProcess.changeUserPassword(user, newPassword);
		System.out.println("Password changed successfully!");
	}


}
