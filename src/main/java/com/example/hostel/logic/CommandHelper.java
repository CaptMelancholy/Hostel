package com.example.hostel.logic;

import com.example.hostel.logic.commands.*;

import java.util.HashMap;
import java.util.Map;

public class CommandHelper {

    private static final CommandHelper instance = new CommandHelper();

    private final Map<CommandName, ICommand> commandPull = new HashMap<>();



    public CommandHelper() {
         commandPull.put(CommandName.MAIN_PAGE, new MainPageCommand());
         commandPull.put(CommandName.UNKNOWN_COMMAND, new UnknownCommand());
         commandPull.put(CommandName.AUTH_PAGE, new AuthorizationPageCommand());
         commandPull.put(CommandName.REG_COMMAND, new RegistrationPageCommand());
         commandPull.put(CommandName.SINGOUT_COMMAND, new SingOutCommand());
         commandPull.put(CommandName.FORBIDDEN_COMMAND, new ForbiddenCommand());
         commandPull.put(CommandName.ADMIN_PAGE, new AdminPanelCommand());
         commandPull.put(CommandName.BAN_COMMAND, new BanCommand());
         commandPull.put(CommandName.BAN_USER_COMMAND, new BanUserCommand());
         commandPull.put(CommandName.ADMIN_ADD_ROOM, new AdminRoomsListCommand());
         commandPull.put(CommandName.ADMIN_ORDERS, new AdminOrdersCommand());
         commandPull.put(CommandName.USER_BOOK, new UserBookCommand());
         commandPull.put(CommandName.USER_ORDERLIST, new UserOrdersListCommand());
         commandPull.put(CommandName.ADMIN_EDIT_ROOM, new AdminRoomEditCommand());
    }
    public static CommandHelper getInstance() {
        return instance;
    }
    public Map<CommandName, ICommand> getCommandPull() {
        return commandPull;
    }
    public ICommand getCommand(String commandName) {
        CommandName name = CommandName.valueOf(commandName.toUpperCase());
        ICommand command;
        command = commandPull.get(name);
        return command;
    }
}
