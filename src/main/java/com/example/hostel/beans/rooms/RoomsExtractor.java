package com.example.hostel.beans.rooms;

import com.example.hostel.beans.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomsExtractor {
    public List<Rooms> extractData(ResultSet resultSet) throws SQLException {
        List<Rooms> rooms = new ArrayList<>();
        while (resultSet.next()) {
            Rooms room = new Rooms();
            room.setId(resultSet.getLong("id_rooms"));
            room.setNum(resultSet.getLong("rooms_guest_amount"));
            room.setPrice(resultSet.getFloat("rooms_price"));
            room.setDiscription(resultSet.getString("rooms_discription"));
            room.setDeleted(resultSet.getBoolean("rooms_deleted"));
            rooms.add(room);
        }
        return rooms;
    }

    public Rooms extractDataOnce(ResultSet resultSet) throws SQLException {
        Rooms room = new Rooms();
        resultSet.next();
        room.setId(resultSet.getLong("id_rooms"));
        room.setNum(resultSet.getLong("rooms_guest_amount"));
        room.setPrice(resultSet.getFloat("rooms_price"));
        room.setDiscription(resultSet.getString("rooms_discription"));
        room.setDeleted(resultSet.getBoolean("rooms_deleted"));
        return room;
    }
}
