package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ControllerLayer.SnakeGame;
import ModelLayer.BoardLayer.CellType;
import ModelLayer.BoardLayer.FoodSquare;
import ModelLayer.SnakeLayer.Direction;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;

public class SnakeGameTest{
    @Test
    public void moveSnakeTest() {
        long seed = 0;
        SnakeGame snakeGame = new SnakeGame(20, 10, 2, true, "completa", 1, "quadrados", 5, 2, null, true, false, "textual", seed);
        snakeGame.moveSnake(Direction.DOWN);
        assertEquals("Cabeça: [(12.0,5.0), (14.0,5.0), (14.0,7.0), (12.0,7.0)] Tail: []", snakeGame.getSnake().toString());
        assertEquals(CellType.HEAD, snakeGame.getGameBoard().getBoard()[5][12].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getGameBoard().getBoard()[6][12].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getGameBoard().getBoard()[5][13].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getGameBoard().getBoard()[6][13].getCellType());
        snakeGame.moveSnake(Direction.RIGHT);
        snakeGame.getGameBoard().updateSnakeCells();
        assertEquals("Cabeça: [(14.0,7.0), (14.0,5.0), (16.0,5.0), (16.0,7.0)] Tail: []", snakeGame.getSnake().toString());
        assertEquals(CellType.HEAD, snakeGame.getGameBoard().getBoard()[5][14].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getGameBoard().getBoard()[6][14].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getGameBoard().getBoard()[5][15].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getGameBoard().getBoard()[6][15].getCellType());
    }

    @Test
    public void foodContainedInSnakeTest() throws CloneNotSupportedException {
        long seed = 1;
        SnakeGame game = new SnakeGame(100, 100, 10, true, "contorno", 5, "quadrados", 100, 5, new Ponto(50, 50), true, true, "textual", seed);
        System.out.println(game.getSnake().toString());
        FoodSquare foodSquare = new FoodSquare(new Quadrado("28 30 34 30 34 36 28 36"));
        game.getGameBoard().setFood(foodSquare);
        game.foodContainedInSnake(); 
        assertEquals(100, game.getScore().getScore());
        assertEquals(2, game.getSnake().getBody().size());
    }
}
