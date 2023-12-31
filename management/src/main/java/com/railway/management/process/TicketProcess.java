package com.railway.management.process;

import com.railway.management.dao.TicketDAO;
import com.railway.management.dao.Impl.TicketDAOImpl;
import com.railway.management.entity.Destination;
import com.railway.management.entity.Ticket;
import com.railway.management.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class TicketProcess {

	private static boolean isTicketExists(Session session, String startingStation, String endStation) {
		Ticket existingTicket = (Ticket) session
				.createQuery("FROM Ticket WHERE startingStation = :startingStation AND endStation = :endStation")
				.setParameter("startingStation", startingStation).setParameter("endStation", endStation).uniqueResult();

		return existingTicket != null;
	}

	private static void saveTicket(Session session, Ticket ticket) {
		TicketDAO ticketDAO = new TicketDAOImpl(session.getSessionFactory());
		ticketDAO.saveTicket(ticket);
	}

	public static void bookTicket(User user, Destination destination, String startingStation, String endStation,
			String timeOfBooking, String paymentMode, int cost) {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

		try (SessionFactory factory = configuration.buildSessionFactory()) {
			try (Session session = factory.openSession()) {
				Ticket ticket = new Ticket();
				ticket.setUser(user); // Set the User entity

				// Set other properties
				ticket.setDestination(destination);
				ticket.setStartingStation(startingStation);
				ticket.setEndStation(endStation);
				ticket.setTimeOfBooking(timeOfBooking);
				ticket.setPaymentMode(paymentMode);
				ticket.setCost(cost);

				if (!isTicketExists(session, ticket.getStartingStation(), ticket.getEndStation())) {
					Transaction transaction = session.beginTransaction();

					saveTicket(session, ticket);

					// Commit the transaction
					transaction.commit();
				} else {
					System.out.println("Ticket with the same starting and end stations already exists. Skipping...");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void showTickets() {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {

			TicketDAO ticketDAO = new TicketDAOImpl(session.getSessionFactory());

			List<Ticket> tickets = ticketDAO.getAllTickets();

			if (tickets != null && !tickets.isEmpty()) {
				System.out.println("Printing all stored Tickets:");
				for (Ticket ticket : tickets) {
					System.out.println(ticket);
				}
			} else {
				System.out.println("No Tickets found.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Ticket> getBookedTicketsByUser(User user) {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {

			TicketDAO ticketDAO = new TicketDAOImpl(session.getSessionFactory());

			return ticketDAO.getTicketsByUser(user);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
