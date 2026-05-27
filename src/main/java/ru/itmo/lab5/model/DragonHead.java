package ru.itmo.lab5.model;

import java.util.Objects;
import ru.itmo.lab5.exception.ValidationException;

/**
 * Stores dragon head parameters.
 */
public class DragonHead implements Comparable<DragonHead> {
    private final Float size;
    private final int toothCount;

    /**
     * Creates validated head parameters.
     *
     * @param size head size, cannot be null
     * @param toothCount number of teeth
     * @throws ValidationException if constraints are violated
     */
    public DragonHead(Float size, int toothCount) throws ValidationException {
        if (size == null) {
            throw new ValidationException("Поле head.size не может быть null.");
        }
        this.size = size;
        this.toothCount = toothCount;
    }

    /**
     * Returns head size.
     *
     * @return head size
     */
    public Float getSize() {
        return size;
    }

    /**
     * Returns tooth count.
     *
     * @return tooth count
     */
    public int getToothCount() {
        return toothCount;
    }

    @Override
    public int compareTo(DragonHead other) {
        int bySize = this.size.compareTo(other.size);
        if (bySize != 0) {
            return bySize;
        }
        return Integer.compare(this.toothCount, other.toothCount);
    }

    @Override
    public String toString() {
        return "DragonHead{size=" + size + ", toothCount=" + toothCount + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DragonHead that)) {
            return false;
        }
        return toothCount == that.toothCount && Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, toothCount);
    }
}
