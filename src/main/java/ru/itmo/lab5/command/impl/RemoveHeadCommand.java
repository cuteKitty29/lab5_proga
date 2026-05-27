package ru.itmo.lab5.command.impl;

import ru.itmo.lab5.command.AbstractCommand;
import ru.itmo.lab5.io.Console;
import ru.itmo.lab5.manager.CollectionManager;

/**
 * Removes and prints the first queue element.
 */
public class RemoveHeadCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final Console console;

    /**
     * Creates the remove_head command.
     *
     * @param collectionManager collection receiver
     * @param console output helper
     */
    public RemoveHeadCommand(CollectionManager collectionManager, Console console) {
        super("remove_head", "remove_head : вывести первый элемент коллекции и удалить его");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String arguments) {
        collectionManager.removeHead()
                .ifPresentOrElse(
                        dragon -> {
                            console.println("Удалён элемент:");
                            console.println(dragon.toString());
                        },
                        () -> console.println("Коллекция пуста.")
                );
    }
}
