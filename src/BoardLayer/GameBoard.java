package BoardLayer;

import java.util.List;

public class GameBoard {
    private List<Obstacle> listOfObstacles; 
    private Food food;
    
    public GameBoard (List<Obstacle> listofObstacles, Food food) {
        this.listOfObstacles = listofObstacles;
        this.food = food;
    }

    public boolean snakeCollided() {return true;}
    public boolean foodContainedInSnake() {return true;}
    public void generateFood() {}
    public void generateObstacle() {}
}
