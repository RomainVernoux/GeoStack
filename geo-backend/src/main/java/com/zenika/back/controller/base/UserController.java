package com.zenika.back.controller.base;

import com.zenika.back.message.GcmToken;
import org.springframework.web.bind.annotation.RestController;

/**
 * User controller interface.
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 10/01/2016.
 */
@RestController
public interface UserController {

    /**
     * Saves the given user GCM token in the database.
     *
     * @param gcmToken The new GCM token.
     */
    void updateGcmToken(GcmToken gcmToken);
}
