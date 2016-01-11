package com.zenika.back.dao.base;

import com.zenika.back.message.FenceCrossing;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Geofence DAO interface
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 04/01/2016.
 */
@Repository
public interface FenceDao {

    /**
     * Checks whether the user crossed registered fences.
     *
     * @param userId The user identifier.
     */
    List<FenceCrossing> checkFenceCrossing(Long userId);
}


