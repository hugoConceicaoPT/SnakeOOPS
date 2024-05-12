package Tests;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Random;

import org.junit.Test;

import ModelLayer.SnakeLayer.Direction;
import ModelLayer.SnakeLayer.ManualMovementStrategy;
import ModelLayer.SnakeLayer.MovementStrategy;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Snake;

/** Classe que representa uma classe teste para testar as funcionalidades da classe ManualMovementStrategy
    Responsabilidade: Testar as funcionalidades da classe ManualMovementStrategy
    @version 1.0 12/05/2024
    @author Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class ManualMovementStrategyTest {
    @Test
    public void setNextDirectionTest() {
        Random random = new Random(1);
        MovementStrategy movementStrategy = new ManualMovementStrategy();
        Direction nexDirection = Direction.UP;
        LinkedList<Quadrado> body = new LinkedList<>();
        Quadrado quadrado = new Quadrado("3 6 3 4 5 4 5 6");
        Quadrado quadrado1 = new Quadrado("5 6 5 4 7 4 7 6");
        Quadrado quadrado2 = new Quadrado("7 6 7 4 9 4 9 6");
        Quadrado quadrado3 = new Quadrado("9 6 11 6 11 4 9 4");
        body.add(quadrado3);
        body.add(quadrado2);
        body.add(quadrado1);
        body.add(quadrado);
        Snake snake = new Snake(body, false, random);
        snake.setNextDirection(nexDirection);
        movementStrategy.setNextDirection(snake,null);
        assertEquals(nexDirection, snake.getNextDirection());
    }
}
