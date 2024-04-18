package GameLayer.BoardLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import GameLayer.SnakeLayer.Ponto;
import GameLayer.SnakeLayer.Quadrado;
import GameLayer.SnakeLayer.Score;
import GameLayer.SnakeLayer.Snake;

public class GameBoard {
    private List<Obstacle> listOfObstacles; 
    private Food food;
    private int rows;
    private int columns;
    private Cell [][] board;
    private Snake snake;
    private Score score;

    /** Construtor para criar uma board no jogo
     * @param listofObstacles lista de obstáculos
     * @param foodType tipo de comida
     * @param snake cobra
     * @param rows linhas
     * @param columns colunas
     */
    public GameBoard (Snake snake, int rows, int columns) {
        this.rows = rows;
        this.score = new Score(0);
        this.snake = snake;
        this.columns = columns;
        this.board = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = new Cell(i,j);
            }
        }
        generateObstacles();
        generateFood();
    }

    /** Verifica se a cobra colida com as paredes da board ou com o obstáculo
     * @return
     */
    public boolean snakeCollided() {
        for (int i = 0; i < listOfObstacles.size(); i++) {
            if (listOfObstacles.get(i).osbtacleIntersect(snake)) {
                return true;
            }
        }
        return false;
    }

    /** Verifica se a comida esta contida na cobra
     * @return verdadeiro se estiver contido, falso se não
     * @throws CloneNotSupportedException
     */
    public boolean foodContainedInSnake() throws CloneNotSupportedException {
        if (food.FoodIntersetaHead(snake)) {
            snake.increaseSize();
            score.increaseScore();
            return true; 
        }
        return false; 
    }

    /** Gera uma comida aleatória na board */
    public void generateFood() {
        Random random = new Random();
        boolean isEmpty = true;
        while (isEmpty) {
            int row = random.nextInt(rows);
            int column = random.nextInt(columns);
            if (board[row][column].getCellType() == CellType.EMPTY) {
                Cell cellFood = new Cell(row, column);
                cellFood.setCellType(CellType.FOOD);
                List<Ponto> pontos = new ArrayList<>();
                pontos.add(new Ponto(row,column));
                pontos.add(new Ponto(row,column+1));
                pontos.add(new Ponto(row+1,column+1));
                pontos.add(new Ponto(row+1,column));
                FoodSquare food = new FoodSquare(new Quadrado(pontos));
                isEmpty = false;
            }
        }
    }

    /** Gera um obstáculo aleatório na board */
<<<<<<< Updated upstream
    public void generateObstacle() {
        Random random = new Random();
        boolean isEmpty = true;
        while(isEmpty){
            int row = random.nextInt(rows);
            int column = random.nextInt(columns);
            Cell cellObstacle = new Cell(row, column);
            if (cellObstacle.getCellType() == CellType.EMPTY) {
=======
    public void generateObstacles() {
        Random random = new Random();
        boolean isEmpty = true;
        while (isEmpty) {
            int row = random.nextInt(rows);
            int column = random.nextInt(columns);
            if (board[row][column].getCellType() == CellType.EMPTY) {
                Cell cellObstacle = new Cell(row, column);
>>>>>>> Stashed changes
                cellObstacle.setCellType(CellType.OBSTACLE);
                isEmpty = false;
            }
        }
    }

    public List<Obstacle> getListOfObstacles() {
        return listOfObstacles;
    }

    public void setListOfObstacles(List<Obstacle> listOfObstacles) {
        this.listOfObstacles = listOfObstacles;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
