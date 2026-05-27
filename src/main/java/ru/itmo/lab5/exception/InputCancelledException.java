package ru.itmo.lab5.exception;

/**
 * Signals that the current interactive or script input cannot be continued.
 */
public class InputCancelledException extends Exception {
    /**
     * Creates an exception with the given message.
     *
     * @param message description of the interruption
     */
    public InputCancelledException(String message) {
        super(message);
    }
}
