package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    private static Connection connection;
    private static final String USER_NAME = "admin";
    private static final String PASSWORD = "admin";
    private static final String CONNECTION_URL = "jdbc:mysql://localHost:3306/kata";


    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            return DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
        }
        return connection;
    }

    public static void closeConnection(){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
