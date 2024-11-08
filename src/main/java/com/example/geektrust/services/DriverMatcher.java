package com.example.geektrust.services;

import com.example.geektrust.entities.Driver;
import com.example.geektrust.entities.Location;
import com.example.geektrust.entities.Rider;
import com.example.geektrust.entities.User;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DriverMatcher {
    // calculate distance between two points using Euclidean Distance Formula
    private int calculateDistance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    private List<Driver> getAvailableDrivers(List<Driver> drivers) {
        return drivers.stream().filter(User::isAvailable).collect(Collectors.toList());
    }

    public List<Driver> match(Rider rider, List<Driver> drivers) {
        Location riderLocation = rider.getLocation();
        List<Driver> availableDrivers = getAvailableDrivers(drivers);

        List<Driver> matchedDrivers = availableDrivers.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        driver -> calculateDistance(riderLocation.getX(), riderLocation.getY(), driver.getLocation().getX(), driver.getLocation().getY())
                )).entrySet().stream()
                .filter(entry -> entry.getValue() < 5)
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if (matchedDrivers.isEmpty()) {
            System.out.println("NO_DRIVERS_AVAILABLE");
            return matchedDrivers;
        }

        System.out.print("DRIVERS_MATCHED");
        matchedDrivers.forEach(driver -> System.out.print(" " + driver.getUserId()));
        System.out.println();

        return matchedDrivers;
    }
}
