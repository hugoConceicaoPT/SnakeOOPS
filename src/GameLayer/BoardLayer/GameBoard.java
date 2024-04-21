package GameLayer.BoardLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import GameLayer.SnakeLayer.Circunferencia;
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
    private boolean isFoodCircle;

    /** Construtor para criar uma board no jogo
     * @param listofObstacles lista de obstáculos
     * @param foodType tipo de comida
     * @param snake cobra
     * @param rows linhas
     * @param columns colunas
     */
    public GameBoard (Snake snake, int columns, int rows, boolean isFoodCircle) {
        this.rows = rows;
        this.score = new Score(0);
        this.snake = snake;
        this.columns = columns;
        this.isFoodCircle = isFoodCircle;
        this.board = new Cell[columns][rows];
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
            if (listOfObstacles.get(i).obstacleIntersect(snake)) {
                return true;
            }
        }
        if(snake.collidedWithHerself())
            return true;
        if(snake.getHead().getPontos().get(0).getX() < 0 || snake.getHead().getPontos().get(0).getX() > columns 
            || snake.getHead().getPontos().get(0).getY() < 0 || snake.getHead().getPontos().get(0).getY() > rows)
            return true;
        return false;
    }

    /** Verifica se a comida esta contida na cobra
     * @return verdadeiro se estiver contido, falso se não
     * @throws CloneNotSupportedException
     */
    public boolean foodContainedInSnake() throws CloneNotSupportedException {
        if (food.foodIntersetaHead(snake)) {
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
                if(isFoodCircle) {
                    Ponto raio = new Ponto((column+(column-1)) / 2, row);
                    Ponto centro = new Ponto((column+(column-1)) / 2, (row+row-1) / 2);
                    Circunferencia circunferencia = new Circunferencia(centro, centro.dist(raio));
                    this.food = new FoodCircle(circunferencia);
                }
                else {
                    List<Ponto> pontos = new ArrayList<>();
                    pontos.add(new Ponto(row,column));
                    pontos.add(new Ponto(row,column+1));
                    pontos.add(new Ponto(row+1,column+1));
                    pontos.add(new Ponto(row+1,column));
                    this.food = new FoodSquare(new Quadrado(pontos));
                }
                isEmpty = false;
            }
        }
    }

    /** Gera um obstáculo aleatório na board */
    public void generateObstacles() {
        Random random = new Random();
        boolean isEmpty = true;
        while (isEmpty) {
            int row = random.nextInt(rows);
            int column = random.nextInt(columns);
            if (board[row][column].getCellType() == CellType.EMPTY) {
                Cell cellObstacle = new Cell(row, column);
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
