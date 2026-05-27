package ru.itmo.lab5.command.impl;

import ru.itmo.lab5.app.ApplicationRuntime;
import ru.itmo.lab5.command.AbstractCommand;
import ru.itmo.lab5.io.Console;

/**
 * Stops the application without saving.
 */
public class ExitCommand extends AbstractCommand {
    private final ApplicationRuntime runtime;
    private final Console console;

    /**
     * Creates the exit command.
     *
     * @param runtime application runtime state
     * @param console output helper
     */
    public ExitCommand(ApplicationRuntime runtime, Console console) {
        super("exit", "exit : завершить программу (без сохранения в файл)");
        this.runtime = runtime;
        this.console = console;
    }

    @Override
    public void execute(String arguments) {
        runtime.stop();
        console.println("Программа завершает работу без автоматического сохранения.");
    }
}
