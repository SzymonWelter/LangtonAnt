package controllers;

import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;

public class MainController {

    private AntsController antsController;

    @FXML
    void initialize(){

        antsController = AntsController.getInstance();
    }



}
