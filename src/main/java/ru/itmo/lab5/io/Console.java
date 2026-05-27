package ru.itmo.lab5.io;

import java.io.PrintStream;

/**
 * Provides a small wrapper around standard output and error streams.
 */
public class Console {
    private final PrintStream out;
    private final PrintStream err;

    /**
     * Creates a console wrapper.
     *
     * @param out standard output stream
     * @param err standard error stream
     */
    public Console(PrintStream out, PrintStream err) {
        this.out = out;
        this.err = err;
    }

    /**
     * Prints a regular message.
     *
     * @param message text to print
     */
    public void println(String message) {
        out.println(message);
    }

    /**
     * Prints text without a line break.
     *
     * @param message text to print
     */
    public void print(String message) {
        out.print(message);
    }

    /**
     * Prints an error message.
     *
     * @param message text to print
     */
    public void error(String message) {
        err.println(message);
    }

    /**
     * Prints a warning message.
     *
     * @param message text to print
     */
    public void warning(String message) {
        err.println("Предупреждение: " + message);
    }
}
