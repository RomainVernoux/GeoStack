package com.zenika.geoapp;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.zenika.geoapp.message.GcmToken;
import com.zenika.geoapp.service.UserServiceImpl;
import com.zenika.geoapp.service.base.UserService;

import java.io.IOException;

/**
 * Service handling GCM registration.
 * <p/>
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 10/01/2016.
 */
public class RegistrationIntentService extends IntentService {


    private static final String TAG = "RegIntentService";
    private static final String[] TOPICS = {"global"};

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        try {
            // Ask GCM for a client token
            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            Log.i(TAG, "GCM Registration Token: " + token);

            // Send any registration to your app's server.
            sendRegistrationToServer(token);

            // Subscribe to topic channels
            subscribeTopics(token);
        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
        }
    }

    /**
     * Sends the registration token to the server.
     * <p/>
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        UserService userService = new UserServiceImpl(this);
        GcmToken gcmToken = new GcmToken();
        gcmToken.setUserId(1); // TODO user accounts
        gcmToken.setGcmToken(token);
        try {
            userService.updateGcmToken(gcmToken);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Subscribe to any GCM topics of interest, as defined by the TOPICS constant.
     *
     * @param token GCM token
     * @throws IOException if unable to reach the GCM PubSub service
     */
    private void subscribeTopics(String token) throws IOException {
        GcmPubSub pubSub = GcmPubSub.getInstance(this);
        for (String topic : TOPICS) {
            pubSub.subscribe(token, "/topics/" + topic, null);
        }
    }
}
