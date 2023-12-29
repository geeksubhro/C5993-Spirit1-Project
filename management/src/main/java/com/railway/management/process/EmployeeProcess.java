package com.railway.management.process;

import com.railway.management.dao.EmployeeDAO;
import com.railway.management.dao.PersonDAO;
import com.railway.management.dao.Impl.EmployeeDAOImpl;
import com.railway.management.dao.Impl.PersonDAOImpl;
import com.railway.management.entity.Employee;
import com.railway.management.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class EmployeeProcess {

    private static boolean isPersonExists(Session session, String userId, Long phone, String email) {
        // Check if a person with the same ID, phone, email, or employee ID already exists
        Person existingPerson = (Person) session.createQuery(
                "FROM Person WHERE userId = :userId OR phone = :phone OR email = :email")
                .setParameter("userId", userId).setParameter("phone", phone).setParameter("email", email)
                .uniqueResult();
        System.out.println("Checking" + existingPerson);
        return existingPerson != null;
    }

    private static void savePerson(Session session, Person person) {
        PersonDAO personDAO = new PersonDAOImpl(session.getSessionFactory());
        personDAO.savePerson(person);
    }

    private static void saveEmployee(Session session, Employee employee) {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl(session.getSessionFactory());
        employeeDAO.saveEmployee(employee);
    }

    public static void addEmployee(String name, String email, int age, String address, Long phone, String userId,
                                   String password, int salary) {
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

                    Employee employee = new Employee(salary, person);
                    saveEmployee(session, employee);

                    transaction.commit();
                    System.out.println("Employee Created......");
                } else {
                    System.out.println("Person with the same ID, phone, or email already exists. Skipping...");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showEmployees() {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        EmployeeDAO employeeDAO = new EmployeeDAOImpl(sessionFactory);

        List<Employee> employees = employeeDAO.getAllEmployees();

        if (employees != null && !employees.isEmpty()) {
            System.out.println("Printing all stored Employees:");
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        } else {
            System.out.println("No Employees found.");
        }
    }

    public static void updateEmployeeDetails() {
        showEmployees();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the employee_id you want to update: ");
        int employeeId = scanner.nextInt();

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

        try (SessionFactory factory = configuration.buildSessionFactory()) {
            try (Session session = factory.openSession()) {
                EmployeeDAO employeeDAO = new EmployeeDAOImpl(session.getSessionFactory());
                Employee employee = employeeDAO.getEmployeeById(employeeId);

                if (employee != null) {
                    System.out.println("Employee details for employee_id " + employeeId + ":");
                    System.out.println(employee);

                    System.out.println("Select the details you want to update:");
                    System.out.println("1. Salary");
                    System.out.println("2. Person Details");

                    System.out.print("Enter the option number to update: ");
                    int option = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    switch (option) {
                        case 1:
                            System.out.print("Enter new Salary: ");
                            int newSalary = scanner.nextInt();
                            employee.setSalary(newSalary);
                            break;
                        case 2:
                            updatePersonDetails(employee.getPerson().getId(), session);
                            break;
                        default:
                            System.out.println("Invalid option. No changes made.");
                            return;
                    }

                    Transaction transaction = session.beginTransaction();
                    employeeDAO.updateEmployee(employee);
                    transaction.commit();

                    System.out.println("Employee details updated successfully.");
                } else {
                    System.out.println("Employee not found for the provided employee_id.");
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
}
