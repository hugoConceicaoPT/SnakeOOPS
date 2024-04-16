package GameLayer.BoardLayer;

import java.util.List;
import java.util.Random;

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
            Cell cellFood = new Cell(row, column);
            if (cellFood.getCellType() == CellType.EMPTY) {
                cellFood.setCellType(CellType.FOOD);
                isEmpty = false;
            }
        }
    }

    /** Gera um obstáculo aleatório na board */
    public void generateObstacle() {
        Random random = new Random();
        boolean isEmpty = true;
        while(isEmpty){
            int row = random.nextInt(rows);
            int column = random.nextInt(columns);
            Cell cellObstacle = new Cell(row, column);
            if (cellObstacle.getCellType() == CellType.EMPTY) {
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

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
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
