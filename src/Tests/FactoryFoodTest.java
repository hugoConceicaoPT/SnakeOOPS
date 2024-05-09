package Tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import ModelLayer.BoardLayer.FactoryFood;
import ModelLayer.BoardLayer.Food;
import ModelLayer.BoardLayer.FoodCircle;
import ModelLayer.BoardLayer.FoodSquare;
import ModelLayer.SnakeLayer.Circunferencia;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;

public class FactoryFoodTest {
    @Test
    public void createFoodTest() {
        FactoryFood foodFactory = new FactoryFood();
        Quadrado quadrado = new Quadrado("3 6 3 4 5 4 5 6");
        Food foodSquare = foodFactory.createFood(quadrado);
        assertNotNull(foodSquare);
        assertTrue(foodSquare instanceof FoodSquare);
        Circunferencia circunferencia = new Circunferencia(new Ponto<Integer>(3, 4), 2);
        Food foodCircle = foodFactory.createFood(circunferencia);
        assertNotNull(foodCircle);
        assertTrue(foodCircle instanceof FoodCircle);
    }
}
