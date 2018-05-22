package controllers;

import javaFiles.Board;
import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class GameController {

    @FXML
    private Slider speedSlider;

    private IntegerProperty cycle;

    private DoubleProperty speedProperty;

    @FXML
    private Button startAndStopButton;

    @FXML
    private Label cycleLabel;

    private AntsController antsController;

    private AnimationTimer timer;

    private static GameController instance;

    public static GameController getInstance() {
        return instance;
    }

    @FXML
    public void initialize() {

        instance = this;

        cycle = new SimpleIntegerProperty(0);
        speedProperty = new SimpleDoubleProperty(1);
        speedSlider.valueProperty().bindBidirectional(speedProperty);

        antsController = AntsController.getInstance();
        StringConverter converter = new NumberStringConverter();
        cycleLabel.textProperty().bindBidirectional(cycle, converter);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (int i = 0; i < speedProperty.getValue(); i++) {
                    antsController.onUpdate();
                    cycle.setValue(cycle.get() + 1);
                }
            }
        };
    }

    public void theGame() {

        if (startAndStopButton.getText().equals("Start")) {
            timer.start();
            startAndStopButton.setText("Stop");
        } else {
            stopGame();
        }

    }

    public void resetGame() {
        stopGame();
        BoardController.getInstance().clear();
        antsController.clear();
        cycle.setValue(0);
    }

    public void stopAndClearAnts() {
        stopGame();
        antsController.clear();
    }

    public void stopGame() {
        timer.stop();
        startAndStopButton.setText("Start");
    }

    public void clear(){
        stopGame();
        BoardController.getInstance().clear();
    }
}
