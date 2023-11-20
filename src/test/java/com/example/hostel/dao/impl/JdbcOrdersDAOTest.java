package com.example.hostel.dao.impl;

import com.example.hostel.beans.orders.Orders;
import com.example.hostel.dao.OrdersDAO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JdbcOrdersDAOTest {

    private static Orders orders;
    private final OrdersDAO ordersDAO = JdbcOrdersDAO.getInstance();

    @Test
    void cancelOrders() {

    }

    @Test
    void cancelOrdersFromBannedUser() {
    }

    @Test
    void addOrder() {
    }

    @Test
    void findAllOrdersOfUser() {
    }

    @Test
    void updateOrderStatus() {
    }

    @Test
    void findAllOrders() {
    }
}