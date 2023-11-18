package com.example.hostel.dao;

import com.example.hostel.beans.rooms.Rooms;
import com.example.hostel.exceptions.DaoException;

import java.util.List;
import java.util.Map;

public interface RoomsDAO {


    Rooms findRoomByID(Long ID) throws DaoException;
    Map<String, String> updateRoom(Rooms room) throws DaoException;
    Map<String, String> addRoom(Rooms room) throws DaoException;
    Map<String, String> deleteRoom(Long ID) throws DaoException;
    List<Rooms> findAllRooms() throws DaoException;
}
