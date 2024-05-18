package ModelLayer.BoardLayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import ModelLayer.SnakeLayer.Direction;
import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Retangulo;
import ModelLayer.SnakeLayer.Snake;
import ModelLayer.SnakeLayer.Triangulo;

/**
 * Classe que representa o tabuleiro de jogo.
 * Responsabilidade: Gerenciar os elementos de jogo como comida, obstáculos e a cobra.
 * @version 1.0 10/05/2024
 * @author Hugo Conceição João Ventura Eduarda Pereira
 * @inv O tabuleiro deve ter uma largura e altura positivas, e deve conter pelo menos um obstáculo e um tipo de comida.
 */
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

    /**
     * Construtor para criar um tabuleiro de jogo.
     * @param snake a cobra no jogo
     * @param widthBoard largura do tabuleiro
     * @param heightBoard altura do tabuleiro
     * @param foodType tipo de comida
     * @param foodDimension dimensão da comida
     * @param obstaclesQuantity quantidade de obstáculos
     * @param listObstacleRotacionPoint pontos de rotação dos obstáculos
     * @param isObstacleDynamic indica se os obstáculos são dinâmicos
     * @param random gerador de números aleatórios
     */
    public GameBoard (Snake snake, int widthBoard, int heightBoard, FoodType foodType, int foodDimension,int obstaclesQuantity, List<Ponto<? extends Number>> listObstacleRotacionPoint,List<Integer> listObstacleAngles ,boolean isObstacleDynamic, Random random) {
        if(widthBoard <= 0 || heightBoard <= 0) {
            throw new IllegalArgumentException("A largura e a altura do tabuleiro devem ser maiores que zero.");
        }
        this.random = random;
        while(widthBoard % snake.getArestaHeadLength() != 0)
            widthBoard--;
        this.widthBoard = widthBoard;
        this.snake = snake;
        while(heightBoard % snake.getArestaHeadLength() != 0)
            heightBoard--;
        this.heightBoard = heightBoard;
        this.listOfObstacles = new ArrayList<>();
        this.foodType = foodType;
        this.foodDimension = foodDimension;
        this.obstaclesQuantity = obstaclesQuantity;
        generateObstacles(listObstacleRotacionPoint,listObstacleAngles,isObstacleDynamic);
        generateFood();
    }

    /**
     * Verifica se a cobra intersecta algum obstáculo.
     * @return verdadeiro se intersecta, falso caso contrário.
     */
    public boolean snakeIntersectsObstacle(){
        for (Obstacle obstacle : listOfObstacles) {
            if (obstacle.obstacleIntersect(snake)) {
                return true;
            }
        }
        return false;  
    }

    /**
     * Verifica se algum obstáculo está contido na cobra.
     * @return verdadeiro se contido, falso caso contrário.
     */
    public boolean obstacleContainedInSnake() {
        for (Obstacle obstacle : listOfObstacles) {
            if (obstacle.obstacleContained(snake)) {
                return true;
            }
        }
        return false; 
    }

    /**
     * Verifica se a cabeça da cobra saiu dos limites do tabuleiro.
     * @return verdadeiro se a cobra saiu do tabuleiro, falso caso contrário.
     */
    public boolean snakeLeftBoard() {
        return snake.getHead().getMinX() < 0 || snake.getHead().getMinY() < 0 
            || snake.getHead().getMaxX() > widthBoard || snake.getHead().getMaxY() > heightBoard;
    }

    /**
     * Verifica se a comida está contida em algum obstáculo.
     * @return verdadeiro se a comida estiver contida, falso caso contrário.
     */
    public boolean foodContainedInObstacle() {
        for (Obstacle obstacle : listOfObstacles) {
            if (food.foodContainedObstacle(obstacle)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Remove a comida do tabuleiro.
     */
    public void removeFood(){
        this.food = null;
    }

    /**
     * Verifica se a comida intersecta algum obstáculo.
     * @return verdadeiro se a comida intersecta um obstáculo, falso caso contrário.
     */
    public boolean foodIntersectObstacle() {
        for (Obstacle obstacle : listOfObstacles) {
            if (food.foodIntersectObstacle(obstacle)) {
                return true;
            }
        }   
        return false;
    }

    /**
     * Verifica se a comida intersecta a cobra.
     * @return verdadeiro se a comida intersecta a cobra, falso caso contrário.
     */
    public boolean foodIntersectSnake() {
        return food.foodIntersectSnake(snake);
    }

    /**
     * Verifica se a comida está contida na cabeça da cobra, removendo a comida e gerando uma nova.
     * @return verdadeiro se a comida está contida, falso caso contrário.
     */
    public boolean foodContainedInSnakeHead() {
        if (food.foodContainedInSnakeHead(snake)) {
            removeFood();
            generateFood();
            return true; 
        }
        return false; 
    }

    /**
     * Gera uma comida de forma aleatória no tabuleiro, garantindo que ela não intersecte a cobra ou os obstáculos.
     */
    public void generateFood() {
        int maxIterations = 1000;
        int iterations = 0;
        while (iterations < maxIterations) {
            int x = 0;
            int y = 0;
            x = (int) this.snake.getHead().getMinX() +  (random.nextInt(this.widthBoard / this.snake.getArestaHeadLength()) * this.snake.getArestaHeadLength());
            x %= this.widthBoard - 1;
            y = (int) this.snake.getHead().getMinY() +  (random.nextInt(this.heightBoard / this.snake.getArestaHeadLength()) * this.snake.getArestaHeadLength());
            y %= this.heightBoard - 1;
            FactoryFood factory = new FactoryFood();
            this.food = factory.createFood(x, y, foodType, foodDimension);
            if(!foodIntersectObstacle() && !foodContainedInObstacle() && !foodIntersectSnake() && !this.food.foodContainedInSnakeHead(snake)) {
                break;
            }
            this.food = null;
            iterations++;
        }
        if(iterations == maxIterations) {
            this.food = null;
        }
    }

    /** Gera um obstáculo aleatório na board */
    public void generateObstacles(List<Ponto<? extends Number>> listRotacionPoint, List<Integer> listObstacleAngles ,boolean isDynamic) {
        for(int w = 0; w < this.obstaclesQuantity; w++) {
            int obstacleSize = (random.nextInt(this.snake.getArestaHeadLength()) + this.foodDimension) % this.snake.getArestaHeadLength() + 1;
            boolean isEmpty = true; 
            this.obstacleType = ObstacleType.values()[random.nextInt(Direction.values().length)];
            while (isEmpty) {
                int x = random.nextInt(widthBoard - obstacleSize);
                int y = random.nextInt(heightBoard - obstacleSize);
                List<Ponto<? extends Number>> pontos = new ArrayList<>();
                switch (obstacleType) {
                    case POLYGON:
                        if (listRotacionPoint.isEmpty()) {
                            pontos = createPolygonPoints(x, y, obstacleSize);
                            this.listOfObstacles.add(new Obstacle(new Poligono(pontos),null,0,isDynamic));
                            break;
                        }
                        pontos = createPolygonPoints(x, y, obstacleSize);
                        this.listOfObstacles.add(new Obstacle(new Poligono(pontos), listRotacionPoint.get(w) ,listObstacleAngles.get(w),isDynamic));
                        break;
                    case SQUARE:
                        if(listRotacionPoint.isEmpty()) {
                            pontos = createSquarePoints(x, y, obstacleSize); 
                            this.listOfObstacles.add(new Obstacle(new Quadrado(pontos), null,0,isDynamic));
                            break;
                        }
                        pontos = createSquarePoints(x, y, obstacleSize); 
                        this.listOfObstacles.add(new Obstacle(new Quadrado(pontos), listRotacionPoint.get(w),listObstacleAngles.get(w),isDynamic));
                        break;
                    case RECTANGLE:
                        if (listRotacionPoint.isEmpty()) {
                            pontos = createRectanglePoints(x, y, obstacleSize);
                            this.listOfObstacles.add(new Obstacle(new Retangulo(pontos),null,0,isDynamic));
                            break;
                        }
                        pontos = createRectanglePoints(x, y, obstacleSize);
                        this.listOfObstacles.add(new Obstacle(new Retangulo(pontos), listRotacionPoint.get(w),listObstacleAngles.get(w) ,isDynamic));
                        break;
                    case TRIANGLE:
                        if (listRotacionPoint.isEmpty()) {
                            pontos = createRectanglePoints(x, y, obstacleSize);
                            this.listOfObstacles.add(new Obstacle(new Retangulo(pontos), null,0,isDynamic));
                            break;
                        }
                        pontos = createTrianglePoints(x, y, obstacleSize);
                        this.listOfObstacles.add(new Obstacle(new Triangulo(pontos), listRotacionPoint.get(w),listObstacleAngles.get(w) ,isDynamic));
                        break;
                    default:
                        break;
                }
                boolean isObstacleIntersectedWithAnotherObstacle = false;
                for (int i = 0; i < listOfObstacles.size() - 1; i++) {
                    if (listOfObstacles.get(i).getPoligono().interseta(listOfObstacles.getLast().getPoligono()) && listOfObstacles.size() > 1) {
                        listOfObstacles.removeLast();
                        isObstacleIntersectedWithAnotherObstacle = true;
                        break;
                    }
                }
                if (!isObstacleIntersectedWithAnotherObstacle) {
                    if (!snakeIntersectsObstacle() && !obstacleContainedInSnake()) {
                        isEmpty = false;
                    } else {
                        listOfObstacles.removeLast();
                    }
                }
            }
        }
    }

    /**
     * Rota os obstáculos no tabuleiro.
     */
    public void rotateObstacles() {
        for (Obstacle obstacle : listOfObstacles) {
            obstacle.rotateObstacle();
        }
    }

    /**
     * Cria os pontos de um polígono no tabuleiro.
     * @param x a coordenada x inicial do polígono.
     * @param y a coordenada y inicial do polígono.
     * @param size o tamanho do polígono.
     * @return uma lista de pontos que define o polígono.
     */
    private List<Ponto<? extends Number>> createPolygonPoints(int x, int y, int size) {
        List<Ponto<? extends Number>> pontos = new ArrayList<>();
        List<String> polygonsTypes = Arrays.asList("L", "T", "S");
        String type = polygonsTypes.get(random.nextInt(polygonsTypes.size()));
        switch (type) {
            case "L":
                pontos.add(new Ponto<Integer>(x, y));
                pontos.add(new Ponto<Integer>(x, y - (size*2)));
                pontos.add(new Ponto<Integer>(x + (size*2), y - (size*2)));
                pontos.add(new Ponto<Integer>(x + (size*2), y - size));
                pontos.add(new Ponto<Integer>(x + size, y - size));
                pontos.add(new Ponto<Integer>(x + size, y));
                break;
            case "T":
                pontos.add(new Ponto<Integer>(x, y));
                pontos.add(new Ponto<Integer>(x, y + size));
                pontos.add(new Ponto<Integer>(x + size, y + size));
                pontos.add(new Ponto<Integer>(x + size, y + (size*2)));
                pontos.add(new Ponto<Integer>(x - (size*2), y + (size*2)));
                pontos.add(new Ponto<Integer>(x - (size*2), y + size));
                pontos.add(new Ponto<Integer>(x - size, y + size));
                pontos.add(new Ponto<Integer>(x - size, y));
                break;
            case "S":
                pontos.add(new Ponto<Integer>(x, y));
                pontos.add(new Ponto<Integer>(x, y + size));
                pontos.add(new Ponto<Integer>(x + size, y + size));
                pontos.add(new Ponto<Integer>(x + size, y + (size*2)));
                pontos.add(new Ponto<Integer>(x - size, y + (size*2)));
                pontos.add(new Ponto<Integer>(x - size, y + size));
                pontos.add(new Ponto<Integer>(x - (size*2), y + size));
                pontos.add(new Ponto<Integer>(x - (size*2), y));
                break;
            default:
                break;
        }
        return pontos;
    }

    /**
     * Cria os pontos de um retângulo no tabuleiro.
     * @param x a coordenada x inicial do retângulo.
     * @param y a coordenada y inicial do retângulo.
     * @param size o tamanho do retângulo.
     * @return uma lista de pontos que define o retângulo.
     */
    private List<Ponto<? extends Number>> createRectanglePoints(int x, int y, int size) {
        List<Ponto<? extends Number>> pontos = new ArrayList<>();
        int width = (random.nextInt(size) + this.foodDimension) % this.widthBoard;
        int height = (random.nextInt(size) + this.foodDimension) % this.heightBoard;
        pontos.add(new Ponto<Integer>(x, y));
        pontos.add(new Ponto<Integer>(x, y + height));
        pontos.add(new Ponto<Integer>(x + width, y + height));
        pontos.add(new Ponto<Integer>(x + width, y));
        return pontos;
    }

    /**
     * Cria os pontos de um triângulo no tabuleiro.
     * @param x a coordenada x inicial do triângulo.
     * @param y a coordenada y inicial do triângulo.
     * @param size o tamanho do triângulo.
     * @return uma lista de pontos que define o triângulo.
     */
    private List<Ponto<? extends Number>> createTrianglePoints(int x, int y, int size) {
        List<Ponto<? extends Number>> pontos = new ArrayList<>();
        pontos.add(new Ponto<Integer>(x, y));
        pontos.add(new Ponto<Integer>(x + size, y));
        pontos.add(new Ponto<Integer>(x + size, y - size));
        return pontos;
    }

    /**
     * Cria os pontos de um quadrado no tabuleiro.
     * @param x a coordenada x inicial do quadrado.
     * @param y a coordenada y inicial do quadrado.
     * @param size o tamanho do quadrado.
     * @return uma lista de pontos que define o quadrado.
     */
    private List<Ponto<? extends Number>> createSquarePoints(int x, int y, int size) {
        List<Ponto<? extends Number>> pontos = new ArrayList<>();
        pontos.add(new Ponto<Integer>(x, y));
        pontos.add(new Ponto<Integer>(x + size, y));
        pontos.add(new Ponto<Integer>(x + size, y + size));
        pontos.add(new Ponto<Integer>(x, y + size));
        return pontos;
    }

    /**
     * Retorna a lista de obstáculos no tabuleiro.
     * @return a lista de obstáculos.
     */
    public List<Obstacle> getListOfObstacles() {
        return listOfObstacles;
    }

    /**
     * Define a lista de obstáculos no tabuleiro.
     * @param listOfObstacles a lista de obstáculos.
     */
    public void setListOfObstacles(List<Obstacle> listOfObstacles) {
        this.listOfObstacles = listOfObstacles;
    }

    /**
     * Retorna a comida no tabuleiro.
     * @return a comida.
     */
    public Food getFood() {
        return food;
    }

    /**
     * Define a comida no tabuleiro.
     * @param food a comida.
     */
    public void setFood(Food food) {
        this.food = food;
    }

    /**
     * Retorna a cobra no tabuleiro.
     * @return a cobra.
     */
    public Snake getSnake() {
        return snake;
    }

    /**
     * Define a cobra no tabuleiro.
     * @param snake a cobra.
     */
    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    /**
     * Retorna o tipo de comida no tabuleiro.
     * @return o tipo de comida.
     */
    public FoodType getFoodType() {
        return foodType;
    }

    /**
     * Define o tipo de comida no tabuleiro.
     * @param foodType o tipo de comida.
     */
    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    /**
     * Retorna o tipo de obstáculo no tabuleiro.
     * @return o tipo de obstáculo.
     */
    public ObstacleType getObstacleType() {
        return obstacleType;
    }

    /**
     * Define o tipo de obstáculo no tabuleiro.
     * @param obstacleType o tipo de obstáculo.
     */
    public void setObstacleType(ObstacleType obstacleType) {
        this.obstacleType = obstacleType;
    }

    /**
     * Retorna o gerador de números aleatórios.
     * @return o gerador de números aleatórios.
     */
    public Random getRandom() {
        return random;
    }

    /**
     * Define o gerador de números aleatórios.
     * @param random o gerador de números aleatórios.
     */
    public void setRandom(Random random) {
        this.random = random;
    }

    /**
     * Retorna a dimensão da comida no tabuleiro.
     * @return a dimensão da comida.
     */
    public int getFoodDimension() {
        return foodDimension;
    }

    /**
     * Define a dimensão da comida no tabuleiro.
     * @param foodDimension a dimensão da comida.
     */
    public void setFoodDimension(int foodDimension) {
        this.foodDimension = foodDimension;
    }

    /**
     * Retorna a quantidade de obstáculos no tabuleiro.
     * @return a quantidade de obstáculos.
     */
    public int getObstaclesQuantity() {
        return obstaclesQuantity;
    }

    /**
     * Define a quantidade de obstáculos no tabuleiro.
     * @param obstaclesQuantity a quantidade de obstáculos.
     */
    public void setObstaclesQuantity(int obstaclesQuantity) {
        this.obstaclesQuantity = obstaclesQuantity;
    }

    /**
     * Retorna a altura do tabuleiro.
     * @return a altura do tabuleiro.
     */
    public int getHeightBoard() {
        return heightBoard;
    }

    /**
     * Define a altura do tabuleiro.
     * @param heightBoard a altura do tabuleiro.
     */
    public void setHeightBoard(int heightBoard) {
        this.heightBoard = heightBoard;
    }

    /**
     * Retorna a largura do tabuleiro.
     * @return a largura do tabuleiro.
     */
    public int getWidthBoard() {
        return widthBoard;
    }

    /**
     * Define a largura do tabuleiro.
     * @param widthBoard a largura do tabuleiro.
     */
    public void setWidthBoard(int widthBoard) {
        this.widthBoard = widthBoard;
    }
}
