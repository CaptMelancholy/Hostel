package com.example.hostel.dao.impl;

import com.example.hostel.beans.rooms.Rooms;
import com.example.hostel.beans.rooms.RoomsExtractor;
import com.example.hostel.dao.RoomsDAO;
import com.example.hostel.exceptions.DaoException;
import com.example.hostel.uttils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class JdbcRoomsDAO implements RoomsDAO {

    private static volatile RoomsDAO instance;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final RoomsExtractor roomsExtractor = new RoomsExtractor();
    private static final String ADD_ROOM = "INSERT INTO rooms (rooms_guest_amount, rooms_price, rooms_discription, rooms_deleted) VALUE (?, ?, ?, ?)";

    private static final String DELETE_ROOM = "UPDATE rooms SET rooms_deleted = ? WHERE id_rooms = ?";

    private static final String FIND_BY_ID = "SELECT * FROM rooms WHERE id_rooms = ?";

    private static final String UPDATE_ROOM = "UPDATE rooms SET rooms_guest_amount = ?, rooms_price = ?, rooms_discription = ?, rooms_deleted = ? WHERE id_rooms = ?";

    private static final String FIND_ALL_ROOMS = "SELECT * FROM rooms WHERE rooms_deleted = false";
    public static RoomsDAO getInstance() {
        if(instance == null) {
            synchronized (RoomsDAO.class) {
                if(instance == null) {
                    instance = new JdbcRoomsDAO();
                }
            }
        }
        return instance;
    }
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public Rooms findRoomByID(Long ID) throws DaoException {
        Rooms room;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            lock.writeLock().lock();
            conn = connectionPool.getConnection();
            statement = conn.prepareStatement(FIND_BY_ID);
            statement.setLong(1, ID);
            ResultSet resultSet = statement.executeQuery();
            room = roomsExtractor.extractDataOnce(resultSet);
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
        return room;
    }

    @Override
    public Map<String, String> updateRoom(Rooms room) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            lock.writeLock().lock();
            conn = connectionPool.getConnection();
            statement = conn.prepareStatement(UPDATE_ROOM);
            statement.setLong(1, room.getNum());
            statement.setFloat(2, room.getPrice());
            statement.setString(3, room.getDiscription());
            statement.setBoolean(4, room.getDeleted());
            statement.setLong(5, room.getId());
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
    public Map<String, String> addRoom(Rooms room) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            lock.writeLock().lock();
            conn = connectionPool.getConnection();
            statement = conn.prepareStatement(ADD_ROOM);
            statement.setLong(1, room.getNum());
            statement.setFloat(2, room.getPrice());
            statement.setString(3, room.getDiscription());
            statement.setBoolean(4, room.getDeleted());
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
    public Map<String, String> deleteRoom(Long id) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            lock.writeLock().lock();
            conn = connectionPool.getConnection();
            statement = conn.prepareStatement(DELETE_ROOM);
            statement.setBoolean(1, true);
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
    public List<Rooms> findAllRooms() throws DaoException {
        List<Rooms> rooms;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
           lock.writeLock().lock();
           conn = connectionPool.getConnection();
           statement = conn.prepareStatement(FIND_ALL_ROOMS);
           ResultSet resultSet = statement.executeQuery();
           rooms = roomsExtractor.extractData(resultSet);
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
        return rooms;
    }
}
