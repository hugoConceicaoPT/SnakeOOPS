package ViewLayer;

import ModelLayer.BoardLayer.CellType;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;

public class ContourRasterization extends RasterizationStrategy {

    public ContourRasterization(GameBoard gameBoard) {
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
            Quadrado segment = this.gameBoard.getSnake().getBody().get(i);
            int minX = (int) segment.getMinX();
            int maxX = (int) segment.getMaxX();
            int minY = (int) segment.getMinY();
            int maxY = (int) segment.getMaxY();
        

            for (int j = minX; j < maxX; j++) {
                if (board[minY][j].getCellType() == CellType.FOOD) {
                    board[minY][j].setCellType(CellType.SNAKEFOOD);
                } else {
                    board[minY][j].setCellType(CellType.TAIL);
                }
        
                if (board[maxY - 1][j].getCellType() == CellType.FOOD) {
                    board[maxY - 1][j].setCellType(CellType.SNAKEFOOD);
                } else {
                    board[maxY - 1][j].setCellType(CellType.TAIL);
                }
            }
        
            
            for (int w = minY; w < maxY; w++) {
                if (board[w][minX].getCellType() == CellType.FOOD) {
                    board[w][minX].setCellType(CellType.SNAKEFOOD);
                } else {
                    board[w][minX].setCellType(CellType.TAIL);
                }
        
                if (board[w][maxX - 1].getCellType() == CellType.FOOD) {
                    board[w][maxX - 1].setCellType(CellType.SNAKEFOOD);
                } else {
                    board[w][maxX - 1].setCellType(CellType.TAIL);
                }
            }
        }
        
    
        Quadrado head = this.gameBoard.getSnake().getHead();
        int minX = (int) head.getMinX();
        int maxX = (int) head.getMaxX();
        int minY = (int) head.getMinY();
        int maxY = (int) head.getMaxY();
        

        for (int j = minX; j < maxX; j++) {

            if (board[minY][j].getCellType() == CellType.FOOD) {
                board[minY][j].setCellType(CellType.SNAKEFOOD);
            } else {
                board[minY][j].setCellType(CellType.HEAD);
            }
        

            if (board[maxY - 1][j].getCellType() == CellType.FOOD) {
                board[maxY - 1][j].setCellType(CellType.SNAKEFOOD);
            } else {
                board[maxY - 1][j].setCellType(CellType.HEAD);
            }
        }
        
        for (int w = minY; w < maxY; w++) {

            if (board[w][minX].getCellType() == CellType.FOOD) {
                board[w][minX].setCellType(CellType.SNAKEFOOD);
            } else {
                board[w][minX].setCellType(CellType.HEAD);
            }
        
            if (board[w][maxX - 1].getCellType() == CellType.FOOD) {
                board[w][maxX - 1].setCellType(CellType.SNAKEFOOD);
            } else {
                board[w][maxX - 1].setCellType(CellType.HEAD);
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

        for (int i = 0; i < this.gameBoard.getObstaclesQuantity(); i++) {
            Poligono poligono = this.gameBoard.getListOfObstacles().get(i).getPoligono();
            int minX = (int) poligono.getMinX();
            int maxX = (int) poligono.getMaxX();
            int minY = (int) poligono.getMinY();
            int maxY = (int) poligono.getMaxY();
        
            for (int j = minX; j < maxX; j++) {

                if (poligono.contemPonto(new Ponto<Integer>(j, minY)) && minY >= 0 && minY < this.gameBoard.getHeightBoard() && j >= 0 && j < this.gameBoard.getWidthBoard() && board[minY][j].getCellType() == CellType.EMPTY) {
                    board[minY][j].setCellType(CellType.OBSTACLE);
                }
        
                if (poligono.contemPonto(new Ponto<Integer>(j, maxY - 1)) && maxY - 1 >= 0 && maxY - 1 < this.gameBoard.getHeightBoard() && j >= 0 && j < this.gameBoard.getWidthBoard() && board[maxY - 1][j].getCellType() == CellType.EMPTY) {
                    board[maxY - 1][j].setCellType(CellType.OBSTACLE);
                }
            }
        

            for (int w = minY; w < maxY; w++) {

                if (poligono.contemPonto(new Ponto<Integer>(minX, w)) && w >= 0 && w < this.gameBoard.getHeightBoard() && minX >= 0 && minX < this.gameBoard.getWidthBoard() && board[w][minX].getCellType() == CellType.EMPTY) {
                    board[w][minX].setCellType(CellType.OBSTACLE);
                }
        
                if (poligono.contemPonto(new Ponto<Integer>(maxX - 1, w)) && w >= 0 && w < this.gameBoard.getHeightBoard() && maxX - 1 >= 0 && maxX - 1 < this.gameBoard.getWidthBoard() && board[w][maxX - 1].getCellType() == CellType.EMPTY) {
                    board[w][maxX - 1].setCellType(CellType.OBSTACLE);
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

            boolean isFoodPlaced = false;
            int minX = this.gameBoard.getFood().getMinX();
            int maxX = this.gameBoard.getFood().getMaxX();
            int minY = this.gameBoard.getFood().getMinY();
            int maxY = this.gameBoard.getFood().getMaxY();
            
           
            for (int i = minY; i < maxY; i++) {
                for (int j = minX; j < maxX; j++) {
                  
                    if (board[i][j].getCellType() != CellType.EMPTY && board[i][j].getCellType() != CellType.FOOD) {
                        
                        for (int x = minY; x < maxY; x++) {
                            for (int y = minX; y < maxX; y++) {
                                if (board[x][y].getCellType() == CellType.FOOD) {
                                    board[x][y].setCellType(CellType.EMPTY);
                                }
                            }
                        }
            
                       
                        this.gameBoard.removeFood();
                        this.gameBoard.generateFood();
                        isFoodPlaced = true;
                        break;
                    }
                }
            
                if (isFoodPlaced) {
                    break;
                }
            }
            
            if (!isFoodPlaced) {
                for (int i = minY; i < maxY; i++) {
                    for (int j = minX; j < maxX; j++) {
                        board[i][j].setCellType(CellType.FOOD);
                    }
                }
            }
            
        }
    }

}