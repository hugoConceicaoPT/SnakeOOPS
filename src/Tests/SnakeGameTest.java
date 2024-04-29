package Tests;

import static org.junit.Assert.assertEquals;
import java.util.LinkedList;
import java.util.Scanner;

import org.junit.Test;

import ControllerLayer.SnakeGame;
import ModelLayer.BoardLayer.CellType;
import ModelLayer.SnakeLayer.Direction;

public class SnakeGameTest{
    @Test
    public void moveSnakeTest() {
        Scanner sc = new Scanner(System.in);
        SnakeGame snakeGame = new SnakeGame(20, 10, 2, true, "completa", 1, "quadrados", 5, 2, null, true, false, "textual", sc);
        snakeGame.getRandom().setSeed(0);
        System.out.println(snakeGame.getSnake().toString() + " " + snakeGame.getSnake().getDirection());
        snakeGame.moveSnake(Direction.DOWN);
        assertEquals("Cabeça: [(12.0,5.0), (14.0,5.0), (14.0,7.0), (12.0,7.0)] Tail: []", snakeGame.getSnake().toString());
        assertEquals(CellType.HEAD, snakeGame.getGameBoard().getBoard()[5][12].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getGameBoard().getBoard()[6][12].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getGameBoard().getBoard()[5][13].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getGameBoard().getBoard()[6][13].getCellType());
        snakeGame.moveSnake(Direction.RIGHT);
        assertEquals("Cabeça: [(14.0,7.0), (14.0,5.0), (16.0,5.0), (16.0,7.0)] Tail: []", snakeGame.getSnake().toString());
        assertEquals(CellType.HEAD, snakeGame.getGameBoard().getBoard()[5][14].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getGameBoard().getBoard()[6][14].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getGameBoard().getBoard()[5][15].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getGameBoard().getBoard()[6][15].getCellType());
    }
}
