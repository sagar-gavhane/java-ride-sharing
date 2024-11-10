package com.example.geektrust.entities;

import com.example.geektrust.enums.UserType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {
    @Test
    @DisplayName("should return correct user")
    void shouldReturnUser() {
        User driver = new Driver("D1", new Location(1, 1));

        assertEquals("D1", driver.getUserId());
        assertEquals(UserType.DRIVER, driver.getUserType());
        assertTrue(driver.isAvailable());
        assertEquals(1, driver.getLocation().getX());
        assertEquals(1, driver.getLocation().getY());
    }
}