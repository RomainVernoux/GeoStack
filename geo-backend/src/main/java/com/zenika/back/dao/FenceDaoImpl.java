package com.zenika.back.dao;

import com.zenika.back.dao.base.FenceDao;
import com.zenika.back.message.FenceCrossing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Fence DAO.
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 04/01/2016.
 */
@Repository
public class FenceDaoImpl implements FenceDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public FenceDaoImpl(DataSource datasource) {
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }

    @Override
    public List<FenceCrossing> checkFenceCrossing(Long userId) {
         List<Map<String, Object>> resultSet = jdbcTemplate.queryForList(
                "SELECT " +
                    "fe.fe_name AS name, " +
                    "us.us_gcm_token AS gcm_token " +
                "FROM " +
                    "geo.t_fence_fe fe, " +
                    "geo.t_user_us us " +
                "WHERE " +
                    "us.us_id = ?" +
                    "AND ST_Contains(fe.fe_geometry, (SELECT po.po_position FROM geo.t_position_po po WHERE po.us_id = ? ORDER BY po_time DESC LIMIT 1)) " +
                    "AND NOT ST_Contains(fe.fe_geometry, (SELECT po.po_position FROM geo.t_position_po po WHERE po.us_id = ? ORDER BY po_time DESC OFFSET 1 LIMIT 1))",
                userId, userId, userId);

        List<FenceCrossing> crossings = new ArrayList<>();
        for(Map<String, Object> result: resultSet) {
            FenceCrossing crossing = new FenceCrossing();
            crossing.setFenceName((String) result.get("name"));
            crossing.setGcmToken((String) result.get("gcm_token"));
            crossing.setUserId(userId);
            crossings.add(crossing);
        }

        return crossings;
    }
}


