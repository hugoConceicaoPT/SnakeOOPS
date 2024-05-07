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
        snake.setDirection(Direction.RIGHT);
        snake.increaseSize();
        snake.increaseSize();
        FoodCircle foodCircle = new FoodCircle(new Circunferencia(new Ponto(9.5,4.5), 0.5));
        assertFalse(foodCircle.foodContainedInSnakeHead(snake));
        FoodCircle foodCircle2 = new FoodCircle(new Circunferencia(new Ponto(9,4), 0.5));
        snake.move(Direction.RIGHT);
        assertTrue(foodCircle2.foodContainedInSnakeHead(snake));
    }
}
