package Tests;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import ModelLayer.SnakeLayer.Direction;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Snake;

public class SnakeTest {
    @Test
    public void increaseSizeTest() throws CloneNotSupportedException{
        String input1 = "14 6 14 4 12 4 12 6";
        LinkedList<Quadrado> quadrado = new LinkedList<>();
        quadrado.add(new Quadrado(input1));
        Snake snake = new Snake(quadrado,true);
        snake.increaseSize();
        assertEquals(2,snake.getBody().size());
        snake.increaseSize();
        assertEquals(3, snake.getBody().size());
        snake.increaseSize();
        snake.increaseSize();
        snake.increaseSize();
        assertEquals(6, snake.getBody().size());     
    }

    @Test   
    public void collidedWithHerselfTest() throws CloneNotSupportedException{
        String input1 = "10 4 10 2 8 2 8 4";
        LinkedList<Quadrado> quadrado = new LinkedList<>();
        quadrado.add(new Quadrado(input1));
        Snake snake = new Snake(quadrado,true);
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
        LinkedList<Quadrado> quadrado = new LinkedList<>();
        quadrado.add(new Quadrado(input1));
        Snake snake = new Snake(quadrado, true);
        snake.setDirection(Direction.RIGHT);
        snake.increaseSize();
        Direction direction = Direction.UP;
        snake.move(direction);
        assertEquals("Cabeça: [(6.0,6.0), (8.0,6.0), (8.0,4.0), (6.0,4.0)] Tail: [[(8.0,4.0), (8.0,2.0), (6.0,2.0), (6.0,4.0)]]", snake.toString());
        snake.increaseSize();
        snake.move(Direction.RIGHT);
        snake.move(Direction.LEFT);
        assertEquals("Cabeça: [(10.0,4.0), (8.0,4.0), (8.0,6.0), (10.0,6.0)] Tail: [[(6.0,6.0), (8.0,6.0), (8.0,4.0), (6.0,4.0)], [(8.0,4.0), (8.0,2.0), (6.0,2.0), (6.0,4.0)]]", snake.toString());
        snake.move(Direction.DOWN);
        assertEquals("Cabeça: [(8.0,4.0), (10.0,4.0), (10.0,2.0), (8.0,2.0)] Tail: [[(10.0,4.0), (8.0,4.0), (8.0,6.0), (10.0,6.0)], [(6.0,6.0), (8.0,6.0), (8.0,4.0), (6.0,4.0)]]", snake.toString());
        snake.move(Direction.LEFT);
        assertEquals("Cabeça: [(8.0,4.0), (8.0,2.0), (6.0,2.0), (6.0,4.0)] Tail: [[(8.0,4.0), (10.0,4.0), (10.0,2.0), (8.0,2.0)], [(10.0,4.0), (8.0,4.0), (8.0,6.0), (10.0,6.0)]]", snake.toString());
    }
    @Test
    public void toStringTest() throws CloneNotSupportedException{
        String input1 = "8 4 8 2 6 2 6 4";
        LinkedList<Quadrado> quadrado = new LinkedList<>();
        quadrado.add(new Quadrado(input1));
        Snake snake = new Snake(quadrado, true);
        snake.setDirection(Direction.RIGHT);
        snake.increaseSize();
        assertEquals("Cabeça: [(8.0,4.0), (8.0,2.0), (6.0,2.0), (6.0,4.0)] Tail: [[(6.0,4.0), (6.0,2.0), (4.0,2.0), (4.0,4.0)]]", snake.toString());



    }
}
