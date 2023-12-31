package com.example.hostel.logic.commands;

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
import java.util.List;
import java.util.Map;

public class AdminRoomsListCommand implements ICommand {

    private final RoomsDAO roomsDAO = JdbcRoomsDAO.getInstance();
    private final OrdersDAO ordersDAO = JdbcOrdersDAO.getInstance();
    private static final String ROOMS_ATTRIBUTE = "rooms";

    @Override
    public String execute(HttpServletRequest request) throws CommandException, DaoException {
        String subCommand = request.getParameter("subCommand");
        if(!request.getMethod().equals("GET")) {


            switch (subCommand) {
                case "ADD_ROOM":
                    Long num = Long.valueOf(request.getParameter("num"));
                    float price = Float.parseFloat(request.getParameter("price"));
                    String description = request.getParameter("description");
                    addRoom(num, price, description);
                    break;
                case "DELETE_ROOM":
                    Long id = Long.valueOf(request.getParameter("id"));
                    deleteRoom(id);
                    break;
            }

        }
        request.setAttribute(ROOMS_ATTRIBUTE, findRooms());
        return JspPageName.ADMIN_ROOMS;
    }

    private void addRoom(Long num, float price, String description) {
        Rooms rooms = new Rooms(num, price, description);
        try {
            roomsDAO.addRoom(rooms);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteRoom(Long id) {
        try {
            ordersDAO.cancelOrders(id);
            roomsDAO.deleteRoom(id);
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
