package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
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
        snake.increaseSize();
        GameBoard gameBoard = new GameBoard(snake, 20, 10,FoodType.SQUARE,1,2,new Ponto(1,1),false,random);
        FullRasterization rasterizationStrategy = new FullRasterization(gameBoard);
        assertEquals(CellType.HEAD, rasterizationStrategy.getBoard()[6][4].getCellType());
        assertEquals(CellType.HEAD, rasterizationStrategy.getBoard()[6][3].getCellType());
        assertEquals(CellType.HEAD, rasterizationStrategy.getBoard()[7][3].getCellType());
        assertEquals(CellType.HEAD, rasterizationStrategy.getBoard()[7][4].getCellType());
        assertEquals(CellType.TAIL, rasterizationStrategy.getBoard()[4][3].getCellType());
        assertEquals(CellType.TAIL, rasterizationStrategy.getBoard()[4][4].getCellType());
        assertEquals(CellType.TAIL, rasterizationStrategy.getBoard()[5][3].getCellType());
        assertEquals(CellType.TAIL, rasterizationStrategy.getBoard()[5][4].getCellType());
        snake.setNextDirection(Direction.UP);
        snake.move();
        rasterizationStrategy.updateSnakeCells();
        assertEquals(CellType.HEAD, rasterizationStrategy.getBoard()[6][6].getCellType());
        assertEquals(CellType.HEAD, rasterizationStrategy.getBoard()[6][5].getCellType());
        assertEquals(CellType.HEAD, rasterizationStrategy.getBoard()[7][6].getCellType());
        assertEquals(CellType.HEAD, rasterizationStrategy.getBoard()[7][5].getCellType());
        assertEquals(CellType.TAIL, rasterizationStrategy.getBoard()[6][4].getCellType());
        assertEquals(CellType.TAIL, rasterizationStrategy.getBoard()[6][3].getCellType());
        assertEquals(CellType.TAIL, rasterizationStrategy.getBoard()[7][4].getCellType());
        assertEquals(CellType.TAIL, rasterizationStrategy.getBoard()[7][3].getCellType());

    }
}
