package com.zenika.geoapp.message;

/**
 * Gcm Token
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 10/01/2016.
 */
public class GcmToken {

    /**
     * User identifier
     */
    private long mUserId;

    /**
     * GCM token
     */
    private String mGcmToken;

    public long getUserId() {
        return mUserId;
    }

    public void setUserId(long userId) {
        mUserId = userId;
    }

    public void setGcmToken(String gcmToken) {
        mGcmToken = gcmToken;
    }

    public String getGcmToken() {
        return mGcmToken;
    }

}