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
import java.util.Map;

public class UserOrdersListCommand implements ICommand {

    private final OrdersDAO ordersDAO = JdbcOrdersDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException, DaoException {
        Long userID = (Long) request.getSession().getAttribute("id");
        if(!request.getMethod().equals("GET")) {
            Long orderID = Long.valueOf(request.getParameter("orderID"));
            request.setAttribute("message", cancelOrder(orderID));
        }
        request.setAttribute("orders", findUserOrders(userID));
        return JspPageName.USER_ORDERS_LIST;
    }

    private Map<String, String> cancelOrder(Long orderID) {
        try {
            return ordersDAO.updateOrderStatus(orderID, OrderStatus.CANCELED);
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
