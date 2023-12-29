package com.railway.management;

import com.railway.management.process.AdminProcess;
import com.railway.management.process.DestinationProcess;
import com.railway.management.process.EmployeeProcess;
import com.railway.management.process.TicketProcess;
import com.railway.management.process.TrainProcess;
import com.railway.management.process.UserProcess;

public class TestDataShowAll {
	public static void show() {
		AdminProcess.showAdmin();
		EmployeeProcess.showEmployees();
		UserProcess.showUsers();
		TrainProcess.showTrains();
		DestinationProcess.showDestinations();
		TicketProcess.showTickets();
	}

}
