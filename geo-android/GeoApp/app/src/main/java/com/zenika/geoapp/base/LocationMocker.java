package com.zenika.geoapp.base;

import com.zenika.geoapp.mock.base.MockRun;

/**
 * Interface to mock device location.
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 05/01/2016.
 */
public interface LocationMocker {

    /**
     * Dispatches location change events according to the list of coordinates and time intervals
     * given by the provided mock run instance.
     *
     * @param mockRun The mock run instance.
     */
    void dispatchMockLocations(final MockRun mockRun);
}
