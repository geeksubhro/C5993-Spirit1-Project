package com.railway.management;

import com.railway.management.entity.Destination;
import com.railway.management.entity.Train;
import com.railway.management.entity.User;
import com.railway.management.process.AdminProcess;
import com.railway.management.process.DestinationProcess;
import com.railway.management.process.EmployeeProcess;
import com.railway.management.process.TicketProcess;
import com.railway.management.process.TrainProcess;
import com.railway.management.process.UserProcess;

public class TestDataInput {

	public static void AddData() {
		createAdmins();
		createEmployees();
		createUsers();
		createTrains();
		createDestinations();
		createTickets();
	}

	private static void createAdmins() {
		AdminProcess.addAdmin("Admin1", "admin1@example.com", 21, "Admin Kolkata", 32232323L, "Admin1", "rerewwreewre",
				"Limited Access");
		AdminProcess.addAdmin("Admin2", "admin2@example.com", 35, "Admin Town", 987654324310L, "admin2", "adminpass2",
				"Limited Access");
		AdminProcess.addAdmin("Admin3", "admin3@example.com", 28, "Admin Street", 555123443567L, "admin3", "adminpass3",
				"Read Only");
		AdminProcess.addAdmin("Admin4", "admin4@example.com", 40, "Admin Avenue", 999888327777L, "admin4", "adminpass4",
				"Limited Access");
		AdminProcess.addAdmin("Admin5", "admin5@example.com", 25, "Admin Lane", 32323323232L, "admin5", "adminpass5",
				"Full Access");
	}

	private static void createUsers() {

		UserProcess.addUser("User1", "user1@example.com", 25, "User Street 1", 1111111111L, "user1", "userpass1");
		UserProcess.addUser("User2", "user2@example.com", 30, "User Street 2", 2222222222L, "user2", "userpass2");
		UserProcess.addUser("User3", "user3@example.com", 35, "User Street 3", 3333333333L, "user3", "userpass3");
		UserProcess.addUser("User4", "user4@example.com", 40, "User Street 4", 4444444444L, "user4", "userpass4");
		UserProcess.addUser("User5", "user5@example.com", 45, "User Street 5", 5555555555L, "user5", "userpass5");
	}

	private static void createEmployees() {

		// Example 1
		EmployeeProcess.addEmployee("John Smith", "john.smith@example.com", 25, "123 Main St", 1234567890L, "Emp001",
				"password123", 60000);
		// Example 2
		EmployeeProcess.addEmployee("Jane Doe", "jane.doe@example.com", 30, "456 Oak St", 9876543210L, "Emp002",
				"password456", 70000);
		// Example 3
		EmployeeProcess.addEmployee("Bob Johnson", "bob.johnson@example.com", 22, "789 Pine St", 5551234567L, "Emp003",
				"password789", 80000);
		// Example 4
		EmployeeProcess.addEmployee("Alice Brown", "alice.brown@example.com", 28, "567 Elm St", 9876543230L, "Emp004",
				"password789", 55000);
		// Example 5
		EmployeeProcess.addEmployee("Charlie Green", "charlie.green@example.com", 35, "890 Maple St", 5559876543L,
				"Emp005", "password123", 60000);
		// Example 6
		EmployeeProcess.addEmployee("Subhrojeet Dutta", "subhrojeet@gmail.com", 18, "Bhowanipur Kolkata", 7596813096L,
				"Emp006", "Pass123", 50000);
		// Example 7
		EmployeeProcess.addEmployee("Subhrojeet sss", "subhrojeet2@gmail.com", 18, "Bhowanipur Kolkata", 759681223096L,
				"Emp006", "Pas3s123", 5000032);
	}

	private static void createTrains() {
		// Example 1
		TrainProcess.addTrain(1, "Express001", 120, 2022, 300);
		// Example 2
		TrainProcess.addTrain(2, "Local002", 80, 2020, 150);
		// Example 3
		TrainProcess.addTrain(3, "SuperFast003", 150, 2021, 250);
		// Example 4
		TrainProcess.addTrain(4, "Metro004", 100, 2023, 200);
		// Example 5
		TrainProcess.addTrain(5, "Shatabdi005", 200, 2019, 180);
		// Example 6
		TrainProcess.addTrain(6, "Duronto006", 180, 2020, 220);

	}

	private static void createDestinations() {
		// Example 1
		Train train1 = TrainProcess.getTrainById(1); // Assuming a method like getTrainById exists
		DestinationProcess.addDestination(train1, "StationA", "StationB", "08:00", "10:00", "100 km");

		// Example 2
		Train train2 = TrainProcess.getTrainById(2);
		DestinationProcess.addDestination(train2, "StationB", "StationC", "12:00", "14:00", "150 km");

		// Example 3
		Train train3 = TrainProcess.getTrainById(3);
		DestinationProcess.addDestination(train3, "StationC", "StationD", "15:30", "18:00", "200 km");

		// Example 4
		Train train4 = TrainProcess.getTrainById(4);
		DestinationProcess.addDestination(train4, "StationD", "StationE", "09:30", "11:30", "120 km");

		// Example 5
		Train train5 = TrainProcess.getTrainById(5);
		DestinationProcess.addDestination(train5, "StationE", "StationF", "14:45", "16:30", "180 km");

		// Example 6
		Train train6 = TrainProcess.getTrainById(6);
		DestinationProcess.addDestination(train6, "StationF", "StationG", "17:00", "19:00", "160 km");
	}

	private static void createTickets() {
		// Example 1
		User user1 = UserProcess.getUserById(1); // Assuming a method like getUserById exists
		Destination destination1 = DestinationProcess.getDestinationById(1); // Assuming a method like
																				// getDestinationById exists
		TicketProcess.bookTicket(user1, destination1, "StationA", "StationB", "2023-12-28 15:00:00", "Credit Card", 50);

		// Example 2
		User user2 = UserProcess.getUserById(2);
		Destination destination2 = DestinationProcess.getDestinationById(2);
		TicketProcess.bookTicket(user2, destination2, "StationB", "StationC", "2023-12-28 16:30:00", "Debit Card", 70);

		// Add more examples as needed
	}

}