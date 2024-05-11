package Tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import ModelLayer.BoardLayer.FoodSquare;
import ModelLayer.BoardLayer.Obstacle;
import ModelLayer.SnakeLayer.Direction;
import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Snake;

public class FoodSquareTest {
    @Test
    public void foodContainedInSnakeTest() throws CloneNotSupportedException {
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Random random = new Random(120);
        Snake snake = new Snake(listaQuadrados, true,random);
        snake.setCurrentDirection(Direction.RIGHT);
        snake.setNextDirection(Direction.RIGHT);
        snake.move();
        snake.increaseSize();
        snake.move();
        snake.increaseSize();
        Quadrado quadrado = new Quadrado("10 5 10 4 9 4 9 5");
        FoodSquare foodCircle = new FoodSquare(quadrado);
        assertTrue(foodCircle.foodContainedInSnakeHead(snake));
        assertTrue(new FoodSquare(new Quadrado("11 5 11 4 10 4 10 5")).foodContainedInSnakeHead(snake));
        assertFalse(new FoodSquare(new Quadrado("7 5 8 5 8 4 7 4")).foodContainedInSnakeHead(snake));
        assertFalse(new FoodSquare(new Quadrado("8 6 9 6 9 5 8 5")).foodContainedInSnakeHead(snake));
        assertTrue(new FoodSquare(new Quadrado("11 4 12 4 12 3 11 3")).foodContainedInSnakeHead(snake));    
        snake.setNextDirection(Direction.UP);
        snake.move();
        assertTrue(new FoodSquare(new Quadrado("10 7 11 7 11 6 10 6")).foodContainedInSnakeHead(snake));

    }
        @Test
    public void foodIntersectObstacleTest() {

        FoodSquare food = new FoodSquare(new Quadrado("5.0,5.0;7.0,5.0;7.0,7.0;5.0,7.0"));


        List<Ponto<? extends Number>> nonIntersectingPoints = new ArrayList<>();
        nonIntersectingPoints.add(new Ponto<>(1.0, 1.0));
        nonIntersectingPoints.add(new Ponto<>(2.0, 1.0));
        nonIntersectingPoints.add(new Ponto<>(2.0, 2.0));
        nonIntersectingPoints.add(new Ponto<>(1.0, 2.0));
        Poligono nonIntersectingPolygon = new Poligono(nonIntersectingPoints);
        Obstacle nonIntersectingObstacle = new Obstacle(nonIntersectingPolygon, null, 0, false);
        assertFalse(food.foodIntersectObstacle(nonIntersectingObstacle));

        List<Ponto<? extends Number>>  intersectingPoints = new ArrayList<>();
        intersectingPoints.add(new Ponto<>(4.0, 4.0));
        intersectingPoints.add(new Ponto<>(6.0, 4.0));
        intersectingPoints.add(new Ponto<>(6.0, 6.0));
        intersectingPoints.add(new Ponto<>(4.0, 6.0));
        Poligono intersectingPolygon = new Poligono(intersectingPoints);
        Ponto<Double> rotationPoint = intersectingPolygon.getCentroide(); 
        Obstacle intersectingObstacle = new Obstacle(intersectingPolygon, rotationPoint, 45, true);
        assertTrue(food.foodIntersectObstacle(intersectingObstacle));
    }
    @Test
    public void foodIntersectSnakeTest() {
        LinkedList<Quadrado> snakeBody = new LinkedList<>();
        snakeBody.add(new Quadrado("8 5 8 6 9 6 9 5"));
        snakeBody.add(new Quadrado("7 5 7 6 8 6 8 5"));
        snakeBody.add(new Quadrado("6 5 6 6 7 6 7 5"));
        Snake snake = new Snake(snakeBody, true, new Random());

        FoodSquare intersectingFood = new FoodSquare(new Quadrado("7.0,5.0;8.0,5.0;8.0,6.0;7.0,6.0"));
        assertTrue(intersectingFood.foodIntersectSnake(snake));


        FoodSquare nonIntersectingFood = new FoodSquare(new Quadrado("10.0,5.0;11.0,5.0;11.0,6.0;10.0,6.0"));
        assertFalse(nonIntersectingFood.foodIntersectSnake(snake));
    }

    @Test
    public void foodContainedObstacleTest() {

        FoodSquare food = new FoodSquare(new Quadrado("5.0,5.0;7.0,5.0;7.0,7.0;5.0,7.0"));

       
        List<Ponto<? extends Number>>  containingPoints = new ArrayList<>();
        containingPoints.add(new Ponto<>(4.0, 4.0));
        containingPoints.add(new Ponto<>(8.0, 4.0));
        containingPoints.add(new Ponto<>(8.0, 8.0));
        containingPoints.add(new Ponto<>(4.0, 8.0));
        Poligono containingPolygon = new Poligono(containingPoints);
        Obstacle containingObstacle = new Obstacle(containingPolygon, null, 0, false);
        assertTrue(food.foodContainedObstacle(containingObstacle));

       
        List<Ponto<? extends Number>> containedPoints = new ArrayList<>();
        containedPoints.add(new Ponto<>(5.5, 5.5));
        containedPoints.add(new Ponto<>(6.5, 5.5));
        containedPoints.add(new Ponto<>(6.5, 6.5));
        containedPoints.add(new Ponto<>(5.5, 6.5));
        Poligono containedPolygon = new Poligono(containedPoints);
        Obstacle containedObstacle = new Obstacle(containedPolygon, null, 0, false);
        assertTrue(food.foodContainedObstacle(containedObstacle));

        
        List<Ponto<? extends Number>>  nonContainingPoints = new ArrayList<>();
        nonContainingPoints.add(new Ponto<>(6.0, 6.0));
        nonContainingPoints.add(new Ponto<>(8.0, 6.0));
        nonContainingPoints.add(new Ponto<>(8.0, 8.0));
        nonContainingPoints.add(new Ponto<>(6.0, 8.0));
        Poligono nonContainingPolygon = new Poligono(nonContainingPoints);
        Obstacle nonContainingObstacle = new Obstacle(nonContainingPolygon, null, 0, false);
        assertFalse(food.foodContainedObstacle(nonContainingObstacle));
    }

}
