package com.railway.management.dataInput;

import com.railway.management.entity.Destination;
import com.railway.management.entity.User;
import com.railway.management.process.DestinationProcess;
import com.railway.management.process.TicketProcess;
import com.railway.management.process.UserProcess;

import java.util.Scanner;

public class TicketDataInput {

    public static void bookTicket() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Ticket Details:");
        UserProcess.showUsers();
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        User user = UserProcess.getUserById(userId);

        DestinationProcess.showDestinations();
        System.out.print("Enter Destination ID: ");
        int destinationId = scanner.nextInt();
        Destination destination = DestinationProcess.getDestinationById(destinationId);

        System.out.print("Starting Station: ");
        String startingStation = scanner.next();

        System.out.print("End Station: ");
        String endStation = scanner.next();

        System.out.print("Time of Booking: ");
        String timeOfBooking = scanner.next();

        System.out.print("Payment Mode: ");
        String paymentMode = scanner.next();

        System.out.print("Cost: ");
        int cost = scanner.nextInt();

        TicketProcess.bookTicket(user, destination, startingStation, endStation, timeOfBooking, paymentMode, cost);

        scanner.close();
    }
}
