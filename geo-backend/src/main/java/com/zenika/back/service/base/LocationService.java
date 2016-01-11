package com.zenika.back.service.base;

import com.zenika.back.message.Location;
import org.springframework.stereotype.Service;

/**
 * Location service interface.
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 01/01/2016.
 */
@Service
public interface LocationService {

    /**
     * Saves the given user location in the database.
     *
     * @param location The new user location.
     */
    void updateLocation(Location location);

    /**
     * Checks if the user crossed a fence.
     *
     * @param userId The user identifier.
     */
    void notifyFences(Long userId);
}
