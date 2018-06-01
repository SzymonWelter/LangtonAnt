package javaFiles;

import controllers.AntsController;

import java.util.Observable;
import java.util.Observer;

/**
 * \brief Klasa, która jest obserwatorem mrówki
 *
 * Klasa ta jest niezbędna do odświeżania aktualnego stanu mrówek
 */
public class AntObserver implements Observer {

    private AntsController antsController = AntsController.getInstance();


    public AntObserver(Ant observableAnt) {
        antsController.showAntProperties(observableAnt);
    }

    /**
     * Update wywołuje metodę w kontrolerze mrówek, która uaktualnia ich parametry
     */
    @Override
    public void update(Observable o, Object arg) {
        antsController.showAntProperties((Ant) o);
    }
}
