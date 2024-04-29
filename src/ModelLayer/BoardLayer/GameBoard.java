package ModelLayer.BoardLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import ModelLayer.SnakeLayer.Circunferencia;
import ModelLayer.SnakeLayer.Direction;
import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Retangulo;
import ModelLayer.SnakeLayer.Snake;
import ModelLayer.SnakeLayer.Triangulo;

public class GameBoard {
    private List<Obstacle> listOfObstacles; 
    private Food food;
    private int rows;
    private int columns;
    private Cell [][] board;
    private Snake snake;
    private FoodType foodType;
    private int foodDimension;
    private int obstaclesQuantity;
    private ObstacleType obstacleType;
    private Random random;

    /** Construtor para criar uma board no jogo
     * @param listofObstacles lista de obstáculos
     * @param foodType tipo de comida
     * @param snake cobra
     * @param rows linhas
     * @param columns colunas
     */
    public GameBoard (Snake snake, int columns, int rows, FoodType foodType, int foodDimension,int obstaclesQuantity, Ponto obstacleRotacionPoint, boolean isObstacleMovementAroundCenter ,boolean isObstacleDynamic, Random random) {
        if(columns <= 0 || rows <= 0) {
            throw new IllegalArgumentException("O número de linhas e colunas deve ser maior que zero.");
        }
        this.random = random;
        this.rows = rows;
        this.snake = snake;
        this.columns = columns;
        this.listOfObstacles = new ArrayList<>();
        this.board = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = new Cell();
            }
        }
        this.foodType = foodType;
        this.foodDimension = foodDimension;
        this.obstaclesQuantity = obstaclesQuantity;
        this.obstacleType = ObstacleType.values()[random.nextInt(Direction.values().length)];
        updateSnakeCells();
        generateObstacles(obstacleRotacionPoint,isObstacleDynamic,isObstacleMovementAroundCenter);
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

    /** Verifica se a comida esta contida na cobra
     * @return verdadeiro se estiver contido, falso se não
     * @throws CloneNotSupportedException
     */
    public boolean foodContainedInSnake() {
        if (food.foodContainedInHead(snake)) {
            removeFood();
            generateFood();
            return true; 
        }
        return false; 
    }

    public void removeFood(){
        this.food = null;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(board[i][j].getCellType() == CellType.FOOD) {
                    board[i][j].setCellType(CellType.EMPTY);
                }
            }
        }
    }

    public boolean foodIntersectObstacle() {
        for(int i = 0; i < this.listOfObstacles.size(); i++) {
            if(this.food.foodIntersectObstacle(this.listOfObstacles.get(i)))
                return true;
        }   
        return false;
    }

    /** Gera uma comida aleatória na board */
    public void generateFood() {
        boolean isEmpty = true;
        while (isEmpty) {
            int row = this.random.nextInt(rows - this.foodDimension - 1);
            int column = this.random.nextInt(columns - this.foodDimension - 1);
            boolean isAvailable = true;
            for (int i = row; i < row + this.foodDimension; i++ ) {
                for(int j = column; j < column + this.foodDimension; j++) {
                    if(board[i][j].getCellType() != CellType.EMPTY) {
                        isAvailable = false;
                        break;
                    }
                }
            }

            if(isAvailable) {
                for (int i = row; i < row + this.foodDimension; i++) {
                    for (int j = column; j < column + this.foodDimension; j++) {
                        board[i][j].setCellType(CellType.FOOD);
                    }
                }

                if(this.foodType == FoodType.CIRCLE) {
                    double centroX = row + (this.foodDimension - 1) / 2;
                    double centroY = column + (this.foodDimension - 1) / 2;
                    FactoryFood factory = new FactoryFood();
                    this.food = factory.createFood(new Circunferencia(new Ponto(centroX,centroY), (this.foodDimension/2.0)));
                }
                else if (this.foodType == FoodType.SQUARE) {
                    List<Ponto> pontos = new ArrayList<>();
                    pontos = createSquarePoints(column, row, this.foodDimension);
                    FactoryFood factory = new FactoryFood();
                    this.food = factory.createFood(new Quadrado(pontos));
                }
                isEmpty = false;
            }
        }
    }

    /** Gera um obstáculo aleatório na board */
    public void generateObstacles(Ponto rotacionPoint, boolean isDynamic, boolean isMovementAroundCenter) {
        int obstacleSize = this.snake.getArestaHeadLength();
        for(int w = 0; w < this.obstaclesQuantity; w++) {
            boolean isEmpty = true; 
            while (isEmpty) {
                int row = this.random.nextInt(rows - obstacleSize - 1); 
                int column = this.random.nextInt(columns - obstacleSize - 1);
                boolean isAvailable = true;
                for (int i = row; i < row + obstacleSize; i++) {
                    for (int j = column; j < column + obstacleSize; j++) {
                        if (board[i][j].getCellType() != CellType.EMPTY) {
                            isAvailable = false;
                            break;
                        }
                    }
                }
                if (isAvailable) {
                    for (int i = row; i < row + obstacleSize; i++) {
                        for (int j = column; j < column + obstacleSize; j++) {
                            board[i][j].setCellType(CellType.OBSTACLE);
                        }
                    }

                    List<Ponto> pontos = new ArrayList<>();
                    switch (obstacleType) {
                        case POLYGON:
                            pontos = createPolygonPoints(column, row, obstacleSize);
                            this.listOfObstacles.add(new Obstacle(new Poligono(pontos), rotacionPoint ,isDynamic, isMovementAroundCenter));
                            isEmpty = false;
                            break;
                        case SQUARE:
                            pontos = createSquarePoints(column, row, obstacleSize); 
                            this.listOfObstacles.add(new Obstacle(new Quadrado(pontos), rotacionPoint ,isDynamic, isMovementAroundCenter));
                            isEmpty = false;
                            break;
                        case RECTANGLE:
                            pontos = createRectanglePoints(column, row, obstacleSize);
                            this.listOfObstacles.add(new Obstacle(new Retangulo(pontos), rotacionPoint ,isDynamic, isMovementAroundCenter));
                            isEmpty = false;
                            break;
                        case TRIANGLE:
                            pontos = createTrianglePoints(column, row, obstacleSize);
                            this.listOfObstacles.add(new Obstacle(new Triangulo(pontos), rotacionPoint ,isDynamic, isMovementAroundCenter));
                            isEmpty = false;
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }
    
    public void rotateObstacles() {
        for(int i = 0; i < this.listOfObstacles.size(); i++) {
            this.listOfObstacles.get(i).rotateObstacle();
        }
    }
    public void updateSnakeCells() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j].getCellType() == CellType.HEAD || board[i][j].getCellType() == CellType.TAIL) {
                    board[i][j].setCellType(CellType.EMPTY);
                }
            }
        }
    
        for (int i = 1; i < snake.getBody().size(); i++) {
            int maxX = Integer.MIN_VALUE;
            int maxY = Integer.MIN_VALUE;
            int minX = Integer.MAX_VALUE;
            int minY = Integer.MAX_VALUE;
            for(int x = 0; x < snake.getBody().get(i).getPontos().size(); x++) {
                maxX = (int) Math.max(maxX, snake.getBody().get(i).getPontos().get(x).getX());
                maxY = (int) Math.max(maxY, snake.getBody().get(i).getPontos().get(x).getY());
                minX = (int) Math.min(minX, snake.getBody().get(i).getPontos().get(x).getX());
                minY = (int) Math.min(minY, snake.getBody().get(i).getPontos().get(x).getY());
            }
            for(int w = minY; w < maxY; w++) {
                for(int j = minX ; j < maxX; j++) {
                    board[w][j].setCellType(CellType.TAIL);
                }
            }
        }
    
        Quadrado head = snake.getHead();
        int maxHeadX = Integer.MIN_VALUE;
        int maxHeadY = Integer.MIN_VALUE;
        int minHeadX = Integer.MAX_VALUE;
        int minHeadY = Integer.MAX_VALUE;
        for(int x = 0; x < head.getPontos().size(); x++) {
            maxHeadX = (int) Math.max(maxHeadX, head.getPontos().get(x).getX());
            maxHeadY = (int) Math.max(maxHeadY, head.getPontos().get(x).getY());
            minHeadX = (int) Math.min(minHeadX, head.getPontos().get(x).getX());
            minHeadY = (int) Math.min(minHeadY, head.getPontos().get(x).getY());
        }
        for(int w = minHeadY; w < maxHeadY; w++) {
            for(int j = minHeadX; j < maxHeadX; j++) {
                board[w][j].setCellType(CellType.HEAD);
            }
        }
    }
    
    

    private List<Ponto> createPolygonPoints (int column, int row, int size) {
        List<Ponto> pontos = new ArrayList<>();
        int sides = this.random.nextInt(size) + 3;
        double angleIncrement = 2 * Math.PI / sides;
        double radius = size / 2.0;

        for (int i = 0; i < sides; i++) {
            double angle = i * angleIncrement;
            double x = column + radius * Math.cos(angle);
            double y = row + radius * Math.sin(angle);
            pontos.add(new Ponto(x, y));
        }
    
        return pontos;
    }

    private List<Ponto> createRectanglePoints(int column, int row, int size) {
        List<Ponto> pontos = new ArrayList<>();
        int width = this.random.nextInt(size-1) + 1;
        int height = this.random.nextInt(size-1) + 1;
        if(width == height)
            width = this.random.nextInt(size-1) + 1;
            height = this.random.nextInt(size-1) + 1;
        pontos.add(new Ponto(column, row));
        pontos.add(new Ponto(column, row + height));
        pontos.add(new Ponto(column + width, row + height));
        pontos.add(new Ponto(column + width, row));
        return pontos;
    }

    private List<Ponto> createTrianglePoints(int column, int row, int size) {
        List<Ponto> pontos = new ArrayList<>();
        double halfSize = size / 2.0;
        pontos.add(new Ponto(column, row + size)); 
        pontos.add(new Ponto(column + halfSize, row)); 
        pontos.add(new Ponto(column + size, row + size)); 
        return pontos;
    }

    private List<Ponto> createSquarePoints(int column, int row, int size) {
        List<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(column, row));
        pontos.add(new Ponto(column + size, row));
        pontos.add(new Ponto(column + size, row + size));
        pontos.add(new Ponto(column, row + size));

        return pontos;
    }

    @Override
    public String toString() {
        String result = "";
        for(int i = this.rows - 1; i >= 0; i--) {
            for(int j = 0; j < this.columns; j++) {
                result+= board[i][j].toString() + " ";
            }
            result += "\n";
        }
        return result;
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

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public int getFoodDimension() {
        return foodDimension;
    }

    public void setFoodDimension(int foodDimension) {
        this.foodDimension = foodDimension;
    }

    public int getObstaclesQuantity() {
        return obstaclesQuantity;
    }

    public void setObstaclesQuantity(int obstaclesQuantity) {
        this.obstaclesQuantity = obstaclesQuantity;
    }
}
