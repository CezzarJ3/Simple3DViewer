package com.vsu.cgcourse;

import com.vsu.cgcourse.math.Vector3f;
import com.vsu.cgcourse.obj_writer.ObjWriter;
import javafx.fxml.FXML;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.File;

import com.vsu.cgcourse.model.Mesh;
import com.vsu.cgcourse.obj_reader.ObjReader;
import com.vsu.cgcourse.render_engine.Camera;
import com.vsu.cgcourse.render_engine.RenderEngine;

public class GuiController {

    final private float TRANSLATION = 5.0F;
    @FXML
    public TextField changeScaleX, changeScaleY, changeScaleZ;
    @FXML
    public Button resetButton;

    private float sx = 1, sy = 1, sz = 1, rx = 180, ry = 0, rz = 0, tx = 0, ty = 0, tz = 0;

    private float positionPrimaryButtonX = 0, positionPrimaryButtonY = 0,
            positionSecondaryButtonX = 0, positionSecondaryButtonY = 0,
            positionPrimaryControlX = 0;

    @FXML
    AnchorPane anchorPane;

    @FXML
    private Canvas canvas;

    private Mesh mesh = null;

    private Camera camera = new Camera(
            new Vector3f(0, 00, 1500),
            new Vector3f(0, 0, 0),
            1.0F, 1, 0.01F, 100);

    private Timeline timeline;

    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

        changeScaleX.setText(sx + "");
        changeScaleY.setText(sy + "");
        changeScaleZ.setText(sz + "");

        timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);

        KeyFrame frame = new KeyFrame(Duration.millis(15), event -> {
            double width = canvas.getWidth();
            double height = canvas.getHeight();

            canvas.getGraphicsContext2D().clearRect(0, 0, width, height);
            camera.setAspectRatio((float) (width / height));

            if (mesh != null) {
                RenderEngine.render(canvas.getGraphicsContext2D(), camera, mesh, (int) width, (int) height, sx, sy, sz, rx, ry, rz, tx, ty, tz);
            }
        });

        timeline.getKeyFrames().add(frame);
        timeline.play();
    }

    @FXML
    private void onOpenModelMenuItemClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Model (*.obj)", "*.obj"));
        fileChooser.setTitle("Load Model");

        File file = fileChooser.showOpenDialog((Stage) canvas.getScene().getWindow());
        if (file == null) {
            return;
        }

        Path fileName = Path.of(file.getAbsolutePath());
        try {
            String fileContent = Files.readString(fileName);
            mesh = ObjReader.read(fileContent);

            // todo: обработка ошибок (готово?)
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка чтения!");
            alert.setContentText("\tПроизошла ошибка чтения модели!\n" + exception.getMessage());
            alert.showAndWait();
        }
    }

    //todo: тут надо доделать после исправления ошибок
    @FXML
    private void onWriteModelMenuItemClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Model (*.obj)", "*.obj"));
        fileChooser.setTitle("Write Model");

        File file = fileChooser.showSaveDialog((Stage) canvas.getScene().getWindow());
        try {
            String filename = file.getAbsolutePath();
            System.out.println(filename);
            ObjWriter.write(mesh, filename);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка записи!");
            alert.setContentText("\tПроизошла ошибка записи!\n" + e.getMessage());
            alert.showAndWait();
        }

        // todo: обработка ошибок (выполнена?)
    }

    @FXML
    public void handleCameraForward(ActionEvent actionEvent) {
        camera.movePosition(new Vector3f(0, 0, -TRANSLATION));
    }

    @FXML
    public void handleCameraBackward(ActionEvent actionEvent) {
        camera.movePosition(new Vector3f(0, 0, TRANSLATION));
    }

    @FXML
    public void handleCameraLeft(ActionEvent actionEvent) {
        camera.movePosition(new Vector3f(TRANSLATION, 0, 0));
    }

    @FXML
    public void handleCameraRight(ActionEvent actionEvent) {
        camera.movePosition(new Vector3f(-TRANSLATION, 0, 0));
    }

    @FXML
    public void handleCameraUp(ActionEvent actionEvent) {
        camera.movePosition(new Vector3f(0, TRANSLATION, 0));
    }

    @FXML
    public void handleCameraDown(ActionEvent actionEvent) {
        camera.movePosition(new Vector3f(0, -TRANSLATION, 0));
    }

    @FXML
    public void changeScaleForX(ActionEvent inputEvent) {
        sx = Float.parseFloat(changeScaleX.getText());
        canvas.requestFocus();
    }

    @FXML
    public void changeScaleForY(ActionEvent inputEvent) {
        sy = Float.parseFloat(changeScaleY.getText());
        canvas.requestFocus();
    }

    @FXML
    public void changeScaleForZ(ActionEvent inputEvent) {
        sz = Float.parseFloat(changeScaleZ.getText());
        canvas.requestFocus();
    }

    @FXML
    public void focusCanvas(MouseEvent mouseEvent) {
        canvas.requestFocus();
    }

    @FXML
    public void focusChangeScaleForX(MouseEvent mouseEvent) {
        changeScaleX.requestFocus();
    }

    @FXML
    public void focusChangeScaleForY(MouseEvent mouseEvent) {
        changeScaleY.requestFocus();
    }

    @FXML
    public void forChangeScaleForZ(MouseEvent mouseEvent) {
        changeScaleZ.requestFocus();
    }

    @FXML
    public void handlerStartDrag(MouseDragEvent mouseDragEvent) {
        if (mouseDragEvent.isPrimaryButtonDown()) {
            positionPrimaryButtonX = (float) mouseDragEvent.getX();
            positionPrimaryButtonY = (float) mouseDragEvent.getY();
        } else if (mouseDragEvent.isSecondaryButtonDown()) {
            positionSecondaryButtonX = (float) mouseDragEvent.getX();
            positionSecondaryButtonY = (float) mouseDragEvent.getY();
        }
    }

    @FXML
    public void handlerRotateAndTranslateModel(MouseEvent mouseEvent) {
        if (mouseEvent.isPrimaryButtonDown()) {
            if (mouseEvent.getX() - positionPrimaryButtonX > 10) {
                ry++;
                positionPrimaryButtonX = (float) mouseEvent.getX();
            } else if (mouseEvent.getX() - positionPrimaryButtonX < -10) {
                ry--;
                positionPrimaryButtonX = (float) mouseEvent.getX();
            }
            if (mouseEvent.getY() - positionPrimaryButtonY > 10) {
                rx++;
                positionPrimaryButtonY = (float) mouseEvent.getY();
            } else if (mouseEvent.getY() - positionPrimaryButtonY < -10) {
                rx--;
                positionPrimaryButtonY = (float) mouseEvent.getY();
            }
        }
        if (mouseEvent.isSecondaryButtonDown()) {
            if (mouseEvent.getX() - positionSecondaryButtonX > 10) {
                tx += 0.5;
                positionSecondaryButtonX = (float) mouseEvent.getX();
            } else if (mouseEvent.getX() - positionSecondaryButtonX < -10) {
                tx -= 0.5;
                positionSecondaryButtonX = (float) mouseEvent.getX();
            }
            if (mouseEvent.getY() - positionSecondaryButtonY > 10) {
                ty += 0.5;
                positionSecondaryButtonY = (float) mouseEvent.getY();
            } else if (mouseEvent.getY() - positionSecondaryButtonY < -10) {
                ty -= 0.5;
                positionSecondaryButtonY = (float) mouseEvent.getY();
            }
        }
        if (mouseEvent.isControlDown() && mouseEvent.isPrimaryButtonDown()) {
            if (mouseEvent.getX() - positionPrimaryControlX > 10) {
                rz++;
                positionPrimaryControlX = (float) mouseEvent.getX();
            } else if (mouseEvent.getX() - positionPrimaryControlX < -10) {
                rz--;
                positionPrimaryControlX = (float) mouseEvent.getX();
            }
        }
    }

    @FXML
    public void handlerTranslateAlongZ(ScrollEvent scrollEvent) {
        if (scrollEvent.getDeltaY() > 0) {
            tz += 30;
        } else {
            tz -= 30;
        }
    }

    @FXML
    public void handlerResetValues(ActionEvent actionEvent) {
        sx = 1;
        sy = 1;
        sz = 1;
        rx = 180;
        ry = 0;
        rz = 0;
        tx = 0;
        ty = 0;
        tz = 0;
        positionPrimaryButtonX = 0;
        positionPrimaryButtonY = 0;
        positionSecondaryButtonX = 0;
        positionSecondaryButtonY = 0;
        positionPrimaryControlX = 0;
    }
}