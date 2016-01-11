package com.zenika.geoapp;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.zenika.geoapp.base.LocationProvider;

/**
 * A fragment with buttons to (un)register for location updates.
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 03/01/2016.
 */
public class LocationUpdateFragment extends Fragment {

    /**
     * The application context
     */
    private Context mContext;

    /**
     * The application's location provider
     */
    private LocationProvider mLocationProvider;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mContext = context;
        mLocationProvider = (LocationProvider) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.location_update, container, false);

        final Spinner spinner = (Spinner) view.findViewById(R.id.location_policy_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mContext,
                R.array.array_location_update, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button registerButton = (Button) view.findViewById(R.id.location_register);
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mLocationProvider.unregisterForLocationUpdates();
                String provider = null;
                switch (spinner.getSelectedItemPosition()) {
                    case 0:
                        provider = LocationManager.NETWORK_PROVIDER;
                        break;
                    case 1:
                        provider = LocationManager.GPS_PROVIDER;
                        break;
                }
                mLocationProvider.registerForLocationUpdates(provider);
            }
        });

        Button unregisterButton = (Button) view.findViewById(R.id.location_unregister);
        unregisterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mLocationProvider.unregisterForLocationUpdates();
            }
        });

        return view;
    }
}
