package com.railway.management.controller;

import java.util.Scanner;

import com.railway.management.authentication.UserAuthentication;
import com.railway.management.entity.User;
import com.railway.management.process.UserProcess;
import com.railway.management.process.TicketProcess;

public class User_Login {

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        User user = getUser(scanner);
        if (user == null)
            return;
        int choice = 0;

        System.out.println("Welcome to the Train Management System, " + user.getName() + "!");
        while (choice != 5) {
            printMenuOptions();
            choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    viewProfile(user);
                    break;
                case 2:
                    viewBookedTickets(user);
                    break;
                case 3:
                    changePassword(user, scanner);
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

        scanner.close();
    }

    private static User getUser(Scanner scanner) {
        System.out.println("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.println("Enter Password: ");
        String password = scanner.nextLine();

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

    private static int getUserChoice(Scanner scanner) {
        int choice = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                isValidInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                printMenuOptions();
            }
        }

        return choice;
    }

    private static void viewProfile(User user) {
        System.out.println("You chose View My Profile.");
        System.out.println(user);
    }

    private static void viewBookedTickets(User user) {
        System.out.println("You chose Booked Tickets:");
        TicketProcess.getBookedTicketsByUser(user).forEach(System.out::println);
    }

    private static void changePassword(User user, Scanner scanner) {
        System.out.println("You chose Change Password.");
        UserProcess.changeUserPassword(user, scanner);
    }
}
