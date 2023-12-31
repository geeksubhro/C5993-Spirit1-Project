package com.railway.management.process;

import com.railway.management.dao.DestinationDAO;
import com.railway.management.dao.Impl.DestinationDAOImpl;
import com.railway.management.entity.Destination;
import com.railway.management.entity.Train;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DestinationProcess {

	private static boolean isDestinationExists(Session session, String destinationName) {
		Destination existingDestination = (Destination) session
				.createQuery("FROM Destination WHERE startStation = :startStation")
				.setParameter("startStation", destinationName).uniqueResult();

		return existingDestination != null;
	}

	private static void saveDestination(Session session, Destination destination) {
		DestinationDAO destinationDAO = new DestinationDAOImpl(session.getSessionFactory());
		destinationDAO.saveDestination(destination);
	}

	public static void addDestination(Train train, String startStation, String endStation, String startTime,
			String endTime, String distance) {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

		try (Session session = configuration.buildSessionFactory().openSession()) {
			Destination destination = new Destination();
			destination.setTrain(train);
			destination.setStartStation(startStation);
			destination.setEndStation(endStation);
			destination.setStartTime(startTime);
			destination.setEndTime(endTime);
			destination.setDistance(distance);
			if (!isDestinationExists(session, destination.getStartStation())) {
				Transaction transaction = session.beginTransaction();

				saveDestination(session, destination);

				// Commit the transaction
				transaction.commit();
			} else {
				System.out.println("Destination with the same start station already exists. Skipping...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void showDestinations() {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		Session session = configuration.buildSessionFactory().openSession();

		DestinationDAO destinationDAO = new DestinationDAOImpl(session.getSessionFactory());

		List<Destination> destinations = destinationDAO.getAllDestinations();

		if (destinations != null && !destinations.isEmpty()) {
			System.out.println("Printing all stored Destinations:");
			for (Destination destination : destinations) {
				System.out.println(destination);
			}
		} else {
			System.out.println("No Destinations found.");
		}
	}

	public static void updateDestinationDetails() {
		showDestinations(); // Display existing destinations
		InputProcess inputProcess = new InputProcess();
		int destinationId = inputProcess.getInt("Enter the destination_id you want to update: ");

		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

		try (Session session = configuration.buildSessionFactory().openSession()) {
			DestinationDAO destinationDAO = new DestinationDAOImpl(session.getSessionFactory());
			Destination destination = destinationDAO.getDestinationById(destinationId);

			if (destination != null) {
				System.out.println("Destination details for destination_id " + destinationId + ":");
				System.out.println(destination);

				System.out.println("Select the details you want to update:");
				System.out.println("1. Train");
				System.out.println("2. Start Station");
				System.out.println("3. End Station");
				System.out.println("4. Start Time");
				System.out.println("5. End Time");
				System.out.println("6. Distance");

				int option = inputProcess.getInt("Enter the option number to update: ");

				switch (option) {
				case 1:
					updateTrainDetails(destination.getTrain().getTrainId(), session, inputProcess);
					break;
				case 2:
					destination.setStartStation(inputProcess.getString("Enter new Start Station: "));
					break;
				case 3:
					destination.setEndStation(inputProcess.getString("Enter new End Station: "));
					break;
				case 4:
					destination.setStartTime(inputProcess.getString("Enter new Start Time: "));
					break;
				case 5:
					destination.setEndTime(inputProcess.getString("Enter new End Time: "));
					break;
				case 6:
					destination.setDistance(inputProcess.getString("Enter new Distance: "));
					break;
				default:
					System.out.println("Invalid option. No changes made.");
					return;
				}

				Transaction transaction = session.beginTransaction();
				destinationDAO.updateDestination(destination);
				transaction.commit();

				System.out.println("Destination details updated successfully.");
			} else {
				System.out.println("Destination not found for the provided destination_id.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void updateTrainDetails(int trainId, Session session, InputProcess inputProcess) {
		TrainProcess.updateTrainDetails(trainId, session);
	}

	public static Destination getDestinationById(int destinationId) {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

		try (Session session = configuration.buildSessionFactory().openSession()) {
			DestinationDAO destinationDAO = new DestinationDAOImpl(session.getSessionFactory());
			return destinationDAO.getDestinationById(destinationId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
