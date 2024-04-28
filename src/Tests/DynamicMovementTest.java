package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        obstacleMovement.rotateObstacle(quadrado, new Ponto(5,5));
        assertEquals("[(6.0,2.0), (8.0,2.0), (8.0,4.0), (6.0,4.0)]", quadrado.toString());
        Triangulo triangulo = new Triangulo("3 4 2 2 4 2");
        obstacleMovement.rotateObstacle(triangulo, new Ponto(3,2.67));
        assertEquals("[(2.0,3.0), (4.0,2.0), (4.0,4.0)]", triangulo.toString());
    }
}
