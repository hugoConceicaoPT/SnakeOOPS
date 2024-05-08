package ViewLayer;

import ModelLayer.BoardLayer.Cell;
import ModelLayer.BoardLayer.GameBoard;

public abstract class RasterizationStrategy {

    protected Cell [][] board;
    protected GameBoard gameBoard;
    protected int rows;
    protected int cols;

    public RasterizationStrategy(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.rows = this.gameBoard.getHeightBoard();
        this.cols = this.gameBoard.getWidthBoard(); 
        this.board = new Cell[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    @Override
    public abstract String toString();

    public abstract void updateObstacleCells();
    public abstract void updateSnakeCells();
    public abstract void updateFoodCells();

    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }
}
