package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ModelLayer.BoardLayer.DynamicMovement;
import ModelLayer.BoardLayer.ObstacleMovement;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Triangulo;

public class DynamicMovementTest {
    @Test
    public void rotateObstacleTest() {
        ObstacleMovement obstacleMovement = new DynamicMovement();
        Quadrado quadrado = new Quadrado("2 4 2 2 4 2 4 4");
        List<Integer> angle = new ArrayList<>();
        angle.add(0);
        obstacleMovement.rotateObstacle(quadrado, new Ponto<Integer>(5,5),angle.get(0));
        assertEquals("[(6,2), (8,2), (8,4), (6,4)]", quadrado.toString());
        Triangulo triangulo = new Triangulo("3.0 4.0 2.0 2.0 4.0 2.0");
        obstacleMovement.rotateObstacle(triangulo, new Ponto<Double>(3.0,2.67),angle.get(0));
        assertEquals("[(1.67,2.67), (3.67,1.67), (3.67,3.67)]", triangulo.toString());
    }
}
