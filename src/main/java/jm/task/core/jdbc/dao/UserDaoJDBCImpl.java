package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String query = "CREATE TABLE IF NOT EXISTS user(" +
                "user_id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "user_name VARCHAR(40)," +
                "user_last_name VARCHAR(40)," +
                "age INT" +
                ")";
        Connection connection = Util.getConnection();
        try (connection) {
            connection.setAutoCommit(false);
            Statement st = connection.createStatement();
            st.executeUpdate(query);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS user";
        Connection connection = Util.getConnection();
        try (connection) {
            connection.setAutoCommit(false);
            Statement st = connection.createStatement();
            st.executeUpdate(query);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO user (user_name, user_last_name, age) VALUE (?, ?, ?)";
        Connection connection = Util.getConnection();
        try (connection) {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setInt(3, age);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM user WHERE user_id = ?";
        Connection connection = Util.getConnection();
        try (connection) {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (Connection connection = Util.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                byte age = resultSet.getByte(4);
                User user = new User(name, lastName, age);
                user.setId(id);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM user";
        Connection connection = Util.getConnection();
        try (connection) {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
