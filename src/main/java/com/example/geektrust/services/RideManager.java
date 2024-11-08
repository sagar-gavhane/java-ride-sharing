package com.example.geektrust.services;

import com.example.geektrust.entities.Driver;
import com.example.geektrust.entities.Ride;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RideManager {
    private final Map<String, List<Driver>> matchedDriversForRide = new HashMap<>(); // <rideId, List<Driver>>
    private final Map<String, Ride> ridesMap = new HashMap<>();

    public RideManager() {
    }

    public void setMatchedDriversForRide(String rideId, List<Driver> drivers) {
        matchedDriversForRide.put(rideId, drivers);
    }
}
