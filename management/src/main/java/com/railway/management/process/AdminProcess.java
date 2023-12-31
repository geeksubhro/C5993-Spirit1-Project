package com.railway.management.process;

import com.railway.management.dao.AdminDAO;
import com.railway.management.dao.PersonDAO;
import com.railway.management.dao.Impl.AdminDAOImpl;
import com.railway.management.dao.Impl.PersonDAOImpl;
import com.railway.management.entity.Admin;
import com.railway.management.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class AdminProcess {

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

    private static void saveAdmin(Session session, Admin admin) {
        AdminDAO adminDAO = new AdminDAOImpl(session.getSessionFactory());
        adminDAO.saveAdmin(admin);
    }

    public static void addAdmin(String name, String email, int age, String address, Long phone, String userId,
                                String password, String adminPrivilege) {
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

                    Admin admin = new Admin();
                    admin.setPerson(person);
                    admin.setAdminPrivilege(adminPrivilege);
                    saveAdmin(session, admin);

                    transaction.commit();
                    System.out.println("Admin Created......");
                } else {
                    System.out.println("Person with the same ID, phone, or email already exists. Skipping...");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAdmin() {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        AdminDAO adminDAO = new AdminDAOImpl(sessionFactory);

        List<Admin> admins = adminDAO.getAllAdmins();

        if (admins != null && !admins.isEmpty()) {
            System.out.println("Printing all stored Admins:");
            for (Admin admin : admins) {
                System.out.println(admin);
            }
        } else {
            System.out.println("No Admins found.");
        }
    }

    public static void updateAdminDetails() {
    	showAdmin();
    	InputProcess inputProcess =new InputProcess();
        int adminId = inputProcess.getInt("Enter the admin_id you want to update: ");
        
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

        try (SessionFactory factory = configuration.buildSessionFactory()) {
            try (Session session = factory.openSession()) {
                AdminDAO adminDAO = new AdminDAOImpl(session.getSessionFactory());
                Admin admin = adminDAO.getAdminById(adminId);

                if (admin != null) {
                    System.out.println("Admin details for admin_id " + adminId + ":");
                    System.out.println(admin);

                    System.out.println("Select the details you want to update:");
                    System.out.println("1. Admin Privilege");
                    System.out.println("2. Person Details");

                    System.out.print("Enter the option number to update: ");
                    int option = inputProcess.getInt(" ");

                    switch (option) {
                        case 1:
                            System.out.print("Enter new Admin Privilege: ");
                            String newPrivilege = inputProcess.getString(" ");
                            admin.setAdminPrivilege(newPrivilege);
                            break;
                        case 2:
                            updatePersonDetails(admin.getPerson().getId(), session);
                            break;
                        default:
                            System.out.println("Invalid option. No changes made.");
                            return;
                    }

                    Transaction transaction = session.beginTransaction();
                    adminDAO.updateAdmin(admin);
                    transaction.commit();

                    System.out.println("Admin details updated successfully.");
                } else {
                    System.out.println("Admin not found for the provided admin_id.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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

            try {
				InputProcess inputProcess= new InputProcess();
				int option = inputProcess.getInt("Enter the option number to update:  ");

				switch (option) {
				    case 1:
				        String newName = inputProcess.getString("Enter new Name:  ");
				        person.setName(newName);
				        break;
				    case 2:
				        String newEmail =  inputProcess.getString("Enter new Email:  ");
				        person.setEmail(newEmail);
				        break;
				    case 3:
				        int newAge =  inputProcess.getInt("Enter new Age:  ");
				        person.setAge(newAge);
				        break;
				    case 4:
				        String newAddress =  inputProcess.getString("Enter new Address:  ");
				        person.setAddress(newAddress);
				        break;
				    case 5:
				        Long newPhone =  inputProcess.getLong("Enter new Phone:  ");
				        person.setPhone(newPhone);
				        break;
				    case 6:
				        String newUserId =  inputProcess.getString("Enter new User ID:  ");
				        person.setUserId(newUserId);
				        break;
				    case 7:
				        String newPassword =  inputProcess.getString("Enter new Password:  ");
				        person.setPassword(newPassword);
				        break;
				    default:
				        System.out.println("Invalid option. No changes made.");
				        return;
				}
			}
            catch (Exception e) {
            	e.printStackTrace();
            }
            Transaction transaction = session.beginTransaction();
            personDAO.updatePerson(person);
            transaction.commit();

            System.out.println("Person details updated successfully.");
        } else {
            System.out.println("Person not found for the provided person_id.");
        }
    }
}
