package com.example.hostel.dao;

import com.example.hostel.beans.rooms.Rooms;
import com.example.hostel.exceptions.DaoException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RoomsDAO {


    Rooms findRoomByID(Long ID) throws DaoException;
    Map<String, String> updateRoom(Rooms room) throws DaoException;
    Map<String, String> addRoom(Rooms room) throws DaoException;
    Map<String, String> deleteRoom(Long ID) throws DaoException;
    List<Rooms> findAllRooms() throws DaoException;
}
