package controllers;

import javaFiles.Ant;
import javaFiles.Board;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class BoardController {
    @FXML
    private TextField widthField;

    @FXML
    private TextField heightField;

    @FXML
    private Canvas mainCanvas;

    private GraphicsContext gc;

    private Board board;

    private static final int START_BOARD_WIDTH = 602;
    private static final int START_BOARD_HEIGHT = 602;

    private static BoardController instance;

    public BoardController()
    {
        instance = this;
        AntsController.getInstance().setBoardController(this);
    }
    public static BoardController getInstance(){
        return instance;
    }

    @FXML
    public void initialize() {
        gc = mainCanvas.getGraphicsContext2D();
        board = new Board(START_BOARD_WIDTH, START_BOARD_HEIGHT);
        mainCanvas.setWidth(START_BOARD_WIDTH);
        mainCanvas.setHeight(START_BOARD_HEIGHT);
    }

    public void onMouseClicked(javafx.scene.input.MouseEvent e){
        AntsController.addAntToList(new Ant((int)e.getX(),(int)e.getY()));
    }

    @FXML
    public void setNewSizeOfBoard(){

        GameController.getInstance().resetGame();

        int width = Integer.parseInt(widthField.getText()) + 2;
        int height = Integer.parseInt(heightField.getText()) + 2;

        board = new Board(width,height);
        mainCanvas.setWidth(width);
        mainCanvas.setHeight(height);
        gc.clearRect(0,0,width,height);
    }

    public void setNewPixelColor(Ant ant){
        int x = ant.getX();
        int y = ant.getY();
        Color newColor = ant.getNewColor(board.getBoardColor(x,y));
        board.setBoardColor(newColor,x,y);
        gc.getPixelWriter().setColor(x,y,newColor);
    }

    public int getHeight(){
        return board.getHeight();
    }

    public int getWidth(){
        return board.getWidth();
    }

    public void clear() {
        board = new Board(board.getWidth(),board.getHeight());
        gc.clearRect(0,0,board.getWidth(),board.getHeight());
    }
}

