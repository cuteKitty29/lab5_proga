package ru.itmo.lab5.command.impl;

import ru.itmo.lab5.command.AbstractCommand;
import ru.itmo.lab5.exception.CommandException;
import ru.itmo.lab5.io.Console;
import ru.itmo.lab5.manager.CollectionManager;

/**
 * Counts dragons with age greater than a specified value.
 */
public class CountGreaterThanAgeCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final Console console;

    /**
     * Creates the count_greater_than_age command.
     *
     * @param collectionManager collection receiver
     * @param console output helper
     */
    public CountGreaterThanAgeCommand(CollectionManager collectionManager, Console console) {
        super("count_greater_than_age", "count_greater_than_age age : вывести количество элементов с age больше заданного");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String arguments) throws CommandException {
        long age;
        try {
            age = Long.parseLong(arguments.trim());
        } catch (RuntimeException e) {
            throw new CommandException("Нужно указать целое число age.");
        }
        console.println("Количество элементов: " + collectionManager.countGreaterThanAge(age));
    }
}
