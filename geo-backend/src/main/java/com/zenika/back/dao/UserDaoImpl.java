package com.zenika.back.dao;

import com.zenika.back.dao.base.UserDao;
import com.zenika.back.message.GcmToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Locale;

/**
 * Location DAO.
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 01/01/2016.
 */
@Repository
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(DataSource datasource) {
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGcmToken(GcmToken gcmToken) {
        jdbcTemplate.update("UPDATE geo.t_user_us SET us_gcm_token = ? WHERE us_id = ?",
                gcmToken.getToken(), gcmToken.getUserId());
    }
}


