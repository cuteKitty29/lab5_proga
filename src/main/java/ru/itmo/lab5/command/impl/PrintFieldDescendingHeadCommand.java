package ru.itmo.lab5.command.impl;

import java.util.List;
import ru.itmo.lab5.command.AbstractCommand;
import ru.itmo.lab5.io.Console;
import ru.itmo.lab5.manager.CollectionManager;
import ru.itmo.lab5.model.DragonHead;

/**
 * Prints all non-null head values in descending order.
 */
public class PrintFieldDescendingHeadCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final Console console;

    /**
     * Creates the print_field_descending_head command.
     *
     * @param collectionManager collection receiver
     * @param console output helper
     */
    public PrintFieldDescendingHeadCommand(CollectionManager collectionManager, Console console) {
        super("print_field_descending_head", "print_field_descending_head : вывести значения поля head по убыванию");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String arguments) {
        List<DragonHead> heads = collectionManager.getHeadsDescending();
        if (heads.isEmpty()) {
            console.println("Подходящих значений head нет.");
            return;
        }
        for (DragonHead head : heads) {
            console.println(head.toString());
        }
    }
}
