package Tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.Random;

import org.junit.Test;

import ModelLayer.BoardLayer.FoodSquare;
import ModelLayer.SnakeLayer.Direction;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Snake;

public class FoodSquareTest {
    @Test
    public void foodContainedInSnakeTest() throws CloneNotSupportedException {
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Random random = new Random(120);
        Snake snake = new Snake(listaQuadrados, true,random);
        snake.setCurrentDirection(Direction.RIGHT);
        snake.setNextDirection(Direction.RIGHT);
        snake.move();
        snake.increaseSize();
        snake.move();
        snake.increaseSize();
        Quadrado quadrado = new Quadrado("10 5 10 4 9 4 9 5");
        FoodSquare foodCircle = new FoodSquare(quadrado);
        assertTrue(foodCircle.foodContainedInSnake(snake));
        assertTrue(new FoodSquare(new Quadrado("11 5 11 4 10 4 10 5")).foodContainedInSnake(snake));
        assertFalse(new FoodSquare(new Quadrado("7 5 8 5 8 4 7 4")).foodContainedInSnake(snake));
        assertFalse(new FoodSquare(new Quadrado("8 6 9 6 9 5 8 5")).foodContainedInSnake(snake));
        assertTrue(new FoodSquare(new Quadrado("11 4 12 4 12 3 11 3")).foodContainedInSnake(snake));    
        snake.setNextDirection(Direction.UP);
        snake.move();
        assertTrue(new FoodSquare(new Quadrado("10 7 11 7 11 6 10 6")).foodContainedInSnake(snake));

    }
}
