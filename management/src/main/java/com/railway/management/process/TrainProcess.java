package com.railway.management.process;

import com.railway.management.dao.TrainDAO;
import com.railway.management.dao.Impl.TrainDAOImpl;
import com.railway.management.entity.Train;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class TrainProcess {
final static    InputProcess inputProcess = new InputProcess();
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

        try (Session session = configuration.buildSessionFactory().openSession()) {
            if (!isTrainExists(session, trainId)) {
                Transaction transaction = session.beginTransaction();

                Train train = new Train();
                train.setTrainId(trainId);
                train.setTrainName(trainName);
                train.setSpeed(speed);
                train.setYearOfStart(yearOfStart);
                train.setCapacity(capacity);

                train.setDestinations(List.of()); // Empty list for destinations

                saveTrain(session, train);

                // Commit the transaction
                transaction.commit();
                System.out.println("Train Created......");
            } else {
                System.out.println("Train with the same ID already exists. Skipping...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveTrain(Train train) {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

        try (Session session = configuration.buildSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            saveTrain(session, train);

            // Commit the transaction
            transaction.commit();
            System.out.println("Train Created......");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showTrains() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

        try (Session session = configuration.buildSessionFactory().openSession()) {
            TrainDAO trainDAO = new TrainDAOImpl(session.getSessionFactory());

            List<Train> trains = trainDAO.getAllTrains();

            if (trains != null && !trains.isEmpty()) {
                System.out.println("Printing all stored Trains:");
                for (Train train : trains) {
                    System.out.println(train);
                }
            } else {
                System.out.println("No Trains found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateTrainDetails() {
        showTrains();
        int trainId = inputProcess.getInt("Enter the train ID you want to update: ");

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

        try (Session sessionToUpdate = configuration.buildSessionFactory().openSession()) {
            Train train = sessionToUpdate.get(Train.class, trainId);

            if (train != null) {
                System.out.println("Train details for train ID " + trainId + ":");
                System.out.println(train);

                System.out.println("Select the details you want to update:");
                System.out.println("1. Train Name");
                System.out.println("2. Speed");
                System.out.println("3. Year of Start");
                System.out.println("4. Capacity");

                int option = inputProcess.getInt("Enter the option number to update: ");

                switch (option) {
                    case 1:
                        String newTrainName = inputProcess.getString("Enter new Train Name: ");
                        train.setTrainName(newTrainName);
                        break;
                    case 2:
                        int newSpeed = inputProcess.getInt("Enter new Speed: ");
                        train.setSpeed(newSpeed);
                        break;
                    case 3:
                        int newYearOfStart = inputProcess.getInt("Enter new Year of Start: ");
                        train.setYearOfStart(newYearOfStart);
                        break;
                    case 4:
                        int newCapacity = inputProcess.getInt("Enter new Capacity: ");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateTrainDetails(int trainId, Session session) {
        Train train = session.get(Train.class, trainId);

        if (train != null) {
            System.out.println("Train details for train ID " + trainId + ":");
            System.out.println(train);

            System.out.println("Select the details you want to update:");
            System.out.println("1. Train Name");
            System.out.println("2. Speed");
            System.out.println("3. Year of Start");
            System.out.println("4. Capacity");

            int option = inputProcess.getInt("Enter the option number to update: ");

            switch (option) {
                case 1:
                    String newTrainName = inputProcess.getString("Enter new Train Name: ");
                    train.setTrainName(newTrainName);
                    break;
                case 2:
                    int newSpeed = inputProcess.getInt("Enter new Speed: ");
                    train.setSpeed(newSpeed);
                    break;
                case 3:
                    int newYearOfStart = inputProcess.getInt("Enter new Year of Start: ");
                    train.setYearOfStart(newYearOfStart);
                    break;
                case 4:
                    int newCapacity = inputProcess.getInt("Enter new Capacity: ");
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
    }

    public static Train getTrainById(int id) {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

        try (Session session = configuration.buildSessionFactory().openSession()) {
            TrainDAO trainDAO = new TrainDAOImpl(session.getSessionFactory());
            return trainDAO.getTrainById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
