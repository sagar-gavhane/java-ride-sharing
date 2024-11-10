package com.example.geektrust.entities;

import com.example.geektrust.enums.RideStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RideTest {
    @Test
    @DisplayName("should return valid ride")
    void shouldReturnValidRide() {
        String riderId = "RIDE-001";
        Rider rider = new Rider("R1", new Location(0, 0));
        Driver driver = new Driver("D1", new Location(4, 3));

        Ride ride = new Ride(riderId, rider, driver);

        assertEquals("RIDE-001", ride.getRideId());
        assertEquals(0, rider.getLocation().getX());
        assertEquals(0, rider.getLocation().getY());

        assertEquals(4, driver.getLocation().getX());
        assertEquals(3, driver.getLocation().getY());
        assertEquals(RideStatus.RIDE_REQUESTED, ride.getRideStatus());
    }

    @Test
    @DisplayName("should start ride")
    void shouldStartRide() {
        String riderId = "RIDE-001";
        Rider rider = new Rider("R1", new Location(0, 0));
        Driver driver = new Driver("D1", new Location(4, 3));

        Ride ride = new Ride(riderId, rider, driver);
        ride.start();

        assertEquals(RideStatus.RIDE_STARTED, ride.getRideStatus());
    }

    @Test
    @DisplayName("should stop ride")
    void shouldStopRide() {
        String riderId = "RIDE-001";
        Rider rider = new Rider("R1", new Location(0, 0));
        Driver driver = new Driver("D1", new Location(4, 3));
        Location destination = new Location(4, 3);

        Ride ride = new Ride(riderId, rider, driver);
        ride.start();
        ride.stop(destination, 10);

        assertEquals(RideStatus.RIDE_ENDED, ride.getRideStatus());
        assertEquals(4, ride.getDestination().getX());
        assertEquals(3, ride.getDestination().getY());
        assertEquals(10, ride.getTimeTaken());
        assertEquals(5, ride.getTraveledDistanceInKm());
    }

    @Test
    @DisplayName("should return valid ride bill")
    void shouldReturnValidRideBill() {
        String riderId = "RIDE-001";
        Rider rider = new Rider("R1", new Location(0, 0));
        Driver driver = new Driver("D1", new Location(4, 3));
        Location destination = new Location(4, 3);

        Ride ride = new Ride(riderId, rider, driver);
        ride.start();
        ride.stop(destination, 10);

        assertEquals(RideStatus.RIDE_ENDED, ride.getRideStatus());
        assertEquals(4, ride.getDestination().getX());
        assertEquals(3, ride.getDestination().getY());
        assertEquals(10, ride.getTimeTaken());
        assertEquals(5, ride.getTraveledDistanceInKm());
        assertEquals(123.0, ride.getTotalBill());
    }
}