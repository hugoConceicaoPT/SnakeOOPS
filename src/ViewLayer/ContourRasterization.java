package ViewLayer;

import ModelLayer.BoardLayer.CellType;
import ModelLayer.BoardLayer.Food;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.SegmentoReta;

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
                board[minY][j].setCellType(CellType.TAIL);
                board[maxY - 1][j].setCellType(CellType.TAIL);
            }
            
            for (int g = minY; g < maxY; g++) {
                board[g][minX].setCellType(CellType.TAIL);
                board[g][maxX - 1].setCellType(CellType.TAIL);
            }
            
        }
    

        Quadrado head = this.gameBoard.getSnake().getHead();
        int minX = (int) head.getMinX();
        int maxX = (int) head.getMaxX();
        int minY = (int) head.getMinY();
        int maxY = (int) head.getMaxY();
        for (int j = minX; j < maxX; j++) {
            board[minY][j].setCellType(CellType.HEAD);
            board[maxY - 1][j].setCellType(CellType.HEAD);
        }
        
        for (int g = minY; g < maxY; g++) {
            board[g][minX].setCellType(CellType.TAIL);
            board[g][maxX - 1].setCellType(CellType.TAIL);
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
            SegmentoReta segment = this.gameBoard.getListOfObstacles().get(i).getPoligono().getAresta().get(i);
            int maxX = (int) poligono.getMaxX();
            int maxY = (int) poligono.getMaxY();
            floodFill(maxX, maxY, segment);
        }
        
    }

    public void floodFill(int x, int y,SegmentoReta segmentoReta){
        if (segmentoReta.contemPonto(new Ponto<Integer>(x,y))) {
            board[y][x].setCellType(CellType.OBSTACLE);
            floodFill(x+1, y, segmentoReta);
            floodFill(x-1, y, segmentoReta);
            floodFill(x, y+1, segmentoReta);
            floodFill(x, y-1, segmentoReta);
        }
    }

    public void updateFoodCells() {

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (board[i][j].getCellType() == CellType.FOOD) {
                    board[i][j].setCellType(CellType.EMPTY);
                }
            }
        }

        Food food = this.gameBoard.getFood();
        int minX = (int) food.getMinX();
        int maxX = (int) food.getMaxX();
        int minY = (int) food.getMinY();
        int maxY = (int) food.getMaxY();
    
    
        for (int j = minX; j < maxX; j++) {
            board[minY][j].setCellType(CellType.FOOD);
            board[maxY - 1][j].setCellType(CellType.FOOD);
        }
        
        for (int g = minY; g < maxY; g++) {
            board[g][minX].setCellType(CellType.FOOD);
            board[g][maxX - 1].setCellType(CellType.FOOD);
        }
    }
    

}