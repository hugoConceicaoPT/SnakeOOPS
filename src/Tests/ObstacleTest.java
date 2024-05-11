package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import ModelLayer.BoardLayer.Obstacle;
import ModelLayer.SnakeLayer.Direction;
import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Snake;
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
            @Test
    public void testObstacleIntersect() {
        
        Triangulo triangulo = new Triangulo("3 4 2 2 4 2");
        Obstacle obstacle = new Obstacle(triangulo, null, 0, true);

        
        Ponto<Integer> insidePoint = new Ponto<>(3, 3);
        assertTrue(obstacle.obstacleIntersect(insidePoint));

       
        Ponto<Integer> outsidePoint = new Ponto<>(1, 1);
        assertFalse(obstacle.obstacleIntersect(outsidePoint));

        
        Ponto<Integer> borderPoint = new Ponto<>(2, 2);
        assertTrue(obstacle.obstacleIntersect(borderPoint));
    }


    @Test
public void obstacleContainedTest() {
    long seed = 116;
    Random random = new Random(seed);
    String inputSnake = "8 4 8 2 6 2 6 4";
    LinkedList<Quadrado> quadradoSnake = new LinkedList<>();
    quadradoSnake.add(new Quadrado(inputSnake));
    Snake snake = new Snake(quadradoSnake, true, random);
    snake.setCurrentDirection(Direction.RIGHT);
    snake.setNextDirection(Direction.RIGHT);

    // Mover a cobra para uma posição onde sua cabeça não intersecta o polígono do obstáculo
    snake.move();
    snake.increaseSize();

    // Criar um polígono e um obstáculo que não contém a cabeça da cobra
    Poligono poligono1 = new Poligono("4 4 5 4 5 5 4 5");
    Obstacle obstacle1 = new Obstacle(poligono1, null, 0, false);
    assertFalse(obstacle1.obstacleContained(snake));

    // Mover a cobra para uma posição onde sua cabeça intersecta o polígono do obstáculo
    snake.setNextDirection(Direction.RIGHT);
    snake.move();
    snake.increaseSize();

    
    Poligono poligono2 = new Poligono("6 4 7 4 7 5 6 5");
    Obstacle obstacle2 = new Obstacle(poligono2, null, 0, false);
    assertTrue(obstacle2.obstacleContained(snake));
}


@Test
public void obstacleIntersectTest() {
    long seed = 116;
    Random random = new Random(seed);
    String inputSnake = "8 4 8 2 6 2 6 4";
    LinkedList<Quadrado> quadradoSnake = new LinkedList<>();
    quadradoSnake.add(new Quadrado(inputSnake));
    Snake snake = new Snake(quadradoSnake, true, random);
    snake.setCurrentDirection(Direction.RIGHT);
    snake.setNextDirection(Direction.RIGHT);

    
    snake.move();
    snake.increaseSize();

  
    Poligono poligono1 = new Poligono("4 4 5 4 5 5 4 5");
    Obstacle obstacle1 = new Obstacle(poligono1, null, 0, false);
    assertFalse(obstacle1.obstacleIntersect(snake));

    snake.setNextDirection(Direction.RIGHT);
    snake.move();
    snake.increaseSize();

    
    Poligono poligono2 = new Poligono("6 4 7 4 7 5 6 5");
    Obstacle obstacle2 = new Obstacle(poligono2, null, 0, false);
    assertTrue(obstacle2.obstacleIntersect(snake));
}


    }

