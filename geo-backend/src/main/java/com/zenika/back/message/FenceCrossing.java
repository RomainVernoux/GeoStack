package com.zenika.back.message;

/**
 * Fence crossing message.
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 04/01/2016.
 */
public class FenceCrossing {

    /**
     * User identifier
     */
    private long userId;

    /**
     * Fence name
     */
    private String fenceName;

    /**
     * GCM token
     */
    private String gcmToken;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFenceName() {
        return fenceName;
    }

    public void setFenceName(String fenceName) {
        this.fenceName = fenceName;
    }

    public String getGcmToken() {
        return gcmToken;
    }

    public void setGcmToken(String gcmToken) {
        this.gcmToken = gcmToken;
    }

    @Override
    public String toString() {
        return fenceName;
    }
}