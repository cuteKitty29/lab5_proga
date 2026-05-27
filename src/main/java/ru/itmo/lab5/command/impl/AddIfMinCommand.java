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
 * Adds a dragon only if it is smaller than the current queue head.
 */
public class AddIfMinCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final IdGenerator idGenerator;
    private final DragonAsker dragonAsker;
    private final Console console;

    /**
     * Creates the add_if_min command.
     *
     * @param collectionManager collection receiver
     * @param idGenerator id generator
     * @param dragonAsker builder for complex object input
     * @param console output helper
     */
    public AddIfMinCommand(
            CollectionManager collectionManager,
            IdGenerator idGenerator,
            DragonAsker dragonAsker,
            Console console){

        super("add_if_min", "add_if_min {element} : добавить элемент, если он меньше минимального");
        this.collectionManager = collectionManager;
        this.idGenerator = idGenerator;
        this.dragonAsker = dragonAsker;
        this.console = console;
    }

    @Override
    public void execute(String arguments) throws CommandException {
        try {
            Dragon dragon = dragonAsker.askDragon(idGenerator.nextId(), new Date());
            boolean added = collectionManager.addIfMin(dragon);
            if (added) {
                console.println("Элемент добавлен.");
            } else {
                console.println("Элемент не был добавлен, потому что он не меньше текущего минимального.");
            }
        } catch (InputCancelledException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
