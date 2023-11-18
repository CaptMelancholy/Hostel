package com.example.hostel.dao;

import com.example.hostel.beans.orders.OrderStatus;
import com.example.hostel.beans.orders.Orders;
import com.example.hostel.beans.rooms.Rooms;
import com.example.hostel.exceptions.DaoException;

import java.util.List;
import java.util.Map;

public interface OrdersDAO {


    Map<String, String> cancelOrders(Long id) throws DaoException;

    Map<String, String> cancelOrdersFromBannedUser(Long id) throws DaoException;
    Map<String, String> addOrder(Orders orders) throws DaoException;

    List<Orders> findAllOrdersOfUser(Long id) throws DaoException;

    Map<String, String> updateOrderStatus(Long ID, OrderStatus status) throws DaoException;
    List<Orders> findAllOrders() throws DaoException;
}
