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

public class AdminOrdersCommand implements ICommand {
    private final OrdersDAO ordersDAO = JdbcOrdersDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException, DaoException {
        String subCommand = request.getParameter("subCommand");
        if(!request.getMethod().equals("GET")) {
            Long id = Long.valueOf(request.getParameter("id"));
            switch (subCommand) {
                case "APPROVE_ORDER":
                    request.setAttribute("message", changeOrderStatus(id, OrderStatus.APPROVED));
                    break;

                case "REJECT_ORDER":
                    request.setAttribute("message", changeOrderStatus(id, OrderStatus.REJECTED));
                    break;
            }
        }
        request.setAttribute("orders", findUsersOrders());
        return JspPageName.ADMIN_ORDERS;
    }

    private Map<String, String> changeOrderStatus(Long orderID, OrderStatus status) {
        try {
            return ordersDAO.updateOrderStatus(orderID, status);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
    private List<Orders> findUsersOrders() {
        try {
            return ordersDAO.findAllOrders();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
}
