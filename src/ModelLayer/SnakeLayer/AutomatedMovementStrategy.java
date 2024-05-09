package ModelLayer.SnakeLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.BoardLayer.Obstacle;

public class AutomatedMovementStrategy implements MovementStrategy {

    @Override
    public Direction setNextDirection(Snake snake, GameBoard gameBoard) {
        if (gameBoard != null && gameBoard.getFood() != null) {
            return calculateDirectionToFood(snake, (Ponto<?>) gameBoard.getFood().getCentroide());
        }

        return avoidObstacles(snake, gameBoard);
    }

    private Direction calculateDirectionToFood(Snake snake, Ponto<? extends Number> foodCentroide) {
        double headX = snake.getHead().getCentroide().getX().doubleValue();
        double headY = snake.getHead().getCentroide().getY().doubleValue();
        double foodX = foodCentroide.getX().doubleValue();
        double foodY = foodCentroide.getY().doubleValue();

        if (foodX < headX && snake.getCurrentDirection() != Direction.RIGHT)
            return Direction.LEFT;
        else if (foodX > headX && snake.getCurrentDirection() != Direction.LEFT)
            return Direction.RIGHT;
        else if (foodY < headY && snake.getCurrentDirection() != Direction.UP) 
            return Direction.DOWN;
        else
            return Direction.UP;
    }

    private Direction avoidObstacles(Snake snake, GameBoard gameBoard) {
        if (gameBoard != null && gameBoard.getListOfObstacles() != null) {
            for (Obstacle obstacle : gameBoard.getListOfObstacles()) {
                if (willCollideWithObstacleAfterMovingInDirection(snake, obstacle, snake.getCurrentDirection())) {
                    Direction nextDirection = chooseRandomDirectionWithoutCollision(snake, obstacle);
                    if (nextDirection != null) {
                        return nextDirection;
                    }
                }
            }
        }
        return snake.getCurrentDirection();
    }

    private boolean willCollideWithObstacleAfterMovingInDirection(Snake snake, Obstacle obstacle, Direction direction) {
        Snake snakeClone;
        try {
            snakeClone = (Snake) snake.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return true; 
        }

        snakeClone.moveSquare(snakeClone.getHead(), snake.getCurrentDirection(), direction);
        return snakeClone.getHead().interseta(obstacle.getPoligono());
    }

    private Direction chooseRandomDirectionWithoutCollision(Snake snake, Obstacle obstacle) {
        List<Direction> availableDirections = new ArrayList<>();
        for (Direction dir : Direction.values()) {
            if (!willCollideWithObstacleAfterMovingInDirection(snake, obstacle, dir)) {
                availableDirections.add(dir);
            }
        }

        if (!availableDirections.isEmpty()) {
            Random rand = new Random();
            int index = rand.nextInt(availableDirections.size());
            return availableDirections.get(index);
        } else {
            return null;
        }
    }
}
