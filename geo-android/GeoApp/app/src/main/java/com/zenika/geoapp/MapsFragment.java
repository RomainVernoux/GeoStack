package com.zenika.geoapp;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.zenika.geoapp.base.LocationMocker;

/**
 * A fragment with Google Maps view.
 * <p/>
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 09/01/2016.
 */
public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback {

    /**
     * The application's location mocker
     */
    private LocationMocker mLocationMocker;

    /**
     * The underlying map.
     */
    private GoogleMap mMap;

    /**
     * The current location marker.
     */
    private Marker mMarker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Center the view
        LatLng centerCoordinates = new LatLng(48.8735107,2.2973425);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(centerCoordinates));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(centerCoordinates, 16f));

        // Draw the fences
        mMap.addCircle(new CircleOptions()
                .center(new LatLng(48.873768, 2.295028))
                .radius(80f)
                .strokeColor(Color.argb(128, 0, 0, 255)));

        mMap.addCircle(new CircleOptions()
                .center(new LatLng(48.872018, 2.300559))
                .radius(20f)
                .strokeColor(Color.argb(128, 0, 0, 255)));
    }

    /**
     * Updates the location marker on the map according the provided coordinates.
     * @param latitude The new latitude.
     * @param longitude The new longitude.
     */
    public void updateLocation(Double latitude, Double longitude) {
        if (mMap != null) {
            if (mMarker != null) {
                mMarker.remove();
            }
            LatLng coordinates = new LatLng(latitude, longitude);
            mMarker = mMap.addMarker(new MarkerOptions().position(coordinates));
        }
    }
}
