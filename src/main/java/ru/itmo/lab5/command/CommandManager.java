package ru.itmo.lab5.command;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import ru.itmo.lab5.exception.CommandException;
import ru.itmo.lab5.io.Console;
import ru.itmo.lab5.io.InputManager;

/**
 * Stores available commands and delegates execution to them.
 */
public class CommandManager {
    private final Map<String, Command> commands = new LinkedHashMap<>();
    private final Console console;
    private final InputManager inputManager;

    /**
     * Creates a new command manager.
     *
     * @param console output helper
     */
    public CommandManager(Console console, InputManager inputManager) {
        this.console = console;
        this.inputManager = inputManager;
    }

    /**
     * Registers a command by its name.
     *
     * @param command command instance
     */
    public void register(Command command) {
        commands.put(command.getName(), command);
    }

    /**
     * Returns all registered commands in registration order.
     *
     * @return registered commands
     */
    public Collection<Command> getCommands() {
        return commands.values();
    }

    /**
     * Parses and executes a raw command line.
     *
     * @param line raw line from console or script
     */
    public void executeLine(String line) {
        if (line == null) {
            return;
        }
        String trimmed = line.trim();
        if (trimmed.isEmpty()) {
            return;
        }

        String[] parts = trimmed.split("\\s+", 2);
        String commandName = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        Command command = commands.get(commandName);
        if (command == null && inputManager.isInteractiveMode()) {
            console.error("Неизвестная команда: " + commandName + ". Введите help для списка команд.");
            return;
        }
        if (!inputManager.isInteractiveMode() && command == null){
            return;
        }

        try {
            command.execute(arguments);
        } catch (CommandException e) {
            console.error("Ошибка выполнения команды " + commandName + ": " + e.getMessage());
        }
    }
}
