package ru.itmo.lab5.model;

import java.util.Objects;
import ru.itmo.lab5.exception.ValidationException;

/**
 * Stores dragon coordinates.
 */
public class Coordinates {
    private final double x;
    private final Float y;

    /**
     * Creates validated coordinates.
     *
     * @param x x coordinate
     * @param y y coordinate, must be greater than -524 and not null
     * @throws ValidationException if constraints are violated
     */
    public Coordinates(double x, Float y) throws ValidationException {
        if (y == null) {
            throw new ValidationException("Поле coordinates.y не может быть null.");
        }
        if (y <= -524) {
            throw new ValidationException("Поле coordinates.y должно быть больше -524.");
        }
        this.x = x;
        this.y = y;
    }

    /**
     * Returns x coordinate.
     *
     * @return x coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Returns y coordinate.
     *
     * @return y coordinate
     */
    public Float getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates{x=" + x + ", y=" + y + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Coordinates that)) {
            return false;
        }
        return Double.compare(that.x, x) == 0 && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
