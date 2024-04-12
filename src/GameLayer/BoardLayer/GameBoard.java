package GameLayer.BoardLayer;

import java.util.List;

import GameLayer.SnakeLayer.Retangulo;
import GameLayer.SnakeLayer.Score;
import GameLayer.SnakeLayer.Snake;
import GameLayer.SnakeLayer.SnakeGame;

public class GameBoard {
    private List<Obstacle> listOfObstacles; 
    private Food food;
    private int rows;
    private int columns;
    private String foodType;
    private Cell [][] board;
    private SnakeGame snake;


    public GameBoard (List<Obstacle> listofObstacles,String foodType,int rows, int columns) {
        this.listOfObstacles = listofObstacles;
        this.foodType = foodType;
        this.rows = rows;
        this.columns = columns;
        this.board = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = new Cell(i,j);
            }
        }
    }

    public boolean snakeCollided() {return true;}
    public boolean foodContainedInSnake() {
        if (food.FoodIntersetaHead(snake.getSnake())) {
            snake.getSnake().increaseSize();
            snake.getScore().increaseScore();
           return true; 
        }
        return false; 
    }
 /*   public void generateFood() {

        boolean isEmpty = true;
        while (isEmpty) {
            int row = (int)(Math.random * rows);
            int column = (int)(Math.random *columns);
            Cell cellFood = new Cell(row, column);
            if (cellFood.getCellType() == CellType.EMPTY) {
                cellFood.setCellType(CellType.FOOD);
                isEmpty = false;
            }
        }
    }
    */
    public void generateObstacle() {}
}
