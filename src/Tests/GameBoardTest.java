package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

import ModelLayer.BoardLayer.FoodCircle;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.SnakeLayer.Circunferencia;
import ModelLayer.SnakeLayer.Direction;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Snake;

public class GameBoardTest {

    @Test
    public void foodContainedInSnakeTest() throws CloneNotSupportedException {
        String input = "8 5 8 3 6 3 6 5";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados, true);
        snake.setDirection(Direction.RIGHT);
        snake.increaseSize();
        snake.increaseSize();
        GameBoard gameBoard = new GameBoard(snake, 200, 100,true,1,1,false);
        FoodCircle foodCircle = new FoodCircle(new Circunferencia(new Ponto(9.5,4.5), 0.5));
        gameBoard.setFood(foodCircle);
        snake.move(Direction.RIGHT);
        assertTrue(gameBoard.foodContainedInSnake());
        snake.move(Direction.UP);
        assertFalse(gameBoard.foodContainedInSnake());
    }
}
