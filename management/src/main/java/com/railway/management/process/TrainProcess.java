package com.railway.management.process;

import com.railway.management.dao.TrainDAO;
import com.railway.management.dao.Impl.TrainDAOImpl;
import com.railway.management.entity.Train;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrainProcess {

	private static boolean isTrainExists(Session session, int trainId) {
		Train existingTrain = (Train) session.createQuery("FROM Train WHERE trainId = :trainId")
				.setParameter("trainId", trainId).uniqueResult();

		return existingTrain != null;
	}

	private static void saveTrain(Session session, Train train) {
		TrainDAO trainDAO = new TrainDAOImpl(session.getSessionFactory());
		trainDAO.saveTrain(train);
	}

	public static void addTrain(int trainId, String trainName, int speed, int yearOfStart, int capacity) {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

		try (SessionFactory factory = configuration.buildSessionFactory()) {
			try (Session session = factory.openSession()) {
				if (!isTrainExists(session, trainId)) {
					Transaction transaction = session.beginTransaction();

					Train train = new Train();
					train.setTrainId(trainId);
					train.setTrainName(trainName);
					train.setSpeed(speed);
					train.setYearOfStart(yearOfStart);
					train.setCapacity(capacity);

					train.setDestinations(new ArrayList<>());

					saveTrain(session, train);

					// Commit the transaction
					transaction.commit();
				} else {
					System.out.println("Train with the same ID already exists. Skipping...");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveTrain(Train train) {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

		try (SessionFactory factory = configuration.buildSessionFactory()) {
			try (Session session = factory.openSession()) {
				Transaction transaction = session.beginTransaction();

				saveTrain(session, train);

				// Commit the transaction
				transaction.commit();
				System.out.println("Train Created......");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void showTrains() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		TrainDAO trainDAO = new TrainDAOImpl(sessionFactory);

		List<Train> trains = trainDAO.getAllTrains();

		if (trains != null && !trains.isEmpty()) {
			System.out.println("Printing all stored Trains:");
			for (Train train : trains) {
				System.out.println(train);
			}
		} else {
			System.out.println("No Trains found.");
		}
	}

	public static void updateTrainDetails() {
		TrainProcess.showTrains();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the train ID you want to update: ");
		int trainId = scanner.nextInt();
		scanner.nextLine(); // Consume the newline character

		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

		try (SessionFactory factory = configuration.buildSessionFactory()) {
			try (Session sessionToUpdate = factory.openSession()) {
				Train train = sessionToUpdate.get(Train.class, trainId);

				if (train != null) {
					System.out.println("Train details for train ID " + trainId + ":");
					System.out.println(train);

					System.out.println("Select the details you want to update:");
					System.out.println("1. Train Name");
					System.out.println("2. Speed");
					System.out.println("3. Year of Start");
					System.out.println("4. Capacity");

					System.out.print("Enter the option number to update: ");
					int option = scanner.nextInt();
					scanner.nextLine(); // Consume the newline character

					switch (option) {
					case 1:
						System.out.print("Enter new Train Name: ");
						String newTrainName = scanner.nextLine();
						train.setTrainName(newTrainName);
						break;
					case 2:
						System.out.print("Enter new Speed: ");
						int newSpeed = scanner.nextInt();
						train.setSpeed(newSpeed);
						break;
					case 3:
						System.out.print("Enter new Year of Start: ");
						int newYearOfStart = scanner.nextInt();
						train.setYearOfStart(newYearOfStart);
						break;
					case 4:
						System.out.print("Enter new Capacity: ");
						int newCapacity = scanner.nextInt();
						train.setCapacity(newCapacity);
						break;
					default:
						System.out.println("Invalid option. No changes made.");
						return;
					}

					Transaction transaction = sessionToUpdate.beginTransaction();
					sessionToUpdate.update(train);
					transaction.commit();

					System.out.println("Train details updated successfully.");
				} else {
					System.out.println("Train not found for the provided train ID.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}

	public static void updateTrainDetails(int trainId, Session session) {
		Scanner scanner = new Scanner(System.in);

		Train train = session.get(Train.class, trainId);

		if (train != null) {
			System.out.println("Train details for train ID " + trainId + ":");
			System.out.println(train);

			System.out.println("Select the details you want to update:");
			System.out.println("1. Train Name");
			System.out.println("2. Speed");
			System.out.println("3. Year of Start");
			System.out.println("4. Capacity");

			System.out.print("Enter the option number to update: ");
			int option = scanner.nextInt();
			scanner.nextLine(); // Consume the newline character

			switch (option) {
			case 1:
				System.out.print("Enter new Train Name: ");
				String newTrainName = scanner.nextLine();
				train.setTrainName(newTrainName);
				break;
			case 2:
				System.out.print("Enter new Speed: ");
				int newSpeed = scanner.nextInt();
				train.setSpeed(newSpeed);
				break;
			case 3:
				System.out.print("Enter new Year of Start: ");
				int newYearOfStart = scanner.nextInt();
				train.setYearOfStart(newYearOfStart);
				break;
			case 4:
				System.out.print("Enter new Capacity: ");
				int newCapacity = scanner.nextInt();
				train.setCapacity(newCapacity);
				break;
			default:
				System.out.println("Invalid option. No changes made.");
				return;
			}

			Transaction transaction = session.beginTransaction();
			session.update(train);
			transaction.commit();

			System.out.println("Train details updated successfully.");
		} else {
			System.out.println("Train not found for the provided train ID.");
		}

		scanner.close();
	}

	public static Train getTrainById(int id) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

		TrainDAO trainDAO = new TrainDAOImpl(sessionFactory);
		Train train = trainDAO.getTrainById(id);
		return train;
	}
}