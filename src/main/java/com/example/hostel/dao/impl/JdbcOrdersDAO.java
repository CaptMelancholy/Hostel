package com.example.hostel.dao.impl;

import com.example.hostel.beans.orders.OrderStatus;
import com.example.hostel.beans.orders.Orders;
import com.example.hostel.beans.orders.OrdersExtractor;
import com.example.hostel.dao.OrdersDAO;
import com.example.hostel.dao.RoomsDAO;
import com.example.hostel.exceptions.DaoException;
import com.example.hostel.uttils.ConnectionPool;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class JdbcOrdersDAO implements OrdersDAO {

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
    public Map<String, String> cancelOrders(Long id) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            lock.writeLock().lock();
            conn = connectionPool.getConnection();
            statement = conn.prepareStatement(CANCEL_ALL_DELETED);
            statement.setString(1, OrderStatus.CANCELED.toString());
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
    public Map<String, String> cancelOrdersFromBannedUser(Long id) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            lock.writeLock().lock();
            conn = connectionPool.getConnection();
            statement = conn.prepareStatement(CANCEL_BANNED_USER_ORDER);
            statement.setString(1, OrderStatus.CANCELED.toString());
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
    public Map<String, String> addOrder(Orders orders) throws DaoException {
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
        return orders;
    }

    @Override
    public Map<String, String> updateOrderStatus(Long ID, OrderStatus status) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            lock.writeLock().lock();
            conn = connectionPool.getConnection();
            statement = conn.prepareStatement(UPDATE_ORDER_STATUS);
            statement.setString(1, status.toString());
            statement.setLong(2, ID);
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
        return orders;
    }
}
