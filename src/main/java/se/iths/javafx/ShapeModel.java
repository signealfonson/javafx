package se.iths.javafx;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

import java.util.ArrayDeque;
import java.util.Deque;

public class ShapeModel {

    private ObservableList<Shape> shapeList;
    private Deque <Command> undoDeque;

    private ObservableList<ShapeType> shapeTypesList;

    private ObjectProperty<Color> color;

    private StringProperty size;

    private ObjectProperty<ShapeType> shapeType;

    public ShapeModel() {
        shapeTypesList = FXCollections.observableArrayList(ShapeType.values());
        color = new SimpleObjectProperty<>(Color.PINK);
        size = new SimpleStringProperty("50");
        shapeList = FXCollections.observableArrayList();
        undoDeque = new ArrayDeque<>();
        shapeType = new SimpleObjectProperty<>(ShapeType.CIRCLE);

    }

    public void addShapesToList(Shape shape) {
        shapeList.add(shape);
    }

    public void addChangedShapeToUndoDeque(Shape shape, Color color, int size) {
        Command undo = () -> shape.changeShape(color, size);
        undoDeque.push(undo);
    }
    public void addShapeToUndoDeque(Shape shape) {
        Command undo = () -> shapeList.remove(shape);
        undoDeque.push(undo);
    }

    public void undo() {
        Command undoToExecute = undoDeque.pop();
        undoToExecute.execute();
    }
    public ObservableList<ShapeType> getShapeTypesList() {
        return shapeTypesList;
    }

    public ShapeType getShapeType() {
        return shapeType.get();
    }

    public ObjectProperty<ShapeType> getShapeTypeProperty() {
        return shapeType;
    }

    public Property<Color> getColorProperty() {
        return color;
    }

    public Property<String> getSizeProperty() {
        return size;
    }
    public Color getColor() {
        return color.get();
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public int getSize() {
        return Integer.parseInt(size.get());
    }

    public void setSize(String size) {
        this.size.set(size);
    }
    public ObservableList<Shape> getShapeList() {
        return shapeList;
    }

}
