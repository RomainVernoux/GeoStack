package com.zenika.geoapp.service;

import android.content.Context;
import android.util.Log;

import com.zenika.geoapp.R;
import com.zenika.geoapp.message.LocationMessage;
import com.zenika.geoapp.service.base.LocationService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Location service connecting to the corresponding REST entry point on the backend.
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 03/01/2016.
 */
public class LocationServiceImpl implements LocationService {

    /**
     * The context.
     */
    private Context mContext;

    public LocationServiceImpl(Context context) {
        mContext = context;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateLocation(LocationMessage location) throws IOException {
        try {
            URL url = new URL(mContext.getString(R.string.remote_server_address) + "/location");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.connect();

            JSONObject jsonParam = new JSONObject();
            jsonParam.put("userId", location.getUserId());
            jsonParam.put("latitude", location.getLatitude());
            jsonParam.put("longitude", location.getLongitude());
            jsonParam.put("timestamp", location.getTimestamp());

            DataOutputStream dos = new DataOutputStream(urlConnection.getOutputStream());
            dos.write(jsonParam.toString().getBytes("UTF-8"));
            dos.flush();
            dos.close();

            urlConnection.disconnect();
        } catch (JSONException e) {
            // Should not happen
            Log.e("LocationServiceImpl", e.toString());
        }
    }
}
