package com.railway.management.controller;
import java.util.*;

public class ResetPassword {
	public static void menu() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.next();

        if (isValidUsername(username)) {
            System.out.println("Username is valid. Reset options:");
            System.out.println("1. Reset Password via Email");
            System.out.println("2. Reset Password via SMS");
            System.out.println("3. Answer Security Questions");

            int resetOption = scanner.nextInt();

            switch (resetOption) {
                case 1:
                    System.out.println("Resetting password via email...");
                    // Add email reset logic here
                    break;
                case 2:
                    System.out.println("Resetting password via SMS...");
                    // Add SMS reset logic here
                    break;
                case 3:
                    System.out.println("Answering security questions...");
                    // Add security question reset logic here
                    break;
                default:
                    System.out.println("Invalid reset option");
            }
        } else {
            System.out.println("Invalid username. Password reset failed.");
        }

        scanner.close();
    }

    private static boolean isValidUsername(String username) {
        
        return username.equals("validUser");
    }

}