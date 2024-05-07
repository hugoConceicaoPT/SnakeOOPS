package ModelLayer.SnakeLayer;


public class ManualMovementStrategy implements MovementStrategy {
    @Override
    public Direction setNextDirection(Snake snake) {
        return snake.getNextDirection();
    }
}
