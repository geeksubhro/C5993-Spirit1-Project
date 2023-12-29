package com.railway.management.dataInput;

import java.util.Scanner;

import com.railway.management.process.UserProcess;


public class UserDataInput {

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


        // Call the details method with user-input values
        UserProcess.addUser(name, email, age, address, phone,  userId,
                password);
        scanner.close();
    }
}
