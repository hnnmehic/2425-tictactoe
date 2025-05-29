package at.htlleonding.viergewinnt.controller;

import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.util.Objects;
import java.util.Properties;

public class Database {

    static final String appConfigPath = Objects.requireNonNull(
            Database.class.getResource("/database.properties")
    ).getPath();

    static final String DATABASE = "db";
    static final int PORT = 9092;
    static final String USERNAME = "app";
    static final String PASSWORD = "app";
    public static final String URL = String.format(
            "jdbc:h2:tcp://localhost:%d/%s",PORT,DATABASE
    );

    public static DataSource getDataSource() {

        Properties dbProperties = new Properties();

        try (FileInputStream inputStream = new FileInputStream(appConfigPath)){
            dbProperties.load(inputStream);

            JdbcDataSource dataSource = new JdbcDataSource();
            dataSource.setUser(dbProperties.getProperty("username"));
            dataSource.setPassword(dbProperties.getProperty("password"));
            String url = String.format("jdbc:h2:tcp://localhost:%s/%s",
                    dbProperties.getProperty("port")
            ,dbProperties.getProperty("database"));
            dataSource.setURL(url);
            return dataSource;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            JdbcDataSource dataSource = new JdbcDataSource();
            dataSource.setUser(USERNAME);
            dataSource.setPassword(PASSWORD);
            dataSource.setURL(URL);
            return dataSource;
        }
    }

}
