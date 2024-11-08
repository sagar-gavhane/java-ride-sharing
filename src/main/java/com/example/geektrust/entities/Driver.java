package com.example.geektrust.entities;

import com.example.geektrust.enums.UserType;

public class Driver extends User {
    public Driver(String userId, Location location) {
        super(userId, location, UserType.DRIVER);
    }
}
