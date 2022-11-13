package se.iths.javafx;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShapeModelTest {
    @Test
    void testThatShapeIsAddedWhenAddShapesToListIsCalled() {
        //Given
        ShapeModel shapeModel = new ShapeModel();
        Circle circle = new Circle(15, Color.PINK, 5, 18);

        //When
        shapeModel.addShapesToList(circle);

        //Then
        assertEquals(shapeModel.getShapeList().size(), 1);
        assertEquals(shapeModel.getShapeList().get(0), circle);
    }
    @Test
    void testThatGetSizeReturnsIntegerWhenGetSizeIsCalled() {
        //Given
        ShapeModel shapeModel = new ShapeModel();
        shapeModel.setSize("15");

        //When
        int actualSize = shapeModel.getSize();

        //Then
        assertEquals(15, actualSize);
    }
}