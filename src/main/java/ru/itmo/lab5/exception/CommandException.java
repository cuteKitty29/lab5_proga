package ru.itmo.lab5.exception;

/**
 * Signals that a command cannot be executed correctly.
 */
public class CommandException extends Exception {
    /**
     * Creates an exception with the given message.
     *
     * @param message error description
     */
    public CommandException(String message) {
        super(message);
    }

    /**
     * Creates an exception with a message and a cause.
     *
     * @param message error description
     * @param cause original exception
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
