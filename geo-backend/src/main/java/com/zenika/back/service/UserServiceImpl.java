package com.zenika.back.service;

import com.zenika.back.dao.base.UserDao;
import com.zenika.back.message.GcmToken;
import com.zenika.back.service.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User service.
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 10/01/2016.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGcmToken(GcmToken gcmToken) {
        userDao.updateGcmToken(gcmToken);
    }
}
