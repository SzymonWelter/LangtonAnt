package controllers;

import javaFiles.Ant;
import javaFiles.AntObserver;
import javafx.beans.InvalidationListener;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

import java.util.Observable;
import java.util.Observer;


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
    private TextField behaviorTextField;

    @FXML
    private ListView<Ant> antListView;

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

        behaviorTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.isEmpty())
                switch(newValue.charAt(newValue.length()-1)){
                    case 'R':
                    case 'L':
                    case 'r':
                    case 'l':
                        break;
                    default:
                        behaviorTextField.setText(oldValue);
                }
        });

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
        }
    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }

    public void changeBehavior(){
        for (Ant ant:observableAntList) {
            ant.setBehavior(behaviorTextField.getText());
        }
    }

    public void showAntProperties(Ant ant){
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
    }


}
