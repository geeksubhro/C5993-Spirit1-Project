package com.railway.management.dataInput;

import com.railway.management.entity.Destination;
import com.railway.management.entity.User;
import com.railway.management.process.DestinationProcess;
import com.railway.management.process.InputProcess;
import com.railway.management.process.TicketProcess;
import com.railway.management.process.UserProcess;

public class TicketDataInput {

	public static void bookTicket() {
		InputProcess inputProcess = new InputProcess();

		System.out.println("Enter Ticket Details:");
		UserProcess.showUsers();
		int userId = inputProcess.getInt("Enter User ID: ");
		User user = UserProcess.getUserById(userId);

		DestinationProcess.showDestinations();
		int destinationId = inputProcess.getInt("Enter Destination ID: ");
		Destination destination = DestinationProcess.getDestinationById(destinationId);

		String startingStation = inputProcess.getString("Starting Station: ");

		String endStation = inputProcess.getString("End Station: ");

		String timeOfBooking = inputProcess.getString("Time of Booking: ");

		String paymentMode = inputProcess.getString("Payment Mode: ");

		int cost = inputProcess.getInt("Cost: ");

		TicketProcess.bookTicket(user, destination, startingStation, endStation, timeOfBooking, paymentMode, cost);

	}
}
