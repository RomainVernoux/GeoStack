package com.zenika.back.dao;

import com.zenika.back.dao.base.LocationDao;
import com.zenika.back.message.Location;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Locale;

/**
 * Location DAO.
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 01/01/2016.
 */
@Repository
public class LocationDaoImpl implements LocationDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public LocationDaoImpl(DataSource datasource) {
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateLocation(Location location) {
        jdbcTemplate.update(
                "INSERT INTO " +
                        "geo.t_position_po (us_id, po_position, po_time) " +
                "VALUES " +
                        "(?, ST_MakePoint(?, ?), to_timestamp(?))",
                location.getUserId(), location.getLongitude(), location.getLatitude(), location.getTimestamp() / 1000);
    }
}


