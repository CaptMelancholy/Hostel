package com.example.hostel.dao.impl;

import com.example.hostel.beans.user.User;
import com.example.hostel.beans.user.UserExtractor;
import com.example.hostel.dao.UserDAO;
import com.example.hostel.exceptions.DaoException;
import com.example.hostel.uttils.ConnectionPool;

import javax.servlet.http.HttpSession;
import java.sql.*;
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
    private final UserExtractor userExtractor = new UserExtractor();





    private static final String ADD_USER = "INSERT INTO user (user_login, user_password, user_name, user_surname, user_role, user_discount, user_ban, user_email) VALUES (?,?,?,?,?,?,?,?)";

    private static final String FIND_USER_BY_LOGIN_PASSWORD = "SELECT * FROM user WHERE user_login = ? AND user_password = ?";

    private static final String FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE user_login = ?";
    private static final String BAN_USER = "UPDATE user SET user_ban = ? WHERE user_login = ?";
    private static final String SET_ADMIN = "UPDATE user SET user_role = ? WHERE user_login = ?";
    private static final String SET_DISCOUNT = "UPDATE user SET user_discount = ? WHERE user_login = ?";
    private static final String FIND_ALL_USERS = "SELECT * FROM user";
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
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    @Override
    public Optional<User> findUser(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByLoginAndPass(String login, String password) throws DaoException {
        Optional<User> user;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            lock.readLock().lock();
            conn = connectionPool.getConnection();
            statement = conn.prepareStatement(FIND_USER_BY_LOGIN_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            user = userExtractor.extractData(resultSet).stream().findAny();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            lock.readLock().unlock();
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {

                }
            }
            if (conn != null) {
                connectionPool.releaseConnection(conn);
            }
        }
        return user;
    }

    @Override
    public User findUserByLogin(String login) throws DaoException {
        User user;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            lock.readLock().lock();
            conn = connectionPool.getConnection();
            statement = conn.prepareStatement(FIND_USER_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            user = userExtractor.extractDataOnce(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            lock.readLock().unlock();
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {

                }
            }
            if (conn != null) {
                connectionPool.releaseConnection(conn);
            }
        }
        return user;
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
            statement.setString(8, user.getEmail());
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
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            lock.writeLock().lock();
            conn = connectionPool.getConnection();
            statement = conn.prepareStatement(BAN_USER);
            statement.setBoolean(1, !user.getBanStatus());
            statement.setString(2, user.getLogin());
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
    public Map<String, String> setUserAdmin(User user) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            lock.writeLock().lock();
            conn = connectionPool.getConnection();
            statement = conn.prepareStatement(SET_ADMIN);
            statement.setBoolean(1, !user.getAdminRole());
            statement.setString(2, user.getLogin());
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
    public Map<String, String> setUserDiscount(User user) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            lock.writeLock().lock();
            conn = connectionPool.getConnection();
            statement = conn.prepareStatement(SET_DISCOUNT);
            statement.setFloat(1, user.getDiscount());
            statement.setString(2, user.getLogin());
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
    public void deleteUser(User user) throws DaoException {

    }

    @Override
    public List<User> findAllUsers() throws DaoException {
        List <User> users;
        Connection conn = null;
        Statement statement = null;
        try {
            lock.readLock().lock();
            conn = connectionPool.getConnection();
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS);
            users = userExtractor.extractData(resultSet);

        } catch (SQLException e) {
            throw new DaoException("Error");
        } finally {
            lock.readLock().unlock();
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {

                }
            }
            if (conn != null) {
                connectionPool.releaseConnection(conn);
            }
        }
        return users;
    }
}
