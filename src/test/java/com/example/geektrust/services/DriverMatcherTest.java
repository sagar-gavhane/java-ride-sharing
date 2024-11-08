package com.example.geektrust.services;

import com.example.geektrust.entities.Driver;
import com.example.geektrust.entities.Location;
import com.example.geektrust.entities.Rider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class DriverMatcherTest {
    @Test
    @DisplayName("should be returning available drivers within 5km distance")
    void shouldReturnAvailableDrivers() {
        DriverMatcher driverMatcher = new DriverMatcher();
        Rider rider = new Rider("R1", new Location(0, 0));

        Driver driver1 = new Driver("D1", new Location(1, 1));
        Driver driver2 = new Driver("D2", new Location(4, 5));
        Driver driver3 = new Driver("D3", new Location(2, 2));

        List<Driver> drivers = new ArrayList<>();
        drivers.add(driver1);
        drivers.add(driver2);
        drivers.add(driver3);

        List<Driver> actual = driverMatcher.match(rider, drivers);
        assertArrayEquals(Arrays.asList(driver1, driver3).toArray(), actual.toArray());
    }

    @Test
    @DisplayName("should return empty list if no drivers are available")
    void shouldReturnEmptyList() {
        DriverMatcher driverMatcher = new DriverMatcher();
        Rider rider = new Rider("R1", new Location(0, 0));

        Driver driver1 = new Driver("D1", new Location(10, 12));
        Driver driver2 = new Driver("D2", new Location(4, 5));
        Driver driver3 = new Driver("D3", new Location(21, 20));

        List<Driver> drivers = new ArrayList<>();
        drivers.add(driver1);
        drivers.add(driver2);
        drivers.add(driver3);

        List<Driver> actual = driverMatcher.match(rider, drivers);
        assertArrayEquals(Arrays.asList().toArray(), actual.toArray());
    }
}