package javaFiles;


import javafx.scene.paint.Color;

/**
 * \brief Klasa reprezentująca tablicę
 *
 * Tablica kolorów na podstawie której kolorowny jest canvas
 */
public class Board {

    private Color[][] board; /*!< Tablica kolorów odwzorowująca canvas, zczytywanie kolorów z canvasa jest nieopłacalne*/
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

    /**
     * Zwracanie koloru z pozycji tablicy o wsp x, y
     *
     * @param x współrzene x
     * @param y współrzędne y
     * @return kolor z tablicy o zadanych wsp
     */
    public Color getBoardColor(int x, int y) {
        return board[x][y];
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

    /**
     * Ustawianie pozycji tablicy o zadanych wsp zadanego koloru
     */
    public void setBoardColor(Color color, int x, int y) {
        board[x][y] = color;
    }
}
