package ru.itmo.lab5.command.impl;

import java.io.IOException;
import ru.itmo.lab5.command.AbstractCommand;
import ru.itmo.lab5.exception.CommandException;
import ru.itmo.lab5.exception.ScriptRecursionException;
import ru.itmo.lab5.io.Console;
import ru.itmo.lab5.io.InputManager;

/**
 * Pushes a script file into the input stack.
 */
public class ExecuteScriptCommand extends AbstractCommand {
    private final InputManager inputManager;
    private final Console console;

    /**
     * Creates the execute_script command.
     *
     * @param inputManager input manager
     * @param console output helper
     */
    public ExecuteScriptCommand(InputManager inputManager, Console console) {
        super("execute_script", "execute_script file_name : считать и исполнить скрипт из файла");
        this.inputManager = inputManager;
        this.console = console;
    }

    @Override
    public void execute(String arguments) throws CommandException {
        String fileName = arguments == null ? "" : arguments.trim();
        if (fileName.isEmpty()) {
            throw new CommandException("Нужно указать путь к файлу скрипта.");
        }
        try {
            inputManager.pushScript(fileName);
            console.println("Скрипт подключён: " + fileName);
        } catch (ScriptRecursionException e) {
            throw new CommandException(e.getMessage(), e);
        } catch (IOException e) {
            throw new CommandException("Не удалось открыть скрипт: " + e.getMessage(), e);
        }
    }
}
