package GameLayer.BoardLayer;

import java.util.List;
import java.util.Random;

import GameLayer.SnakeLayer.Retangulo;
import GameLayer.SnakeLayer.Score;
import GameLayer.SnakeLayer.Snake;

public class GameBoard {
    private List<Obstacle> listOfObstacles; 
    private Food food;
    private int rows;
    private int columns;
    private String foodType;
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
    public GameBoard (List<Obstacle> listofObstacles,String foodType,Snake snake, int rows, int columns) {
        this.listOfObstacles = listofObstacles;
        this.foodType = foodType;
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
    }

    /** Verifica se a cobra colida com as paredes da board ou com o obstáculo
     * @return
     */
    public boolean snakeCollided() {return true;}

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
            Cell cellFood = new Cell(row, column);
            if (cellFood.getCellType() == CellType.EMPTY) {
                cellFood.setCellType(CellType.FOOD);
                isEmpty = false;
            }
        }
    }

    /** Gera um obstáculo aleatório na board */
    public void generateObstacle() {}
}
