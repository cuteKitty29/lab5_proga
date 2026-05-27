package ru.itmo.lab5.command.impl;

import java.util.List;
import ru.itmo.lab5.command.AbstractCommand;
import ru.itmo.lab5.io.Console;
import ru.itmo.lab5.manager.CollectionManager;
import ru.itmo.lab5.model.Dragon;

/**
 * Prints all collection elements in a sorted readable form.
 */
public class ShowCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final Console console;

    /**
     * Creates the show command.
     *
     * @param collectionManager collection receiver
     * @param console output helper
     */
    public ShowCommand(CollectionManager collectionManager, Console console) {
        super("show", "show : вывести все элементы коллекции");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String arguments) {
        List<Dragon> dragons = collectionManager.getSortedView();
        if (dragons.isEmpty()) {
            console.println("Коллекция пуста.");
            return;
        }
        for (Dragon dragon : dragons) {
            console.println(dragon.toString());
        }
    }
}
