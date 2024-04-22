package Tests;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ModelLayer.SnakeLayer.Direction;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Snake;

public class SnakeTest {
    @Test
    public void increaseSizeTest() throws CloneNotSupportedException{
        String input1 = "14 6 14 4 12 4 12 6";
        List<Quadrado> quadrado = new ArrayList<>();
        quadrado.add(new Quadrado(input1));
        Snake snake = new Snake(quadrado);
        snake.increaseSize();
        assertEquals(2,snake.getTail().size() + 1);
        snake.increaseSize();
        assertEquals(3, snake.getTail().size() + 1);
        snake.increaseSize();
        snake.increaseSize();
        snake.increaseSize();
        assertEquals(6, snake.getTail().size() + 1);     
    }

    @Test   
    public void collidedWithHerselfTest() throws CloneNotSupportedException{
        String input1 = "10 4 10 2 8 2 8 4";
        List<Quadrado> quadrado = new ArrayList<>();
        quadrado.add(new Quadrado(input1));
        Snake snake = new Snake(quadrado);
        snake.setDirection(Direction.RIGHT);
        snake.increaseSize();
        snake.increaseSize();
        snake.increaseSize();
        snake.increaseSize();
        assertFalse(snake.collidedWithHerself());
        snake.move(Direction.UP);
        snake.move(Direction.LEFT);
        snake.move(Direction.DOWN);
        assertTrue(snake.collidedWithHerself());
    }

    @Test
    public void moveTest() throws CloneNotSupportedException {
        String input1 = "8 4 8 2 6 2 6 4";
        List<Quadrado> quadrado = new ArrayList<>();
        quadrado.add(new Quadrado(input1));
        Snake snake = new Snake(quadrado);
        snake.setDirection(Direction.RIGHT);
        snake.increaseSize();
        Direction direction = Direction.UP;
        snake.move(direction);
        assertEquals("Cabeça: [(6,6), (8,6), (8,4), (6,4)] Tail: [[(8,4), (8,2), (6,2), (6,4)]]", snake.toString());
        snake.increaseSize();
        snake.move(Direction.RIGHT);
        assertEquals("Cabeça: [(10,6), (10,4), (8,4), (8,6)] Tail: [[(8,6), (8,4), (6,4), (6,6)], [(8,4), (8,2), (6,2), (6,4)]]", snake.toString());
        snake.move(Direction.DOWN);
        assertEquals("Cabeça: [(10,2), (8,2), (8,4), (10,4)] Tail: [[(10,6), (10,4), (8,4), (8,6)], [(8,6), (8,4), (6,4), (6,6)]]", snake.toString());
        snake.move(Direction.LEFT);
        assertEquals("Cabeça: [(6,2), (6,4), (8,4), (8,2)] Tail: [[(10,4), (10,2), (8,2), (8,4)], [(10,6), (10,4), (8,4), (8,6)]]", snake.toString());
    }
    @Test
    public void toStringTest() throws CloneNotSupportedException{
        String input1 = "8 4 8 2 6 2 6 4";
        List<Quadrado> quadrado = new ArrayList<>();
        quadrado.add(new Quadrado(input1));
        Snake snake = new Snake(quadrado);
        snake.setDirection(Direction.RIGHT);
        snake.increaseSize();
        assertEquals("Cabeça: [(8.0,4.0), (8.0,2.0), (6.0,2.0), (6.0,4.0)] Tail: [[(6.0,4.0), (6.0,2.0), (4.0,2.0), (4.0,4.0)]]", snake.toString());



    }
}
