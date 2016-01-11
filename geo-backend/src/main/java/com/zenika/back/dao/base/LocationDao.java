package com.zenika.back.dao.base;

import com.zenika.back.message.Location;
import org.springframework.stereotype.Repository;

/**
 * Location DAO interface
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 01/01/2016.
 */
@Repository
public interface LocationDao {

    /**
     * Saves the given user location in the database.
     *
     * @param location The new user location.
     */
    void updateLocation(Location location);
}


