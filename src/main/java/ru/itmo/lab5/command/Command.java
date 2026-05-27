package ru.itmo.lab5.command;

import ru.itmo.lab5.exception.CommandException;

/**
 * Command interface for the command pattern.
 */
public interface Command {
    /**
     * Returns the command name used in the console.
     *
     * @return command name
     */
    String getName();

    /**
     * Returns a short help description.
     *
     * @return help description
     */
    String getDescription();

    /**
     * Executes the command.
     *
     * @param arguments raw text after the command name
     * @throws CommandException if the command cannot be executed
     */
    void execute(String arguments) throws CommandException;
}
