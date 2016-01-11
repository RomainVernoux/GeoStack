package com.zenika.geoapp.service.base;

import com.zenika.geoapp.message.GcmToken;
import com.zenika.geoapp.message.LocationMessage;

import java.io.IOException;

/**
 * Interface for user services connecting to the corresponding REST entry point on the backend.
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 10/01/2016.
 */
public interface UserService {

    /**
     * Posts the new user GCM token to the remote server.
     *
     * @param gcmToken The new GCM token.
     * @throws IOException When the post failed.
     */
    void updateGcmToken(GcmToken gcmToken) throws IOException;
}
