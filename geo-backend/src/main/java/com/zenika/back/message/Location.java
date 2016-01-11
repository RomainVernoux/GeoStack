package com.zenika.back.message;

/**
 * Location message.
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 01/01/2016.
 */
public class Location {

    /**
     * User identifier
     */
    private long userId;

    /**
     * Latitude coordinate
     */
    private double latitude;

    /**
     * Longitude coordinate
     */
    private double longitude;

    /**
     * Timestamp
     */
    private long timestamp;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format("{userId: %d, longitude: %.6f, latitude: %.6f, timestamp: %d",
                userId, longitude, latitude, timestamp);
    }
}