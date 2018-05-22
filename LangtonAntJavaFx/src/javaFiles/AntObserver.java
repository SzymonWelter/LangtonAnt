package javaFiles;

import controllers.AntsController;
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
