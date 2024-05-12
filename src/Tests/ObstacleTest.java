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

/** Classe que representa uma classe teste para testar as funcionalidades da classe Obstacle
    Responsabilidade: Testar as funcionalidades da classe Obstacle
    @version 1.0 12/05/2024
    @author Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class ObstacleTest {
    @Test
    public void rotateObstacleTest() {
        Triangulo triangulo = new Triangulo("3 4 2 2 4 2");
        List<Integer> angle = new ArrayList<>();
        angle.add(90);
        Obstacle obstacle = new Obstacle(triangulo, null, angle.get(0),true);
        obstacle.rotateObstacle();
        Triangulo triangulo1 = new Triangulo("3 4 2 2 4 2");
        assertEquals("Obstáculo: [(1.6666666666666665,2.6666666666666665), (3.6666666666666665,1.6666666666666665), (3.6666666666666665,3.6666666666666665)]", obstacle.toString());
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

        snake.move();
        snake.increaseSize();

        Poligono poligono1 = new Poligono("4 4 4 5 4 5 5 4 5");
        Obstacle obstacle1 = new Obstacle(poligono1, null, 0, false);
        assertFalse(obstacle1.obstacleContained(snake));

        snake.setNextDirection(Direction.RIGHT);
        snake.move();
        snake.increaseSize();
        
        Poligono poligono2 = new Poligono("4 10 4 11 4 11 3 10 3");
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

    
        Poligono poligono1 = new Poligono("4 4 4 5 4 5 5 4 5");
        Obstacle obstacle1 = new Obstacle(poligono1, null, 0, false);
        assertFalse(obstacle1.obstacleIntersect(snake));

        snake.setNextDirection(Direction.RIGHT);
        snake.move();
        snake.increaseSize();

        
        Poligono poligono2 = new Poligono("4 11 5 13 5 13 3 11 3");
        Obstacle obstacle2 = new Obstacle(poligono2, null, 0, false);
        assertTrue(obstacle2.obstacleIntersect(snake));
    }
}

