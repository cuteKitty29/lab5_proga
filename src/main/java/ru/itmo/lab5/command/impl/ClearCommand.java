package ru.itmo.lab5.command.impl;

import ru.itmo.lab5.command.AbstractCommand;
import ru.itmo.lab5.io.Console;
import ru.itmo.lab5.manager.CollectionManager;

/**
 * Clears the collection.
 */
public class ClearCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final Console console;

    /**
     * Creates the clear command.
     *
     * @param collectionManager collection receiver
     * @param console output helper
     */
    public ClearCommand(CollectionManager collectionManager, Console console) {
        super("clear", "clear : очистить коллекцию");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String arguments) {
        collectionManager.clear();
        console.println("Коллекция очищена.");
    }
}
