package BoardLayer;

import java.util.List;

public class GameBoard {
    private int width;
    private int height;
    private List<Obstacle> listOfObstacles; 
    private Food food;
    
    public GameBoard (int width, int height, List<Obstacle> listofObstacles, Food food) {
        this.width = width;
        this.height = height;
        this.listOfObstacles = listofObstacles;
        this.food = food;
    }

    
}
