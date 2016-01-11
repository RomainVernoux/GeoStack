package com.zenika.back.dao.base;

import com.zenika.back.message.GcmToken;
import org.springframework.stereotype.Repository;

/**
 * User DAO interface
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 10/01/2016.
 */
@Repository
public interface UserDao {

    /**
     * Saves the given user GCM token in the database.
     *
     * @param gcmToken The new GCM token.
     */
    void updateGcmToken(GcmToken gcmToken);
}


