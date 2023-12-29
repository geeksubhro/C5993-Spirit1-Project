package com.railway.management.entity;

import javax.persistence.*;

@Entity
@Table(name = "Ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	public Ticket(int id, User user, Destination destination, String startingStation, String endStation,
			String timeOfBooking, String paymentMode, int cost) {
		super();
		this.id = id;
		this.user = user;
		this.destination = destination;
		this.startingStation = startingStation;
		this.endStation = endStation;
		this.timeOfBooking = timeOfBooking;
		this.paymentMode = paymentMode;
		this.cost = cost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public String getStartingStation() {
		return startingStation;
	}

	public void setStartingStation(String startingStation) {
		this.startingStation = startingStation;
	}

	public String getEndStation() {
		return endStation;
	}

	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}

	public String getTimeOfBooking() {
		return timeOfBooking;
	}

	public void setTimeOfBooking(String timeOfBooking) {
		this.timeOfBooking = timeOfBooking;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;

	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		StringBuilder box = new StringBuilder();
		box.append("+-----------------------------------------------------------+\n");
		box.append("| Ticket ID: ").append(id).append("\n");
		box.append("| User: ").append(user.getPerson().getName()).append("\n");
		box.append("| Route: ").append(startingStation).append(" to ").append(endStation).append("\n");
		box.append("| Booking Time: ").append(timeOfBooking).append("\n");
		box.append("| Payment Mode: ").append(paymentMode).append("\n");
		box.append("| Cost: ").append(cost).append(" INR").append("\n");
		box.append("+-----------------------------------------------------------+");

		return box.toString();
	}

	public Ticket() {
		super();
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "destination_id")
	private Destination destination;

	@Column(name = "starting_station")
	private String startingStation;

	@Column(name = "end_station")
	private String endStation;

	@Column(name = "time_of_booking")
	private String timeOfBooking;

	@Column(name = "payment_mode")
	private String paymentMode;

	@Column(name = "cost")
	private int cost;

	// Constructors, getters, setters, and other methods
}
