package com.railway.management.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Train")
public class Train {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	
	private int id;
	@Column(name = "train_id")
	private int trainId;

	@Column(name = "train_name")
	private String trainName;

	@Column(name = "speed")
	private int speed;

	@Column(name = "year_of_start")
	private int yearOfStart;

	@Column(name = "capacity")
	private int capacity;

	@OneToMany(mappedBy = "train", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Destination> destinations;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTrainId() {
		return trainId;
	}

	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getYearOfStart() {
		return yearOfStart;
	}

	public void setYearOfStart(int yearOfStart) {
		this.yearOfStart = yearOfStart;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<Destination> getDestinations() {
		return destinations;
	}

	public void setDestinations(List<Destination> destinations) {
		this.destinations = destinations;
	}

	@Override
	public String toString() {
	    StringBuilder box = new StringBuilder();
	    box.append("+----------------------------------------+\n");
	    box.append("| Train ID: ").append(id).append("\n");
	    box.append("| Train Number: ").append(trainId).append("\n");
	    box.append("| Train Name: ").append(trainName).append("\n");
	    box.append("| Speed: ").append(speed).append(" km/h").append("\n");
	    box.append("| Year of Start: ").append(yearOfStart).append("\n");
	    box.append("| Capacity: ").append(capacity).append(" passengers").append("\n");
	    box.append("+----------------------------------------+");

	    return box.toString();
	}


	public Train(int id, int trainId, String trainName, int speed, int yearOfStart, int capacity) {
		super();
		this.id = id;
		this.trainId = trainId;
		this.trainName = trainName;
		this.speed = speed;
		this.yearOfStart = yearOfStart;
		this.capacity = capacity;
	}

	public Train() {
		super();
	}

}
