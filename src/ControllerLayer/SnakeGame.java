package ControllerLayer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import ModelLayer.BoardLayer.FoodType;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.SnakeLayer.AutomatedMovementStrategy;
import ModelLayer.SnakeLayer.Direction;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Score;
import ModelLayer.SnakeLayer.Snake;
import ViewLayer.ContourRasterization;
import ViewLayer.FullRasterization;
import ViewLayer.GraphicalUI;
import ViewLayer.RasterizationStrategy;
import ViewLayer.TextualUI;
import ViewLayer.UI;
public class SnakeGame implements KeyListener {
    private Random random;
    private int widthBoard;
    private int heightBoard;
    private int headSnakeDimension;
    private boolean isSnakeManualMovement;
    private int foodDimension;
    private int scorePerFood;
    private FoodType foodType;
    private int obstaclesQuantity;
    private Ponto obstacleRotacionPoint;
    private boolean isObstacleDynamic;
    private boolean isGameOver;
    private Score score;
    private Snake snake;
    private GameBoard gameBoard;
    private RasterizationStrategy rasterizationStrategy;
    private UI userInterface;
    private Leaderboard leaderboard;

    /** Construtor para criar um jogo da cobra        
     * @param snake a cobra do jogo 
     */
    public SnakeGame (int widthBoard, int heightBoard, int headSnakeDimension,boolean isSnakeManualMovement, String rasterizationMode, int foodDimension, String foodType ,int scorePerFood, int obstaclesQuantity, Ponto obstacleRotacionPoint, boolean isObstacleDynamic, String UIMode, long seed) throws CloneNotSupportedException {
        this.random = new Random(seed);
        this.isGameOver = false;
        this.widthBoard = widthBoard;
        this.heightBoard = heightBoard;
        this.headSnakeDimension = headSnakeDimension;
        this.isSnakeManualMovement = isSnakeManualMovement;
        LinkedList<Quadrado> bodySnake = new LinkedList<>();
        Quadrado quadrado = new Quadrado(createSquarePoints(this.widthBoard, this.heightBoard, this.headSnakeDimension));
        bodySnake.addFirst(quadrado);
        this.snake = new Snake(bodySnake, isSnakeManualMovement,this.random);
        if(foodType.equals("quadrados")) this.foodType = FoodType.SQUARE;
        else this.foodType = FoodType.CIRCLE;
        this.foodDimension = foodDimension;
        this.scorePerFood = scorePerFood;
        this.obstaclesQuantity = obstaclesQuantity;
        this.obstacleRotacionPoint = obstacleRotacionPoint;
        this.isObstacleDynamic = isObstacleDynamic;
        this.score = new Score(0,this.scorePerFood);
        this.gameBoard = new GameBoard(this.snake, this.widthBoard, this.heightBoard, this.foodType,this.foodDimension, this.obstaclesQuantity, this.obstacleRotacionPoint,this.isObstacleDynamic,this.random);
        if(rasterizationMode.equals("contorno"))
            this.rasterizationStrategy = new ContourRasterization(this.gameBoard);
        else
            this.rasterizationStrategy = new FullRasterization(this.gameBoard);
        if(UIMode.equals("textual"))
            this.userInterface = new TextualUI(this.rasterizationStrategy);
        else
            this.userInterface = new GraphicalUI();
        if(userInterface instanceof GraphicalUI) 
            ((GraphicalUI) userInterface).addKeyListener(this);
        this.leaderboard = new Leaderboard();
    }


    /** Obtém se acabou o jogo ou não
     * @return se acabou o jogo ou não
     */
    public boolean isGameOver() {
        return isGameOver;
    }

    /** Atualiza o estado do gameOver
     * @param gameOver o novo estado do gameOver
     */
    public void setGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    /** Obtém o score do jogo
     * @return o score do jogo
     */
    public Score getScore() {
        return score;
    }

    /** Atualiza o score do jogo
     * @param score o novo score do jogo
     */
    public void setScore(Score score) {
        this.score = score;
    }

    /** Obtém a snake do jogo                                                                       
     * @return a snake do jogo
     */
    public Snake getSnake() {
        return snake;
    }

    /** Atualiza a snake do jogo
     * @param snake a nova snake do jogo
     */
    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    /** Inicializa o jogo */
    public void runGame(Scanner sc) {
        int iterationCount = 0;
        while(!this.isGameOver) {
            if(iterationCount == 0) {
                this.rasterizationStrategy.updateBoard();
                userInterface.display(score,gameBoard);
            }
            if(isSnakeManualMovement) {
                System.out.println("Pressione as teclas 'W' para cima, 'A' para esquerda, 'S' para baixo, 'D' para direita.");
                System.out.print("Digite a direção desejada ou pressione Enter para manter a direção atual: ");
                String input = sc.nextLine();
                if(!input.isEmpty()) {
                    char directionInput = input.toUpperCase().charAt(0);
                    switch (directionInput) {
                        case 'W':
                            moveSnake(Direction.UP);
                            break;
                        case 'A':
                            moveSnake(Direction.LEFT);
                            break;
                        case 'S':
                            moveSnake(Direction.DOWN);
                            break;
                        case 'D':
                            moveSnake(Direction.RIGHT);
                            break;
                        default:
                            moveSnake(this.snake.getCurrentDirection());
                            break;
                    }
                }
                else 
                    moveSnake(this.snake.getCurrentDirection());
            }
            else {
                moveSnake(this.snake.getCurrentDirection());
            }
            if(this.gameBoard.getFood() == null) {
                this.isGameOver = true;
                score.setPoints(Integer.MAX_VALUE);
                System.out.println("Zerou o Jogo! Pontuação final: " + score.getPoints());
            }

            if(snakeCollided()) {
                this.isGameOver = true;
                System.out.println("Game Over! Pontuação final: " + score.getPoints());
                break;
            }

            if(this.isObstacleDynamic) {
                this.gameBoard.rotateObstacles();
            }

            try {
                foodContainedInSnakeHead();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            if(snakeCollided()) {
                this.isGameOver = true;
                System.out.println("Game Over! Pontuação final: " + score.getPoints());
                break;
            }

            this.rasterizationStrategy.updateBoard();
            if(!this.isSnakeManualMovement) {
                try {
                    Thread.sleep(1000); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                userInterface.display(score,gameBoard);
                iterationCount++;
            }
            else {
                userInterface.display(score,gameBoard);
                iterationCount++;
            }
        }
        sc.close();
    }
    /** Para o jogo */
    public void endGame() {
        this.leaderboard.updateLeaderboard(score);
        System.out.println("Seu jogo acabou. Aqui estão os tops jogadores do jogo:");
        System.out.println(this.leaderboard.generateLeaderboard());
        if(this.userInterface instanceof GraphicalUI) 
            ((GraphicalUI) userInterface).close();  
        this.rasterizationStrategy = null;
        this.obstacleRotacionPoint = null;
        this.score = null;
        this.snake = null;
        this.gameBoard = null;
        this.userInterface = null; 
        this.leaderboard = null;
    }
    
    public void foodContainedInSnakeHead() throws CloneNotSupportedException {
        if(this.gameBoard.foodContainedInSnakeHead()) {
            this.snake.increaseSize();
            this.score.increaseScore();
        }
    }

    /** Verifica se a cobra colida com as paredes da board, ou com o obstáculo, ou com ela própria
     * @return verdadeiro se colidir, falso se não
     */
    public boolean snakeCollided() {
        if(this.gameBoard.snakeIntersectsObstacle() || this.gameBoard.obstacleContainedInSnake() || this.snake.collidedWithHerself() || this.gameBoard.snakeLeftBoard())
            return true;
        return false;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.snake.setNextDirection(Direction.UP);
                this.snake.move();
                this.rasterizationStrategy.updateBoard();
                break;
            case KeyEvent.VK_DOWN:
                this.snake.setNextDirection(Direction.DOWN);
                this.snake.move();
                this.rasterizationStrategy.updateBoard();
                break;
            case KeyEvent.VK_LEFT:
                this.snake.setNextDirection(Direction.LEFT);
                snake.move();
                this.rasterizationStrategy.updateBoard();
                break;
            case KeyEvent.VK_RIGHT:
                this.snake.setNextDirection(Direction.RIGHT);
                this.snake.move();
                this.rasterizationStrategy.updateBoard();
                break;
            default:
                break;
        }
    }

    public void moveSnake(Direction nextDirection) {
        if(this.snake.getMovementStrategy() instanceof AutomatedMovementStrategy) {
            this.snake.setNextDirection(nextDirection);
            this.snake.moveAutomated(gameBoard);
        }
        else {
            this.snake.setNextDirection(nextDirection);
            this.snake.move();
        }
    }

    private List<Ponto> createSquarePoints(int widthBoard, int heightBoard, int size) {
        int x = random.nextInt(widthBoard - size);
        int y = random.nextInt(heightBoard - size); 
        List<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(x, y));
        pontos.add(new Ponto(x + size,y));
        pontos.add(new Ponto(x + size, y + size));
        pontos.add(new Ponto(x, y + size));

        return pontos;
    }

    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    /** Obtém a width da board
     * @return o valor da width
     */
    public int getWidthBoard() {
        return widthBoard;
    }

    /** Atualiza a width da board
     * @param widthBoard a nova width da board
     */
    public void setWidthBoard(int widthBoard) {
        this.widthBoard = widthBoard;
    }

    /** Obtém a heigth da board
     * @return a heigth da board
     */
    public int getHeightBoard() {
        return heightBoard;
    }

    /** Atualiza a height da board
     * @param heightBoard a nova height da board
     */
    public void setHeightBoard(int heightBoard) {
        this.heightBoard = heightBoard;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public RasterizationStrategy getrasterizationStrategy() {
        return rasterizationStrategy;
    }

    public void setrasterizationStrategy(RasterizationStrategy rasterizationStrategy) {
        this.rasterizationStrategy = rasterizationStrategy;
    }

    public int getHeadSnakeDimension() {
        return headSnakeDimension;
    }

    public void setHeadSnakeDimension(int headDimension) {
        this.headSnakeDimension = headDimension;
    }

    public int getFoodDimension() {
        return foodDimension;
    }

    public void setFoodDimension(int foodDimension) {
        this.foodDimension = foodDimension;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public int getObstaclesQuantity() {
        return obstaclesQuantity;
    }

    public void setObstaclesQuantity(int obstaclesQuantity) {
        this.obstaclesQuantity = obstaclesQuantity;
    }

    public Ponto getObstacleRotacionPoint() {
        return obstacleRotacionPoint;
    }

    public void setObstacleRotacionPoint(Ponto obstacleRotacionPoint) {
        this.obstacleRotacionPoint = obstacleRotacionPoint;
    }

    public boolean isObstacleDynamic() {
        return isObstacleDynamic;
    }

    public void setObstacleDynamic(boolean isObstacleDynamic) {
        this.isObstacleDynamic = isObstacleDynamic;
    }

    public UI getUserInterface() {
        return userInterface;
    }

    public void setUserInterface(UI userInterface) {
        this.userInterface = userInterface;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public boolean isSnakeManualMovement() {
        return isSnakeManualMovement;
    }

    public void setSnakeManualMovement(boolean isSnakeManualMovement) {
        this.isSnakeManualMovement = isSnakeManualMovement;
    }

    public int getScorePerFood() {
        return scorePerFood;
    }

    public void setScorePerFood(int scorePerFood) {
        this.scorePerFood = scorePerFood;
    }


    public RasterizationStrategy getRasterizationStrategy() {
        return rasterizationStrategy;
    }


    public void setRasterizationStrategy(RasterizationStrategy rasterizationStrategy) {
        this.rasterizationStrategy = rasterizationStrategy;
    }


    public Leaderboard getLeaderboard() {
        return leaderboard;
    }


    public void setLeaderboard(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
    }
}
