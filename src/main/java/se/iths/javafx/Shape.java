package se.iths.javafx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

public abstract class Shape {
    private int size;
    private Color color;
    private final double pointX;
    private final double pointY;
    public Shape(int size, Color color, double centerX, double centerY) {
        this.size = size;
        this.color = color;
        this.pointX = centerX;
        this.pointY = centerY;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getPointX() {
        return pointX;
    }

    public double getPointY() {
        return pointY;
    }

    public static Shape createShape(ShapeType type, int size, Color color, double pointX, double pointY) {
        return switch (type) {
            case CIRCLE -> new Circle(size, color, pointX - size/2.0, pointY - size/2.0);
            case SQUARE -> new Square(size, color, pointX - size/2.0, pointY - size/2.0);
        };
    }
    public abstract String getSvgRepresentation();
    public abstract void draw(GraphicsContext context);
    public abstract boolean isInsideShape(double pointX, double pointY);
    public void changeShape(Color color, int size) {
        setColor(color);
        setSize(size);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return size == shape.size && Double.compare(shape.pointX, pointX) == 0 && Double.compare(shape.pointY, pointY) == 0 && Objects.equals(color, shape.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, color, pointX, pointY);
    }
}