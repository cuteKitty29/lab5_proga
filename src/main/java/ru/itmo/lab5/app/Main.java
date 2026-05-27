package ru.itmo.lab5.app;

import java.nio.file.Path;
import ru.itmo.lab5.command.CommandManager;
import ru.itmo.lab5.command.impl.*;
import ru.itmo.lab5.exception.StorageException;
import ru.itmo.lab5.io.*;
import ru.itmo.lab5.manager.*;
import ru.itmo.lab5.storage.*;

/**
 * Entry point of the laboratory work.
 */
public final class Main {
    private Main() {
    }

    /**
     * Starts the application.
     *
     * @param args command line arguments are ignored
     */
    public static void main(String[] args) {
        Console console = new Console(System.out, System.err);
        String fileName = System.getenv(AppConstants.ENV_FILE_VARIABLE);

        if (fileName == null || fileName.isBlank()) {
            console.error("Не задана переменная окружения " + AppConstants.ENV_FILE_VARIABLE + ".");
            console.error("Пример: DRAGON_FILE=/path/to/dragons.xml");
            return;
        }

        Path storagePath = Path.of(fileName).toAbsolutePath().normalize();
        CollectionManager collectionManager = new CollectionManager();
        IdGenerator idGenerator = new IdGenerator();
        DragonXmlManager dragonXmlManager = new DragonXmlManager();

        try {
            LoadResult loadResult = dragonXmlManager.load(storagePath);
            collectionManager.load(loadResult.getDragons());
            idGenerator.initialize(loadResult.getDragons());
            loadResult.getWarnings().forEach(console::warning);
            console.println("Коллекция загружена. Элементов: " + collectionManager.size());
        } catch (StorageException e) {
            console.error("Не удалось загрузить коллекцию: " + e.getMessage());
            console.warning("Программа продолжит работу с пустой коллекцией.");
            idGenerator.initialize(collectionManager.getSnapshot());
        }

        ApplicationRuntime runtime = new ApplicationRuntime();

        try (InputManager inputManager = new InputManager(console)) {
            DragonAsker dragonAsker = new DragonAsker(inputManager, console);
            CommandManager commandManager = new CommandManager(console, inputManager);

            registerCommands(
                    commandManager,
                    collectionManager,
                    idGenerator,
                    dragonXmlManager,
                    dragonAsker,
                    inputManager,
                    runtime,
                    storagePath,
                    console
            );

            while (runtime.isRunning()) {
                if (inputManager.isInteractiveMode()) {
                    console.print("> ");
                }
                String line = inputManager.readCommandLine();
                if (line == null) {
                    break;
                }
                commandManager.executeLine(line);
            }
        }

        console.println("Работа программы завершена.");
    }

    private static void registerCommands(
            CommandManager commandManager,
            CollectionManager collectionManager,
            IdGenerator idGenerator,
            DragonXmlManager dragonXmlManager,
            DragonAsker dragonAsker,
            InputManager inputManager,
            ApplicationRuntime runtime,
            Path storagePath,
            Console console
    ) {
        commandManager.register(new HelpCommand(commandManager, console));
        commandManager.register(new InfoCommand(collectionManager, console, storagePath));
        commandManager.register(new ShowCommand(collectionManager, console));
        commandManager.register(new AddCommand(collectionManager, idGenerator, dragonAsker, console));
        commandManager.register(new UpdateCommand(collectionManager, dragonAsker, console));
        commandManager.register(new RemoveByIdCommand(collectionManager, console));
        commandManager.register(new ClearCommand(collectionManager, console));
        commandManager.register(new SaveCommand(collectionManager, dragonXmlManager, storagePath, console));
        commandManager.register(new ExecuteScriptCommand(inputManager, console));
        commandManager.register(new ExitCommand(runtime, console));
        commandManager.register(new HeadCommand(collectionManager, console));
        commandManager.register(new RemoveHeadCommand(collectionManager, console));
        commandManager.register(new AddIfMinCommand(collectionManager, idGenerator, dragonAsker, console));
        commandManager.register(new SumOfAgeCommand(collectionManager, console));
        commandManager.register(new CountGreaterThanAgeCommand(collectionManager, console));
        commandManager.register(new PrintFieldDescendingHeadCommand(collectionManager, console));
    }
}
