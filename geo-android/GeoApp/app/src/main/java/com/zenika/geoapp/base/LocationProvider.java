package com.zenika.geoapp.base;

/**
 * Interface exposing an Android location provider.
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 03/01/2016.
 */
public interface LocationProvider {

    /**
     * Registers this activity for location updates from the given provider.
     *
     * @param provider The location provider.
     */
    void registerForLocationUpdates(String provider);

    /**
     * Unregisters this activity for location updates from all providers.
     */
    void unregisterForLocationUpdates();
}
