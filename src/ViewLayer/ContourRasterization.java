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
            int maxX = (int) segment.getMaxX()- 1;
            int minY = (int) segment.getMinY();
            int maxY = (int) segment.getMaxY() - 1;
    
            for (int j = minY; j <= maxY; j++) {
                for (int g = minX; g <= maxX; g++) {
                    if(j == minY || j == maxY || g == minX || g == maxX) {
                        board[j][g].setCellType(CellType.TAIL);
                    }
                }
            }
            
            
        }
    

        Quadrado head = this.gameBoard.getSnake().getHead();
        int minX = (int) head.getMinX();
        int maxX = (int) head.getMaxX() - 1;
        int minY = (int) head.getMinY();
        int maxY = (int) head.getMaxY() - 1;
        for (int j = minY; j <= maxY; j++) {
            for (int g = minX; g <= maxX; g++) {
                if(j == minY || j == maxY || g == minX || g == maxX) {
                    board[j][g].setCellType(CellType.HEAD);
                }
            }
        }
    }
    

    @SuppressWarnings("unchecked")
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
            int minY = (int) poligono.getMinY();
            int maxY = (int) poligono.getMaxX() -1;
            @SuppressWarnings("rawtypes")
            Ponto ponto = new Ponto<Integer>(minX, minY);
            for (int j = minY; j <= maxY; j++) {
                if (poligono.contemPonto(new Ponto<Integer>(minX, j))) {
                    ponto.setY(j);
                    break;
                }
            }
            int x = (int) ponto.getX();
            int y = (int) ponto.getY();
            for(int j = 0; j < poligono.getAresta().size(); j++) {
                SegmentoReta segment = this.gameBoard.getListOfObstacles().get(i).getPoligono().getAresta().get(i);
                floodFill(x,y, segment);
            }
        }
        
    }

    public void floodFill(Integer x, Integer y,SegmentoReta segmentoReta){
        if (segmentoReta.contemPonto(new Ponto<Integer>(x,y)) && x >= 0 && y >= 0 && x < this.cols && y < this.rows) {
            board[y][x].setCellType(CellType.OBSTACLE);
            floodFill(x+1, y, segmentoReta);
            floodFill(x, y+1, segmentoReta);
            floodFill(x-1, y, segmentoReta);
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
        int maxX = (int) food.getMaxX() - 1;
        int minY = (int) food.getMinY();
        int maxY = (int) food.getMaxY() - 1;
    
    
        for (int j = minY; j <= maxY; j++) {
            for (int g = minX; g <= maxX; g++) {
                if(j == minY || j == maxY || g == minX || g == maxX) {
                    board[j][g].setCellType(CellType.FOOD);
                }
            }
        }
    }
    

}