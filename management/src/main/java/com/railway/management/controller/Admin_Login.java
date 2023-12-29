package com.railway.management.controller;

import java.util.Scanner;
import com.railway.management.dataInput.AdminDataInput;
import com.railway.management.dataInput.DestinationDataInput;
import com.railway.management.dataInput.EmployeeDataInput;
import com.railway.management.dataInput.TrainDataInput;
import com.railway.management.entity.Admin;
import com.railway.management.process.AdminProcess;
import com.railway.management.process.DestinationProcess;
import com.railway.management.process.EmployeeProcess;
import com.railway.management.process.TrainProcess;
import com.railway.management.authentication.AdminAuthentication;

public class Admin_Login {
    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        Admin admin = getAdmin(scanner);
        if (admin == null)
            return;
        int choice = 0;
        System.out.println("Welcome to the Train Management System!");
        while (choice != 11) {
            System.out.println("Choose an option:");
            System.out.println("1) Add New Train");
            System.out.println("2) Edit Existing Train");
            System.out.println("3) Add New Destination");
            System.out.println("4) Edit Existing Destination");
            System.out.println("5) Add New Admin");
            System.out.println("6) Edit Existing Admin");
            System.out.println("7) Add New Employee");
            System.out.println("8) Edit Existing Employee");
            System.out.println("9) View My Profile");
            System.out.println("10) LogOut");
            System.out.println("11) Exit");

            try {
                try {
                    choice = scanner.nextInt();
                } catch (java.util.NoSuchElementException e) {
                    handleInputMismatch(scanner);
                    continue;
                }
                switch (choice) {
                    case 1:
                        System.out.println("You chose Add New Train.");
                        TrainDataInput.create();
                        break;
                    case 2:
                        System.out.println("You chose Edit Existing Train.");
                        TrainProcess.updateTrainDetails();
                        break;
                    case 3:
                        System.out.println("You chose Add New Destination.");
                        DestinationDataInput.create();
                        break;
                    case 4:
                        System.out.println("You chose Edit Existing Destination.");
                        DestinationProcess.updateDestinationDetails();
                        break;
                    case 5:
                        System.out.println("You chose Add New Admin.");
                        AdminDataInput.create();
                        break;
                    case 6:
                        System.out.println("You chose Edit Existing Admin.");
                        AdminProcess.updateAdminDetails();
                        break;
                    case 7:
                        System.out.println("You chose Add New Employee.");
                        EmployeeDataInput.create();
                        break;
                    case 8:
                        System.out.println("You chose Edit Existing Employee.");
                        EmployeeProcess.updateEmployeeDetails();
                        break;
                    case 9:
                        System.out.println("You chose View My Profile.");
                        System.out.println(admin);
                        break;
                    case 10:
                        System.out.println("Logging out. Goodbye!");
                        System.exit(0);
                        break;
                    case 11:
                        System.out.println("Exiting the program. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose from 1 to 11.");
                        break;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                // Consume the invalid input
                scanner.nextLine();
            }
            catch (java.util.NoSuchElementException e) {
                handleInputMismatch(scanner);
                continue;
            }

        }

        scanner.close();
    }

    private static void handleInputMismatch(Scanner scanner) {
        System.out.println("Input mismatch. Please try logging in again.");
        scanner.nextLine(); // Consume the newline character
    }

    private static Admin getAdmin(Scanner scanner) {
        System.out.println("Enter Admin ID: ");
        String userId = scanner.nextLine();
        System.out.println("Enter Password: ");
        String password = scanner.nextLine();

        Admin authenticatedAdmin = AdminAuthentication.authenticateAdmin(userId, password);

        return authenticatedAdmin;
    }
}
