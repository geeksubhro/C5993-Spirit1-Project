package com.railway.management.dataInput;

import java.util.Scanner;
import com.railway.management.process.AdminProcess;

public class AdminDataInput {

    public static void create() {
        try (Scanner scanner = new Scanner(System.in)) {
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
            String privillage = null;

            System.out.println("Enter Admin Privilege:\n(1 for Full Access, 2 for Limited Access and 3 for Readonly)");
            int privil;
            do {
                privil = scanner.nextInt();
                if (privil == 1) {
                    privillage = "Full Access";
                    break;
                } else if (privil == 2) {
                    privillage = "Limited Access";
                    break;
                } else if (privil == 3) {
                    privillage = "Read Only";
                    break;
                }
            } while (privil > 3 || privil < 1);

            // Call the details method with user-input values
            AdminProcess.addAdmin(name, email, age, address, phone, userId, password, privillage);
        }
    }
}
