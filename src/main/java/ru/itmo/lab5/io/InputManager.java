package ru.itmo.lab5.io;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import ru.itmo.lab5.app.AppConstants;
import ru.itmo.lab5.exception.ScriptRecursionException;

/**
 * Reads lines either from the console or from nested script files.
 */
public class InputManager implements Closeable {
    private final Console console;
    private final Deque<InputSource> sources = new ArrayDeque<>();
    private final Set<Path> activeScripts = new HashSet<>();

    /**
     * Creates an input manager with interactive standard input as the root source.
     *
     * @param console output helper
     */
    public InputManager(Console console) {
        this.console = console;
        this.sources.push(new InputSource("stdin", new Scanner(System.in, AppConstants.DEFAULT_CHARSET), true, null));
    }

    /**
     * Reads the next command line. Finished scripts are automatically closed and removed.
     *
     * @return next command line, or {@code null} when the whole application input is over
     */
    public String readCommandLine() {
        while (!sources.isEmpty()) {
            InputSource current = sources.peek();
            if (current.scanner.hasNextLine()) {
                return current.scanner.nextLine();
            }
            closeCurrentSource();
        }
        return null;
    }

    /**
     * Reads one value for object field input from the current source only.
     *
     * @param prompt prompt shown only in interactive mode
     * @return the next raw line or {@code null} if the current source has no more data
     */
    public String readFieldLine(String prompt) {
        InputSource current = sources.peek();
        if (current == null) {
            return null;
        }
        if (current.interactive) {
            console.print(prompt);
        }
        if (current.scanner.hasNextLine()) {
            return current.scanner.nextLine();
        }
        if (!current.interactive) {
            closeCurrentSource();
        }
        return null;
    }

    /**
     * Adds a new script source on top of the current input stack.
     *
     * @param fileName path to the script file
     * @throws IOException if the file cannot be opened
     * @throws ScriptRecursionException if the script is already active
     */
    public void pushScript(String fileName) throws IOException, ScriptRecursionException {
        Path scriptPath = Path.of(fileName).toAbsolutePath().normalize();
        if (activeScripts.contains(scriptPath)) {
            throw new ScriptRecursionException("Обнаружена рекурсия скриптов: " + scriptPath);
        }
        Scanner scanner = new Scanner(Files.newBufferedReader(scriptPath, AppConstants.DEFAULT_CHARSET));
        sources.push(new InputSource(scriptPath.toString(), scanner, false, scriptPath));
        activeScripts.add(scriptPath);
    }

    /**
     * Returns whether the current source is interactive.
     *
     * @return {@code true} for standard input
     */
    public boolean isInteractiveMode() {
        InputSource current = sources.peek();
        return current != null && current.interactive;
    }

    /**
     * Returns the name of the current input source.
     *
     * @return source name
     */
    public String getCurrentSourceName() {
        InputSource current = sources.peek();
        return current == null ? "<no source>" : current.name;
    }

    private void closeCurrentSource() {
        InputSource current = sources.poll();
        if (current == null) {
            return;
        }
        current.scanner.close();
        if (current.path != null) {
            activeScripts.remove(current.path);
        }
    }

    @Override
    public void close() {
        while (!sources.isEmpty()) {
            closeCurrentSource();
        }
    }

    private record InputSource(String name, Scanner scanner, boolean interactive, Path path) {
    }
}
