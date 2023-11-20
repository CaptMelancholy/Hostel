package com.example.hostel.beans.orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The OrdersExtractor class is responsible for extracting data from a ResultSet and converting it into a List of Orders objects.
 */
public class OrdersExtractor {

    /**
     * Extracts data from the given ResultSet and converts it into a List of Orders objects.
     *
     * @param resultSet The ResultSet containing the data to be extracted.
     * @return A List of Orders objects extracted from the ResultSet.
     * @throws SQLException If an error occurs while accessing the ResultSet.
     */
    public List<Orders> extractData(ResultSet resultSet) throws SQLException {
        List<Orders> orders = new ArrayList<>();
        while(resultSet.next()) {
            Orders order = new Orders();
            order.setId(resultSet.getLong("id_order"));
            order.setRoomID(resultSet.getLong("rooms_id_rooms"));
            order.setUserID(resultSet.getLong("user_id_user"));
            order.setType(OrderTypes.valueOf(resultSet.getString("order_type")));
            order.setName(resultSet.getString("order_name"));
            order.setSurname(resultSet.getString("order_surname"));
            order.setPhoneNumber(resultSet.getString("order_phonenumber"));
            order.setEmail(resultSet.getString("order_email"));
            order.setStatus(OrderStatus.valueOf(resultSet.getString("order_status")));
            order.setDate(resultSet.getTimestamp("order_date"));
            order.setOrderPaid(resultSet.getBoolean("order_paid"));
            order.setDateBegin(resultSet.getDate("order_startDate"));
            order.setDateEnd(resultSet.getDate("order_endDate"));
            orders.add(order);
        }
        return orders;
    }

}
