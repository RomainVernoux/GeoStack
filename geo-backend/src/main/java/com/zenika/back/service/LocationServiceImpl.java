package com.zenika.back.service;

import com.zenika.back.dao.base.FenceDao;
import com.zenika.back.dao.base.LocationDao;
import com.zenika.back.message.FenceCrossing;
import com.zenika.back.message.GcmMessage;
import com.zenika.back.message.Location;
import com.zenika.back.service.base.LocationService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Location service.
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 01/01/2016.
 */
@Service
public class LocationServiceImpl implements LocationService {

    private static final Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);

    @Value("${gcm.api.key}")
    private String API_KEY;

    @Autowired
    private LocationDao locationDao;

    @Autowired
    private FenceDao fenceDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateLocation(Location location) {
        locationDao.updateLocation(location);
        notifyFences(location.getUserId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyFences(Long userId) {
        List<FenceCrossing> crossings = fenceDao.checkFenceCrossing(userId);
        if (!crossings.isEmpty()) {
            logger.info("Geofences crossed: {}", crossings);
            for (FenceCrossing crossing: crossings) {
                try {
                    GcmMessage msg = new GcmMessage(crossing.getGcmToken(), crossing.toString());

                    // Create connection to send GCM Message request.
                    URL url = new URL("https://android.googleapis.com/gcm/send");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("Authorization", "key=" + API_KEY);
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);

                    // Send GCM message content.
                    OutputStream outputStream = conn.getOutputStream();
                    outputStream.write(msg.toString().getBytes());

                    // Read GCM response.
                    InputStream inputStream = conn.getInputStream();
                    String resp = IOUtils.toString(inputStream);
                    logger.info(resp);
                } catch (IOException e) {
                    logger.error("Unable to send GCM message.");
                    logger.error("Please ensure that API_KEY has been replaced by the server " +
                            "API key, and that the device's registration token is correct (if specified).");
                    e.printStackTrace();
                }
            }
        }
    }
}
