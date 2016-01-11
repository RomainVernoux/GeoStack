package com.zenika.back.controller;

import com.zenika.back.controller.base.LocationController;
import com.zenika.back.message.Location;
import com.zenika.back.service.base.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Location controller.
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 01/01/2016.
 */
@RestController
public class LocationControllerImpl implements LocationController {

    private static final Logger logger = LoggerFactory.getLogger(LocationControllerImpl.class);

    @Autowired
    private LocationService locationService;

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/location")
    public void updateLocation(@RequestBody(required = true) Location location) {
        logger.info("Location updated received: {}", location);
        locationService.updateLocation(location);
    }
}
