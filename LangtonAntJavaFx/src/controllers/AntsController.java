package controllers;

import javaFiles.Ant;
import javaFiles.AntObserver;
import javaFiles.Behavior;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;



public class AntsController{

    private static AntsController instance;
    private Ant observableAnt;

    public AntsController(){
        instance = this;
    }

    public static AntsController getInstance(){
        return instance;
    }

    private BoardController boardController;

    private AntObserver antObserver;

    @FXML
    private Label antId;
    private StringProperty antIdProperty = new SimpleStringProperty();

    @FXML
    private Label paramX;
    private IntegerProperty paramXProperty = new SimpleIntegerProperty();

    @FXML
    private Label paramY;
    private IntegerProperty paramYProperty = new SimpleIntegerProperty();

    @FXML
    private Label antBehavior;
    private StringProperty antBehaviorProperty = new SimpleStringProperty();

    @FXML
    private TextField globalBehaviorTextField;

    @FXML
    private ListView<Ant> antListView;

    @FXML
    private Canvas canvaBehavior;

    private GraphicsContext gc;

    private static ObservableList<Ant> observableAntList;

    @FXML
    public void initialize(){

        ListProperty<Ant> antListProperty = new SimpleListProperty<>();
        observableAntList = FXCollections.observableArrayList();
        antListProperty.set(observableAntList);
        antListView.itemsProperty().bindBidirectional(antListProperty);

        StringConverter converter = new NumberStringConverter();

        antId.textProperty().bindBidirectional(antIdProperty);
        paramX.textProperty().bindBidirectional(paramXProperty,converter);
        paramY.textProperty().bindBidirectional(paramYProperty,converter);
        antBehavior.textProperty().bindBidirectional(antBehaviorProperty);

        gc = canvaBehavior.getGraphicsContext2D();

        globalBehaviorTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(parseBehavior(newValue))
                addRectangle(newValue);
            else
                globalBehaviorTextField.setText(oldValue);
        });

        globalBehaviorTextField.setText(Behavior.DEFAULT_BEHAVIOR);

    }

    private boolean parseBehavior(String newValue){

        for(int i = 0; i<newValue.length();i++){
            switch (newValue.charAt(i)){
                case 'R':
                case 'L':
                case 'r':
                case 'l':
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    private void addRectangle(String behavior) {

        canvaBehavior.setWidth(20+behavior.length()*30);
        gc.clearRect(0,0,canvaBehavior.getWidth(),canvaBehavior.getHeight());
        for (int i = 0; i < behavior.length();i++){
            gc.setFill(new Behavior().getColorBehavior(i));
            gc.fillRect(10+i*30,15,30,30);
            gc.setStroke(Color.BLACK);
            gc.strokeRect(10+i*30,15,30,30);
            gc.setFill(Color.BLACK);
            gc.fillText(String.valueOf(behavior.charAt(i)), 20+i*30,35);
        }
    }

    public static void addAntToList(Ant ant){
        ant.setId("#" + String.valueOf(observableAntList.size() + 1));
        observableAntList.add(ant);
    }

    public void onUpdate(){
        for (Ant ant: observableAntList ) {
            boardController.setNewPixelColor(ant);
            ant.spinAnt();
            ant.goThrough();
            ant.refresh();
        }
    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }

    public void changeBehavior(){
        Behavior.setDefaultBehavior(globalBehaviorTextField.getText());
        for (Ant ant:observableAntList) {
            ant.setBehavior();
            ant.refresh();
        }
    }

    public void showAntProperties(Ant ant){
        paramX.setVisible(true);
        paramY.setVisible(true);
        antIdProperty.setValue(ant.toString());
        paramXProperty.setValue(ant.getX());
        paramYProperty.setValue((ant.getY()));
        antBehaviorProperty.setValue(ant.getBehavior().toString());
    }

    public void checkedAnt(){
        if(observableAnt!=null)
            observableAnt.deleteObserver(antObserver);
        observableAnt = antListView.getSelectionModel().getSelectedItem();
        antObserver = new AntObserver(observableAnt);
        observableAnt.addObserver(antObserver);
    }

    public void clear() {
        observableAntList.clear();
        globalBehaviorTextField.setText("RL");
        paramX.setText("");
        paramY.setText("");
        antBehavior.setText("");
        antId.setText("");
    }

}
