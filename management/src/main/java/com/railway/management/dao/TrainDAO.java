package com.railway.management.dao;

import java.util.List;

import com.railway.management.entity.Train;

public interface TrainDAO {
	
	void saveTrain(Train train);
	
	List<Train> getAllTrains();

	Train getTrainById(int id);

	void updateTrain(Train train);

	void deleteTrain(int id);

}