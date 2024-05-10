package ViewLayer;

import ModelLayer.BoardLayer.Cell;
import ModelLayer.BoardLayer.CellType;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;

public class FullRasterization extends RasterizationStrategy {

    public FullRasterization(GameBoard gameBoard) {
        super(gameBoard);
    }

    @Override
    public String toString() {
        String result = "";
        for(int i = this.rows - 1; i >= 0; i--) {
            for(int j = 0; j < this.cols; j++) {
                result += board[i][j].toString() + " ";
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

        for(int i = 0; i < this.gameBoard.getListOfObstacles().size(); i++) {
            for(int row = 0; row < this.rows; row++) {
                for(int col = 0; col < this.cols; col++) {
                    if(this.gameBoard.getListOfObstacles().get(i).getPoligono().contemPonto(new Ponto<Double>(col + 0.5, row + 0.5))) {
                        board[row][col].setCellType(CellType.OBSTACLE);
                    }
                }
            }
        }
    }

    public void updateFoodCells() {

        boolean isFoodNotReseted = false;
        while(!isFoodNotReseted) {
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.cols; j++) {
                    if (board[i][j].getCellType() == CellType.FOOD) {
                        board[i][j].setCellType(CellType.EMPTY);
                    }
                }
            }

            for (int i = this.gameBoard.getFood().getMinY(); i < this.gameBoard.getFood().getMaxY(); i++) {
                for (int j = this.gameBoard.getFood().getMinX(); j < this.gameBoard.getFood().getMaxX(); j++) {
                    if(board[i][j].getCellType() != CellType.EMPTY)
                    {
                        for (int w = 0; w < this.rows; w++) {
                            for (int z = 0; z < this.cols; z++) {
                                if (board[w][z].getCellType() == CellType.FOOD) {
                                    board[w][z].setCellType(CellType.EMPTY);
                                }
                            }
                        }
                        this.gameBoard.removeFood();
                        this.gameBoard.generateFood();
                        isFoodNotReseted = false;
                        break;
                    }
                    else {
                        board[i][j].setCellType(CellType.FOOD);
                        isFoodNotReseted = true;
                    }
                }
                if(!isFoodNotReseted)
                    break;
            }
        }
        System.out.println(this.gameBoard.getFood().toString());
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
