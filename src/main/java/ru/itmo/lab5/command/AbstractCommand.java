package ru.itmo.lab5.command;

/**
 * Base class for concrete commands.
 */
public abstract class AbstractCommand implements Command {
    private final String name;
    private final String description;

    /**
     * Creates a command with its public metadata.
     *
     * @param name command name
     * @param description help description
     */
    protected AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final String getDescription() {
        return description;
    }
}
