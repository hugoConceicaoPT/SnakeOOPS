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
import ViewLayer.FullRasterization;

public class FullRasterizationTest {
    @Test
    public void updateSnakeCellsTest() throws CloneNotSupportedException{
        long seed = 119;
        Random random = new Random(seed);
        String input = "6 3 8 3 8 5 6 5";
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
        FullRasterization rasterizationStrategy = new FullRasterization(gameBoard);
        rasterizationStrategy.updateSnakeCells();
        assertEquals(CellType.HEAD, rasterizationStrategy.getBoard()[4][8].getCellType());
        assertEquals(CellType.HEAD, rasterizationStrategy.getBoard()[3][8].getCellType());
        assertEquals(CellType.HEAD, rasterizationStrategy.getBoard()[3][9].getCellType());
        assertEquals(CellType.HEAD, rasterizationStrategy.getBoard()[4][9].getCellType());
        assertEquals(CellType.TAIL, rasterizationStrategy.getBoard()[3][6].getCellType());
        assertEquals(CellType.TAIL, rasterizationStrategy.getBoard()[4][6].getCellType());
        assertEquals(CellType.TAIL, rasterizationStrategy.getBoard()[3][7].getCellType());
        assertEquals(CellType.TAIL, rasterizationStrategy.getBoard()[4][7].getCellType());
    }

    @Test
    public void updateObstaclesCellsTest() {
        long seed = 1;
        Random random = new Random(seed);
        String input = "6 3 8 3 8 5 6 5";
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
        GameBoard gameBoard = new GameBoard(snake, 10, 10,FoodType.SQUARE,1,2,rotacionPoint,false,random);
        FullRasterization rasterizationStrategy = new FullRasterization(gameBoard);
        rasterizationStrategy.updateSnakeCells();
        assertEquals(CellType.HEAD, rasterizationStrategy.getBoard()[4][8].getCellType());
        assertEquals(CellType.HEAD, rasterizationStrategy.getBoard()[3][8].getCellType());
        assertEquals(CellType.HEAD, rasterizationStrategy.getBoard()[3][9].getCellType());
        assertEquals(CellType.HEAD, rasterizationStrategy.getBoard()[4][9].getCellType());
        assertEquals(CellType.TAIL, rasterizationStrategy.getBoard()[3][6].getCellType());
        assertEquals(CellType.TAIL, rasterizationStrategy.getBoard()[4][6].getCellType());
        assertEquals(CellType.TAIL, rasterizationStrategy.getBoard()[3][7].getCellType());
        assertEquals(CellType.TAIL, rasterizationStrategy.getBoard()[4][7].getCellType());
        rasterizationStrategy.updateObstacleCells();
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[6][8].getCellType());
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[5][0].getCellType());
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[6][0].getCellType());
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[5][1].getCellType());
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[6][1].getCellType());

    }
}
