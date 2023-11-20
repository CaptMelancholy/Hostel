package com.example.hostel.logic.commands;

import com.example.hostel.beans.orders.OrderStatus;
import com.example.hostel.beans.orders.Orders;
import com.example.hostel.controller.JspPageName;
import com.example.hostel.dao.OrdersDAO;
import com.example.hostel.dao.impl.JdbcOrdersDAO;
import com.example.hostel.exceptions.CommandException;
import com.example.hostel.exceptions.DaoException;
import com.example.hostel.logic.ICommand;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserOrdersListCommand implements ICommand {

    private final OrdersDAO ordersDAO = JdbcOrdersDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException, DaoException {
        Long userID = (Long) request.getSession().getAttribute("id");
        if(!request.getMethod().equals("GET")) {
            Long orderID = Long.valueOf(request.getParameter("orderID"));
            cancelOrder(orderID);
        }
        request.setAttribute("orders", findUserOrders(userID));
        return JspPageName.USER_ORDERS_LIST;
    }

    private void cancelOrder(Long orderID) {
        try {
            ordersDAO.updateOrderStatus(orderID, OrderStatus.CANCELED);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
    private List<Orders> findUserOrders(Long userID) {
        try {
            return ordersDAO.findAllOrdersOfUser(userID);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
}
