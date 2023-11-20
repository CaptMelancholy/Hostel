package com.example.hostel.controller;

import com.example.hostel.exceptions.CommandException;
import com.example.hostel.exceptions.DaoException;
import com.example.hostel.logic.CommandHelper;
import com.example.hostel.logic.ICommand;

import java.io.*;
import java.text.ParseException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;


public class FrontController extends HttpServlet {
    /**
 * The FrontController class handles HTTP requests and routes them to appropriate commands.
 */

    private static final long serialVersionUID = 1L;
    /**
     * Name of command parameter
     */
    private static final String COMMAND_NAME = "command";

    /**
     * Simple constructor
     */

    public FrontController() {
        super();
    }

    /**
     * Handles HTTP GET requests.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @throws ServletException if there is a servlet exception
     * @throws IOException      if there is an I/O exception
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * Handles HTTP POST requests.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @throws ServletException if there is a servlet exception
     * @throws IOException      if there is an I/O exception
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * Processes the HTTP request and routes it to the appropriate command.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @throws ServletException if there is a servlet exception
     * @throws IOException      if there is an I/O exception
     */
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(COMMAND_NAME);
        String page;
        if(request.getSession().getAttribute("role") == "BANNED") {
            page = JspPageName.BAN_PAGE;
        } else if (commandName != null) {
            try {
                ICommand command = CommandHelper.getInstance().getCommand(commandName);
                page = command.execute(request);
            } catch (CommandException | DaoException e) {
                request.setAttribute("message", e.getMessage());
                page = JspPageName.ERROR_PAGE;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else if (request.getParameter("sessionLocale") != null) {
            try {

                page = CommandHelper.getInstance().getCommand("MAIN_PAGE").execute(request);
            } catch (CommandException | DaoException e) {
                request.setAttribute("message", e.getMessage());
                page = JspPageName.ERROR_PAGE;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            page = JspPageName.ERROR_PAGE;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        } else {
            errorMessageDirectlyFromResponse(response);
        }
    }


    /**
     * Sends an error message directly in the HTTP response when no JSP page is available.
     *
     * @param response the HTTP response
     * @throws IOException if there is an I/O exception
     */
    private void errorMessageDirectlyFromResponse(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().println("E R R O R");
    }

}