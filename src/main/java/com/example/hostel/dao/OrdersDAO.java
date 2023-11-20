package com.example.hostel.dao;

import com.example.hostel.beans.orders.OrderStatus;
import com.example.hostel.beans.orders.Orders;
import com.example.hostel.exceptions.DaoException;

import java.util.List;

/**
 * The OrdersDAO interface provides methods for accessing and manipulating orders data in the database.
 */
public interface OrdersDAO {
    /**
     * Cancels all orders associated with the given ID.
     *
     * @param id the ID of the orders to be canceled
     * @throws DaoException if there is an error canceling the orders
     */
    void cancelOrders(Long id) throws DaoException;

    /**
     * Cancels all orders from a banned user with the given ID.
     *
     * @param id the ID of the banned user
     * @throws DaoException if there is an error canceling the orders
     */
    void cancelOrdersFromBannedUser(Long id) throws DaoException;

    /**
     * Adds a new order to the database.
     *
     * @param orders the order to be added
     * @throws DaoException if there is an error adding the order
     */
    void addOrder(Orders orders) throws DaoException;

    /**
     * Retrieves all orders associated with the given user ID.
     *
     * @param id the ID of the user
     * @return a list of orders associated with the user
     * @throws DaoException if there is an error retrieving the orders
     */
    List<Orders> findAllOrdersOfUser(Long id) throws DaoException;

    /**
     * Updates the status of an order with the given ID.
     *
     * @param ID     the ID of the order
     * @param status the new status of the order
     * @throws DaoException if there is an error updating the order status
     */
    void updateOrderStatus(Long ID, OrderStatus status) throws DaoException;

    /**
     * Retrieves all orders from the database.
     *
     * @return a list of all orders
     * @throws DaoException if there is an error retrieving the orders
     */
    List<Orders> findAllOrders() throws DaoException;
}
