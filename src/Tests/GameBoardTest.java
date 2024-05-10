package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import ModelLayer.BoardLayer.FoodCircle;
import ModelLayer.BoardLayer.FoodType;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.BoardLayer.Obstacle;
import ModelLayer.SnakeLayer.Circunferencia;
import ModelLayer.SnakeLayer.Direction;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Snake;

public class GameBoardTest {

    @Test
    public void testConstrutor1() {
        long seed = 125;
        Random random = new Random(seed);
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados, true,random);
        List<Ponto<? extends Number>> rotacionPoint = new ArrayList<>();
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        assertThrows(IllegalArgumentException.class, () -> {
            new GameBoard(snake, -1, 100,FoodType.CIRCLE,1,1,rotacionPoint,false,new Random(seed));
        });
    }

    @Test
    public void testConstrutor2() {
        long seed = 125;
        Random random = new Random(seed);
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados, true,random);
        List<Ponto<? extends Number>> rotacionPoint = new ArrayList<>();
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        assertThrows(IllegalArgumentException.class, () -> {
            new GameBoard(snake, 100, -1,FoodType.CIRCLE,1,1,rotacionPoint,false,new Random(seed));
        });
    }


    @Test
    public void foodContainedInSnakeTest() throws CloneNotSupportedException {
        long seed = 125;
        Random random = new Random(seed);
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados, true,random);
        snake.setCurrentDirection(Direction.RIGHT);
        snake.increaseSize();
        snake.increaseSize();
        List<Ponto<? extends Number>> rotacionPoint = new ArrayList<>();
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        GameBoard gameBoard = new GameBoard(snake, 200, 100,FoodType.CIRCLE,1,1,rotacionPoint,false,random);
        FoodCircle foodCircle = new FoodCircle(new Circunferencia(new Ponto<Integer>(9,4), 0.5));
        gameBoard.setFood(foodCircle);
        snake.setNextDirection(Direction.RIGHT);
        snake.move();
        assertTrue(gameBoard.foodContainedInSnakeHead());
        snake.setNextDirection(Direction.UP);
        snake.move();
        assertFalse(gameBoard.foodContainedInSnakeHead());
    }

    @Test
    public void generateObstacleTest() throws CloneNotSupportedException {
        long seed = 124;
        Random random = new Random(seed);
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados, true,random);
        snake.setNextDirection(Direction.RIGHT);
        snake.increaseSize();
        snake.increaseSize();
        List<Ponto<? extends Number>> rotacionPoint = new ArrayList<>();
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        GameBoard gameBoard = new GameBoard(snake, 200, 100,FoodType.CIRCLE,1,2,rotacionPoint,false,random);  
        assertNotNull(gameBoard.getListOfObstacles());
        assertTrue(gameBoard.getListOfObstacles().size() > 0);
        assertFalse(gameBoard.snakeIntersectsObstacle());
        assertFalse(gameBoard.obstacleContainedInSnake());
    }

    @Test
    public void generateFoodTest() throws CloneNotSupportedException{
        long seed = 123;
        Random random = new Random(seed);
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados, true,random);
        snake.setNextDirection(Direction.RIGHT);
        snake.increaseSize();
        List<Ponto<? extends Number>> rotacionPoint = new ArrayList<>();
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        GameBoard gameBoard = new GameBoard(snake, 20, 10,FoodType.SQUARE,1,1,rotacionPoint,false,random);
        assertNotNull(gameBoard.getFood());
        assertFalse(gameBoard.foodContainedInSnakeHead());
        gameBoard.removeFood();
        gameBoard.generateFood();
        System.out.println(snake.toString());
        System.out.println(gameBoard.getFood().toString());
        long seed1 = 123;
        Random random1 = new Random(seed1);
        String input1 = "9 5 9 2 6 2 6 5";
        LinkedList<Quadrado> listaQuadrados1 = new LinkedList<>();
        listaQuadrados1.add(new Quadrado(input1));
        Snake snake1 = new Snake(listaQuadrados, true,random1);
        snake.setCurrentDirection(Direction.RIGHT);
        snake.increaseSize();
        GameBoard gameBoard1 = new GameBoard(snake1, 30, 20,FoodType.SQUARE,2,2,rotacionPoint,false,random1);
        assertEquals("2 ",gameBoard1.getFood().toString());
    }

    @Test
    public void removeFoodTest() throws CloneNotSupportedException {
        long seed = 121;
        Random random = new Random(seed);
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados, true,random);
        snake.setNextDirection(Direction.RIGHT);
        snake.increaseSize();
        List<Ponto<? extends Number>> rotacionPoint = new ArrayList<>();
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        GameBoard gameBoard = new GameBoard(snake, 200, 100,FoodType.CIRCLE,1,1,rotacionPoint,false,random);
        assertNotNull(gameBoard.getFood());
        gameBoard.removeFood();
        assertNull(gameBoard.getFood());
    }

    @Test
    public void snakeLeftBoardTest() throws CloneNotSupportedException{
        long seed = 121;
        Random random = new Random(seed);
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados, true,random);
        snake.setNextDirection(Direction.RIGHT);
        snake.increaseSize();
        List<Ponto<? extends Number>> rotacionPoint = new ArrayList<>();
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        GameBoard gameBoard = new GameBoard(snake, 200, 100,FoodType.SQUARE,1,1,rotacionPoint,false,random);
        assertFalse(gameBoard.snakeLeftBoard());
        String input2 = "-1 0 -1 2 1 2 1 0";
        LinkedList<Quadrado> listaQuadrados2 = new LinkedList<>();
        listaQuadrados2.add(new Quadrado(input2));
        gameBoard.setSnake(new Snake(listaQuadrados2, true,random));
        assertTrue(gameBoard.snakeLeftBoard());
    }

    @Test
    public void obstacleContainedInSnakeTest() throws CloneNotSupportedException {
        long seed = 120;
        Random random = new Random(seed);
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados, true,random);
        snake.setNextDirection(Direction.RIGHT);
        snake.increaseSize();
        List<Ponto<? extends Number>> rotacionPoint = new ArrayList<>();
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        GameBoard gameBoard = new GameBoard(snake, 200, 100,FoodType.SQUARE,1,1,rotacionPoint,false,random);
        assertFalse(gameBoard.obstacleContainedInSnake());
        Obstacle obstacle = new Obstacle(new Quadrado("8 5 8 4 7 4 7 5"), new Ponto<Integer>(1,1) ,false);
        gameBoard.getListOfObstacles().add(obstacle);
        assertTrue(gameBoard.obstacleContainedInSnake());
    }

    @Test
    public void snakeIntersectsObstacleTest() throws CloneNotSupportedException {
        long seed = 119;
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Random random = new Random(seed);
        Snake snake = new Snake(listaQuadrados, true,random);
        snake.setNextDirection(Direction.RIGHT);
        snake.increaseSize();
        List<Ponto<? extends Number>> rotacionPoint = new ArrayList<>();
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        GameBoard gameBoard = new GameBoard(snake, 200, 100,FoodType.SQUARE,1,2,rotacionPoint,false,random);
        assertFalse(gameBoard.snakeIntersectsObstacle());
        Obstacle obstacle = new Obstacle(new Quadrado("5 6 5 4 7 4 7 6"),new Ponto<Integer>(1,1),false);
        gameBoard.getListOfObstacles().add(obstacle);
        assertTrue(gameBoard.snakeIntersectsObstacle());
    } 
}
