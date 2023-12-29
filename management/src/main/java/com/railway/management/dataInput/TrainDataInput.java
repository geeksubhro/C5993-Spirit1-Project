package com.railway.management.dataInput;

import java.util.Scanner;

import com.railway.management.process.TrainProcess;

public class TrainDataInput {

	public static void create() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter Train Details:");

		System.out.print("Train ID: ");
		int trainId = scanner.nextInt();
		scanner.nextLine(); // Consume the newline character

		System.out.print("Train Name: ");
		String trainName = scanner.nextLine();

		System.out.print("Speed: ");
		int speed = scanner.nextInt();
		scanner.nextLine(); // Consume the newline character

		System.out.print("Year of Start: ");
		int yearOfStart = scanner.nextInt();
		scanner.nextLine(); // Consume the newline character

		System.out.print("Capacity: ");
		int capacity = scanner.nextInt();
		scanner.nextLine(); // Consume the newline character

		// Call the addTrain method with user-input values
		TrainProcess.addTrain(trainId, trainName, speed, yearOfStart, capacity);

		scanner.close();
	}
}
