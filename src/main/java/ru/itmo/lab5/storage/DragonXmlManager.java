package ru.itmo.lab5.storage;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import java.nio.file.Files;
import java.nio.file.Path;

import java.time.Instant;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import ru.itmo.lab5.app.AppConstants;
import ru.itmo.lab5.exception.StorageException;
import ru.itmo.lab5.exception.ValidationException;
import ru.itmo.lab5.model.*;
import ru.itmo.lab5.util.XmlEscaper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * Reads and writes the collection in XML format.
 *
 * <p>Reading uses {@link BufferedInputStream}
 * Writing uses {@link BufferedWriter}</p>
 */
public class DragonXmlManager {

    /**
     * Loads dragons from the specified XML file.
     *
     * @param path file path
     * @return loaded dragons and warnings
     * @throws StorageException if the whole file cannot be read or parsed
     */
    public LoadResult load(Path path) throws StorageException {
        if (Files.notExists(path)) {
            return new LoadResult(List.of(), List.of("Файл не найден. Коллекция будет создана пустой."));
        }
        if (!Files.isReadable(path)) {
            throw new StorageException("Нет прав на чтение файла: " + path);
        }

        try {
            if (Files.size(path) == 0) {
                return new LoadResult(List.of(), List.of("Файл пустой. Коллекция будет создана пустой."));
            }
        } catch (IOException e) {
            throw new StorageException("Не удалось проверить размер файла: " + path, e);
        }

        List<Dragon> dragons = new ArrayList<>();
        List<String> warnings = new ArrayList<>();
        List<Integer> seenIds = new ArrayList<>();

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(path.toFile()))) {
            DocumentBuilder builder = createDocumentBuilder();
            Document document = builder.parse(inputStream);
            Element root = document.getDocumentElement();
            if (root == null || !"dragons".equals(root.getTagName())) {
                throw new StorageException("Некорректный корневой XML-элемент. Ожидался <dragons>.");
            }

            for (Node node = root.getFirstChild(); node != null; node = node.getNextSibling()) {
                if (!(node instanceof Element element)) {
                    continue;
                }
                if (!"dragon".equals(element.getTagName())) {
                    warnings.add("Неизвестный элемент <" + element.getTagName() + "> был пропущен.");
                    continue;
                }
                try {
                    Dragon dragon = parseDragon(element);
                    if (seenIds.contains(dragon.getId())) {
                        warnings.add("Один элемент <dragon> пропущен: id=" + dragon.getId() + " уже существует.");
                        continue;
                    }
                    seenIds.add(dragon.getId());
                    dragons.add(dragon);
                } catch (ValidationException | IllegalArgumentException e) {
                    warnings.add("Один элемент <dragon> пропущен: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new StorageException("Ошибка чтения файла: " + path, e);
        } catch (SAXException e) {
            throw new StorageException("XML повреждён или имеет неверный формат: " + e.getMessage(), e);
        } catch (ParserConfigurationException e) {
            throw new StorageException("Не удалось настроить XML-парсер.", e);
        }

        return new LoadResult(dragons, warnings);
    }

    /**
     * Saves the collection to the specified XML file.
     *
     * @param path file path
     * @param dragons dragons to save
     * @throws StorageException if saving fails
     */
    public void save(Path path, List<Dragon> dragons) throws StorageException {
        try {
            Path parent = path.getParent();
            if (parent != null && Files.notExists(parent)) {
                Files.createDirectories(parent);
            }
        } catch (IOException e) {
            throw new StorageException("Не удалось создать директорию для файла: " + path, e);
        }

        if (Files.exists(path) && !Files.isWritable(path)) {
            throw new StorageException("Нет прав на запись в файл: " + path);
        }

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(path.toFile()), AppConstants.DEFAULT_CHARSET))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.newLine();
            writer.write("<dragons>");
            writer.newLine();
            for (Dragon dragon : dragons.stream().sorted().toList()) {
                writeDragon(writer, dragon);
            }
            writer.write("</dragons>");
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new StorageException("Ошибка записи файла: " + path, e);
        }
    }

    private void writeDragon(BufferedWriter writer, Dragon dragon) throws IOException {
        writer.write("  <dragon>");
        writer.newLine();
        writeSimpleElement(writer, 4, "id", String.valueOf(dragon.getId()));
        writeSimpleElement(writer, 4, "name", dragon.getName());

        writer.write("    <coordinates>");
        writer.newLine();
        writeSimpleElement(writer, 6, "x", String.valueOf(dragon.getCoordinates().getX()));
        writeSimpleElement(writer, 6, "y", String.valueOf(dragon.getCoordinates().getY()));
        writer.write("    </coordinates>");
        writer.newLine();

        writeSimpleElement(writer, 4, "creationDate", dragon.getCreationDate().toInstant().toString());
        writeSimpleElement(writer, 4, "age", String.valueOf(dragon.getAge()));
        writeSimpleElement(writer, 4, "color", dragon.getColor() == null ? "" : dragon.getColor().name());
        writeSimpleElement(writer, 4, "type", dragon.getType() == null ? "" : dragon.getType().name());
        writeSimpleElement(writer, 4, "character", dragon.getCharacter() == null ? "" : dragon.getCharacter().name());

        if (dragon.getHead() != null) {
            writer.write("    <head>");
            writer.newLine();
            writeSimpleElement(writer, 6, "size", String.valueOf(dragon.getHead().getSize()));
            writeSimpleElement(writer, 6, "toothCount", String.valueOf(dragon.getHead().getToothCount()));
            writer.write("    </head>");
            writer.newLine();
        } else {
            writeSimpleElement(writer, 4, "head", "");
        }

        writer.write("  </dragon>");
        writer.newLine();
    }

    private void writeSimpleElement(BufferedWriter writer, int indent, String tagName, String value) throws IOException {
        writer.write(" ".repeat(indent));
        writer.write("<" + tagName + ">");
        writer.write(XmlEscaper.escape(value));
        writer.write("</" + tagName + ">");
        writer.newLine();
    }

    private DocumentBuilder createDocumentBuilder() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        factory.setNamespaceAware(false);
        factory.setExpandEntityReferences(false);
        return factory.newDocumentBuilder();
    }

    private Dragon parseDragon(Element dragonElement) throws ValidationException {
        Integer id = parseInteger(requiredText(dragonElement, "id"), "id");
        String name = requiredText(dragonElement, "name").trim();
        Element coordinatesElement = requiredElement(dragonElement, "coordinates");
        Coordinates coordinates = new Coordinates(
                parseDouble(requiredText(coordinatesElement, "x"), "coordinates.x"),
                parseFloat(requiredText(coordinatesElement, "y"), "coordinates.y")
        );
        Date creationDate = parseDate(requiredText(dragonElement, "creationDate"), "creationDate");
        long age = parseLong(requiredText(dragonElement, "age"), "age");
        Color color = parseNullableEnum(optionalText(dragonElement, "color"), Color.class, "color");
        DragonType type = parseNullableEnum(optionalText(dragonElement, "type"), DragonType.class, "type");
        DragonCharacter character = parseNullableEnum(optionalText(dragonElement, "character"), DragonCharacter.class, "character");

        DragonHead head = null;
        Element headElement = optionalElement(dragonElement, "head");
        if (headElement != null && (!headElement.getTextContent().isBlank() || hasElementChildren(headElement))) {
            head = new DragonHead(
                    parseFloat(requiredText(headElement, "size"), "head.size"),
                    parseInteger(requiredText(headElement, "toothCount"), "head.toothCount")
            );
        }

        return new Dragon(id, name, coordinates, creationDate, age, color, type, character, head);
    }

    private boolean hasElementChildren(Element element) {
        for (Node node = element.getFirstChild(); node != null; node = node.getNextSibling()) {
            if (node instanceof Element) {
                return true;
            }
        }
        return false;
    }

    private Element requiredElement(Element parent, String tagName) throws ValidationException {
        Element result = optionalElement(parent, tagName);
        if (result == null) {
            throw new ValidationException("Отсутствует обязательный элемент <" + tagName + ">.");
        }
        return result;
    }

    private Element optionalElement(Element parent, String tagName) {
        for (Node node = parent.getFirstChild(); node != null; node = node.getNextSibling()) {
            if (node instanceof Element element && tagName.equals(element.getTagName())) {
                return element;
            }
        }
        return null;
    }

    private String requiredText(Element parent, String tagName) throws ValidationException {
        String value = optionalText(parent, tagName);
        if (value == null) {
            throw new ValidationException("Отсутствует обязательный элемент <" + tagName + ">.");
        }
        if (value.isBlank()) {
            throw new ValidationException("Элемент <" + tagName + "> не должен быть пустым.");
        }
        return value;
    }

    private String optionalText(Element parent, String tagName) {
        Element element = optionalElement(parent, tagName);
        if (element == null) {
            return null;
        }
        return element.getTextContent();
    }

    private Integer parseInteger(String value, String fieldName) throws ValidationException {
        try {
            return Integer.valueOf(value.trim());
        } catch (NumberFormatException e) {
            throw new ValidationException("Поле " + fieldName + " должно быть целым числом.");
        }
    }

    private long parseLong(String value, String fieldName) throws ValidationException {
        try {
            return Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            throw new ValidationException("Поле " + fieldName + " должно быть целым числом long.");
        }
    }

    private double parseDouble(String value, String fieldName) throws ValidationException {
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            throw new ValidationException("Поле " + fieldName + " должно быть числом double.");
        }
    }

    private Float parseFloat(String value, String fieldName) throws ValidationException {
        try {
            return Float.valueOf(value.trim());
        } catch (NumberFormatException e) {
            throw new ValidationException("Поле " + fieldName + " должно быть числом float.");
        }
    }

    private Date parseDate(String value, String fieldName) throws ValidationException {
        try {
            return Date.from(Instant.parse(value.trim()));
        } catch (DateTimeParseException e) {
            throw new ValidationException("Поле " + fieldName + " должно быть датой в формате ISO-8601.");
        }
    }

    private <E extends Enum<E>> E parseNullableEnum(String value, Class<E> enumClass, String fieldName) throws ValidationException {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return Enum.valueOf(enumClass, value.trim());
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Поле " + fieldName + " содержит неизвестное значение enum.");
        }
    }
}
