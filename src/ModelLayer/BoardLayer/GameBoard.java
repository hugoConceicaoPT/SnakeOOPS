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
        for(int i = 0; i < snake.getBody().size(); i++) {
            for(int j = 0; j < snake.getBody().get(i).getPontos().size(); j++)
            if(snake.getBody().get(i).getPontos().get(j).getX() < 0 || snake.getBody().get(i).getPontos().get(j).getX() > columns 
            || snake.getBody().get(i).getPontos().get(j).getY() < 0 || snake.getBody().get(i).getPontos().get(j).getY() > rows)
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
            int row = 0;
            int column = 0;
            if(this.columns % this.snake.getArestaHeadLength() == 0 && this.snake.getHead().getMaxX() % this.snake.getArestaHeadLength() == 0) 
                column = this.random.nextInt(columns - this.foodDimension);
            else
                column = this.random.nextInt(columns - this.foodDimension - 1) + 1;
            if(this.rows % this.snake.getHead().getMaxY() == 0 && this.snake.getHead().getMaxY() % this.snake.getArestaHeadLength() == 0)
                row = this.random.nextInt(rows - this.foodDimension);
            else
                row = this.random.nextInt(rows - this.foodDimension - 1) + 1;
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
                boolean isReachable = foodIsInReachableArea(row, column);
                if(isReachable) {
                    for (int i = row; i < row + this.foodDimension; i++) {
                        for (int j = column; j < column + this.foodDimension; j++) {
                            board[i][j].setCellType(CellType.FOOD);
                        }
                    }
                    if(this.foodType == FoodType.CIRCLE) {
                        double centroY = row + (this.foodDimension/2.0);
                        double centroX = column + (this.foodDimension/2.0);
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
    }

    private boolean foodIsInReachableArea(int row, int column) {
        double headX = snake.getHead().getCentroide().getX();
        double headY = snake.getHead().getCentroide().getY();
    
        double foodCenterX = column + (foodDimension / 2.0);
        double foodCenterY = row + (foodDimension / 2.0);
    
        double maxDistanceX = snake.getArestaHeadLength();
        double maxDistanceY = snake.getArestaHeadLength();
    
        int stepsX = (int) Math.ceil(Math.abs(foodCenterX - headX) / maxDistanceX);
        int stepsY = (int) Math.ceil(Math.abs(foodCenterY - headY) / maxDistanceY);
    
        double currentX = headX;
        double currentY = headY;
        for (int i = 0; i < Math.max(stepsX, stepsY); i++) {
            if (i < stepsX && Math.abs(currentX - foodCenterX) > 1) {
                currentX += Math.signum(foodCenterX - currentX) * maxDistanceX;
            }
            else if (i < stepsY && Math.abs(currentY - foodCenterY) > 1) {
                currentY += Math.signum(foodCenterY - currentY) * maxDistanceY;
            }
    
            if (currentX >= column && currentX <= column + foodDimension &&
                currentY >= row && currentY <= row + foodDimension) {
                return true;
            }
        }
    
        return false;
    }
    

    /** Gera um obstáculo aleatório na board */
    public void generateObstacles(Ponto rotacionPoint, boolean isDynamic, boolean isMovementAroundCenter) {
        int obstacleSize = random.nextInt(this.snake.getArestaHeadLength()) + 1;
        for(int w = 0; w < this.obstaclesQuantity; w++) {
            boolean isEmpty = true; 
            while (isEmpty) {
                int row = this.random.nextInt(rows - obstacleSize); 
                int column = this.random.nextInt(columns - obstacleSize);
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

    public void updateObstacleCells() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j].getCellType() == CellType.OBSTACLE) {
                    board[i][j].setCellType(CellType.EMPTY);
                }
            }
        }   

        for(int i = 0; i < this.obstaclesQuantity; i++) {
            for(int w = (int) this.getListOfObstacles().get(i).getPoligono().getMinY(); w < (int) this.getListOfObstacles().get(i).getPoligono().getMaxY(); w++) {
                for(int j = (int) this.getListOfObstacles().get(i).getPoligono().getMinX() ; j < (int) this.getListOfObstacles().get(i).getPoligono().getMaxX(); j++) {
                    board[Math.abs(w) % this.rows][Math.abs(j) % this.columns].setCellType(CellType.OBSTACLE);
                }
            }
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
            for(int w = (int) this.snake.getBody().get(i).getMinY(); w < (int) this.snake.getBody().get(i).getMaxY(); w++) {
                for(int j = (int) this.snake.getBody().get(i).getMinX() ; j < (int) this.snake.getBody().get(i).getMaxX(); j++) {
                    board[w][j].setCellType(CellType.TAIL);
                }
            }
        }
    
        Quadrado head = snake.getHead();
        for(int w = (int) head.getMinY(); w < (int) head.getMaxY(); w++) {
            for(int j = (int) head.getMinX(); j < (int) head.getMaxX(); j++) {
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
        int width = this.random.nextInt(size) + 1;
        int height = this.random.nextInt(size) + 1;
        while(width == height)
            width = this.random.nextInt(size) + 1;
            height = this.random.nextInt(size) + 1;
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
