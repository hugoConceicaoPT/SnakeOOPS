package Tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ModelLayer.BoardLayer.Obstacle;
import ModelLayer.SnakeLayer.Triangulo;

public class ObstacleTest {
        @Test
        public void rotateObstacleTest() {
            Triangulo triangulo = new Triangulo("3 4 2 2 4 2");
            List<Integer> angle = new ArrayList<>();
            angle.add(0);
            Obstacle obstacle = new Obstacle(triangulo, null, angle.get(0),true);
            obstacle.rotateObstacle();
            Triangulo triangulo1 = new Triangulo("3 4 2 2 4 2");
            assertEquals("Obstáculo: [(2,3), (4,2), (4,4)]", obstacle.toString());
            Obstacle obstacle1 = new Obstacle(triangulo1, null,angle.get(0) ,false);
            obstacle1.rotateObstacle();
            assertEquals("Obstáculo: [(3,4), (2,2), (4,2)]", obstacle1.toString());
        }
    }

