package com.zenika.back.message;

/**
 * GCM token.
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 10/01/2016.
 */
public class GcmToken {

    /**
     * User identifier
     */
    private long userId;

    /**
     * Token
     */
    private String token;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
