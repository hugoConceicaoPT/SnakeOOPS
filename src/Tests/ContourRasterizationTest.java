package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import ModelLayer.BoardLayer.CellType;
import ModelLayer.BoardLayer.FoodType;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.SnakeLayer.Direction;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Snake;
import ViewLayer.ContourRasterization;


public class ContourRasterizationTest {
        @Test
    public void updateSnakeCellsTest() {
        long seed = 119;
        Random random = new Random(seed);
        String input = "6 3 9 3 9 6 6 6";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados, true,random);
        snake.setCurrentDirection(Direction.RIGHT);
        snake.setNextDirection(Direction.RIGHT);
        snake.move();
        snake.increaseSize();
        List<Ponto<? extends Number>> rotacionPoint = new ArrayList<>();
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        GameBoard gameBoard = new GameBoard(snake, 20, 10,FoodType.SQUARE,1,2,rotacionPoint,false,random);
        ContourRasterization rasterizationStrategy = new ContourRasterization(gameBoard);
        rasterizationStrategy.updateSnakeCells();
        assertEquals(CellType.HEAD, rasterizationStrategy.getBoard()[3][9].getCellType());
        assertEquals(CellType.HEAD, rasterizationStrategy.getBoard()[3][10].getCellType());
        assertEquals(CellType.HEAD, rasterizationStrategy.getBoard()[3][11].getCellType());
        assertEquals(CellType.HEAD, rasterizationStrategy.getBoard()[4][9].getCellType());
        assertEquals(CellType.EMPTY, rasterizationStrategy.getBoard()[4][10].getCellType());
        assertEquals(CellType.HEAD, rasterizationStrategy.getBoard()[4][11].getCellType());
        assertEquals(CellType.HEAD, rasterizationStrategy.getBoard()[5][9].getCellType());
        assertEquals(CellType.HEAD, rasterizationStrategy.getBoard()[5][10].getCellType());
        assertEquals(CellType.HEAD, rasterizationStrategy.getBoard()[5][11].getCellType());
        assertEquals(CellType.TAIL, rasterizationStrategy.getBoard()[3][6].getCellType());
        assertEquals(CellType.TAIL, rasterizationStrategy.getBoard()[3][7].getCellType());
        assertEquals(CellType.TAIL, rasterizationStrategy.getBoard()[3][8].getCellType());
        assertEquals(CellType.TAIL, rasterizationStrategy.getBoard()[4][8].getCellType());
        assertEquals(CellType.TAIL, rasterizationStrategy.getBoard()[5][6].getCellType());
        assertEquals(CellType.TAIL, rasterizationStrategy.getBoard()[5][7].getCellType());
        assertEquals(CellType.TAIL, rasterizationStrategy.getBoard()[5][8].getCellType());
    }

    @Test
    public void updateObstacleCellsTest() {
        long seed = 118;
        Random random = new Random(seed);
        String input = "6 3 9 3 9 6 6 6";
        LinkedList<Quadrado> listaQuadrados = new LinkedList<>();
        listaQuadrados.add(new Quadrado(input));
        Snake snake = new Snake(listaQuadrados, true,random);
        snake.setCurrentDirection(Direction.RIGHT);
        snake.setNextDirection(Direction.RIGHT);
        snake.move();
        snake.increaseSize();
        List<Ponto<? extends Number>> rotacionPoint = new ArrayList<>();
        rotacionPoint.add(null);
        rotacionPoint.add(null);
        GameBoard gameBoard = new GameBoard(snake, 20, 10,FoodType.SQUARE,1,2,rotacionPoint,false,random);
        ContourRasterization rasterizationStrategy = new ContourRasterization(gameBoard);
        rasterizationStrategy.updateObstacleCells();
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[1][5].getCellType());
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[1][6].getCellType());
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[1][7].getCellType());
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[1][8].getCellType());
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[2][5].getCellType());
        assertEquals(CellType.EMPTY, rasterizationStrategy.getBoard()[2][6].getCellType());
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[2][7].getCellType());
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[3][5].getCellType());
        assertEquals(CellType.EMPTY, rasterizationStrategy.getBoard()[3][6].getCellType());
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[3][7].getCellType());
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[4][5].getCellType());
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[4][6].getCellType());
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[4][7].getCellType());
    }
}