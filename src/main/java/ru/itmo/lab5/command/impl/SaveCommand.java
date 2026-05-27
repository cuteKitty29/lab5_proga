package ru.itmo.lab5.command.impl;

import java.nio.file.Path;
import ru.itmo.lab5.command.AbstractCommand;
import ru.itmo.lab5.exception.CommandException;
import ru.itmo.lab5.exception.StorageException;
import ru.itmo.lab5.io.Console;
import ru.itmo.lab5.manager.CollectionManager;
import ru.itmo.lab5.storage.DragonXmlManager;

/**
 * Saves the collection to the configured XML file.
 */
public class SaveCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final DragonXmlManager dragonXmlManager;
    private final Path storagePath;
    private final Console console;

    /**
     * Creates the save command.
     *
     * @param collectionManager collection receiver
     * @param dragonXmlManager xml storage manager
     * @param storagePath storage file path
     * @param console output helper
     */
    public SaveCommand(
            CollectionManager collectionManager,
            DragonXmlManager dragonXmlManager,
            Path storagePath,
            Console console
    ) {
        super("save", "save : сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
        this.dragonXmlManager = dragonXmlManager;
        this.storagePath = storagePath;
        this.console = console;
    }

    @Override
    public void execute(String arguments) throws CommandException {
        try {
            dragonXmlManager.save(storagePath, collectionManager.getSnapshot());
            console.println("Коллекция сохранена в файл.");
        } catch (StorageException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
