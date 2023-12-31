package com.railway.management.controller;

import com.railway.management.authentication.UserAuthentication;
import com.railway.management.entity.User;
import com.railway.management.process.InputProcess;
import com.railway.management.process.TicketProcess;
import com.railway.management.process.UserProcess;

public class User_Login {
	final static    InputProcess inputProcess = new InputProcess();
    public static void menu() {
        User user = getUser();
        if (user == null)
            return;
        int choice = 0;

        System.out.println("Welcome to the Train Management System, " + user.getName() + "!");
        while (choice != 5) {
            printMenuOptions();
            choice = inputProcess.getInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    viewProfile(user);
                    break;
                case 2:
                    viewBookedTickets(user);
                    break;
                case 3:
                    changePassword(user);
                    break;
                case 4:
                    System.out.println("Logging out. Goodbye!");
                    return;
                case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose from 1 to 5.");
                    break;
            }
        }
    }

    private static User getUser() {
        String userId = inputProcess.getString("Enter User ID: ");
        String password = inputProcess.getString("Enter Password: ");

        User authenticatedUser = UserAuthentication.authenticateUser(userId, password);

        return authenticatedUser;
    }

    private static void printMenuOptions() {
        System.out.println("Choose an option:");
        System.out.println("1) View My Profile");
        System.out.println("2) Booked Tickets");
        System.out.println("3) Change Password");
        System.out.println("4) Logout");
        System.out.println("5) Exit");
    }

    private static void viewProfile(User user) {
        System.out.println("You chose View My Profile.");
        System.out.println(user);
    }

    private static void viewBookedTickets(User user) {
        System.out.println("You chose Booked Tickets:");
        TicketProcess.getBookedTicketsByUser(user).forEach(System.out::println);
    }

    private static void changePassword(User user) {
        System.out.println("You chose Change Password.");
        UserProcess.changeUserPassword(user);
    }
}
