package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ControllerLayer.SnakeGame;
import ModelLayer.BoardLayer.CellType;
import ModelLayer.BoardLayer.FoodSquare;
import ModelLayer.SnakeLayer.Direction;
import ModelLayer.SnakeLayer.Quadrado;

public class SnakeGameTest {
    @Test
    public void moveSnakeTest() throws CloneNotSupportedException {
        long seed = 0;
        SnakeGame snakeGame = new SnakeGame("Player",20, 10, 2, true, "completa", 1, "quadrados", 5, 2, null, true, "textual", seed);
        System.out.println(snakeGame.getSnake().toString());
        snakeGame.moveSnake(Direction.UP);
        snakeGame.getrasterizationStrategy().updateSnakeCells();
        assertEquals("Cabeça: [(6.0,8.0), (8.0,8.0), (8.0,10.0), (6.0,10.0)] Tail: []", snakeGame.getSnake().toString());
        assertEquals(CellType.HEAD, snakeGame.getrasterizationStrategy().getBoard()[8][6].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getrasterizationStrategy().getBoard()[8][7].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getrasterizationStrategy().getBoard()[9][6].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getrasterizationStrategy().getBoard()[9][7].getCellType());
        snakeGame.moveSnake(Direction.RIGHT);
        snakeGame.getrasterizationStrategy().updateSnakeCells();
        assertEquals("Cabeça: [(8.0,10.0), (8.0,8.0), (10.0,8.0), (10.0,10.0)] Tail: []", snakeGame.getSnake().toString());
        assertEquals(CellType.HEAD, snakeGame.getrasterizationStrategy().getBoard()[8][8].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getrasterizationStrategy().getBoard()[8][9].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getrasterizationStrategy().getBoard()[9][8].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getrasterizationStrategy().getBoard()[9][9].getCellType());
    }

    @Test
    public void foodContainedInSnakeTest() throws CloneNotSupportedException {
        long seed = 1;
        SnakeGame game = new SnakeGame("Player",100, 100, 10, true, "contorno", 5, "quadrados", 100, 5, null, true, "textual", seed);
        System.out.println(game.getSnake().toString());
        FoodSquare foodSquare = new FoodSquare(new Quadrado("16 30 22 30 22 36 16 36"));
        game.getGameBoard().setFood(foodSquare);
        game.foodContainedInSnakeHead(); 
        assertEquals(100, game.getScore().getPoints());
        assertEquals(2, game.getSnake().getBody().size());
    }
}
