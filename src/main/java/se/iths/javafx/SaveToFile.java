package se.iths.javafx;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SaveToFile {

    static FileChooser fileChooser = new FileChooser();
    static Path filePath;
    private static final String svgStart = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
            "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n" +
            "<svg width=\"488\" height=\"371\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">\n";
    private static final String svgEnd = "</svg>";

    public static String svgRepresentation(ShapeModel shapeModel) {
        String svgContent = "";
        for (Shape shape : shapeModel.getShapeList()) {
            svgContent += shape.getSvgRepresentation() + "\n";
        }
        return svgContent;
    }

    private static String svgSaver(ShapeModel shapeModel) {
        return svgStart + svgRepresentation(shapeModel) + svgEnd;

    }

    public static void save(ShapeModel shapeModel, Stage stage) {
        fileChooser.setInitialFileName("Labb3");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SVG-fil", "*.svg"));
        filePath = fileChooser.showSaveDialog(stage.getOwner()).toPath();
        try {
            Files.write(filePath, svgSaver(shapeModel).getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
