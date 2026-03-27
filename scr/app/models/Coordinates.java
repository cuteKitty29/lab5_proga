package models;

import java.util.Objects;

/** Координаты дракона. */
public class Coordinates {
    private double x;
    private Float y;

    /**
     * Создаёт координаты.
     * @param x координата x
     * @param y координата y, должна быть больше -524 и не быть null
     */
    public Coordinates(double x, Float y) {
        setX(x);
        setY(y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        if (y == null) {
            throw new IllegalArgumentException("Поле coordinates.y не может быть null.");
        }
        if (y <= -524) {
            throw new IllegalArgumentException("Поле coordinates.y должно быть больше -524.");
        }
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{x=" + x + ", y=" + y + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Coordinates ){
            Coordinates that = (Coordinates) o;
            return (this.x == that.getX() && this.y == that.getY());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
