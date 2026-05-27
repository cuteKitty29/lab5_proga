package ru.itmo.lab5.storage;

import java.util.Collections;
import java.util.List;
import ru.itmo.lab5.model.Dragon;

/**
 * Represents the result of loading dragons from the XML file.
 */
public class LoadResult {
    private final List<Dragon> dragons;
    private final List<String> warnings;

    /**
     * Creates a new load result.
     *
     * @param dragons successfully loaded dragons
     * @param warnings warnings collected during parsing
     */
    public LoadResult(List<Dragon> dragons, List<String> warnings) {
        this.dragons = List.copyOf(dragons);
        this.warnings = List.copyOf(warnings);
    }

    /**
     * Returns loaded dragons.
     *
     * @return immutable dragon list
     */
    public List<Dragon> getDragons() {
        return Collections.unmodifiableList(dragons);
    }

    /**
     * Returns warnings collected during loading.
     *
     * @return immutable warnings list
     */
    public List<String> getWarnings() {
        return Collections.unmodifiableList(warnings);
    }
}
