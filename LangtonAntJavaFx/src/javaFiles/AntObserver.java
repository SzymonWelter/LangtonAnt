package javaFiles;

import controllers.AntsController;
import javafx.beans.value.ObservableValue;

import java.util.Observable;
import java.util.Observer;

public class AntObserver implements Observer {

    private AntsController antsController = AntsController.getInstance();

    private Ant observableAnt = null;

    public AntObserver(Ant observableAnt){
        this.observableAnt = observableAnt;
        antsController.showAntProperties(observableAnt);
    }

    @Override
    public void update(Observable o, Object arg) {
        antsController.showAntProperties((Ant)o);
    }
}
