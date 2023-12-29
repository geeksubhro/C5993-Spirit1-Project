package com.railway.management.entity;

import javax.persistence.*;

@Entity
@Table(name = "Destination")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
	@ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @Column(name = "start_station")
    private String startStation;

    @Column(name = "end_station")
    private String endStation;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "distance")
    private String distance;
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public String getStartStation() {
		return startStation;
	}

	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}

	public String getEndStation() {
		return endStation;
	}

	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
	    StringBuilder box = new StringBuilder();
	    box.append("+--------------------------------------------------------+\n");
	    box.append("| Destination ID: ").append(id).append("\n");
	    box.append("| Train: ").append(train).append("\n");
	    box.append("| Start Station: ").append(startStation).append("\n");
	    box.append("| End Station: ").append(endStation).append("\n");
	    box.append("| Start Time: ").append(startTime).append("\n");
	    box.append("| End Time: ").append(endTime).append("\n");
	    box.append("| Distance: ").append(distance).append("\n");
	    box.append("+--------------------------------------------------------+");

	    return box.toString();
	}


	public Destination(int id, Train train, String startStation, String endStation, String startTime, String endTime,
			String distance) {
		super();
		this.id = id;
		this.train = train;
		this.startStation = startStation;
		this.endStation = endStation;
		this.startTime = startTime;
		this.endTime = endTime;
		this.distance = distance;
	}

	public Destination() {
		super();
	}



    // Constructors, getters, setters, and other methods
}
