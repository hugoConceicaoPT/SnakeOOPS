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
import ViewLayer.ContourTextualRasterization;


/** Classe que representa uma classe teste para testar as funcionalidades da classe ContourRasterization
    Responsabilidade: Testar as funcionalidades da classe ContourRasterization
    @version 1.0 12/05/2024
    @author Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class ContourTextualRasterizationTest {
    
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
        List<Integer> angle = new ArrayList<>();
        angle.add(0);
        angle.add(0);
        GameBoard gameBoard = new GameBoard(snake, 20, 10,FoodType.SQUARE,1,2,rotacionPoint,angle,false,random);
        ContourTextualRasterization rasterizationStrategy = new ContourTextualRasterization(gameBoard);
        rasterizationStrategy.updateSnake();
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
        List<Integer> angle = new ArrayList<>();
        angle.add(0);
        angle.add(0);
        GameBoard gameBoard = new GameBoard(snake, 30, 20,FoodType.SQUARE,1,2,rotacionPoint,angle,false,random);
        ContourTextualRasterization rasterizationStrategy = new ContourTextualRasterization(gameBoard);
        rasterizationStrategy.updateObstacles();
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[15][20].getCellType());
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[15][21].getCellType());
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[15][22].getCellType());
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[16][20].getCellType());
        assertEquals(CellType.EMPTY, rasterizationStrategy.getBoard()[16][22].getCellType());
        assertEquals(CellType.EMPTY, rasterizationStrategy.getBoard()[16][21].getCellType());
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[17][20].getCellType());
        assertEquals(CellType.EMPTY, rasterizationStrategy.getBoard()[17][22].getCellType());
        assertEquals(CellType.EMPTY, rasterizationStrategy.getBoard()[17][21].getCellType());
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[18][19].getCellType());
        assertEquals(CellType.EMPTY, rasterizationStrategy.getBoard()[18][20].getCellType());
        assertEquals(CellType.EMPTY, rasterizationStrategy.getBoard()[18][21].getCellType());
        assertEquals(CellType.EMPTY, rasterizationStrategy.getBoard()[18][22].getCellType());
    }
    @Test
    public void updateFoodCellsTest(){
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
        List<Integer> angle = new ArrayList<>();
        angle.add(0);
        GameBoard gameBoard = new GameBoard(snake, 20, 10,FoodType.SQUARE,2,0,rotacionPoint,angle,false,random);
        ContourTextualRasterization rasterizationStrategy = new ContourTextualRasterization(gameBoard);
        rasterizationStrategy.updateFood();
        assertEquals(CellType.FOOD, rasterizationStrategy.getBoard()[1][5].getCellType());
        assertEquals(CellType.FOOD, rasterizationStrategy.getBoard()[1][6].getCellType());
        assertEquals(CellType.FOOD, rasterizationStrategy.getBoard()[1][7].getCellType());
        assertEquals(CellType.FOOD, rasterizationStrategy.getBoard()[1][8].getCellType());
        assertEquals(CellType.FOOD, rasterizationStrategy.getBoard()[2][5].getCellType());
        assertEquals(CellType.EMPTY, rasterizationStrategy.getBoard()[2][6].getCellType());
        assertEquals(CellType.FOOD, rasterizationStrategy.getBoard()[3][7].getCellType());
        assertEquals(CellType.FOOD, rasterizationStrategy.getBoard()[4][8].getCellType());
        assertEquals(CellType.FOOD, rasterizationStrategy.getBoard()[3][5].getCellType());
    }
}
