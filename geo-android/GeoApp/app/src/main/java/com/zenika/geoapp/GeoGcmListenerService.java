package com.zenika.geoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Service handling GCM messages.
 * <p/>
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 10/01/2016.
 */
public class GeoGcmListenerService extends GcmListenerService {

    private static final String TAG = "GeoGcmListenerService";
    public static final String DATA_KEY = "data";
    public static final String DATA_INTENT = "gcm-received";


    /**
     * Called when message is received.
     *
     * @param from SenderID of the sender.
     * @param data Data bundle containing message data as key/value pairs.
     *             For Set of keys use data.keySet().
     */
    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");
        Log.i(TAG, "GCM Message received");
        Log.i(TAG, "From: " + from);
        Log.i(TAG, "Message: " + message);

        // Notify UI
        Intent gcmReceived = new Intent(DATA_INTENT);
        gcmReceived.putExtra(DATA_KEY, message);
        LocalBroadcastManager.getInstance(this).sendBroadcast(gcmReceived);
    }
}
