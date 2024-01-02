package com.railway.management.controller;

import com.railway.management.dataInput.DestinationDataInput;
import com.railway.management.dataInput.TicketDataInput;
import com.railway.management.dataInput.TrainDataInput;
import com.railway.management.entity.Employee;
import com.railway.management.process.DestinationProcess;
import com.railway.management.process.InputProcess;
import com.railway.management.process.TrainProcess;
import com.railway.management.authentication.EmployeeAuthentication;

public class Employee_Login {

    public static void menu() {
    	InputProcess inputProcess= new InputProcess();
        Employee employee = getEmployee();
        if (employee == null)
            return;

        int choice = 0;
        System.out.println("Welcome to the Train Management System!");

        while (choice != 7) {
            printMenuOptions();
            choice = getUserChoice();

            switch (choice) {
                case 1:
                    bookTicket();
                    break;
                case 2:
                    addNewTrain();
                    break;
                case 3:
                    editExistingTrain();
                    break;
                case 4:
                    addNewDestination();
                    break;
                case 5:
                    editExistingDestination();
                    break;
                case 6:
                    viewProfile(employee);
                    break;
                case 7:
                    System.out.println("Signing out. Goodbye!");
                    System.exit(0);
                    break;
                case 8:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose from 1 to 8.");
                    break;
            }
        }

    }

    private static Employee getEmployee(
) {
        InputProcess inputProcess = new InputProcess();
        String userId = inputProcess.getString("Enter Employee ID: ");
        String password = inputProcess.getString("Enter Password: ");

        Employee authenticatedEmployee = EmployeeAuthentication.authenticateEmployee(userId, password);

        return authenticatedEmployee;
    }

    private static void printMenuOptions() {
        System.out.println("Choose an option:");
        System.out.println("1) Book a Ticket for a user");
        System.out.println("2) Add New Train");
        System.out.println("3) Edit Existing Train");
        System.out.println("4) Add New Destination");
        System.out.println("5) Edit Existing Destination");
        System.out.println("6) View Profile");
        System.out.println("7) Sign Out");
        System.out.println("8) Exit");
    }

    private static int getUserChoice() {
        int choice = 0;
        boolean isValidInput = false;
        InputProcess inputProcess = new InputProcess();
        while (!isValidInput) {
            try {
                choice = (inputProcess.getInt("Enter a Number: "));
                isValidInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                printMenuOptions();
            }
        }

        return choice;
    }

    private static void bookTicket() {
        System.out.println("You chose Book a Ticket for a user.");
        TicketDataInput.bookTicket();
    }

    private static void addNewTrain() {
        System.out.println("You chose Add New Train.");
        TrainDataInput.create();
        TrainProcess.updateTrainDetails();
    }

    private static void editExistingTrain() {
        System.out.println("You chose Edit Existing Train.");
        TrainProcess.updateTrainDetails();
    }

    private static void addNewDestination() {
        System.out.println("You chose Add New Destination.");
        DestinationDataInput.create();
    }

    private static void editExistingDestination() {
        System.out.println("You chose Edit Existing Destination.");
        DestinationProcess.updateDestinationDetails();
    }

    private static void viewProfile(Employee employee) {
        System.out.println("You chose View Profile.");
        System.out.println(employee);
    }
}
