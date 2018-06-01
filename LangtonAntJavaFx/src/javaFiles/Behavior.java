package javaFiles;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * \brief Klasa Behavior określa zachowanie mrówki
 *
 * Zachowanie mrówki to String zawierający litery R i L. Litera o indeksie i oznacza obrót mrówki po zmienieniu koloru pixela na kolor o indeksie i.
 */
public class Behavior {


    public static String GLOBAL_BEHAVIOR = "RL"; /*!< Zmienna, którą można ustawić globalne zachowanie*/

    private String stringBehavior; /*!< String określający zachowanie mrówki*/

    private List<Color> colorBehavior; /*!< Lista kolorów dostępnych do użytkowania przez mrówkę*/

    private Character nextStep; /*!< Zmienna na podstawie której zmieniany jest zwrot mrówki*/

    public Behavior() {
        stringBehavior = GLOBAL_BEHAVIOR;
        colorBehavior = new ArrayList<>();
        colorBehavior.addAll(Arrays.asList(Color.WHITE, Color.BLUE, Color.RED, Color.GREEN,
                Color.YELLOW, Color.ORANGE, Color.VIOLET, Color.SEAGREEN, Color.FUCHSIA,
                Color.GREY, Color.DARKRED, Color.DARKGREEN, Color.DARKBLUE, Color.DARKTURQUOISE,
                Color.TAN, Color.PAPAYAWHIP, Color.BROWN, Color.DARKGOLDENROD, Color.KHAKI,
                Color.MAROON, Color.SIENNA, Color.OLIVE, Color.DARKORANGE, Color.ROSYBROWN,
                Color.GAINSBORO, Color.NAVAJOWHITE, Color.FORESTGREEN, Color.CADETBLUE,
                Color.GAINSBORO, Color.HONEYDEW));
    }

    /**
     * Funkcja na podstawie starego koloru zwraca nowy
     * @param oldColor kolor przed zmianą
     * @return kolor po zmianie
     */
    public Color getNextColor(Color oldColor) {
        int newColorIndex = colorBehavior.indexOf(oldColor) + 1 < stringBehavior.length() ? colorBehavior.indexOf(oldColor) + 1 : 0;
        nextStep = stringBehavior.charAt(newColorIndex);
        return colorBehavior.get(newColorIndex);
    }

    public Character getNextStep() {
        return nextStep;
    }

    @Override
    public String toString() {
        return stringBehavior;
    }

    public Color getColorBehavior(int index) {
        return colorBehavior.get(index);
    }

    public void setStringBehavior(String stringBehavior) {
        this.stringBehavior = stringBehavior;
    }

    public void setGlobalBehavior()
    {
        stringBehavior = GLOBAL_BEHAVIOR;
    }
    public int getNumberColor(){
        return colorBehavior.size();
    }

}
