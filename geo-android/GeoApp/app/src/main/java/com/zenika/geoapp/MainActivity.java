package com.zenika.geoapp;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.util.Pair;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.zenika.geoapp.base.LocationMocker;
import com.zenika.geoapp.base.LocationProvider;
import com.zenika.geoapp.message.LocationMessage;
import com.zenika.geoapp.mock.MockChampsElysees;
import com.zenika.geoapp.mock.base.MockRun;
import com.zenika.geoapp.service.LocationServiceImpl;
import com.zenika.geoapp.service.base.LocationService;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;

/**
 * The main Activity of the app.
 * <p/>
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 03/01/2016.
 */
public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        LocationProvider, LocationListener, LocationMocker {

    /**
     * The location manager.
     */
    private LocationManager mLocationManager;

    /**
     * The location service.
     */
    private LocationService mLocationService;

    /**
     * The Maps fragment.
     */
    private MapsFragment mMapsFragment;

    /**
     * The location update fragment.
     */
    private LocationUpdateFragment mLocationUpdateFragment;

    /**
     * The GCM broadcast receiver receiving intents from the {@code GeoGcmListenerService}.
     */
    private BroadcastReceiver mGcmBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Fragments
        mMapsFragment = new MapsFragment();
        mLocationUpdateFragment = new LocationUpdateFragment();

        // Default fragment
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_main_container, mMapsFragment)
                .commit();

        // Location manager
        mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Location service
        mLocationService = new LocationServiceImpl(this);

        // Mock location provider
        mLocationManager.addTestProvider(LocationManager.GPS_PROVIDER,
                false, false, false, false, false, false, false,
                android.location.Criteria.POWER_LOW,
                android.location.Criteria.ACCURACY_FINE);
        mLocationManager.setTestProviderEnabled(LocationManager.GPS_PROVIDER, true);

        // GCM registration
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);

        // GCM message handling
        mGcmBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String msg = intent.getStringExtra(GeoGcmListenerService.DATA_KEY);
                Snackbar.make(findViewById(R.id.content_main_container), msg, Snackbar.LENGTH_SHORT)
                        .show();
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mGcmBroadcastReceiver,
                new IntentFilter(GeoGcmListenerService.DATA_INTENT));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mGcmBroadcastReceiver);
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.map:
                this.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main_container, mMapsFragment)
                        .commit();
                break;
            case R.id.location_update:
                this.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_main_container, mLocationUpdateFragment)
                        .commit();
                break;
            case R.id.mock_run:
                dispatchMockLocations(new MockChampsElysees());
                break;
            default:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerForLocationUpdates(String provider) {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }

        try {
            mLocationManager.requestLocationUpdates(provider, 1000, 0, this);
        } catch (SecurityException e) {
            Snackbar.make(findViewById(R.id.content_main_container),
                    R.string.location_permission_denied, Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unregisterForLocationUpdates() {
        try {
            mLocationManager.removeUpdates(this);
        } catch (SecurityException e) {
            // Nothing to do
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispatchMockLocations(final MockRun mockRun) {

        final Queue<Pair<Double, Double>> coordinateQueue =
                new LinkedList<>(mockRun.getMockRunCoordinates());

        AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                while (!coordinateQueue.isEmpty()) {
                    Pair<Double, Double> coordinate = coordinateQueue.poll();
                    Location newLoc = new Location(LocationManager.GPS_PROVIDER);
                    newLoc.setLatitude(coordinate.first);
                    newLoc.setLongitude(coordinate.second);
                    newLoc.setAccuracy(200);
                    // Hack to work around known bug:
                    // https://code.google.com/p/android/issues/detail?id=52919
                    try {
                        Method locationJellyBeanFixMethod =
                                Location.class.getMethod("makeComplete");
                        locationJellyBeanFixMethod.invoke(newLoc);
                    } catch (Exception e) {
                        // Do nothing
                    }
                    newLoc.setTime(Calendar.getInstance().getTimeInMillis());
                    mLocationManager.setTestProviderLocation(LocationManager.GPS_PROVIDER, newLoc);
                    try {
                        Thread.sleep(mockRun.getIntervalMillis());
                    } catch (InterruptedException e) {
                        // Do nothing
                    }
                }
                return null;
            }
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            asyncTask.execute();
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("MainActivity", "Location changed: " + location.toString());

        final LocationMessage locationMessage = new LocationMessage();
        locationMessage.setUserId(1); // TODO: user accounts
        locationMessage.setLatitude(location.getLatitude());
        locationMessage.setLongitude(location.getLongitude());
        locationMessage.setTimestamp(Calendar.getInstance().getTimeInMillis());

        AsyncTask<LocationMessage, LocationMessage, Boolean> asyncTask =
                new AsyncTask<LocationMessage, LocationMessage, Boolean>() {
                    @Override
                    protected Boolean doInBackground(LocationMessage... params) {
                        try {
                            for (LocationMessage location : params) {
                                mLocationService.updateLocation(location);
                                publishProgress(location);
                            }
                            return true;
                        } catch (IOException e) {
                            e.printStackTrace(); // TODO: store and try again later
                            return false;
                        }
                    }

                    @Override
                    protected void onProgressUpdate(LocationMessage... values) {
                        super.onProgressUpdate(values);
                        for (LocationMessage location : values) {
                            mMapsFragment.updateLocation(location.getLatitude(),
                                    location.getLongitude());
                        }
                    }

                    @Override
                    protected void onPostExecute(Boolean result) {
                        if (result) {
                            Log.d("MainActivity", "Location posted");
                        } else {
                            Log.d("MainActivity", "Connection failed. Location not posted.");
                        }
                    }
                };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, locationMessage);
        } else {
            asyncTask.execute(locationMessage);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Nothing to do
    }

    @Override
    public void onProviderEnabled(String provider) {
        // Nothing to do
    }

    @Override
    public void onProviderDisabled(String provider) {
        // Nothing to do
    }
}
