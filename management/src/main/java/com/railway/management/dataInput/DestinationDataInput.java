package com.railway.management.dataInput;

import com.railway.management.entity.Train;
import com.railway.management.process.DestinationProcess;
import com.railway.management.process.InputProcess;
import com.railway.management.process.TrainProcess;

public class DestinationDataInput {

	public static void create() {
		InputProcess inputProcess = new InputProcess();

		System.out.println("Enter Destination Details:");
		TrainProcess.showTrains();
		String flag = inputProcess.getString("Do You want to Continue or Creat New Train?(yes/no)").toLowerCase();
		if (flag == "yes") {
			TrainDataInput.create();
			TrainProcess.showTrains();
		}

		int id = inputProcess.getInt("Enter Train ID:");
		Train train = TrainProcess.getTrainById(id);

		String startStation = inputProcess.getString("Start Station:  ");

		String endStation = inputProcess.getString("End Station: ");

		String startTime = inputProcess.getString("Start Time: ");

		String endTime = inputProcess.getString("End Time: ");

		String distance = inputProcess.getString("Distance: ");

		DestinationProcess.addDestination(train, startStation, endStation, startTime, endTime, distance);

	}
}
