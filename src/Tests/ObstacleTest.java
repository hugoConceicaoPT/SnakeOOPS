package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ModelLayer.BoardLayer.Obstacle;
import ModelLayer.SnakeLayer.Triangulo;

public class ObstacleTest {
        @Test
        public void rotateObstacleTest() {
            Triangulo triangulo = new Triangulo("3 4 2 2 4 2");
            Obstacle obstacle = new Obstacle(triangulo, null, true);
            obstacle.rotateObstacle();
            Triangulo triangulo1 = new Triangulo("3 4 2 2 4 2");
            assertEquals("Obstáculo: [(2.0,3.0), (4.0,2.0), (4.0,4.0)]", obstacle.toString());
            Obstacle obstacle1 = new Obstacle(triangulo1, null, false);
            obstacle1.rotateObstacle();
            assertEquals("Obstáculo: [(3.0,4.0), (2.0,2.0), (4.0,2.0)]", obstacle1.toString());
        }
    }

