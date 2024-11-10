package com.example.geektrust;

import com.example.geektrust.entities.Driver;
import com.example.geektrust.entities.Location;
import com.example.geektrust.entities.Ride;
import com.example.geektrust.entities.Rider;
import com.example.geektrust.services.DriverMatcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RideSharingApp {
    private final List<String> commands;
    private final Map<String, Driver> driverMap = new HashMap<>(); // <driverId, Driver>
    private final Map<String, Rider> riderMap = new HashMap<>(); // <riderId, Rider>
    private final DriverMatcher driverMatcher = new DriverMatcher();
    private final Map<String, List<Driver>> matchedDriversForRide = new HashMap<>(); // <rideId, List<Driver>>
    private final Map<String, Ride> ridesMap = new HashMap<>();

    public RideSharingApp(List<String> commands) {
        this.commands = commands;
    }

    public void executeCommands() {
        for (String command : commands) {
            String[] split = command.split(" ");
            String operation = split[0];

            switch (operation) {
                case "ADD_DRIVER": {
                    String driverId = split[1];
                    Location location = new Location(Integer.parseInt(split[2]), Integer.parseInt(split[3]));
                    Driver driver = new Driver(driverId, location);
                    driverMap.put(driverId, driver);
                    break;
                }

                case "ADD_RIDER": {
                    String riderId = split[1];
                    Location location = new Location(Integer.parseInt(split[2]), Integer.parseInt(split[3]));
                    Rider rider = new Rider(riderId, location);
                    riderMap.put(riderId, rider);
                    break;
                }

                case "MATCH": {
                    String riderId = split[1];
                    Rider rider = riderMap.get(riderId);

                    if (rider == null) {
                        System.out.println("NO_RIDER_FOUND");
                    }

                    List<Driver> drivers = new ArrayList<>(driverMap.values());
                    List<Driver> matchedDrivers = driverMatcher.match(rider, drivers);
                    matchedDriversForRide.put(riderId, matchedDrivers);
                    break;
                }

                case "START_RIDE": {
                    String rideId = split[1];
                    int selectedDriver = Integer.parseInt(split[2]) - 1;
                    String riderId = split[3];
                    Rider rider = riderMap.get(riderId);

                    Driver driver = matchedDriversForRide.get(riderId).get(selectedDriver);

                    if (driver == null || ridesMap.containsKey(rideId)) {
                        System.out.println("INVALID_RIDE");
                    }

                    Ride ride = new Ride(rideId, rider, driver);
                    ridesMap.put(rideId, ride);
                    ride.start();
                    break;
                }

                case "STOP_RIDE": {
                    String rideId = split[1];
                    int destinationX = Integer.parseInt(split[2]);
                    int destinationY = Integer.parseInt(split[3]);
                    int timeTaken = Integer.parseInt(split[4]);
                    Location destination = new Location(destinationX, destinationY);
                    Ride ride = ridesMap.get(rideId);

                    if (ride == null) {
                        System.out.println("INVALID_RIDE");
                        return;
                    }

                    ride.stop(destination, timeTaken);
                    break;
                }

                case "BILL": {
                    String rideId = split[1];
                    Ride ride = ridesMap.get(rideId);

                    if (ride == null) {
                        System.out.println("INVALID_RIDE");
                        return;
                    }

                    ride.bill();
                    break;
                }

                default: {
                    throw new IllegalArgumentException("Unknown operation: " + operation);
                }
            }
        }
    }

    public List<String> getCommands() {
        return commands;
    }
}
