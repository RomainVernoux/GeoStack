package com.zenika.back.config;

import javax.sql.DataSource;

import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Application configuration
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 01/01/2016.
 */
@Configuration
public class ContextConfiguration {

    @Value("${db.server.address}")
    String dbServerAddress;

    @Value("${db.server.port}")
    Integer dbServerPort;

    @Value("${db.name}")
    String dbName;

    @Value("${db.user}")
    String dbUser;

    @Value("${db.password}")
    String dbPassword;

    @Value("${db.max.connections}")
    Integer dbMaxConn;

    @Bean
    public DataSource dataSource() {
        final PGPoolingDataSource source = new PGPoolingDataSource();
        source.setDataSourceName("geo-db");
        source.setServerName(dbServerAddress);
        source.setPortNumber(dbServerPort);
        source.setDatabaseName(dbName);
        source.setUser(dbUser);
        source.setPassword(dbPassword);
        source.setMaxConnections(dbMaxConn);
        return source;
    }

}
