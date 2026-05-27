package ru.itmo.lab5.app;

/**
 * Stores the current runtime state of the application.
 */
public class ApplicationRuntime {
    private boolean running = true;

    /**
     * Returns {@code true} while the application should continue to work.
     *
     * @return {@code true} if the main loop is active
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Stops the main application loop.
     */
    public void stop() {
        this.running = false;
    }
}
