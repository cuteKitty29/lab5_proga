package ru.itmo.lab5.command.impl;

import ru.itmo.lab5.command.AbstractCommand;
import ru.itmo.lab5.io.Console;
import ru.itmo.lab5.manager.CollectionManager;

/**
 * Prints the first element of the priority queue.
 */
public class HeadCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final Console console;

    /**
     * Creates the head command.
     *
     * @param collectionManager collection receiver
     * @param console output helper
     */
    public HeadCommand(CollectionManager collectionManager, Console console) {
        super("head", "head : вывести первый элемент коллекции");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String arguments) {
        collectionManager.head()
                .ifPresentOrElse(
                        dragon -> console.println(dragon.toString()),
                        () -> console.println("Коллекция пуста.")
                );
    }
}
