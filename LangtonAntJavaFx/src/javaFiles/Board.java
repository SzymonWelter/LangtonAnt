package javaFiles;


import javafx.scene.paint.Color;

/**
 * \brief Klasa reprezentująca tablicę
 *
 * Tablica kolorów na podstawie której kolorowny jest canvas
 */
public class Board {

    private Color[][] board;
    private int width;
    private int height;

    public Board(int width, int height){
        initBoard(width,height);
    }

    public void initBoard(int width, int height){
        this.width = width;
        this.height = height;
        board = new Color[width][height];
        for (int i = 0; i < width; i++)
            for(int j = 0; j < height; j++)
                board[i][j] = Color.WHITE;
    }

    public Color getBoardColor(int w, int h){
        return board[w][h];
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setBoardColor(Color color, int w, int h){
        board[w][h] = color;
    }
}
