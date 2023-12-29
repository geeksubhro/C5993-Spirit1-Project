package com.railway.management.process;

import com.railway.management.dao.DestinationDAO;
import com.railway.management.dao.Impl.DestinationDAOImpl;
import com.railway.management.entity.Destination;
import com.railway.management.entity.Train;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

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

		try (SessionFactory factory = configuration.buildSessionFactory()) {
			try (Session session = factory.openSession()) {
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void showDestinations() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		DestinationDAO destinationDAO = new DestinationDAOImpl(sessionFactory);

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

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the destination_id you want to update: ");
		int destinationId = scanner.nextInt();

		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

		try (SessionFactory factory = configuration.buildSessionFactory()) {
			try (Session session = factory.openSession()) {
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

					System.out.print("Enter the option number to update: ");
					int option = scanner.nextInt();
					scanner.nextLine(); // Consume the newline character

					switch (option) {
					case 1:
						updateTrainDetails(destination.getTrain().getTrainId(), session);
						break;
					case 2:
						System.out.print("Enter new Start Station: ");
						String newStartStation = scanner.nextLine();
						destination.setStartStation(newStartStation);
						break;
					case 3:
						System.out.print("Enter new End Station: ");
						String newEndStation = scanner.nextLine();
						destination.setEndStation(newEndStation);
						break;
					case 4:
						System.out.print("Enter new Start Time: ");
						String newStartTime = scanner.next();
						destination.setStartTime(newStartTime);
						break;
					case 5:
						System.out.print("Enter new End Time: ");
						String newEndTime = scanner.next();
						destination.setEndTime(newEndTime);
						break;
					case 6:
						System.out.print("Enter new Distance: ");
						String newDistance = scanner.nextLine();
						destination.setDistance(newDistance);
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}

    public static Destination getDestinationById(int id) {
    	Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		try (SessionFactory factory = configuration.buildSessionFactory()) {
        try (Session session = factory.openSession()) {
            // Use left join fetch to eagerly fetch the Train association
            Destination destination = session.createQuery(
                    "SELECT d FROM Destination d LEFT JOIN FETCH d.train WHERE d.id = :id",
                    Destination.class)
                    .setParameter("id", id)
                    .uniqueResult();

            return destination;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    }
	private static void updateTrainDetails(int trainId, Session session) {
		TrainProcess.updateTrainDetails(trainId, session);
	}
}
