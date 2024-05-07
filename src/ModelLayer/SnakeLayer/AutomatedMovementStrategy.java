package ModelLayer.SnakeLayer;

import ModelLayer.BoardLayer.GameBoard;

public class AutomatedMovementStrategy implements MovementStrategy {

    @Override
    public Direction setNextDirection(Snake snake, GameBoard gameBoard) {
        if (gameBoard.getFood().getCentroide().getX() <= snake.getHead().getCentroide().getX() && snake.getCurrentDirection() != Direction.RIGHT)
            return Direction.LEFT;
        else if (gameBoard.getFood().getCentroide().getX() >= snake.getHead().getCentroide().getX() && snake.getCurrentDirection() != Direction.LEFT)
            return Direction.RIGHT;
        else if (gameBoard.getFood().getCentroide().getY() <= snake.getHead().getCentroide().getY() && snake.getCurrentDirection() != Direction.UP) 
            return Direction.DOWN;
        else
            return Direction.UP;
    }
}
