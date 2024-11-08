package com.example.geektrust.entities;

import com.example.geektrust.enums.UserType;

public abstract class User {
    private String userId;
    private Location location;
    private boolean available;
    private UserType userType;

    public User(String userId, Location location, UserType userType) {
        this.userId = userId;
        this.location = location;
        this.userType = userType;
        this.available = true;
    }

    public String getUserId() {
        return userId;
    }

    public Location getLocation() {
        return location;
    }

    public UserType getUserType() {
        return userType;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", location=" + location +
                ", available=" + available +
                ", userType=" + userType +
                '}';
    }
}
