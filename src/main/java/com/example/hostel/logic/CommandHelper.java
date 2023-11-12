package com.example.hostel.logic;

import com.example.hostel.logic.commands.AuthorizationPageCommand;
import com.example.hostel.logic.commands.MainPageCommand;
import com.example.hostel.logic.commands.RegistrationPageCommand;
import com.example.hostel.logic.commands.UnknownCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandHelper {

    private static final CommandHelper instance = new CommandHelper();

    private Map<CommandName, ICommand> commandPull = new HashMap<>();

    public CommandHelper() {
         commandPull.put(CommandName.MAIN_PAGE, new MainPageCommand());
         commandPull.put(CommandName.UNKNOWN_COMMAND, new UnknownCommand());
         commandPull.put(CommandName.AUTH_PAGE, new AuthorizationPageCommand());
         commandPull.put(CommandName.REG_COMMAND, new RegistrationPageCommand());
    }
    public static CommandHelper getInstance() {
        return instance;
    }

    public ICommand getCommand(String commandName) {
        CommandName name = CommandName.valueOf(commandName.toUpperCase());
        ICommand command;
        if(name != null) {
            command = commandPull.get(name);
        } else {
            command = commandPull.get(CommandName.UNKNOWN_COMMAND);
        }
        return command;
    }
}
