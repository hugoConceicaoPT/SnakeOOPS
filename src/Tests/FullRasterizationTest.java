package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import ViewLayer.FullTextualRasterization;
import org.junit.Test;

import ViewLayer.CellType;
import ModelLayer.BoardLayer.FoodType;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.SnakeLayer.Direction;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Snake;


/** Classe que representa uma classe teste para testar as funcionalidades da classe FullRasterization
    Responsabilidade: Testar as funcionalidades da classe FullRasterization
    @version 1.0 12/05/2024
    @author Hugo Conceição, João Ventura, Eduarda Pereira
 */
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
        List<Integer> angle = new ArrayList<>();
        angle.add(0);
        angle.add(0);
        GameBoard gameBoard = new GameBoard(snake, 20, 10,FoodType.SQUARE,1,2,rotacionPoint,angle,false,random);
        FullTextualRasterization rasterizationStrategy = new FullTextualRasterization(gameBoard);
        rasterizationStrategy.updateSnake();
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
        List<Integer> angle = new ArrayList<>();
        angle.add(0);
        angle.add(0);
        GameBoard gameBoard = new GameBoard(snake, 10, 10,FoodType.SQUARE,1,2,rotacionPoint,angle,false,random);
        FullTextualRasterization rasterizationStrategy = new FullTextualRasterization(gameBoard);
        rasterizationStrategy.updateObstacles();
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[8][6].getCellType());
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[0][5].getCellType());
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[0][6].getCellType());
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[1][5].getCellType());
        assertEquals(CellType.OBSTACLE, rasterizationStrategy.getBoard()[1][6].getCellType());

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
        GameBoard gameBoard = new GameBoard(snake, 20, 10,FoodType.SQUARE,3,0,rotacionPoint,angle,false,random);
        FullTextualRasterization rasterizationStrategy = new FullTextualRasterization(gameBoard);
        rasterizationStrategy.updateFood();
        assertEquals(CellType.FOOD, rasterizationStrategy.getBoard()[1][5].getCellType());
        assertEquals(CellType.FOOD, rasterizationStrategy.getBoard()[1][6].getCellType());
        assertEquals(CellType.FOOD, rasterizationStrategy.getBoard()[1][7].getCellType());
        assertEquals(CellType.FOOD, rasterizationStrategy.getBoard()[1][8].getCellType());
        assertEquals(CellType.FOOD, rasterizationStrategy.getBoard()[2][5].getCellType());
        assertEquals(CellType.FOOD, rasterizationStrategy.getBoard()[2][6].getCellType());
        assertEquals(CellType.FOOD, rasterizationStrategy.getBoard()[3][7].getCellType());
        assertEquals(CellType.FOOD, rasterizationStrategy.getBoard()[4][8].getCellType());
        assertEquals(CellType.FOOD, rasterizationStrategy.getBoard()[3][5].getCellType());
    }
}
