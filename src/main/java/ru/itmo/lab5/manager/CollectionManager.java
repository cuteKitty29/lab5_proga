package ru.itmo.lab5.manager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import ru.itmo.lab5.model.Dragon;
import ru.itmo.lab5.model.DragonHead;

/**
 * Manages the main {@link PriorityQueue} collection and implements all operations on it.
 */
public class CollectionManager {
    private final PriorityQueue<Dragon> dragons = new PriorityQueue<>();
    private final LocalDateTime initializationTime = LocalDateTime.now();

    /**
     * Replaces the current collection with loaded dragons.
     *
     * @param loadedDragons dragons loaded from file
     */
    public void load(Collection<Dragon> loadedDragons) {
        dragons.clear();
        dragons.addAll(loadedDragons);
    }

    /**
     * Adds a dragon to the collection.
     *
     * @param dragon dragon to add
     * @return {@code true} if the queue changed
     */
    public boolean add(Dragon dragon) {
        return dragons.offer(Objects.requireNonNull(dragon));
    }

    /**
     * Replaces a dragon with the given id.
     *
     * @param id id to replace
     * @param replacement new dragon value
     * @return {@code true} if the dragon existed and was replaced
     */
    public boolean updateById(int id, Dragon replacement) {
        boolean removed = removeById(id);
        if (!removed) {
            return false;
        }
        dragons.offer(replacement);
        return true;
    }

    /**
     * Removes a dragon by id.
     *
     * @param id dragon id
     * @return {@code true} if an element was removed
     */
    public boolean removeById(int id) {
        return dragons.removeIf(dragon -> dragon.getId() == id);
    }

    /**
     * Clears the collection.
     */
    public void clear() {
        dragons.clear();
    }

    /**
     * Returns the first queue element.
     *
     * @return minimal dragon by natural order
     */
    public Optional<Dragon> head() {
        return Optional.ofNullable(dragons.peek());
    }

    /**
     * Removes and returns the first queue element.
     *
     * @return removed minimal dragon
     */
    public Optional<Dragon> removeHead() {
        return Optional.ofNullable(dragons.poll());
    }

    /**
     * Adds a dragon only if it is smaller than the current queue head.
     *
     * @param dragon dragon to compare and maybe add
     * @return {@code true} if the element was added
     */
    public boolean addIfMin(Dragon dragon) {
        Dragon currentHead = dragons.peek();
        if (currentHead == null || dragon.compareTo(currentHead) < 0) {
            dragons.offer(dragon);
            return true;
        }
        return false;
    }

    /**
     * Returns a sorted snapshot of the queue.
     *
     * @return sorted list of dragons
     */
    public List<Dragon> getSortedView() {
        return dragons.stream()
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Returns a snapshot for saving.
     *
     * @return copy of current elements
     */
    public List<Dragon> getSnapshot() {
        return new ArrayList<>(dragons);
    }

    /**
     * Calculates the sum of age values.
     *
     * @return sum of ages
     */
    public long sumOfAge() {
        return dragons.stream().mapToLong(Dragon::getAge).sum();
    }

    /**
     * Counts dragons with age greater than the given value.
     *
     * @param age threshold
     * @return matching count
     */
    public long countGreaterThanAge(long age) {
        return dragons.stream().filter(dragon -> dragon.getAge() > age).count();
    }

    /**
     * Returns non-null heads sorted in descending order.
     *
     * @return sorted head values
     */
    public List<DragonHead> getHeadsDescending() {
        return dragons.stream()
                .map(Dragon::getHead)
                .filter(Objects::nonNull)
                .sorted((left, right) -> right.compareTo(left))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Finds a dragon by id.
     *
     * @param id dragon id
     * @return dragon if found
     */
    public Optional<Dragon> findById(int id) {
        return dragons.stream().filter(dragon -> dragon.getId() == id).findFirst();
    }

    /**
     * Returns the number of stored dragons.
     *
     * @return collection size
     */
    public int size() {
        return dragons.size();
    }

    /**
     * Returns the collection type.
     *
     * @return class name of the collection
     */
    public String getCollectionType() {
        return dragons.getClass().getName();
    }

    /**
     * Returns initialization time.
     *
     * @return application collection initialization time
     */
    public LocalDateTime getInitializationTime() {
        return initializationTime;
    }
}
