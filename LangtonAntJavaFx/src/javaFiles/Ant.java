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
import java.util.Random;

import static javaFiles.Ant.Direction.*;
import static javaFiles.Ant.Direction.NORTH;

public class Ant {

    public static final Direction DEFAULT_DIR = NORTH;
    private String id;
    private int x;
    private int y;
    private Behavior behavior;
    private Direction dir;
    private static BoardController boardController = BoardController.getInstance();

    enum Direction{
        NORTH(0,-1),
        EAST(1,0),
        SOUTH(0,1),
        WEST(-1,0);

        Point2D vector;

        Direction(int x, int y){
            vector = new Point2D(x,y);
        }

    }

    public Ant(){
        Random r = new Random();
        this.x = r.nextInt();
        this.y = r.nextInt();
        this.dir = NORTH;
        behavior = new Behavior();
    }

    public Ant(int x, int y){
        this.x = x;
        this.y = y;
        this.dir = DEFAULT_DIR;
        behavior = new Behavior();
    }

    public Ant(int x, int y, String id, Direction dir, Behavior behavior){
        this.x = x;
        this.y = y;
        this.id = id;
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
        return x;
    }

    public int getY(){
        return y;
    }

    public void goThrough(){

        x+=dir.vector.getX();
        y+=dir.vector.getY();

        if(x==0)
            x = boardController.getWidth()-2;
        else if(x==boardController.getWidth()-1)
            x = 1;

        if(y==0)
            y = boardController.getHeight() - 2;
        else if(y == boardController.getHeight()-1)
            y=1;
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
}
