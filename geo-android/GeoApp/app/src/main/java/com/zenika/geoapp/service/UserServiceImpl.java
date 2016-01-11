package com.zenika.geoapp.service;

import android.content.Context;
import android.util.Log;

import com.zenika.geoapp.R;
import com.zenika.geoapp.message.GcmToken;
import com.zenika.geoapp.service.base.UserService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * User service connecting to the corresponding REST entry point on the backend.
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 10/01/2016.
 */
public class UserServiceImpl implements UserService {

    /**
     * The context.
     */
    private Context mContext;

    public UserServiceImpl(Context context) {
        mContext = context;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGcmToken(GcmToken gcmToken) throws IOException {
        try {
            URL url = new URL(mContext.getString(R.string.remote_server_address) + "/user");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.connect();

            JSONObject jsonParam = new JSONObject();
            jsonParam.put("userId", gcmToken.getUserId());
            jsonParam.put("token", gcmToken.getGcmToken());

            DataOutputStream dos = new DataOutputStream(urlConnection.getOutputStream());
            dos.write(jsonParam.toString().getBytes("UTF-8"));
            dos.flush();
            dos.close();

            urlConnection.disconnect();
        } catch (JSONException e) {
            // Should not happen
            Log.e("UserServiceImpl", e.toString());
        }
    }
}
