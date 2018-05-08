package controllers;

import javaFiles.Ant;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class AntsController {

    private static AntsController instance;

    public AntsController(){
        instance = this;
    }

    public static AntsController getInstance(){
        return instance;
    }

    private BoardController boardController;

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
        System.out.println(boardController);
        this.boardController = boardController;
    }

    public void changeBehavior(){
        for (Ant ant:observableAntList) {
            ant.setBehavior(behaviorTextField.getText());
        }
    }

    public void showAntProperties(MouseEvent e){
        Ant ant = antListView.getSelectionModel().getSelectedItem();

    }

    public void clear() {
        observableAntList.clear();
    }
}
