package ModelLayer.BoardLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ModelLayer.SnakeLayer.Circunferencia;
import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Score;
import ModelLayer.SnakeLayer.Snake;

public class GameBoard {
    private List<Obstacle> listOfObstacles; 
    private Food food;
    private int rows;
    private int columns;
    private Cell [][] board;
    private Snake snake;
    private Score score;
    private FoodType foodType;
    private ObstacleType obstacleType;
    private int foodSize;
    private int obstacleSize;
    private boolean isObstacleDynamic;
    private long seed;

    /** Construtor para criar uma board no jogo
     * @param listofObstacles lista de obstáculos
     * @param foodType tipo de comida
     * @param snake cobra
     * @param rows linhas
     * @param columns colunas
     */
    public GameBoard (Snake snake, int columns, int rows, FoodType foodType, int foodSize, int obstacleSize, ObstacleType obstacleType ,boolean isObstacleDynamic, long seed) {
        this.rows = rows;
        this.score = new Score(0);
        this.snake = snake;
        this.columns = columns;
        this.board = new Cell[columns][rows];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                board[i][j] = new Cell();
            }
        }
        this.foodType = foodType;
        this.foodSize = foodSize;
        this.obstacleSize = obstacleSize;
        this.obstacleType = obstacleType;
        this.isObstacleDynamic = isObstacleDynamic;
        this.seed = seed;
        generateObstacles();
        generateFood();
    }
    public boolean snakeIntersectsObstacle(){
        for (int i = 0; i < listOfObstacles.size(); i++) {
            if (listOfObstacles.get(i).obstacleIntersect(snake)) {
                return true;
            }
        }
        return false;  
    }   

    public boolean obstacleContainedInSnake() {
        for (int i = 0; i < listOfObstacles.size(); i++) {
            if (listOfObstacles.get(i).obstacleContained(snake)) {
                return true;
            }
        }
        return false; 
    }

    public boolean snakeLeftBoard() {
        for(int i = 0; i < snake.getHead().getPontos().size(); i++) {
            if(snake.getHead().getPontos().get(i).getX() < 0 || snake.getHead().getPontos().get(i).getX() > columns 
            || snake.getHead().getPontos().get(i).getY() < 0 || snake.getHead().getPontos().get(i).getY() > rows)
               return true;
        }
        return false;
    }

    /** Verifica se a cobra colida com as paredes da board, ou com o obstáculo, ou com ela própria
     * @return verdadeiro se colidir, falso se não
     */
    public boolean snakeCollided() {
        if(snakeIntersectsObstacle() || obstacleContainedInSnake() || this.snake.collidedWithHerself() || snakeLeftBoard() )
            return true;
        return false;
    }

    /** Verifica se a comida esta contida na cobra
     * @return verdadeiro se estiver contido, falso se não
     * @throws CloneNotSupportedException
     */
    public boolean foodContainedInSnake() throws CloneNotSupportedException {
        if (food.foodContainedInHead(snake)) {
            snake.increaseSize();
            score.increaseScore();
            removeFood();
            generateFood();
            return true; 
        }
        return false; 
    }

    public void removeFood(){
        this.food = null;
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if(board[i][j].getCellType() == CellType.FOOD) {
                    board[i][j].setCellType(CellType.EMPTY);
                }
            }
        }
    }

    /** Gera uma comida aleatória na board */
    public void generateFood() {
        Random random = new Random(this.seed);
        boolean isEmpty = true;
        while (isEmpty) {
            int row = random.nextInt(rows - (foodSize - 1));
            int column = random.nextInt(columns - (foodSize - 1));
            boolean isAvailable = true;
            for (int i = column; i < column + foodSize; i++ ) {
                for(int j = row; j < row + foodSize; j++) {
                    if(board[i][j].getCellType() != CellType.EMPTY) {
                        isAvailable = false;
                        break;
                    }
                }
            }

            if(isAvailable) {
                for (int i = column; i < column + foodSize; i++) {
                    for (int j = row; j < row + foodSize; j++) {
                        board[i][j].setCellType(CellType.FOOD);
                    }
                }

                if(this.foodType == FoodType.CIRCULO) {
                    double centroX = column + (foodSize - 1) / 2;
                    double centroY = row + (foodSize - 1) / 2;
                    FactoryFood factory = new FactoryFood();
                    this.food = factory.createFood(new Circunferencia(new Ponto(centroX,centroY), foodSize/2));
                }
                else if (this.foodType == FoodType.QUADRADO ) {
                    List<Ponto> pontos = new ArrayList<>();
                    for (int i = column; i < column + foodSize; i++) {
                        pontos.add(new Ponto(i, row));
                        pontos.add(new Ponto(i, row + foodSize));
                        pontos.add(new Ponto(column + foodSize, row + foodSize));
                        pontos.add(new Ponto(column + foodSize, row));
                    }
                    FactoryFood factory = new FactoryFood();
                    this.food = factory.createFood(new Quadrado(pontos));
                }
                isEmpty = false;
            }
        }
    }

    /** Gera um obstáculo aleatório na board */
    public void generateObstacles() {
        Random random = new Random(this.seed);
        boolean isEmpty = true;
        while (isEmpty) {
            int row = random.nextInt(rows - (obstacleSize - 1)); 
            int column = random.nextInt(columns - (obstacleSize - 1));
            boolean isAvailable = true;
            for (int i = column; i < column + obstacleSize; i++) {
                for (int j = row; j < row + obstacleSize; j++) {
                    if (board[i][j].getCellType() != CellType.EMPTY) {
                        isAvailable = false;
                        break;
                    }
                }
            }
            if (isAvailable) {
                for (int i = column; i < column + obstacleSize; i++) {
                    for (int j = row; j < row + obstacleSize; j++) {
                        board[i][j].setCellType(CellType.OBSTACLE);
                    }
                }

                List<Ponto> pontos = new ArrayList<>();
                for (int i = column; i < column + this.obstacleSize; i++) {
                    pontos.add(new Ponto(i, row));
                    pontos.add(new Ponto(i, row + this.obstacleSize));
                    pontos.add(new Ponto(column + this.obstacleSize, row + this.obstacleSize));
                    pontos.add(new Ponto(column + this.obstacleSize, row));
                }
                this.listOfObstacles.add(new Obstacle(new Poligono(pontos), this.isObstacleDynamic));
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
    public FoodType getFoodType() {
        return foodType;
    }
    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }
    public ObstacleType getObstacleType() {
        return obstacleType;
    }
    public void setObstacleType(ObstacleType obstacleType) {
        this.obstacleType = obstacleType;
    }
    public int getFoodSize() {
        return foodSize;
    }
    public void setFoodSize(int foodSize) {
        this.foodSize = foodSize;
    }
    public int getObstacleSize() {
        return obstacleSize;
    }
    public void setObstacleSize(int obstacleSize) {
        this.obstacleSize = obstacleSize;
    }
    public boolean isObstacleDynamic() {
        return isObstacleDynamic;
    }
    public void setObstacleDynamic(boolean isObstacleDynamic) {
        this.isObstacleDynamic = isObstacleDynamic;
    }
    public long getSeed() {
        return seed;
    }
    public void setSeed(long seed) {
        this.seed = seed;
    }
}
