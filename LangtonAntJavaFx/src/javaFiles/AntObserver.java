package javaFiles;

import controllers.AntsController;
<<<<<<< HEAD
=======
import javafx.beans.value.ObservableValue;
>>>>>>> 2f2ca34afab785e2cf1f52a446b8832852a5a527

import java.util.Observable;
import java.util.Observer;

public class AntObserver implements Observer {

    private AntsController antsController = AntsController.getInstance();


    public AntObserver(Ant observableAnt){
        antsController.showAntProperties(observableAnt);
    }

    @Override
    public void update(Observable o, Object arg) {
        antsController.showAntProperties((Ant)o);
    }
}
