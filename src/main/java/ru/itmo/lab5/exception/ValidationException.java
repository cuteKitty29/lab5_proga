package ru.itmo.lab5.exception;

/**
 * Signals that a value does not satisfy business validation rules.
 */
public class ValidationException extends Exception {
    /**
     * Creates an exception with the given message.
     *
     * @param message validation error description
     */
    public ValidationException(String message) {
        super(message);
    }
}
