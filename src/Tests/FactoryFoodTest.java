package Tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.Test;

import ModelLayer.BoardLayer.FactoryFood;
import ModelLayer.BoardLayer.Food;
import ModelLayer.BoardLayer.FoodCircle;
import ModelLayer.BoardLayer.FoodSquare;
import ModelLayer.BoardLayer.FoodType;

/** Classe que representa uma classe teste para testar as funcionalidades da classe FactoryFood
    Responsabilidade: Testar as funcionalidades da classe FactoryFood
    @version 1.0 12/05/2024
    @author Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class FactoryFoodTest {
    @Test
    public void createFoodTest() {
        FactoryFood foodFactory = new FactoryFood();
        Food foodSquare = foodFactory.createFood(4,5,FoodType.SQUARE,2);
        assertNotNull(foodSquare);
        assertTrue(foodSquare instanceof FoodSquare);
        Food foodCircle = foodFactory.createFood(1,2,FoodType.CIRCLE,4);
        assertNotNull(foodCircle);
        assertTrue(foodCircle instanceof FoodCircle);
    }

}
