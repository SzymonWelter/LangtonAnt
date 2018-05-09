package javaFiles;

import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Behavior {


    public static final String DEFAULT_BEHAVIOR = "RL";

    private String stringBehavior;

    private List<Color> colorBehavior;

    private Character nextStep;

    public Behavior(){
        stringBehavior = DEFAULT_BEHAVIOR;
        colorBehavior = new ArrayList<>();
        colorBehavior.addAll(Arrays.asList(Color.WHITE,Color.BLUE,Color.RED, Color.GREEN,
                Color.YELLOW, Color.ORANGE,Color.VIOLET,Color.SEAGREEN,Color.FUCHSIA,
                Color.GREY,Color.DARKRED,Color.DARKGREEN,Color.DARKBLUE, Color.DARKTURQUOISE,Color.TAN,
                Color.PAPAYAWHIP,Color.BROWN, Color.DARKGOLDENROD,Color.KHAKI,Color.MAROON,
                Color.SIENNA,Color.OLIVE,Color.DARKORANGE,Color.ROSYBROWN));
    }


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

    @Override
    public String toString(){
        return stringBehavior;
    }
}
