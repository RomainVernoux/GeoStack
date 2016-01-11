package com.zenika.back.service.base;

import com.zenika.back.message.GcmToken;
import org.springframework.stereotype.Service;

/**
 * User service interface.
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 10/01/2016.
 */
@Service
public interface UserService {

    /**
     * Saves the given user GCM token in the database.
     *
     * @param gcmToken The new GCM token.
     */
    void updateGcmToken(GcmToken gcmToken);
}
