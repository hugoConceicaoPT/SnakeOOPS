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


/** Classe que representa uma classe teste para testar as funcionalidades da classe DynamicMovement
    Responsabilidade: Testar as funcionalidades da classe DynamicMovement
    @version 1.0 12/05/2024
    @author Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class DynamicMovementTest {
    @Test
    public void rotateObstacleTest() {
        ObstacleMovement obstacleMovement = new DynamicMovement();
        Quadrado quadrado = new Quadrado("2 4 2 2 4 2 4 4");
        List<Integer> angle = new ArrayList<>();
        angle.add(90);
        obstacleMovement.rotateObstacle(quadrado, new Ponto<Integer>(5,5),angle.get(0));
        assertEquals("[(6.0,2.0), (8.0,2.0), (8.0,4.0), (6.0,4.0)]", quadrado.toString());
        Triangulo triangulo = new Triangulo("3.0 4.0 2.0 2.0 4.0 2.0");
        obstacleMovement.rotateObstacle(triangulo, new Ponto<Double>(3.0,2.67),angle.get(0));
        assertEquals("[(1.67,2.67), (3.67,1.67), (3.67,3.67)]", triangulo.toString());
    }

}
