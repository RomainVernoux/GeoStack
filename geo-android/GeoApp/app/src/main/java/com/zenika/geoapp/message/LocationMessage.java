package com.zenika.geoapp.message;

/**
 * Location message.
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 01/01/2016.
 */
public class LocationMessage {

    /**
     * User identifier
     */
    private long mUserId;

    /**
     * Latitude coordinate
     */
    private double mLatitude;

    /**
     * Longitude coordinate
     */
    private double mLongitude;

    /**
     * Timestamp
     */
    private long mTimestamp;

    public long getUserId() {
        return mUserId;
    }

    public void setUserId(long userId) {
        mUserId = userId;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    public long getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(long timestamp) {
        mTimestamp = timestamp;
    }
}