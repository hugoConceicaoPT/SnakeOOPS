package Tests;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import org.junit.Test;

import ModelLayer.SnakeLayer.Direction;
import ModelLayer.SnakeLayer.ManualMovementStrategy;
import ModelLayer.SnakeLayer.MovementStrategy;
import ModelLayer.SnakeLayer.Quadrado;

public class ManualMovementStrategyTest {
    @Test
    public void moveTest() {
        MovementStrategy movementStrategy = new ManualMovementStrategy();
        Direction nexDirection = Direction.UP;
        Direction currentDirection = Direction.RIGHT;
        LinkedList<Quadrado> body = new LinkedList<>();
        Quadrado quadrado = new Quadrado("3 6 3 4 5 4 5 6");
        Quadrado quadrado1 = new Quadrado("5 6 5 4 7 4 7 6");
        Quadrado quadrado2 = new Quadrado("7 6 7 4 9 4 9 6");
        Quadrado quadrado3 = new Quadrado("9 6 11 6 11 4 9 4");
        body.add(quadrado3);
        body.add(quadrado2);
        body.add(quadrado1);
        body.add(quadrado);
        movementStrategy.move(nexDirection, body, currentDirection, 2);
        assertEquals("[[(9.0,6.0), (11.0,6.0), (11.0,8.0), (9.0,8.0)], [(9.0,6.0), (11.0,6.0), (11.0,4.0), (9.0,4.0)], [(7.0,6.0), (7.0,4.0), (9.0,4.0), (9.0,6.0)], [(5.0,6.0), (5.0,4.0), (7.0,4.0), (7.0,6.0)]]", body.toString());
    }
}
