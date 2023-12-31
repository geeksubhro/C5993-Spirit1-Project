package com.railway.management.controller;

import com.railway.management.dataInput.UserDataInput;
import com.railway.management.exceptions.NeedNewMenuException;
import com.railway.management.process.InputProcess;

public class UserChoice {

    public static void menu() throws NeedNewMenuException {
        int choice = 0;
        InputProcess inputProcess = new InputProcess();

        try {
			while (choice != 6) {
				System.out.println("Welcome User!! \nIn our App!!\nRailway Management System");
			    printMenuOptions();

			    try {
			        choice = inputProcess.getInt("Enter your choice: ");
			    } catch (NumberFormatException e) {
			        System.out.println("Invalid choice. Please choose from 1 to 6.");
			        continue;
			    }

			    switch (choice) {
			        case 1:
			            System.out.println("You chose User Login.");
			            User_Login.menu();
			            break;
			        case 2:
			            System.out.println("You chose User SignUp.");
			            UserDataInput.create();
			            break;
			        case 3:
			            System.out.println("You chose Admin SignIn.");
			            Admin_Login.menu();
			            break;
			        case 4:
			            System.out.println("You chose Employee Login.");
			            Employee_Login.menu();
			            break;
			        case 5:
			            System.out.println("You chose Reset Password for Users.");
			            resetPassword();
			            break;
			        case 6:
			            System.out.println("Exiting the program. Goodbye!");
			            break;
			        default:
			            System.out.println("Invalid choice. Please choose from 1 to 6.");
			            break;
			    }
			}
		} catch (Exception e) {
			 System.out.println("Unknown Error");
			    throw new NeedNewMenuException("Just Retry Again");
		}
    }

    private static void printMenuOptions() {
        System.out.println("Choose an option:");
        System.out.println("1) User Login");
        System.out.println("2) User SignUp");
        System.out.println("3) Admin SignIn");
        System.out.println("4) Employee Login");
        System.out.println("5) Reset Password Your Password");
        System.out.println("6) Exit");
    }

    private static void resetPassword() {
        // Implement reset password logic here
        System.out.println("Resetting password...");
        ResetPassword.menu();
    }
}
