package controllers;

import javaFiles.Ant;
import javaFiles.Board;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

/**
 * \brief Klasa, która jest kontrolerem BoardComponent
 */
public class BoardController {

    private static final int START_BOARD_WIDTH = 602;
    private static final int START_BOARD_HEIGHT = 602;
    private static BoardController instance;

    @FXML
    private TextField widthField;
    @FXML
    private TextField heightField;
    @FXML
    private Canvas mainCanvas;

    private Tooltip tooltip = new Tooltip();
    private GraphicsContext gc;
    private Board board;
    private AntsController antsController = AntsController.getInstance();

    public BoardController() {
        instance = this;
        antsController.setBoardController(this);
    }

    public static BoardController getInstance() {
        return instance;
    }

    @FXML
    public void initialize() {
        gc = mainCanvas.getGraphicsContext2D();
        board = new Board(START_BOARD_WIDTH, START_BOARD_HEIGHT);
        mainCanvas.setWidth(START_BOARD_WIDTH);
        mainCanvas.setHeight(START_BOARD_HEIGHT);
        gc.strokeRect(0, 0, START_BOARD_WIDTH, START_BOARD_HEIGHT);
    }

    public void onMouseClicked(javafx.scene.input.MouseEvent e) {
        antsController.addAntToList(new Ant((int) e.getX(), (int) e.getY()));
    }

    @FXML
    public void setNewSizeOfBoard() {

        GameController.getInstance().stopAndClearAnts();

        int width = Integer.parseInt(widthField.getText()) + 2;
        int height = Integer.parseInt(heightField.getText()) + 2;

        board = new Board(width, height);
        mainCanvas.setWidth(width);
        mainCanvas.setHeight(height);
        gc.clearRect(0, 0, width, height);
        gc.strokeRect(0, 0, width, height);
    }

    public void setNewPixelColor(Ant ant) {
        int x = ant.getX();
        int y = ant.getY();
        Color newColor = ant.getNewColor(board.getBoardColor(x, y));
        board.setBoardColor(newColor, x, y);
        gc.getPixelWriter().setColor(x, y, newColor);
    }

    public int getHeight() {
        return board.getHeight();
    }

    public int getWidth() {
        return board.getWidth();
    }

    public void clear() {
        board = new Board(board.getWidth(), board.getHeight());
        gc.clearRect(0, 0, board.getWidth(), board.getHeight());
        gc.strokeRect(0, 0, board.getWidth(), board.getHeight());
    }

    public void showPosition(MouseEvent event) {
        tooltip.setText("x: " + event.getX() + " y: " + event.getY());
        tooltip.show(mainCanvas, event.getScreenX() + 10, event.getScreenY() + 10);
    }

    public void hidePosition(MouseEvent event) {
        tooltip.hide();
    }

    public void savePicture() {
        File file = new File("LangtonAntJavaFx/out/Output");
        WritableImage writableImage = new WritableImage((int) mainCanvas.getWidth(), (int) mainCanvas.getHeight());
        mainCanvas.snapshot(null, writableImage);
        RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
        try {
            ImageIO.write(renderedImage, "png", file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}

