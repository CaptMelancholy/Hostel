package com.example.hostel.beans.rooms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The RoomsExtractor class is responsible for extracting data from a ResultSet and converting it into a List of Rooms objects.
 */
public class RoomsExtractor {

    /**
     * Extracts data from the given ResultSet and converts it into a List of Rooms objects.
     *
     * @param resultSet The ResultSet containing the data to be extracted.
     * @return A List of Rooms objects extracted from the ResultSet.
     * @throws SQLException If an error occurs while accessing the ResultSet.
     */
    public List<Rooms> extractData(ResultSet resultSet) throws SQLException {
        List<Rooms> rooms = new ArrayList<>();
        while (resultSet.next()) {
            Rooms room = new Rooms();
            room.setId(resultSet.getLong("id_rooms"));
            room.setNum(resultSet.getLong("rooms_guest_amount"));
            room.setPrice(resultSet.getFloat("rooms_price"));
            room.setDescription(resultSet.getString("rooms_discription"));
            room.setDeleted(resultSet.getBoolean("rooms_deleted"));
            rooms.add(room);
        }
        return rooms;
    }

    /**
     * Extracts data from the given ResultSet and converts it into a single Rooms object.
     * This method assumes that the ResultSet contains only one row of data.
     *
     * @param resultSet The ResultSet containing the data to be extracted.
     * @return A single Rooms object extracted from the ResultSet.
     * @throws SQLException If an error occurs while accessing the ResultSet.
     */
    public Rooms extractDataOnce(ResultSet resultSet) throws SQLException {
        Rooms room = new Rooms();
        resultSet.next();
        room.setId(resultSet.getLong("id_rooms"));
        room.setNum(resultSet.getLong("rooms_guest_amount"));
        room.setPrice(resultSet.getFloat("rooms_price"));
        room.setDescription(resultSet.getString("rooms_discription"));
        room.setDeleted(resultSet.getBoolean("rooms_deleted"));
        return room;
    }
}
