package se.iths.javafx;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ShapeViewController {
    ShapeModel model = new ShapeModel();
    public Canvas canvas;
    public GraphicsContext context;
    public Button undoButton;
    public CheckBox selectModeBox;
    public ColorPicker colorPickerChoice;
    public ChoiceBox<ShapeType> choiceBox;
    public TextField sizeField;
    public Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        context = canvas.getGraphicsContext2D();
        choiceBox.setItems(model.getShapeTypesList());
        choiceBox.valueProperty().bindBidirectional(model.getShapeTypeProperty());
        colorPickerChoice.valueProperty().bindBidirectional(model.getColorProperty());
        sizeField.textProperty().bindBidirectional(model.getSizeProperty());
    }

    public void onCanvasClicked(MouseEvent mouseEvent) {
        if (selectModeBox.isSelected()){
            model.getShapeList().stream()
                    .filter(shape -> shape.isInsideShape(mouseEvent.getX(), mouseEvent.getY()))
                    .reduce((first, second) -> second)
                    .ifPresent(presentShape -> {
                        model.addChangedShapeToUndoDeque(presentShape, presentShape.getColor(), presentShape.getSize());
                        presentShape.changeShape(model.getColor(), model.getSize());
                    });
            draw();
        } else {
            Shape shape = Shape.createShape(model.getShapeType(), model.getSize(), model.getColor(), mouseEvent.getX(), mouseEvent.getY());
            model.addShapeToUndoDeque(shape);
            model.addShapesToList(shape);
            draw();
        }
    }
    private void draw() {
        context.setFill(Color.WHITESMOKE);
        context.fillRect(0,0,1000,1000);
        model.getShapeList().forEach(shape -> shape.draw(context));
    }

    public void undoClicked() {
        model.undo();
        draw();
    }

    public void saveClicked() {
        SaveToFile.save(model, stage);
    }
}