package LabProgramming5.Human;

import java.util.Objects;

/**
 * Местоположение объектов
 */
public class Coordinates {
    private Integer x;
    private Double y;

    public Coordinates(Integer x, Double y) {
        if (x == null || y == null) throw new NullPointerException();
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public void setX(Integer x) {
        if (x == null) throw new NullPointerException();
        this.x = x;
    }

    public void setY(Double y) {
        if (y == null) throw new NullPointerException();
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Coordinates))
            return false;
        Coordinates that = (Coordinates) o;
        return x.equals(that.x) && y.equals(that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "{" + "\n\t\t\"x\": " + x + ",\n\t\t\"y\": " + y + "\n\t}";
    }
}
