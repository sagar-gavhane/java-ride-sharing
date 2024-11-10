package com.example.geektrust.entities;

import com.example.geektrust.enums.RideStatus;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Ride {
    private final String rideId;
    private final Rider rider;
    private final Driver driver;
    private RideStatus rideStatus = RideStatus.RIDE_REQUESTED;
    private Location destination;
    private double traveledDistanceInKm;
    private long timeTaken;

    public Ride(String rideId, Rider rider, Driver driver) {
        this.rideId = rideId;
        this.rider = rider;
        this.driver = driver;
    }

    public void start() {
        System.out.println("RIDE_STARTED " + rideId);
        rideStatus = RideStatus.RIDE_STARTED;
    }

    public void stop(Location destination, long timeTaken) {
        if (rideStatus != RideStatus.RIDE_STARTED) {
            throw new IllegalStateException("Ride is not started");
        }

        System.out.println("RIDE_STOPPED " + rideId);
        rideStatus = RideStatus.RIDE_ENDED;
        this.destination = destination;
        this.timeTaken = timeTaken;
        this.traveledDistanceInKm = calculateDistance(rider.getLocation().getX(), rider.getLocation().getY(), destination.getX(), destination.getY());
    }

    public double getTotalBill() {
        final int baseFare = 50;
        final float perKm = 6.5f;
        final int perMin = 2;
        final float serviceCharge = 1.20f;
        final double totalFare = (baseFare + (perKm * traveledDistanceInKm) + (perMin * timeTaken)) * serviceCharge;

        return BigDecimal.valueOf(totalFare).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
    }

    public void bill() {
        double totalBill = getTotalBill();
        String bill = String.format("BILL %s %s %.2f", rideId, driver.getUserId(), totalBill);
        System.out.println(bill);
    }

    private double calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public String getRideId() {
        return rideId;
    }

    public Rider getRider() {
        return rider;
    }

    public Driver getDriver() {
        return driver;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public Location getDestination() {
        return destination;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public double getTraveledDistanceInKm() {
        return traveledDistanceInKm;
    }
}
