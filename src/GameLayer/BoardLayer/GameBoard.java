package GameLayer.BoardLayer;

import java.util.List;

public class GameBoard {
    private List<Obstacle> listOfObstacles; 
    private Food food;
    private int rows;
    private int columns;
    private Cell [][] board;
    
    public GameBoard (List<Obstacle> listofObstacles, Food food,int rows, int columns) {
        this.listOfObstacles = listofObstacles;
        this.food = food;
        this.rows = rows;
        this.columns = columns;
        this.board = new Cell[rows][columns];
    }

    public boolean snakeCollided() {return true;}
    public boolean foodContainedInSnake() {return true;}
    public void generateFood() {}
    public void generateObstacle() {}
}
