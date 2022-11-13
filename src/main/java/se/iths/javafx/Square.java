package se.iths.javafx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Shape {
    public double halfSize = getSize() /2.0;
    public double centerX = getPointX() + halfSize;
    public double centerY = getPointY() + halfSize;

    public Square(int size, Color color, double pointX, double pointY) {
        super(size, color, pointX, pointY);
    }

    @Override
    public String getSvgRepresentation() {
        String convertColor = getColor().toString().substring(2, 10);
        return "<rect x=\"" + getPointX() +
                "\" y=\"" + getPointY() +
                "\" width=\"" + getSize() +
                "\" height=\"" + getSize() +
                "\" fill=\"#" + convertColor +
                "\" />";
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(getColor());
        context.fillRect(getPointX(), getPointY(), getSize(), getSize());
    }

    @Override
    public boolean isInsideShape(double pointX, double pointY) {
        return pointX >= (centerX - halfSize) &&
                pointX <= (centerX + halfSize) &&
                pointY >= (centerY - halfSize) &&
                pointY <= (centerY + halfSize);
    }
}
