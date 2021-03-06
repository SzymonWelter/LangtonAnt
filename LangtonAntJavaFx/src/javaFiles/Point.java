package javaFiles;

/**
 * \brief Klasa reprezentująca punkt lub wektor
 */
public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void add(Point point) {
        this.x += point.getX();
        this.y += point.getY();
    }


    public String toString() {
        return "x: " + x + ", y: " + y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
