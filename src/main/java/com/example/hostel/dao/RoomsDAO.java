package com.example.hostel.dao;

import com.example.hostel.beans.rooms.Rooms;
import com.example.hostel.exceptions.DaoException;

import java.util.List;
import java.util.Map;

/**
 * The RoomsDAO interface provides methods for accessing and manipulating room data in the database.
 */
public interface RoomsDAO {
    /**
     * Retrieves a room with the given ID.
     *
     * @param ID the ID of the room
     * @return the room with the given ID
     * @throws DaoException if there is an error retrieving the room
     */
    Rooms findRoomByID(Long ID) throws DaoException;

    /**
     * Updates the information of a room.
     *
     * @param room the room to be updated
     * @throws DaoException if there is an error updating the room
     */
    void updateRoom(Rooms room) throws DaoException;

    /**
     * Adds a new room to the database.
     *
     * @param room the room to be added
     * @throws DaoException if there is an error adding the room
     */
    void addRoom(Rooms room) throws DaoException;

    /**
     * Deletes a room with the given ID from the database.
     *
     * @param ID the ID of the room to be deleted
     * @throws DaoException if there is an error deleting the room
     */
    void deleteRoom(Long ID) throws DaoException;

    /**
     * Retrieves all rooms from the database.
     *
     * @return a list of all rooms
     * @throws DaoException if there is an error retrieving the rooms
     */
    List<Rooms> findAllRooms() throws DaoException;
}
