package javaFiles;

import controllers.BoardController;
import controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sun.awt.SunHints;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Random;

import static javaFiles.Ant.Direction.*;
import static javaFiles.Ant.Direction.NORTH;

public class Ant extends Observable {

    public static final Direction DEFAULT_DIR = NORTH;
    private String id;
    private Point localization = new Point();
    private Behavior behavior;
    private Direction dir;
    private static BoardController boardController = BoardController.getInstance();



    enum Direction{
        NORTH(0,-1),
        EAST(1,0),
        SOUTH(0,1),
        WEST(-1,0);

        Point vector;

        Direction(int x, int y){
            vector = new Point(x,y);
        }

    }

    public Ant(){
        Random r = new Random();
        localization.setX(r.nextInt());
        localization.setY(r.nextInt());
        this.dir = NORTH;
        behavior = new Behavior();
    }

    public Ant(int x, int y){
        localization.setX(x);
        localization.setY(y);
        this.dir = DEFAULT_DIR;
        behavior = new Behavior();
    }

    public Ant(int x, int y, String id, Direction dir, Behavior behavior){
        localization.setX(x);
        localization.setY(y);
        this.dir = dir;
        this.behavior = behavior;
    }

    public void spinAnt(){
        if(behavior.getNextStep().equals('R')){
            spin(EAST, SOUTH, WEST, NORTH);
        }else{
            spin(WEST, NORTH, EAST, SOUTH);
        }
    }

    private void spin(Direction changeNorth, Direction changeEast, Direction changeSouth, Direction changeWest) {
        switch(dir) {
            case NORTH:
                dir = changeNorth;
                break;
            case EAST:
                dir =  changeEast;
                break;
            case SOUTH:
                dir = changeSouth;
                break;
            case WEST:
                dir = changeWest;
                break;
        }
    }

    public int getX(){
        return localization.getX();
    }

    public int getY(){
        return localization.getY();
    }

    public void goThrough(){

        localization.add(dir.vector);

        if(localization.getX()==0)
            localization.setX(boardController.getWidth()-2);
        else if(localization.getX()==boardController.getWidth()-1)
            localization.setX(1);

        if(localization.getY()==0)
            localization.setY(boardController.getHeight()-2);
        else if(localization.getY()==boardController.getHeight()-1)
            localization.setY(1);

    }

    public void refresh() {
        setChanged();
        notifyObservers();
    }


    public Color getNewColor(Color boardColor) {
        return behavior.getNextColor(boardColor);
    }

    @Override
    public String toString(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBehavior(Behavior behavior)
    {
        this.behavior = behavior;
    }

    public void setBehavior(String stringBehavior){
        behavior.setStringBehavior(stringBehavior);
    }

    public Behavior getBehavior() {
        return behavior;
    }

    public void setBehavior() {
        behavior.setStringBehavior(Behavior.getDefaultBehavior());
    }
}
