package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import ModelLayer.BoardLayer.*;
import ModelLayer.SnakeLayer.*;
import ViewLayer.CellType;
import ViewLayer.FullTextualRasterization;
import ViewLayer.TextualUI;
import ViewLayer.UI;
import org.junit.Test;

import ControllerLayer.SnakeGame;

/** Classe que representa uma classe teste para testar as funcionalidades da classe SnakeGame
    Responsabilidade: Testar as funcionalidades da classe SnakeGame
    @version 1.0 12/05/2024
    @author Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class SnakeGameTest {
    @Test
    public void moveSnakeTest() throws CloneNotSupportedException {
        long seed = 0;
        Random random = new Random(seed);
        List<Integer> angle = new ArrayList<>();
        List<Ponto<? extends Number>> rotacionPoint = new ArrayList<>();
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        angle.add(0);
        angle.add(0);
        String input1 = "4 8 6 8 6 10 4 10";
        LinkedList<Quadrado> quadrado = new LinkedList<>();
        quadrado.add(new Quadrado(input1));
        Snake snake = new Snake(quadrado,true,random);
        GameBoard gameBoard = new GameBoard(snake,20, 10, FoodType.SQUARE,2,1, rotacionPoint, angle, false, random);
        UI userInterface = new TextualUI(new FullTextualRasterization(gameBoard));
        SnakeGame snakeGame = new SnakeGame("Player",gameBoard,userInterface, 2);
        snakeGame.moveSnake(Direction.UP);
        userInterface.getTextualRasterizationStrategy().updateSnake();
        assertEquals("Cabeça: [(6.0,8.0), (8.0,8.0), (8.0,10.0), (6.0,10.0)] Tail: []", snakeGame.getGameBoard().getSnake().toString());
        assertEquals(CellType.HEAD, snakeGame.getUserInterface().getTextualRasterizationStrategy().getBoard()[8][6].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getUserInterface().getTextualRasterizationStrategy().getBoard()[8][7].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getUserInterface().getTextualRasterizationStrategy().getBoard()[9][6].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getUserInterface().getTextualRasterizationStrategy().getBoard()[9][7].getCellType());
        snakeGame.moveSnake(Direction.RIGHT);
        snakeGame.getUserInterface().getTextualRasterizationStrategy().updateSnake();
        assertEquals("Cabeça: [(8.0,10.0), (8.0,8.0), (10.0,8.0), (10.0,10.0)] Tail: []", snakeGame.getGameBoard().getSnake().toString());
        assertEquals(CellType.HEAD, snakeGame.getUserInterface().getTextualRasterizationStrategy().getBoard()[8][8].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getUserInterface().getTextualRasterizationStrategy().getBoard()[8][9].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getUserInterface().getTextualRasterizationStrategy().getBoard()[9][8].getCellType());
        assertEquals(CellType.HEAD, snakeGame.getUserInterface().getTextualRasterizationStrategy().getBoard()[9][9].getCellType());
    }

    @Test
    public void foodContainedInSnakeHeadTest() throws CloneNotSupportedException {
        long seed = 1;
        Random random = new Random();
        List<Integer> angle = new ArrayList<>();
        List<Ponto<? extends Number>> rotacionPoint = new ArrayList<>();
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        angle.add(0);
        angle.add(0);
        angle.add(0);
        angle.add(0);
        angle.add(0);
        String input1 = "10 10 40 10 40 40 10 40";
        LinkedList<Quadrado> quadrado = new LinkedList<>();
        quadrado.add(new Quadrado(input1));
        Snake snake = new Snake(quadrado,true,random);
        GameBoard gameBoard = new GameBoard(snake,100,100,FoodType.SQUARE,6,5,rotacionPoint,angle,false,random);
        UI userInterface = new TextualUI(new FullTextualRasterization(gameBoard));
        SnakeGame game = new SnakeGame("Player",gameBoard,userInterface,10);
        FoodSquare foodSquare = new FoodSquare(new Quadrado("16 30 22 30 22 36 16 36"));
        game.getGameBoard().setFood(foodSquare);
        game.foodContainedInSnakeHead(); 
        assertEquals(100, game.getScore().getPoints());
        assertEquals(2, game.getGameBoard().getSnake().getBody().size());
    }

    @Test
    public void snakeCollidedTest(){
        long seed = 1;
        Random random = new Random();
        List<Integer> angle = new ArrayList<>();
        List<Ponto<? extends Number>> rotacionPoint = new ArrayList<>();
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        angle.add(0);
        angle.add(0);
        angle.add(0);
        angle.add(0);
        angle.add(0);
        String input1 = "10 10 40 10 40 40 10 40";
        LinkedList<Quadrado> quadrado = new LinkedList<>();
        quadrado.add(new Quadrado(input1));
        Snake snake = new Snake(quadrado,true,random);
        GameBoard gameBoard = new GameBoard(snake,100,100,FoodType.SQUARE,6,5,rotacionPoint,angle,false,random);
        UI userInterface = new TextualUI(new FullTextualRasterization(gameBoard));
        SnakeGame game = new SnakeGame("Player",gameBoard,userInterface,10);
        assertEquals(game.getGameBoard().getObstaclesQuantity(), 5);
        assertFalse(game.snakeCollided());
    }
}
