package Tests;



import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import ModelLayer.BoardLayer.FoodCircle;
import ModelLayer.BoardLayer.Obstacle;
import ModelLayer.SnakeLayer.Circunferencia;
import ModelLayer.SnakeLayer.Direction;
import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Snake;

public class FoodCircleTest {
    @Test
    public void foodContainedInSnakeTest() throws CloneNotSupportedException {
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        Random random = new Random(122);
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados, true,random);
        snake.setCurrentDirection(Direction.RIGHT);
        snake.setNextDirection(Direction.RIGHT);
        snake.move();
        snake.increaseSize();
        snake.move();
        snake.increaseSize();
        FoodCircle foodCircle = new FoodCircle(new Circunferencia(new Ponto<Double>(9.5,5.0), 0.5));
        assertFalse(foodCircle.foodContainedInSnakeHead(snake));
        FoodCircle foodCircle2 = new FoodCircle(new Circunferencia(new Ponto<Integer>(9,4), 0.5));
        assertTrue(foodCircle2.foodContainedInSnakeHead(snake));
        assertTrue(new FoodCircle(new Circunferencia(new Ponto<Integer>(10,4), 0.5)).foodContainedInSnakeHead(snake));
        assertTrue(new FoodCircle(new Circunferencia(new Ponto<Double>(10.5,3.5), 0.5)).foodContainedInSnakeHead(snake));
    }


    @Test
    public void foodIntersectObstacleTest() {
        
        Circunferencia foodCircle = new Circunferencia(new Ponto<Double>(5.0, 5.0), 1.0);
        FoodCircle food = new FoodCircle(foodCircle);

       
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
    public void foodContainedObstacleTest() {
        
        Circunferencia foodCircle = new Circunferencia(new Ponto<Double>(5.0, 5.0), 2.0);
        FoodCircle food = new FoodCircle(foodCircle);

       
        List<Ponto<? extends Number>> containingObstaclePoints = new ArrayList<>();
        containingObstaclePoints.add(new Ponto<>(3.0, 3.0));
        containingObstaclePoints.add(new Ponto<>(7.0, 3.0));
        containingObstaclePoints.add(new Ponto<>(7.0, 7.0));
        containingObstaclePoints.add(new Ponto<>(3.0, 7.0));
        Poligono containingPolygon = new Poligono(containingObstaclePoints);
        Obstacle containingObstacle = new Obstacle(containingPolygon, null, 0, false);
        assertTrue(food.foodContainedObstacle(containingObstacle));

        
        List<Ponto<? extends Number>> containedObstaclePoints = new ArrayList<>();
        containedObstaclePoints.add(new Ponto<>(4.0, 4.0));
        containedObstaclePoints.add(new Ponto<>(6.0, 4.0));
        containedObstaclePoints.add(new Ponto<>(6.0, 6.0));
        containedObstaclePoints.add(new Ponto<>(4.0, 6.0));
        Poligono containedPolygon = new Poligono(containedObstaclePoints);
        Obstacle containedObstacle = new Obstacle(containedPolygon, null, 0, false);
        assertTrue(food.foodContainedObstacle(containedObstacle));        
    }
    @Test
    public void foodIntersectSnakeTest() {
        
        LinkedList<Quadrado> snakeBody = new LinkedList<>();
        snakeBody.add(new Quadrado("8 5 8 6 9 6 9 5")); 
        snakeBody.add(new Quadrado("7 5 7 6 8 6 8 5")); 
        snakeBody.add(new Quadrado("6 5 6 6 7 6 7 5")); 
        Snake snake = new Snake(snakeBody, true, new Random());
    
       
        Circunferencia intersectingFoodCircle = new Circunferencia(new Ponto<Double>(7.5, 5.5), 0.5);
        FoodCircle intersectingFood = new FoodCircle(intersectingFoodCircle);
        assertTrue(intersectingFood.foodIntersectSnake(snake));
    
        
        Circunferencia nonIntersectingFoodCircle = new Circunferencia(new Ponto<Double>(10.5, 5.5), 0.5);
        FoodCircle nonIntersectingFood = new FoodCircle(nonIntersectingFoodCircle);
        assertFalse(nonIntersectingFood.foodIntersectSnake(snake));
    }
    
}


