package com.example.hostel.logic.commands;

import com.example.hostel.beans.rooms.Rooms;
import com.example.hostel.beans.user.User;
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
                    String discription = request.getParameter("discription");
                    request.setAttribute("message", addRoom(request, num, price, discription));
                    break;
                case "DELETE_ROOM":
                    Long id = Long.valueOf(request.getParameter("id"));
                    request.setAttribute("message", deleteRoom(request, id));
                    break;
            }

        }
        request.setAttribute(ROOMS_ATTRIBUTE, findRooms());
        return JspPageName.ADMIN_ROOMS;
    }

    private Map<String, String> addRoom(HttpServletRequest request, Long num, float price, String discription) {
        Rooms rooms = new Rooms(num, price, discription);
        try {
            return roomsDAO.addRoom(rooms);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, String> deleteRoom(HttpServletRequest request, Long id) {
        try {
            ordersDAO.cancelOrders(id);
            return roomsDAO.deleteRoom(id);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, String> editRoom(HttpServletRequest request, Long id, Long num, float price, String discription) {
        Rooms rooms = new Rooms(id, num, price, discription);
        try {
            ordersDAO.cancelOrders(id);
            return roomsDAO.updateRoom(rooms);
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
