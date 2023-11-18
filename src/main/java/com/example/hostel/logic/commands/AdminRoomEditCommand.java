package com.example.hostel.logic.commands;

import com.example.hostel.beans.rooms.Rooms;
import com.example.hostel.controller.JspPageName;
import com.example.hostel.dao.OrdersDAO;
import com.example.hostel.dao.RoomsDAO;
import com.example.hostel.dao.impl.JdbcOrdersDAO;
import com.example.hostel.dao.impl.JdbcRoomsDAO;
import com.example.hostel.exceptions.CommandException;
import com.example.hostel.exceptions.DaoException;
import com.example.hostel.logic.CommandHelper;
import com.example.hostel.logic.CommandName;
import com.example.hostel.logic.ICommand;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class AdminRoomEditCommand implements ICommand {
    private final RoomsDAO roomsDAO = JdbcRoomsDAO.getInstance();
    private final OrdersDAO ordersDAO = JdbcOrdersDAO.getInstance();
    private static final String ROOM_ATTRIBUTE = "room";
    @Override
    public String execute(HttpServletRequest request) throws CommandException, DaoException {
        Long id = Long.valueOf(request.getParameter("id"));
        String subCommand = request.getParameter("subCommand");
        if(!request.getMethod().equals("GET") && subCommand != null) {
            Long num = Long.valueOf(request.getParameter("num"));
            float price = Float.parseFloat(request.getParameter("price"));
            String discription = request.getParameter("discription");
            request.setAttribute("message", editRoom(request, id, num, price, discription));
            return JspPageName.MAIN_PAGE;
        }
        request.setAttribute(ROOM_ATTRIBUTE, findRoomByID(id));
        return JspPageName.EDIT_ROOM;
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

    private Rooms findRoomByID(Long ID) throws CommandException {
        try {
            return roomsDAO.findRoomByID(ID);
        } catch (DaoException e) {
            throw new CommandException(e.getMessage());
        }
    }
}
