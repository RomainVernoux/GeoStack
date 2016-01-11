package com.zenika.back.controller;

import com.zenika.back.controller.base.UserController;
import com.zenika.back.message.GcmToken;
import com.zenika.back.service.base.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * User controller.
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 10/01/2016.
 */
@RestController
public class UserControllerImpl implements UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserControllerImpl.class);

    @Autowired
    private UserService userService;

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public void updateGcmToken(@RequestBody(required = true) GcmToken gcmToken) {
        logger.info("GCM token update received for user {}", gcmToken.getUserId());
        userService.updateGcmToken(gcmToken);
    }
}
