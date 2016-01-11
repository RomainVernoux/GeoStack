package com.zenika.geoapp.mock.base;

import android.support.v4.util.Pair;

import java.util.List;

/**
 * Interface for mock runs (sequence of mock locations).
 * <p/>
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 10/01/2016.
 */
public interface MockRun {

    /**
     * Provides the list of (latitude, longitude) pairs of this mock run.
     *
     * @return The list of (latitude, longitude) pairs.
     */
    List<Pair<Double, Double>> getMockRunCoordinates();

    /**
     * Provides the interval (in milliseconds) between consecutive locations.
     *
     * @return the interval in milliseconds.
     */
    Long getIntervalMillis();
}
