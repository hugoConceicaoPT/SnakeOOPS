package ModelLayer.SnakeLayer;

import ModelLayer.BoardLayer.GameBoard;

public class ManualMovementStrategy implements MovementStrategy {
    @Override
    public Direction setNextDirection(Snake snake, GameBoard gameBoard) {
        return snake.getNextDirection();
    }
}
