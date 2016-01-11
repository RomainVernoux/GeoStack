package com.zenika.back.controller.base;

import com.zenika.back.message.Location;
import org.springframework.web.bind.annotation.*;

/**
 * Location controller interface.
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 01/01/2016.
 */
@RestController
public interface LocationController {

    /**
     * Saves the given user location in the database.
     *
     * @param location The new user location.
     */
    void updateLocation(Location location);
}
