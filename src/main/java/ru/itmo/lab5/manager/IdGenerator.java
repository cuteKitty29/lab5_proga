package ru.itmo.lab5.manager;

import java.util.Collection;
import ru.itmo.lab5.model.Dragon;

/**
 * Generates unique positive identifiers for dragons.
 */
public class IdGenerator {
    private int currentMaxId = 0;

    /**
     * Returns the next unique id.
     *
     * @return positive unique id
     */
    public synchronized int nextId() {
        currentMaxId++;
        return currentMaxId;
    }

    /**
     * Initializes the generator from already existing dragons.
     *
     * @param dragons dragons loaded from storage
     */
    public synchronized void initialize(Collection<Dragon> dragons) {
        currentMaxId = dragons.stream()
                .map(Dragon::getId)
                .filter(id -> id != null && id > 0)
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);
    }
}
