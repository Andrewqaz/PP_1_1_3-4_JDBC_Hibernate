package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public static Connection getConnection() throws SQLException {
        String userName = "admin";
        String password = "admin";

        String connectionURL = "jdbc:mysql://localHost:3306/kata";
        return DriverManager.getConnection(connectionURL, userName, password);
    }


}
