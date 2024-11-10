package com.example.geektrust.entities;

import com.example.geektrust.enums.UserType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RiderTest {
    @Test
    @DisplayName("should return correct rider")
    void shouldReturnRider() {
        User rider = new Rider("R1", new Location(1, 1));

        assertEquals("R1", rider.getUserId());
        assertEquals(UserType.RIDER, rider.getUserType());
        assertTrue(rider.isAvailable());
        assertEquals(1, rider.getLocation().getX());
        assertEquals(1, rider.getLocation().getY());
    }
}