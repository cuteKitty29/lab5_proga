package models;

import java.util.Objects;

/** Голова дракона. */
public class DragonHead implements Comparable<DragonHead> {
    private Float size;
    private int toothCount;

    /**
     * Создаёт объект головы.
     * @param size размер головы, не может быть null
     * @param toothCount количество зубов
     */
    public DragonHead(Float size, int toothCount) {
        setSize(size);
        setToothCount(toothCount);
    }

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        if (size == null) {
            throw new IllegalArgumentException("Поле head.size не может быть null.");
        }
        this.size = size;
    }

    public int getToothCount() {
        return toothCount;
    }

    public void setToothCount(int toothCount) {
        this.toothCount = toothCount;
    }

    @Override
    public int compareTo(DragonHead other) {
        int bySize = Float.compare(this.size, other.size);
        return bySize != 0 ? bySize : Integer.compare(this.toothCount, other.toothCount);
    }

    @Override
    public String toString() {
        return "DragonHead{size=" + size + ", toothCount=" + toothCount + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o.instanceof DragonHead){
            DragonHead that = (DragonHead) o;
            return (this.size == that.getSize() && this.toothCount == that.getToothCount());
        }
        else{
            return false;
        }

    }

    @Override
    public int hashCode() {
        return Objects.hash(size, toothCount);
    }
}
