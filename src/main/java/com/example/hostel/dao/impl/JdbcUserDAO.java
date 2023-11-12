package com.example.hostel.dao.impl;

import com.example.hostel.beans.user.User;
import com.example.hostel.beans.user.UserExtractor;
import com.example.hostel.dao.UserDAO;
import com.example.hostel.exceptions.DaoException;
import com.example.hostel.uttils.ConnectionPool;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class JdbcUserDAO implements UserDAO {
    private static volatile UserDAO instance;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    /**
     * UsersExtractor
     */
    private UserExtractor userExtractor = new UserExtractor();




    public static String ADD_USER = "INSERT INTO user (user_login, user_password, user_name, user_surname, user_role, user_discount, user_ban) VALUES (?,?,?,?,?,?,?)";
    public static UserDAO getInstance() {
        if (instance == null) {
            synchronized (UserDAO.class) {
                if (instance == null) {
                    instance = new JdbcUserDAO();
                }
            }
        }
        return instance;
    }
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    @Override
    public Optional<User> findUser(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByLoginAndPass(String login, String password) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Map<String, String> addUser(User user, HttpSession session) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            lock.writeLock().lock();
            conn = connectionPool.getConnection();
            statement = conn.prepareStatement(ADD_USER);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getUserName());
            statement.setString(4, user.getUserSurname());
            statement.setBoolean(5, user.getAdminRole());
            statement.setFloat(6, user.getDiscount());
            statement.setBoolean(7, user.getBanStatus());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("error in add");
        } finally {
            lock.writeLock().unlock();
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    throw new DaoException("fuck");
                }
            }
            if (conn != null) {
                connectionPool.releaseConnection(conn);
            }
        }

        return null;
    }

    @Override
    public Map<String, String> setUserBan(User user) throws DaoException {
        return null;
    }

    @Override
    public Map<String, String> setUserDiscount(User user) throws DaoException {
        return null;
    }

    @Override
    public void deleteUser(User user) throws DaoException {

    }

    @Override
    public List<User> findAllUsers() throws DaoException {
        return null;
    }
}
