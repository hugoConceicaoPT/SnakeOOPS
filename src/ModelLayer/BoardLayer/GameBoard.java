package ModelLayer.BoardLayer;

import java.util.ArrayList;
import java.util.Arrays;
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
    public GameBoard (Snake snake, int widthBoard, int heightBoard, FoodType foodType, int foodDimension,int obstaclesQuantity, List<Ponto<? extends Number>> listObstacleRotacionPoint,boolean isObstacleDynamic, Random random) {
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
        generateObstacles(listObstacleRotacionPoint,isObstacleDynamic);
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
        if(this.snake.getHead().getMinX() < 0 || this.snake.getHead().getMinY() < 0 
            || this.snake.getHead().getMaxX() > this.widthBoard || this.snake.getHead().getMaxY() > this.heightBoard)
            return true;
        return false;
    }

    /** Verifica se a comida esta contida na cobra
     * @return verdadeiro se estiver contido, falso se não
     * @throws CloneNotSupportedException
     */

    public boolean foodContainedInSnake() throws CloneNotSupportedException {
        if (food.foodContainedInSnake(snake)) {
            removeFood();
            generateFood();
            return true; 
        }
        return false; 
    }

    public boolean foodContainedInObstacle() {
        for(int i = 0; i < this.listOfObstacles.size(); i++) {
            if(food.foodContainedObstacle(this.listOfObstacles.get(i)))
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

    public boolean foodIntersectSnake() {
        return food.foodIntersectSnake(snake);
    }

    /** Gera uma comida aleatória na board */
    public void generateFood() {
        boolean isEmpty = true;
        int maxIterations = this.widthBoard * this.heightBoard / this.foodDimension;
        int iterations = 0;
        while (isEmpty || iterations < maxIterations) {
            int y = 0;
            int x = 0;
            if(this.widthBoard % this.snake.getArestaHeadLength() == 0 && this.snake.getHead().getMaxX() % this.snake.getArestaHeadLength() == 0) 
                x = this.random.nextInt(this.widthBoard - this.foodDimension);
            else
                x = this.random.nextInt(this.widthBoard - this.foodDimension - 1) + this.foodDimension;
            if(this.heightBoard % this.snake.getHead().getMaxY() == 0 && this.snake.getHead().getMaxY() % this.snake.getArestaHeadLength() == 0)
                y = this.random.nextInt(this.heightBoard - this.foodDimension);
            else
                y = this.random.nextInt(this.heightBoard - this.foodDimension - 1) + this.foodDimension;
            if(this.foodType == FoodType.CIRCLE) {
                double centroY = y + (this.foodDimension/2.0);
                double centroX = x + (this.foodDimension/2.0);
                FactoryFood factory = new FactoryFood();
                this.food = factory.createFood(new Circunferencia(new Ponto<Double>(centroX,centroY), (this.foodDimension/2.0)));
            }
            else if (this.foodType == FoodType.SQUARE) {
                List<Ponto<? extends Number>> pontos = new ArrayList<>();
                pontos = createSquarePoints(x, y, this.foodDimension);
                FactoryFood factory = new FactoryFood();
                this.food = factory.createFood(new Quadrado(pontos));
            }
            if(!foodIntersectObstacle() && !foodContainedInObstacle() && !foodIntersectSnake() && !this.food.foodContainedInSnake(snake)) {
                boolean isReachable = foodIsInReachableArea();
                if(isReachable) isEmpty = false;
                else { 
                    this.food = null;
                    isEmpty = true;
                }
            }
            iterations++;
        }
        if(iterations == maxIterations) {
            this.food = null;
            return;
        }
    }

    private boolean foodIsInReachableArea() {
        if (food == null) {
            return false; 
        }
        Snake snakeClone = null;
        try {
            snakeClone = (Snake) this.snake.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        double foodX = this.food.getCentroide().getX().doubleValue();
        double foodY = this.food.getCentroide().getY().doubleValue();
        boolean isReachable = false;
        int maxIterations = 50;
        int iterations = 0;
        while(iterations < maxIterations) {
            double headX = snakeClone.getHead().getCentroide().getX();
            double headY = snakeClone.getHead().getCentroide().getY();
            Direction nextDirection = calculateDirection(headX, headY, foodX , foodY, snakeClone);
            if(this.food.foodContainedInSnake(snakeClone) && snakeClone.getHead().getMinX() >= 0 
                && snakeClone.getHead().getMinY() >= 0 && snakeClone.getHead().getMaxX() < this.widthBoard && snakeClone.getHead().getMaxY() < this.heightBoard) {
                for(int i = 0; i < this.listOfObstacles.size(); i++ ){
                    if(!this.listOfObstacles.get(i).obstacleContained(snakeClone) && !this.listOfObstacles.get(i).obstacleIntersect(snakeClone)) {
                        isReachable = true;
                    }
                    else {
                        isReachable = false;
                        break;
                    }
                }
                if(isReachable)
                    break;
            }
            else if(this.food.foodIntersectSnake(snakeClone)) {
                isReachable = false;
                break;
            }
            snakeClone.setNextDirection(nextDirection);
            snakeClone.move();
            iterations++;
        }
        snakeClone = null;
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
    public void generateObstacles(List<Ponto<? extends Number>> listRotacionPoint, boolean isDynamic) {
        for(int w = 0; w < this.obstaclesQuantity; w++) {
            int obstacleSize = random.nextInt(this.snake.getArestaHeadLength()) + 1;
            boolean isEmpty = true; 
            this.obstacleType = ObstacleType.values()[random.nextInt(Direction.values().length)];
            while (isEmpty) {
                int y = this.random.nextInt(this.heightBoard - obstacleSize); 
                int x = this.random.nextInt(this.widthBoard - obstacleSize);
                List<Ponto<? extends Number>> pontos = new ArrayList<>();
                switch (obstacleType) {
                    case POLYGON:
                        if(listRotacionPoint.size() == 0) {
                            pontos = createPolygonPoints(x, y, obstacleSize);
                            this.listOfObstacles.add(new Obstacle(new Poligono(pontos),null,isDynamic));
                            break;
                        }
                        pontos = createPolygonPoints(x, y, obstacleSize);
                        this.listOfObstacles.add(new Obstacle(new Poligono(pontos), listRotacionPoint.get(w) ,isDynamic));
                        break;
                    case SQUARE:
                        if(listRotacionPoint.size() == 0) {
                            pontos = createSquarePoints(x, y, obstacleSize); 
                            this.listOfObstacles.add(new Obstacle(new Quadrado(pontos), null,isDynamic));
                            break;
                        }
                        pontos = createSquarePoints(x, y, obstacleSize); 
                        this.listOfObstacles.add(new Obstacle(new Quadrado(pontos), listRotacionPoint.get(w),isDynamic));
                        break;
                    case RECTANGLE:
                        if(listRotacionPoint.size() == 0 ) {
                            pontos = createRectanglePoints(x, y, obstacleSize);
                        this.listOfObstacles.add(new Obstacle(new Retangulo(pontos),null,isDynamic));
                        break;
                        }
                        pontos = createRectanglePoints(x, y, obstacleSize);
                        this.listOfObstacles.add(new Obstacle(new Retangulo(pontos), listRotacionPoint.get(w) ,isDynamic));
                        break;
                    case TRIANGLE:
                        if(listRotacionPoint.size() == 0) {
                            pontos = createRectanglePoints(x, y, obstacleSize);
                            this.listOfObstacles.add(new Obstacle(new Retangulo(pontos), null,isDynamic));
                            break;
                        }
                        pontos = createTrianglePoints(x, y, obstacleSize);
                        this.listOfObstacles.add(new Obstacle(new Triangulo(pontos), listRotacionPoint.get(w) ,isDynamic));
                        break;
                    default:
                        break;
                }
                boolean isObstacleIntersectedWithAnotherObstacle = false;
                for(int i = 0; i < this.listOfObstacles.size() - 1; i++) {
                    if(this.listOfObstacles.get(i).getPoligono().interseta(this.listOfObstacles.get(this.listOfObstacles.size()-1).getPoligono()) && this.listOfObstacles.size() > 1) {
                        this.listOfObstacles.remove(this.listOfObstacles.size() - 1);
                        isEmpty = true;
                        isObstacleIntersectedWithAnotherObstacle = true;
                        break;
                    }
                }
                if(!isObstacleIntersectedWithAnotherObstacle) {
                    if(!snakeIntersectsObstacle() && !obstacleContainedInSnake()) {
                        isEmpty = false;
                    }
                    else {
                        this.listOfObstacles.remove(this.listOfObstacles.size() - 1);
                        isEmpty = true;
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

    private List<Ponto<? extends Number>> createPolygonPoints (int x, int y, int size) {
        List<Ponto<? extends Number>> pontos = new ArrayList<>();
        List<String> polygonsTypes = Arrays.asList("L", "T", "S");
        String type = polygonsTypes.get(random.nextInt(polygonsTypes.size()));
        switch (type) {
            case "L": 
                pontos.add(new Ponto<Integer>(x, y));
                pontos.add(new Ponto<Integer>(x, y - (size+1)));
                pontos.add(new Ponto<Integer>(x + (size+1), y - (size+1)));
                pontos.add(new Ponto<Integer>(x + (size+1), y - (size)));
                pontos.add(new Ponto<Integer>(x + size, y - size));
                pontos.add(new Ponto<Integer>(x + size, y));
                break;
            case "T": 
                pontos.add(new Ponto<Integer>(x, y));
                pontos.add(new Ponto<Integer>(x, y + size));
                pontos.add(new Ponto<Integer>(x + size, y + size));
                pontos.add(new Ponto<Integer>(x + size, y + (size+1)));
                pontos.add(new Ponto<Integer>(x - (size+1), y + (size+1)));
                pontos.add(new Ponto<Integer>(x - (size+1), y + size));
                pontos.add(new Ponto<Integer>(x - size, y + size));
                pontos.add(new Ponto<Integer>(x - size, y));
                break;
            case "S": 
                pontos.add(new Ponto<Integer>(x, y));
                pontos.add(new Ponto<Integer>(x, y + size));
                pontos.add(new Ponto<Integer>(x + size, y + size));
                pontos.add(new Ponto<Integer>(x + size, y + (size+1)));
                pontos.add(new Ponto<Integer>(x - size, y + (size+1)));
                pontos.add(new Ponto<Integer>(x - size, y + size));
                pontos.add(new Ponto<Integer>(x - (size+1), y + size));
                pontos.add(new Ponto<Integer>(x - (size+1), y));
                break;
            default:
                break;
        }

        return pontos;
    }

    private List<Ponto<? extends Number>> createRectanglePoints(int x, int y, int size) {
        List<Ponto<? extends Number>> pontos = new ArrayList<>();
        int width = this.random.nextInt(size) + 1;
        int height = this.random.nextInt(size) + 1;
        pontos.add(new Ponto<Integer>(x, y));
        pontos.add(new Ponto<Integer>(x, y + height));
        pontos.add(new Ponto<Integer>(x + width,y + height));
        pontos.add(new Ponto<Integer>(x+ width,y));
        return pontos;
    }

    private List<Ponto<? extends Number>> createTrianglePoints(int x, int y, int size) {
        List<Ponto<? extends Number>> pontos = new ArrayList<>();
        pontos.add(new Ponto<Integer>(x, y)); 
        pontos.add(new Ponto<Integer>(x + size,y)); 
        pontos.add(new Ponto<Integer>(x + size,y - size)); 
        return pontos;
    }

    private List<Ponto<? extends Number>> createSquarePoints(int x, int y, int size) {
        List<Ponto<? extends Number>> pontos = new ArrayList<>();
        pontos.add(new Ponto<Integer>(x, y));
        pontos.add(new Ponto<Integer>(x + size,y));
        pontos.add(new Ponto<Integer>(x + size, y + size));
        pontos.add(new Ponto<Integer>(x, y + size));

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
