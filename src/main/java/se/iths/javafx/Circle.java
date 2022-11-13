package se.iths.javafx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape{
    double radie = getSize() /2.0;

    public Circle(int size, Color color, double pointX, double pointY) {
        super(size, color, pointX, pointY);
    }

    @Override
    public String getSvgRepresentation() {
        String convertColor = getColor().toString().substring(2, 10);
        return "<circle cx=\"" + getPointX() +
                "\" cy=\"" + getPointY() +
                "\" r=\"" + radie +
                "\" fill=\"#" + convertColor +
                "\" />";
    }
    @Override
    public void draw(GraphicsContext context) {
        context.setFill(getColor());
        context.fillOval(getPointX(), getPointY(), getSize(), getSize());
    }
    @Override
    public boolean isInsideShape(double pointX, double pointY) {
        double distX = pointX - (this.getPointX() + radie);
        double distY = pointY - (this.getPointY() + radie);
        double distance = Math.sqrt( (distX*distX) + (distY*distY) );

        return distance <= radie;
    }
}
