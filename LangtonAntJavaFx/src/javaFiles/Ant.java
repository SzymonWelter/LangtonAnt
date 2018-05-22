package javaFiles;

import controllers.BoardController;
import javafx.scene.paint.Color;

import java.util.Observable;
import java.util.Random;

import static javaFiles.Ant.Direction.*;

public class Ant extends Observable {

    public static final Direction DEFAULT_DIR = N;
    private String id;
    private Point localization;
    private Behavior behavior;
    private Direction dir;
    private static BoardController boardController = BoardController.getInstance();




    enum Direction{
        N(new Point(0,-1)),
        E(new Point(1,0)),
        S(new Point(0,1)),
        W(new Point(-1,0));

        Point vector;

        Direction(Point point){
            vector = point;
        }

        @Override
        public String toString(){
            if(vector.getX() == 1)
                return E.name();
            else if(vector.getX() == -1)
                return W.name();
            else if(vector.getY() == 1)
                return S.name();
            else
                return N.name();
        }
    }

    public Ant(){
        Random random = new Random();
        this.localization = new Point(random.nextInt(boardController.getWidth()),random.nextInt(boardController.getHeight()));
        setRandomDirection();
        this.behavior = new Behavior();
    }



    public Ant(int x, int y){
        localization = new Point(x,y);
        setRandomDirection();
        behavior = new Behavior();
    }

    public Ant(int x, int y, String id, Direction dir, Behavior behavior){
        this.id = id;
        this.localization = new Point(x,y);
        this.dir = dir;
        this.behavior = behavior;
    }

    public void spinAnt() {

        if (behavior.getNextStep() == 'R' || behavior.getNextStep() == 'r')
            spin(E, S, W, N);
        else
            spin(W, N, E, S);

    }

    protected void spin(Direction chN, Direction chE, Direction chS, Direction chW) {
        switch(dir) {
            case N:
                dir = chN;
                break;
            case E:
                dir =  chE;
                break;
            case S:
                dir = chS;
                break;
            case W:
                dir = chW;
                break;
        }
    }

    private void setRandomDirection() {
        Random random = new Random();
        int dirNo = random.nextInt(4);
        switch (dirNo){
            case 0:
                dir = N;
                break;
            case 1:
                dir = E;
                break;
            case 2:
                dir = S;
                break;
            case 3:
                dir = W;
                break;
        }
    }

    public int getX(){
        return localization.getX();
    }

    public int getY(){
        return localization.getY();
    }

    public void antStep(){

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

    public Behavior getBehavior() {
        return behavior;
    }

    public void setBehavior(String stringBehavior) {
        behavior.setStringBehavior(stringBehavior);
    }

    public void setLocalization(int x, int y) {
        this.localization = new Point(x,y);
    }

    public String getDir() {
        return dir.toString();
    }

    public void setDirection(String direction) {
        switch (direction){
            case "N":
                dir = N;
                break;
            case "E":
                dir = E;
                break;
            case "S":
                dir = S;
                break;
            case "W":
                dir = W;
                break;
            default:
                dir = DEFAULT_DIR;
        }
    }
}
