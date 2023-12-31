package com.railway.management.process;

import java.util.Scanner;

public class InputProcess {

    private Scanner scanner;

    public InputProcess() {
        this.scanner = new Scanner(System.in);
    }

    public int getInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    public double getDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.nextDouble();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid double.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    public long getLong(String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.nextLong();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid long.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    public String getString(String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.next();
                
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid long.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    // You can add more methods for other types if needed

    public void closeScanner() {
        scanner.close();
    }
}
