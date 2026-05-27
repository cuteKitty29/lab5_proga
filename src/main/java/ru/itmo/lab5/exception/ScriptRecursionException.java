package ru.itmo.lab5.exception;

/**
 * Signals a recursive call of {@code execute_script}.
 */
public class ScriptRecursionException extends Exception {
    /**
     * Creates an exception with the given message.
     *
     * @param message error description
     */
    public ScriptRecursionException(String message) {
        super(message);
    }
}
