package com.example.geektrust.entities;

import com.example.geektrust.enums.UserType;

public class Rider extends User {
    public Rider(String userId, Location location) {
        super(userId, location, UserType.RIDER);
    }
}
