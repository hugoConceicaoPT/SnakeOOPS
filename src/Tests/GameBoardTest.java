package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

import ModelLayer.BoardLayer.FoodCircle;
import ModelLayer.BoardLayer.FoodType;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.BoardLayer.Obstacle;
import ModelLayer.BoardLayer.ObstacleType;
import ModelLayer.SnakeLayer.Circunferencia;
import ModelLayer.SnakeLayer.Direction;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Snake;

public class GameBoardTest {

    @Test
    public void foodContainedInSnakeTest() throws CloneNotSupportedException {
        long seed = 125;
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados, true);
        snake.setDirection(Direction.RIGHT);
        snake.increaseSize();
        snake.increaseSize();
        GameBoard gameBoard = new GameBoard(snake, 200, 100,FoodType.CIRCLE,ObstacleType.POLYGON,false,seed);
        FoodCircle foodCircle = new FoodCircle(new Circunferencia(new Ponto(7,4), 0.5));
        gameBoard.setFood(foodCircle);
        snake.move(Direction.RIGHT);
        assertTrue(gameBoard.foodContainedInSnake());
        snake.move(Direction.UP);
        assertFalse(gameBoard.foodContainedInSnake());
    }

    @Test
    public void generateObstacleTest() throws CloneNotSupportedException {
        long seed = 124;
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados, true);
        snake.setDirection(Direction.RIGHT);
        snake.increaseSize();
        snake.increaseSize();
        GameBoard gameBoard = new GameBoard(snake, 200, 100,FoodType.CIRCLE,ObstacleType.SQUARE,false,seed);   
        gameBoard.generateObstacles();
        assertNotNull(gameBoard.getListOfObstacles());
        assertTrue(gameBoard.getListOfObstacles().size() > 0);
        assertFalse(gameBoard.snakeIntersectsObstacle());
        assertFalse(gameBoard.obstacleContainedInSnake());
        assertFalse(gameBoard.foodIntersectObstacle());
    }

    @Test
    public void generateFoodTest() throws CloneNotSupportedException{
        long seed = 123;
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados, true);
        snake.setDirection(Direction.RIGHT);
        snake.increaseSize();
        GameBoard gameBoard = new GameBoard(snake, 200, 100,FoodType.SQUARE,ObstacleType.TRIANGLE,false,seed);  
        gameBoard.generateFood();
        assertNotNull(gameBoard.getFood());
        assertFalse(gameBoard.foodContainedInSnake());
        assertFalse(gameBoard.foodIntersectObstacle());
    }

    @Test
    public void snakeCollidedTest() throws CloneNotSupportedException {
        long seed = 122;
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados, true);
        snake.setDirection(Direction.RIGHT);
        snake.increaseSize();
        GameBoard gameBoard = new GameBoard(snake, 200, 100,FoodType.SQUARE,ObstacleType.TRIANGLE,false,seed); 
        assertFalse(gameBoard.snakeCollided());
        Obstacle obstacle = new Obstacle(new Quadrado("8 5 8 4 7 4 7 5"), false);
        gameBoard.getListOfObstacles().add(obstacle);
        assertTrue(gameBoard.snakeCollided());
    }
}
