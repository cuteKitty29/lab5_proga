package ru.itmo.lab5.command.impl;

import java.util.Date;
import ru.itmo.lab5.command.AbstractCommand;
import ru.itmo.lab5.exception.CommandException;
import ru.itmo.lab5.exception.InputCancelledException;
import ru.itmo.lab5.io.Console;
import ru.itmo.lab5.io.DragonAsker;
import ru.itmo.lab5.manager.*;
import ru.itmo.lab5.model.Dragon;

/**
 * Adds a new element to the collection.
 */
public class AddCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final IdGenerator idGenerator;
    private final DragonAsker dragonAsker;
    private final Console console;

    /**
     * Creates the add command.
     *
     * @param collectionManager collection receiver
     * @param idGenerator id generator
     * @param dragonAsker builder for complex object input
     * @param console output helper
     */
    public AddCommand(
            CollectionManager collectionManager,
            IdGenerator idGenerator,
            DragonAsker dragonAsker,
            Console console
    ) {
        super("add", "add {element} : добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.idGenerator = idGenerator;
        this.dragonAsker = dragonAsker;
        this.console = console;
    }

    @Override
    public void execute(String arguments) throws CommandException {
        try {
            Dragon dragon = dragonAsker.askDragon(idGenerator.nextId(), new Date());
            collectionManager.add(dragon);
            console.println("Элемент успешно добавлен.");
        } catch (InputCancelledException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
