package com.example.hostel.dao.impl;

import com.example.hostel.beans.rooms.Rooms;
import com.example.hostel.beans.rooms.RoomsExtractor;
import com.example.hostel.dao.RoomsDAO;
import com.example.hostel.exceptions.DaoException;
import com.example.hostel.uttils.ConnectionPool;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * The JdbcRoomsDAO class implements the RoomsDAO interface and provides
 * functionality to interact with the database for orders related operations.
 */
public class JdbcRoomsDAO implements RoomsDAO {

    private static volatile RoomsDAO instance;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final RoomsExtractor roomsExtractor = new RoomsExtractor();
    private static final Logger logger = Logger.getLogger(RoomsDAO.class);
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
            logger.log(Level.INFO, "Success in finding room by ID");
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error in findRoomByID", e);
            throw new DaoException("Error in finding room by ID");
        } finally {
            lock.writeLock().unlock();
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR, "Error in findRoomByID", e);
                }
            }
            if (conn != null) {
                connectionPool.releaseConnection(conn);
            }
        }
        return room;
    }

    @Override
    public void updateRoom(Rooms room) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            lock.writeLock().lock();
            conn = connectionPool.getConnection();
            statement = conn.prepareStatement(UPDATE_ROOM);
            statement.setLong(1, room.getNum());
            statement.setFloat(2, room.getPrice());
            statement.setString(3, room.getDescription());
            statement.setBoolean(4, room.getDeleted());
            statement.setLong(5, room.getId());
            statement.executeUpdate();
            logger.log(Level.INFO, "Success in in updating room");
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error in updateRoom", e);
            throw new DaoException("Error in updating room");
        } finally {
            lock.writeLock().unlock();
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR, "Error in updateRoom", e);
                }
            }
            if (conn != null) {
                connectionPool.releaseConnection(conn);
            }
        }
    }

    @Override
    public void addRoom(Rooms room) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            lock.writeLock().lock();
            conn = connectionPool.getConnection();
            statement = conn.prepareStatement(ADD_ROOM);
            statement.setLong(1, room.getNum());
            statement.setFloat(2, room.getPrice());
            statement.setString(3, room.getDescription());
            statement.setBoolean(4, room.getDeleted());
            statement.executeUpdate();
            logger.log(Level.INFO, "Success in updating room");
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error in addRoom", e);
            throw new DaoException("Error in add room");
        } finally {
            lock.writeLock().unlock();
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR, "Error in addRoom", e);
                }
            }
            if (conn != null) {
                connectionPool.releaseConnection(conn);
            }
        }
    }

    @Override
    public void deleteRoom(Long id) throws DaoException {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            lock.writeLock().lock();
            conn = connectionPool.getConnection();
            statement = conn.prepareStatement(DELETE_ROOM);
            statement.setBoolean(1, true);
            statement.setLong(2, id);
            statement.executeUpdate();
            logger.log(Level.INFO, "Success in deleting room");
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error in deleteRoom", e);
            throw new DaoException("Error in delete room");
        } finally {
            lock.writeLock().unlock();
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR, "Error in deleteRoom", e);
                }
            }
            if (conn != null) {
                connectionPool.releaseConnection(conn);
            }
        }
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
            logger.log(Level.INFO, "Success in searching all rooms");
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error in findAllRooms", e);
            throw new DaoException("Error in searching all rooms");
        } finally {
            lock.writeLock().unlock();
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR, "Error in findAllRooms", e);
                }
            }
            if (conn != null) {
                connectionPool.releaseConnection(conn);
            }
        }
        return rooms;
    }
}
