package ViewLayer;

import ModelLayer.BoardLayer.Cell;
import ModelLayer.BoardLayer.CellType;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.SnakeLayer.Quadrado;

public class FullRasterization extends RasterizationStrategy {

    public FullRasterization(GameBoard gameBoard) {
        super(gameBoard);
    }

    @Override
    public void updateBoard() {
        updateSnakeCells();
        updateObstacleCells();
        updateFoodCells();
    }

    @Override
    public String toString() {
        String result = "";
        for(int i = this.rows - 1; i >= 0; i--) {
            for(int j = 0; j < this.cols; j++) {
                result+= board[i][j].toString() + " ";
            }
            result += "\n";
        }
        return result;
    }

    public void updateSnakeCells() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (board[i][j].getCellType() == CellType.HEAD || board[i][j].getCellType() == CellType.TAIL) {
                    board[i][j].setCellType(CellType.EMPTY);
                }
            }
        }
    
        for (int i = 1; i < this.gameBoard.getSnake().getBody().size(); i++) {
            for(int w = (int) this.gameBoard.getSnake().getBody().get(i).getMinY(); w < (int) this.gameBoard.getSnake().getBody().get(i).getMaxY(); w++) {
                for(int j = (int) this.gameBoard.getSnake().getBody().get(i).getMinX() ; j < (int) this.gameBoard.getSnake().getBody().get(i).getMaxX(); j++) {
                    board[w][j].setCellType(CellType.TAIL);
                }
            }
        }
    
        Quadrado head = this.gameBoard.getSnake().getHead();
        for(int w = (int) head.getMinY(); w < (int) head.getMaxY(); w++) {
            for(int j = (int) head.getMinX(); j < (int) head.getMaxX(); j++) {
                board[w][j].setCellType(CellType.HEAD);
            }
        }
    }

    public void updateObstacleCells() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (board[i][j].getCellType() == CellType.OBSTACLE) {
                    board[i][j].setCellType(CellType.EMPTY);
                }
            }
        }   

        for(int i = 0; i < this.gameBoard.getObstaclesQuantity(); i++) {
            for(int w = (int) this.gameBoard.getListOfObstacles().get(i).getPoligono().getMinY(); w < (int) this.gameBoard.getListOfObstacles().get(i).getPoligono().getMaxY(); w++) {
                for(int j = (int) this.gameBoard.getListOfObstacles().get(i).getPoligono().getMinX() ; j < (int) this.gameBoard.getListOfObstacles().get(i).getPoligono().getMaxX(); j++) {
                    board[Math.abs(w) % this.rows][Math.abs(j) % this.cols].setCellType(CellType.OBSTACLE);
                }
            }
        }
    }

    public void updateFoodCells() {
        boolean isEmpty = false;
        for(int i = 0; i < this.rows - this.gameBoard.getFoodDimension(); i++) {
            for(int j = 0; j < this.cols - this.gameBoard.getFoodDimension(); j++) {
                for(int k = i; k < i + this.gameBoard.getFoodDimension(); k++) {
                    for(int l = j; l < j + this.gameBoard.getFoodDimension(); l++) {
                        if (board[k][l].getCellType() != CellType.EMPTY) {
                            break;
                        }
                    }
                    isEmpty = true;
                }
            }
        }

        if(isEmpty) {
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.cols; j++) {
                    if (board[i][j].getCellType() == CellType.FOOD) {
                        board[i][j].setCellType(CellType.EMPTY);
                    }
                }
            }

            for (int i = this.gameBoard.getFood().getMinY(); i < this.gameBoard.getFood().getMaxY(); i++) {
                for (int j = this.gameBoard.getFood().getMinX(); j < this.gameBoard.getFood().getMaxX(); j++) {
                    board[i][j].setCellType(CellType.FOOD);
                }
            }
        }
        else this.gameBoard.setFood(null);
    }

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
