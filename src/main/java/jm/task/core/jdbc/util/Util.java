package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String USER_NAME = "admin";
    private static final String PASSWORD = "admin";
    private static final String CONNECTION_URL = "jdbc:mysql://localHost:3306/kata";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
    }
}
