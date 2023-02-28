package hello.jdbc.domain;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

public class SimpleConnectionMaker {
    public Connection makeNewConnection() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource.getConnection();
    }
}
