package javaFiles;

import controllers.MainController;
import javafx.geometry.Point2D;
import static javaFiles.Ant.Direction.*;

public class Ant {

    private String id;
    private int x;
    private int y;
    private Direction dir;
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

    public Ant(int x, int y, String id){
        this.x = x;
        this.y = y;
        this.dir = NORTH;
        this.id = id;

    }

    void goAnt(boolean isRight){
        if(isRight){
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
            x = MainController.getWidth()-2;
        else if(x==MainController.getWidth()-1)
            x = 1;

        if(y==0)
            y = MainController.getHeight() - 2;
        else if(y == MainController.getHeight()-1)
            y=1;
    }

    @Override
    public String toString(){
        return id;
    }


}
