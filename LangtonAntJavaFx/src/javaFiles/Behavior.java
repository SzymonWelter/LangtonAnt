package javaFiles;

import javafx.scene.paint.Color;

public class Behavior {


    private String stringBehavior;

    private final Color [] colorBehavior = new Color[]{ Color.WHITE,
                Color.BLUE,Color.RED,Color.GREEN, Color.SEAGREEN,
                Color.DARKKHAKI ,Color.VIOLET, Color.ALICEBLUE, Color.BROWN,
                Color.GOLDENROD, Color.DARKGOLDENROD, Color.GREENYELLOW, Color.GOLDENROD, Color.CORAL,
                Color.DARKORCHID, Color.FUCHSIA, Color.LIGHTSKYBLUE,Color.LEMONCHIFFON,
                Color.MEDIUMAQUAMARINE
    };

    private static final Behavior instance = new Behavior();

    private Behavior(){
        stringBehavior = "RL";
    }

    public static Behavior getInstance(){
       return instance;
    }

    public Color setNewPixelColor(Color color, Ant ant){
        for(int i = 0; i < stringBehavior.length();i++) {
            if (colorBehavior[i].equals(color)) {

                if (stringBehavior.charAt(i) == 'R') {
                    ant.goAnt(true);
                } else ant.goAnt(false);

                if (i + 1 < stringBehavior.length())
                    return colorBehavior[i + 1];
                else return colorBehavior[0];
            }
        }
        if(stringBehavior.charAt(0) == 'R')
            ant.goAnt(true);
        else ant.goAnt(false);

        return colorBehavior[0];
    }

    public void setStringBehavior(String stringBehavior) {
        this.stringBehavior = stringBehavior;
    }

}
