package ru.itmo.lab5.command.impl;

import ru.itmo.lab5.command.AbstractCommand;
import ru.itmo.lab5.command.Command;
import ru.itmo.lab5.command.CommandManager;
import ru.itmo.lab5.io.Console;

/**
 * Prints the list of all available commands.
 */
public class HelpCommand extends AbstractCommand {
    private final CommandManager commandManager;
    private final Console console;

    /**
     * Creates the help command.
     *
     * @param commandManager command registry
     * @param console output helper
     */
    public HelpCommand(CommandManager commandManager, Console console) {
        super("help", "help : вывести справку по доступным командам");
        this.commandManager = commandManager;
        this.console = console;
    }

    @Override
    public void execute(String arguments) {
        for (Command command : commandManager.getCommands()) {
            console.println(command.getDescription());
        }
    }
}
