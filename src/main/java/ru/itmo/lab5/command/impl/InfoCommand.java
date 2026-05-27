package ru.itmo.lab5.command.impl;

import java.nio.file.Path;
import ru.itmo.lab5.command.AbstractCommand;
import ru.itmo.lab5.io.Console;
import ru.itmo.lab5.manager.CollectionManager;

/**
 * Prints information about the collection.
 */
public class InfoCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final Console console;
    private final Path storagePath;

    /**
     * Creates the info command.
     *
     * @param collectionManager collection receiver
     * @param console output helper
     * @param storagePath storage file path
     */
    public InfoCommand(CollectionManager collectionManager, Console console, Path storagePath) {
        super("info", "info : вывести информацию о коллекции");
        this.collectionManager = collectionManager;
        this.console = console;
        this.storagePath = storagePath;
    }

    @Override
    public void execute(String arguments) {
        console.println("Тип коллекции: " + collectionManager.getCollectionType());
        console.println("Дата инициализации: " + collectionManager.getInitializationTime());
        console.println("Количество элементов: " + collectionManager.size());
        console.println("Файл хранения: " + storagePath.toAbsolutePath());
    }
}
