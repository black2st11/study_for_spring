package hello.jdbc.domain;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
    public Connection makeConnection() throws SQLException;
}
