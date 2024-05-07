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
    private int heightBoard;
    private int widthBoard;
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
    public GameBoard (Snake snake, int widthBoard, int heightBoard, FoodType foodType, int foodDimension,int obstaclesQuantity, Ponto obstacleRotacionPoint,boolean isObstacleDynamic, Random random) throws CloneNotSupportedException {
        if(widthBoard <= 0 || heightBoard <= 0) {
            throw new IllegalArgumentException("O número de linhas e colunas deve ser maior que zero.");
        }
        this.random = random;
        this.widthBoard = widthBoard;
        this.snake = snake;
        this.heightBoard = heightBoard;
        this.listOfObstacles = new ArrayList<>();
        this.foodType = foodType;
        this.foodDimension = foodDimension;
        this.obstaclesQuantity = obstaclesQuantity;
        this.obstacleType = ObstacleType.values()[random.nextInt(Direction.values().length)];
        generateObstacles(obstacleRotacionPoint,isObstacleDynamic);
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
            if(snake.getBody().get(i).getPontos().get(j).getX() < 0 || snake.getBody().get(i).getPontos().get(j).getX() > this.widthBoard 
            || snake.getBody().get(i).getPontos().get(j).getY() < 0 || snake.getBody().get(i).getPontos().get(j).getY() > this.heightBoard)
               return true;
        }
        return false;
    }

    /** Verifica se a comida esta contida na cobra
     * @return verdadeiro se estiver contido, falso se não
     * @throws CloneNotSupportedException
     */
    public boolean foodContainedInSnakeHead() throws CloneNotSupportedException {
        if (food.foodContainedInSnakeHead(snake)) {
            removeFood();
            generateFood();
            return true; 
        }
        return false; 
    }

    public boolean foodContainedInSnake() throws CloneNotSupportedException {
        if (food.foodContainedInSnake(snake)) {
            removeFood();
            generateFood();
            return true; 
        }
        return false; 
    }


    public void removeFood(){
        this.food = null;
    }

    public boolean foodIntersectObstacle() {
        for(int i = 0; i < this.listOfObstacles.size(); i++) {
            if(this.food.foodIntersectObstacle(this.listOfObstacles.get(i)))
                return true;
        }   
        return false;
    }

    /** Gera uma comida aleatória na board */
    public void generateFood() throws CloneNotSupportedException {
        boolean isEmpty = true;
        while (isEmpty) {
            int y = 0;
            int x = 0;
            if(this.widthBoard % this.snake.getArestaHeadLength() == 0 && this.snake.getHead().getMaxX() % this.snake.getArestaHeadLength() == 0) 
                x = this.random.nextInt(this.widthBoard - this.foodDimension);
            else
                x = this.random.nextInt(this.widthBoard - this.foodDimension - 1) + 1;
            if(this.heightBoard % this.snake.getHead().getMaxY() == 0 && this.snake.getHead().getMaxY() % this.snake.getArestaHeadLength() == 0)
                y = this.random.nextInt(this.widthBoard - this.foodDimension);
            else
                y = this.random.nextInt(this.widthBoard - this.foodDimension - 1) + 1;
            if(this.foodType == FoodType.CIRCLE) {
                double centroY = y + (this.foodDimension/2.0);
                double centroX = x + (this.foodDimension/2.0);
                FactoryFood factory = new FactoryFood();
                this.food = factory.createFood(new Circunferencia(new Ponto(centroX,centroY), (this.foodDimension/2.0)));
            }
            else if (this.foodType == FoodType.SQUARE) {
                List<Ponto> pontos = new ArrayList<>();
                pontos = createSquarePoints(x, y, this.foodDimension);
                FactoryFood factory = new FactoryFood();
                this.food = factory.createFood(new Quadrado(pontos));
            }
            if(foodIntersectObstacle() || foodContainedInSnakeHead() || foodContainedInSnake()) {
                this.food = null;
            }
            else {
                boolean isReachable = foodIsInReachableArea();
                if(isReachable) isEmpty = false;
                else this.food = null;
            }
        }
    }

    private boolean foodIsInReachableArea() throws CloneNotSupportedException {
        if (food == null) {
            return false; 
        }
    
        Snake snakeClone = (Snake) this.snake.clone();
    
        double foodX = this.food.getCentroide().getX();
        double foodY = this.food.getCentroide().getY();
        boolean isReachable = false;
        while(true) {
            double headX = snakeClone.getHead().getCentroide().getX();
            double headY = snakeClone.getHead().getCentroide().getY();
            Direction nextDirection = calculateDirection(headX, headY, foodX , foodY, snakeClone);
            if(this.food.foodContainedInSnakeHead(snakeClone)) {
                isReachable = true;
                break;
            }
            else if(this.food.foodIntersectSnake(snakeClone)) {
                isReachable = false;
                break;
            }
            snakeClone.setNextDirection(nextDirection);
            snakeClone.move();
        }
        return isReachable;
    }
    
    private Direction calculateDirection(double headX, double headY, double foodX, double foodY, Snake snake) {
        if (foodX < headX && snake.getCurrentDirection() != Direction.RIGHT)
            return Direction.LEFT;
        else if (foodX > headX && snake.getCurrentDirection() != Direction.LEFT)
            return Direction.RIGHT;
        else if (foodY < headY && snake.getCurrentDirection() != Direction.UP) 
            return Direction.DOWN;
        else
            return Direction.UP;
    }
    
    

    /** Gera um obstáculo aleatório na board */
    public void generateObstacles(Ponto rotacionPoint, boolean isDynamic) {
        int obstacleSize = random.nextInt(this.snake.getArestaHeadLength()) + 1;
        for(int w = 0; w < this.obstaclesQuantity; w++) {
            boolean isEmpty = true; 
            while (isEmpty) {
                int y = this.random.nextInt(this.heightBoard - obstacleSize); 
                int x = this.random.nextInt(this.widthBoard - obstacleSize);
                List<Ponto> pontos = new ArrayList<>();
                switch (obstacleType) {
                    case POLYGON:
                        pontos = createPolygonPoints(x, y, obstacleSize);
                        this.listOfObstacles.add(new Obstacle(new Poligono(pontos), rotacionPoint ,isDynamic));
                        isEmpty = false;
                        break;
                    case SQUARE:
                        pontos = createSquarePoints(x, y, obstacleSize); 
                        this.listOfObstacles.add(new Obstacle(new Quadrado(pontos), rotacionPoint ,isDynamic));
                        isEmpty = false;
                        break;
                    case RECTANGLE:
                        pontos = createRectanglePoints(x, y, obstacleSize);
                        this.listOfObstacles.add(new Obstacle(new Retangulo(pontos), rotacionPoint ,isDynamic));
                        isEmpty = false;
                        break;
                    case TRIANGLE:
                        pontos = createTrianglePoints(x, y, obstacleSize);
                        this.listOfObstacles.add(new Obstacle(new Triangulo(pontos), rotacionPoint ,isDynamic));
                        isEmpty = false;
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    public void rotateObstacles() {
        for(int i = 0; i < this.listOfObstacles.size(); i++) {
            this.listOfObstacles.get(i).rotateObstacle();
        }
    }


    private List<Ponto> createPolygonPoints (int x, int y, int size) {
        List<Ponto> pontos = new ArrayList<>();
        int sides = this.random.nextInt(size) + 3;
        double angleIncrement = 2 * Math.PI / sides;
        double radius = size / 2.0;

        for (int i = 0; i < sides; i++) {
            double angle = i * angleIncrement;
            double newX = x + radius * Math.cos(angle);
            double newY = y + radius * Math.sin(angle);
            pontos.add(new Ponto(newX, newY));
        }
    
        return pontos;
    }

    private List<Ponto> createRectanglePoints(int x, int y, int size) {
        List<Ponto> pontos = new ArrayList<>();
        int width = this.random.nextInt(size) + 1;
        int height = this.random.nextInt(size) + 1;
        while(width == height)
            width = this.random.nextInt(size) + 1;
            height = this.random.nextInt(size) + 1;
        pontos.add(new Ponto(x, y));
        pontos.add(new Ponto(x, y + height));
        pontos.add(new Ponto(x + width,y + height));
        pontos.add(new Ponto(x+ width,y));
        return pontos;
    }

    private List<Ponto> createTrianglePoints(int x, int y, int size) {
        List<Ponto> pontos = new ArrayList<>();
        double halfSize = size / 2.0;
        pontos.add(new Ponto(x, y+ size)); 
        pontos.add(new Ponto(x + halfSize,y)); 
        pontos.add(new Ponto(x + size,y + size)); 
        return pontos;
    }

    private List<Ponto> createSquarePoints(int x, int y, int size) {
        List<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(x, y));
        pontos.add(new Ponto(x + size,y));
        pontos.add(new Ponto(x + size, y + size));
        pontos.add(new Ponto(x, y + size));

        return pontos;
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

    public int getHeightBoard() {
        return heightBoard;
    }

    public void setHeightBoard(int heightBoard) {
        this.heightBoard = heightBoard;
    }

    public int getWidthBoard() {
        return widthBoard;
    }

    public void setWidthBoard(int widthBoard) {
        this.widthBoard = widthBoard;
    }
}
