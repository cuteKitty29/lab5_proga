package ru.itmo.lab5.util;

/**
 * Escapes special XML characters in text values.
 */
public final class XmlEscaper {
    private XmlEscaper() {
    }

    /**
     * Escapes XML special characters.
     *
     * @param value source text
     * @return escaped text
     */
    public static String escape(String value) {
        if (value == null) {
            return "";
        }
        return value
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }
}
