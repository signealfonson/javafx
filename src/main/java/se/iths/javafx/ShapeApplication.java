package se.iths.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ShapeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShapeApplication.class.getResource("shape-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        ShapeViewController shapeViewController = fxmlLoader.getController();
        shapeViewController.setStage(stage);
        stage.setTitle("Shape drawings");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}