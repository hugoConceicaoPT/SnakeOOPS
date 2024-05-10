package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.Random;

import org.junit.Test;

import ModelLayer.BoardLayer.FoodCircle;
import ModelLayer.SnakeLayer.Circunferencia;
import ModelLayer.SnakeLayer.Direction;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Snake;

public class FoodCircleTest {
    @Test
    public void foodContainedInSnakeTest() throws CloneNotSupportedException {
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        Random random = new Random(122);
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados, true,random);
        snake.setCurrentDirection(Direction.RIGHT);
        snake.setNextDirection(Direction.RIGHT);
        snake.move();
        snake.increaseSize();
        snake.move();
        snake.increaseSize();
        FoodCircle foodCircle = new FoodCircle(new Circunferencia(new Ponto<Double>(9.5,5.0), 0.5));
        assertFalse(foodCircle.foodContainedInSnakeHead(snake));
        FoodCircle foodCircle2 = new FoodCircle(new Circunferencia(new Ponto<Integer>(9,4), 0.5));
        assertTrue(foodCircle2.foodContainedInSnakeHead(snake));
        assertTrue(new FoodCircle(new Circunferencia(new Ponto<Integer>(10,4), 0.5)).foodContainedInSnakeHead(snake));
        assertTrue(new FoodCircle(new Circunferencia(new Ponto<Double>(10.5,3.5), 0.5)).foodContainedInSnakeHead(snake));
    }
}
