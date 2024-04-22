package Tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ModelLayer.BoardLayer.FoodSquare;
import ModelLayer.SnakeLayer.Direction;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Snake;

public class FoodSquareTest {
    @Test
    public void foodContainedInSnakeTest() throws CloneNotSupportedException {
        String input = "8 5 8 3 6 3 6 5";
        List<Quadrado> listaQuadrados = new ArrayList<>();
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados);
        snake.setDirection(Direction.RIGHT);
        snake.increaseSize();
        snake.increaseSize();
        Quadrado quadrado = new Quadrado("10 5 10 4 9 4 9 5");
        FoodSquare foodCircle = new FoodSquare(quadrado);
        assertFalse(foodCircle.foodIntersetaHead(snake));
        snake.move(Direction.RIGHT);
        assertTrue(foodCircle.foodIntersetaHead(snake));
    }
}
