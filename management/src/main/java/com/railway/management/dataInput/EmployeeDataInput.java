package com.railway.management.dataInput;

import java.util.Scanner;

import com.railway.management.process.EmployeeProcess;


public class EmployeeDataInput {

    public static void create() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Person Details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Address: ");
        String address = scanner.nextLine();

        System.out.print("Phone: ");
        Long phone = scanner.nextLong();
        scanner.nextLine(); // Consume the newline character

        System.out.print("User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.println("Enter Employee Details:");

        System.out.print("Salary: ");
        int salary = scanner.nextInt();

        // Call the details method with user-input values
        EmployeeProcess.addEmployee(name, email, age, address, phone, userId, password, salary);

        scanner.close();
    }
}
