package com.zenika.back.message;

import org.json.JSONObject;

/**
 * GCM message.
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 10/01/2016.
 */
public class GcmMessage extends JSONObject {

    /**
     * Constructs a new GCM message.
     * @param gcmToken The GCM of the target client.
     * @param message The message.
     */
    public GcmMessage(String gcmToken, String message) {
        JSONObject data = new JSONObject();
        data.put("message", message);
        this.put("to", gcmToken);
        this.put("data", data);
    }
}
