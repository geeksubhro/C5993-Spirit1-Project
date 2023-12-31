package com.railway.management.dataInput;

import java.util.Scanner;

import com.railway.management.process.InputProcess;
import com.railway.management.process.TrainProcess;

public class TrainDataInput {

	public static void create() {
		InputProcess inputProcess = new InputProcess();

		System.out.println("Enter Train Details:");

		int trainId = inputProcess.getInt("Train ID: ");

		String trainName = inputProcess.getString("Train Name: ");

		int speed = inputProcess.getInt("Speed: ");

		int yearOfStart = inputProcess.getInt("Year of Start: ");

		int capacity = inputProcess.getInt("Capacity: ");

		// Call the addTrain method with user-input values
		TrainProcess.addTrain(trainId, trainName, speed, yearOfStart, capacity);

	}
}
