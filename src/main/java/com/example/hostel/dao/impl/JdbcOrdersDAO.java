package com.example.hostel.dao.impl;

import com.example.hostel.beans.orders.OrderStatus;
import com.example.hostel.beans.orders.Orders;
import com.example.hostel.beans.orders.OrdersExtractor;
import com.example.hostel.dao.OrdersDAO;
import com.example.hostel.dao.RoomsDAO;
import com.example.hostel.exceptions.DaoException;
import com.example.hostel.uttils.ConnectionPool;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * The JdbcOrdersDAO class implements the OrdersDAO interface and provides
 * functionality to interact with the database for orders related operations.
 */
public class JdbcOrdersDAO implements OrdersDAO {

    private static final Logger logger = Logger.getLogger(OrdersDAO.class);
    private static volatile OrdersDAO instance;

    private  final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final OrdersExtractor ordersExtractor = new OrdersExtractor();
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String CANCEL_ALL_DELETED = "UPDATE orders SET order_status = ? WHERE rooms_id_rooms = ?";
    private static final String ADD_ORDER = "INSERT INTO orders (rooms_id_rooms, user_id_user, order_type, order_name, order_surname, order_phonenumber, order_email, order_status, order_date, order_paid, order_startDate, order_endDate) VALUE (?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_ORDER_STATUS = "UPDATE orders SET order_status = ? WHERE id_order = ?";
    private static final String FIND_ALL_ORDERS = "SELECT * FROM orders";
    private static final String CANCEL_BANNED_USER_ORDER = "UPDATE orders SET order_status = ? WHERE user_id_user = ?";

    private static final String FIND_USERS_ORDERS = "SELECT * FROM orders WHERE user_id_user = ?";
    public static OrdersDAO getInstance() {
        if(instance == null) {
            synchronized (RoomsDAO.class) {
                if(instance == null) {
                    instance = new JdbcOrdersDAO();
                }
            }
        }
        return instance;
    }


    @Override
    public void cancelOrders(Long id) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            lock.writeLock().lock();
            conn = connectionPool.getConnection();
            statement = conn.prepareStatement(CANCEL_ALL_DELETED);
            statement.setString(1, OrderStatus.CANCELED.toString());
            statement.setLong(2, id);
            statement.executeUpdate();
            logger.log(Level.INFO, "Orders Canceled");
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error in cancelOrders", e);
            throw new DaoException("Error in order cancellation");
        } finally {
            lock.writeLock().unlock();
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    logger.log(Level.ERROR, "Error in cancelOrders", ex);
                }
            }
            if (conn != null) {
                connectionPool.releaseConnection(conn);
            }
        }

    }

    @Override
    public void cancelOrdersFromBannedUser(Long id) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            lock.writeLock().lock();
            conn = connectionPool.getConnection();
            statement = conn.prepareStatement(CANCEL_BANNED_USER_ORDER);
            statement.setString(1, OrderStatus.CANCELED.toString());
            statement.setLong(2, id);
            statement.executeUpdate();
            logger.log(Level.INFO, "Success cancel orders from banned user " + id);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error in cancelOrdersFromBannedUser", e);
            throw new DaoException("Error in order cancellation of banned users");
        } finally {
            lock.writeLock().unlock();
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    logger.log(Level.ERROR, "Error in cancelOrdersFromBannedUser", ex);
                }
            }
            if (conn != null) {
                connectionPool.releaseConnection(conn);
            }
        }
    }

    @Override
    public void addOrder(Orders orders) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            lock.writeLock().lock();
            conn = connectionPool.getConnection();
            statement = conn.prepareStatement(ADD_ORDER);
            statement.setLong(1, orders.getRoomID());
            statement.setLong(2, orders.getUserID());
            statement.setString(3, orders.getType().toString());
            statement.setString(4, orders.getName());
            statement.setString(5, orders.getSurname());
            statement.setString(6, orders.getPhoneNumber());
            statement.setString(7, orders.getEmail());
            statement.setString(8, orders.getStatus().toString());
            statement.setTimestamp(9, new Timestamp(orders.getDate().getTime()));
            statement.setBoolean(10, orders.getOrderPaid());
            statement.setDate(11, new Date(orders.getDateBegin().getTime()));
            statement.setDate(12, new Date(orders.getDateEnd().getTime()));
            statement.executeUpdate();
            logger.log(Level.INFO, "Success in adding order");
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error in addOrder", e);
            throw new DaoException("Error in adding order");
        } finally {
            lock.writeLock().unlock();
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR, "Error in addOrder", e);
                }
            }
            if (conn != null) {
                connectionPool.releaseConnection(conn);
            }
        }
    }

    @Override
    public List<Orders> findAllOrdersOfUser(Long id) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        List<Orders> orders;
        try {
            lock.writeLock().lock();
            conn = connectionPool.getConnection();
            statement = conn.prepareStatement(FIND_USERS_ORDERS);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            orders = ordersExtractor.extractData(resultSet);
            logger.log(Level.INFO, "Success in find all orders of user " + id);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error in findAllOrdersOfUser", e);
            throw new DaoException("Error in order finding all orders of user");
        } finally {
            lock.writeLock().unlock();
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR, "Error in findAllOrdersOfUser", e);
                }
            }
            if (conn != null) {
                connectionPool.releaseConnection(conn);
            }
        }
        return orders;
    }

    @Override
    public void updateOrderStatus(Long ID, OrderStatus status) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            lock.writeLock().lock();
            conn = connectionPool.getConnection();
            statement = conn.prepareStatement(UPDATE_ORDER_STATUS);
            statement.setString(1, status.toString());
            statement.setLong(2, ID);
            statement.executeUpdate();
            logger.log(Level.INFO, "Success in update order status " + ID);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error in updateOrderStatus", e);
            throw new DaoException("Error in update order statuses");
        } finally {
            lock.writeLock().unlock();
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR, "Error in updateOrderStatus", e);
                }
            }
            if (conn != null) {
                connectionPool.releaseConnection(conn);
            }
        }
    }

    @Override
    public List<Orders> findAllOrders() throws DaoException {
        List<Orders> orders;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            lock.writeLock().lock();
            conn = connectionPool.getConnection();
            statement = conn.prepareStatement(FIND_ALL_ORDERS);
            ResultSet resultSet = statement.executeQuery();
            orders = ordersExtractor.extractData(resultSet);
            logger.log(Level.INFO, "Success in find all orders");
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error in findAllOrders", e);
            throw new DaoException("Error in order finding all orders");
        } finally {
            lock.writeLock().unlock();
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR, "Error in findAllOrders", e);
                }
            }
            if (conn != null) {
                connectionPool.releaseConnection(conn);
            }
        }
        return orders;
    }
}
