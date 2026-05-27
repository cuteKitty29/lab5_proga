package ru.itmo.lab5.command.impl;

import ru.itmo.lab5.command.AbstractCommand;
import ru.itmo.lab5.exception.CommandException;
import ru.itmo.lab5.io.Console;
import ru.itmo.lab5.manager.CollectionManager;

/**
 * Removes an element by id.
 */
public class RemoveByIdCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final Console console;

    /**
     * Creates the command.
     *
     * @param collectionManager collection receiver
     * @param console output helper
     */
    public RemoveByIdCommand(CollectionManager collectionManager, Console console) {
        super("remove_by_id", "remove_by_id id : удалить элемент из коллекции по id");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String arguments) throws CommandException {
        int id;
        try {
            id = Integer.parseInt(arguments.trim());
        } catch (RuntimeException e) {
            throw new CommandException("Нужно указать целый id.");
        }
        boolean removed = collectionManager.removeById(id);
        if (removed) {
            console.println("Элемент с id=" + id + " удалён.");
        } else {
            console.println("Элемент с id=" + id + " не найден.");
        }
    }
}
