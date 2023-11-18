package com.example.hostel.logic.commands;

import com.example.hostel.beans.orders.OrderTypes;
import com.example.hostel.beans.orders.Orders;
import com.example.hostel.beans.rooms.Rooms;
import com.example.hostel.controller.JspPageName;
import com.example.hostel.dao.OrdersDAO;
import com.example.hostel.dao.RoomsDAO;
import com.example.hostel.dao.impl.JdbcOrdersDAO;
import com.example.hostel.dao.impl.JdbcRoomsDAO;
import com.example.hostel.exceptions.CommandException;
import com.example.hostel.exceptions.DaoException;
import com.example.hostel.logic.ICommand;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserOrderCommand implements ICommand {
    private final OrdersDAO ordersDAO = JdbcOrdersDAO.getInstance();
    private final RoomsDAO roomsDAO = JdbcRoomsDAO.getInstance();
    @Override
    public String execute(HttpServletRequest request) throws CommandException, DaoException, ParseException {

        if(!request.getMethod().equals("GET")) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Long roomID = Long.valueOf(request.getParameter("roomID"));
            Long userID = (Long) request.getSession().getAttribute("id");
            OrderTypes orderType = OrderTypes.valueOf(request.getParameter("type"));
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String phoneNumber = request.getParameter("phone");
            String email = request.getParameter("email");
            Date date = new Date();
            Boolean paid = orderType == OrderTypes.PAID;
            Date startDate = format.parse(request.getParameter("startDate"));
            Date endDate = format.parse(request.getParameter("endDate"));
            request.setAttribute("message", addOrder(new Orders(roomID, userID, orderType, name, surname, phoneNumber, email, date, paid, startDate, endDate)));
        }
        request.setAttribute("rooms", findRooms());
        return JspPageName.USER_ORDERS;
    }

    private Map<String, String> addOrder(Orders order)
    {
        try {
            return ordersDAO.addOrder(order);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Rooms> findRooms() throws CommandException {
        try {
            return roomsDAO.findAllRooms();
        } catch (DaoException e) {
            throw new CommandException(e.getMessage());
        }
    }
}
