package com.railway.management.dataInput;

import com.railway.management.entity.Train;
import com.railway.management.process.DestinationProcess;
import com.railway.management.process.TrainProcess;

import java.util.Scanner;

public class DestinationDataInput {

	public static void create() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter Destination Details:");
		TrainProcess.showTrains();
		System.out.println("Do You want to Continue or Creat New Train?(yes/no)");
		String flag = scanner.next().toLowerCase();
		if (flag == "yes") {
			TrainDataInput.create();
			TrainProcess.showTrains();
		}
		System.out.println("Enter Train ID:");
		int id= scanner.nextInt();
		Train train = TrainProcess.getTrainById(id);
		System.out.print("Start Station: ");
		String startStation = scanner.next();

		System.out.print("End Station: ");
		String endStation = scanner.next();

		System.out.print("Start Time: ");
		String startTime = scanner.next();

		System.out.print("End Time: ");
		String endTime = scanner.next();
		scanner.next(); // Consume the newline character

		System.out.print("Distance: ");
		String distance = scanner.next();

		// Call the details method with user-input values
		DestinationProcess.addDestination(train, startStation, endStation, startTime, endTime, distance);

		scanner.close();
	}
}
