package com.zenika.geoapp.service.base;

import com.zenika.geoapp.message.LocationMessage;

import java.io.IOException;

/**
 * Interface for location services connecting to the corresponding REST entry point on the backend.
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 03/01/2016.
 */
public interface LocationService {

    /**
     * Posts the new location of this user to the remote server.
     *
     * @param location The user location.
     * @throws IOException When the post failed.
     */
    void updateLocation(LocationMessage location) throws IOException;
}
