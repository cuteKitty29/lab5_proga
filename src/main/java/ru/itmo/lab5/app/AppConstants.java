package ru.itmo.lab5.app;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Contains application-wide constants.
 */
public final class AppConstants {
    /**
     * Name of the environment variable that stores the path to the XML file.
     */
    public static final String ENV_FILE_VARIABLE = "DRAGON_FILE";

    /**
     * Charset used for reading and writing text files.
     */
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private AppConstants() {
    }
}
