package controllers;

import javaFiles.Ant;
import javaFiles.Behavior;
import javaFiles.Board;
import javafx.animation.*;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;


public class MainController {

    @FXML
    private TextField widthField;

    @FXML
    private TextField heightField;

    @FXML
    private Canvas mainCanvas;

    @FXML
    private TextField behaviorTextField;

    @FXML
    private Label cycleLabel;

    @FXML
    private ListView<Ant> antList;

    @FXML
    private Slider cycleSlider;

    private ObservableList<Ant> observableAntList;

    private GraphicsContext gc;

    private AnimationTimer timer;

    private int numberOfAnts;

    private Behavior behavior;

    private Board board;

    private static int width;
    private static int height;
    private Integer cycle;


    @FXML
    void initialize(){
        cycle = 0;
        numberOfAnts = 0;
        behavior = Behavior.getInstance();

        ListProperty<Ant> antListProperty = new SimpleListProperty<>();
        observableAntList = FXCollections.observableArrayList();
        antListProperty.set(observableAntList);
        antList.itemsProperty().bindBidirectional(antListProperty);

        gc = mainCanvas.getGraphicsContext2D();
        width = 600;
        height = 600;
        board = new Board(600,600);
        setCanvasValues();

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (int i = 0; i < cycleSlider.getValue(); i++){
                    onUpdate();
                    cycle++;
                }
                cycleLabel.setText(cycle.toString());
            }
        };
    }

    private void onUpdate(){
        for(Ant ant: observableAntList){
          board.setBoardColor(behavior.setNewPixelColor(board.getBoardColor(ant.getX(),ant.getY()),ant),ant.getX(),ant.getY());
          gc.getPixelWriter().setColor(ant.getX(),ant.getY(),board.getBoardColor(ant.getX(),ant.getY()));
          ant.goThrough();
        }
    }

    public void onMouseClicked(javafx.scene.input.MouseEvent e){
        Ant ant = new Ant((int)e.getX(),(int)e.getY(),"#" + ++numberOfAnts);
        observableAntList.add(ant);
    }


    private void setCanvasValues(){

        mainCanvas.setHeight(height);
        mainCanvas.setWidth(width);
        board.initBoard(width,height);
        gc.rect(0,0,height,width);
        gc.stroke();
        gc.clearRect(2,2,height-3,width-3);
    }

    public void setCanvas(){
        timer.stop();
        observableAntList.clear();
        width = Integer.parseInt(widthField.getText());
        height = Integer.parseInt(heightField.getText());
        setCanvasValues();

    }

    public void setStringBehavior(){
        behavior.setStringBehavior(behaviorTextField.getText());
    }

    public void startSimulation(){

        timer.start();
    }
    public void stopSimulation(){

        timer.stop();
    }

    public void reset(){
        numberOfAnts = 0;
        cycle = 0;
        board = new Board(width,height);
        timer.stop();
        observableAntList.clear();
        gc.clearRect(2,2,height-3,width-3);
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }
}