package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import GameLayer.BoardLayer.FoodCircle;
import GameLayer.BoardLayer.GameBoard;
import GameLayer.SnakeLayer.Circunferencia;
import GameLayer.SnakeLayer.Direction;
import GameLayer.SnakeLayer.Ponto;
import GameLayer.SnakeLayer.Quadrado;
import GameLayer.SnakeLayer.Snake;

public class GameBoardTest {

    @Test
    public void foodContainedInSnakeTest() throws CloneNotSupportedException {
        String input = "8 5 8 3 6 3 6 5";
        List<Quadrado> listaQuadrados = new ArrayList<>();
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados);
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
