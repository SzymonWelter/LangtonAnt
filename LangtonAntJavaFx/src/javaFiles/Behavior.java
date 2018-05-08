package javaFiles;

import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Behavior {


    public static final String DEFAULT_BEHAVIOR = "LLRR";

    private String stringBehavior;

    private List<Color> colorBehavior;

    private Character nextStep;

    public Behavior(){
        stringBehavior = DEFAULT_BEHAVIOR;
        colorBehavior = new ArrayList<>();
        colorBehavior.addAll(Arrays.asList(Color.WHITE,Color.BLUE,Color.RED, Color.GREEN,Color.YELLOW));
    }

    /*public Color setNewPixelColor(Color color, Ant ant){
        for(int i = 0; i < stringBehavior.length();i++) {
            if (colorBehavior[i].equals(color)) {

                if (stringBehavior.charAt(i) == 'R') {
                    ant.spinAnt(true);
                } else ant.spinAnt(false);

                if (i + 1 < stringBehavior.length())
                    return colorBehavior[i + 1];
                else return colorBehavior[0];
            }
        }
        if(stringBehavior.charAt(0) == 'R')
            ant.spinAnt(true);
        else ant.spinAnt(false);

        return colorBehavior[0];
    }*/

    public void setStringBehavior(String stringBehavior) {
        this.stringBehavior = stringBehavior;
    }

    public Color getNextColor(Color oldColor) {
        int newColorIndex = colorBehavior.indexOf(oldColor) + 1 < stringBehavior.length() ? colorBehavior.indexOf(oldColor) + 1 : 0;
        nextStep = stringBehavior.charAt(newColorIndex);
        return colorBehavior.get(newColorIndex);
    }

    public Character getNextStep() {
        return nextStep;
    }
}
