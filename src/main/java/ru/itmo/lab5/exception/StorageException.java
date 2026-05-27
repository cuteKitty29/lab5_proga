package ru.itmo.lab5.exception;

/**
 * Signals problems while reading or writing the XML storage file.
 */
public class StorageException extends Exception {
    /**
     * Creates an exception with the given message.
     *
     * @param message error description
     */
    public StorageException(String message) {
        super(message);
    }

    /**
     * Creates an exception with a message and a cause.
     *
     * @param message error description
     * @param cause original exception
     */
    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
