package ru.itmo.lab5.command.impl;

import ru.itmo.lab5.command.AbstractCommand;
import ru.itmo.lab5.io.Console;
import ru.itmo.lab5.manager.CollectionManager;

/**
 * Prints the sum of age values.
 */
public class SumOfAgeCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final Console console;

    /**
     * Creates the sum_of_age command.
     *
     * @param collectionManager collection receiver
     * @param console output helper
     */
    public SumOfAgeCommand(CollectionManager collectionManager, Console console) {
        super("sum_of_age", "sum_of_age : вывести сумму значений поля age");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String arguments) {
        console.println("Сумма age: " + collectionManager.sumOfAge());
    }
}
