package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД

    private static Connection connection;
    private static final String USER_NAME = "admin";
    private static final String PASSWORD = "admin";
    private static final String CONNECTION_URL = "jdbc:mysql://localHost:3306/kata";


    private static SessionFactory sessionFactory;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            return DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Properties properties = new Properties();

            properties.setProperty("hibernate.connection.url", CONNECTION_URL);
            properties.setProperty("hibernate.connection.username", USER_NAME);
            properties.setProperty("hibernate.connection.password", PASSWORD);
            properties.setProperty("show_sql", "true");
            properties.setProperty("hibernate.connection.characterEncoding", "utf8");
            properties.setProperty("hibernate.current_session_context_class", "thread");

            Configuration configuration = new Configuration().addProperties(properties);
            configuration.addAnnotatedClass(User.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
        return sessionFactory;
    }


}
