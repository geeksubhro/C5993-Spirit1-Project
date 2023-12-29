package com.railway.management.dao;

import java.util.List;

import com.railway.management.entity.Destination;

public interface DestinationDAO {

    void saveDestination(Destination destination);

    Destination getDestinationById(int destinationId);

    void updateDestination(Destination destination);

    void deleteDestination(int destinationId);

	List<Destination> getAllDestinations();

    // Other methods if needed
}