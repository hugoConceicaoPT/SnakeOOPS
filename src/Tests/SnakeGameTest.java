package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.junit.Test;

import ControllerLayer.SnakeGame;
import ModelLayer.BoardLayer.CellType;
import ModelLayer.SnakeLayer.Direction;
import ModelLayer.SnakeLayer.Ponto;

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

    @Test
    public void foodContainedInSnakeTest() throws CloneNotSupportedException {
        SnakeGame game = new SnakeGame(100, 100, 10, true, "contorno", 5, "quadrados", 100, 5, new Ponto(50, 50), true, true, "textual", new Scanner(System.in));
        game.foodContainedInSnake(); 
        assertEquals(100, game.getScore());
        assertEquals(1, game.getSnake().getBody().size());
    }
}
