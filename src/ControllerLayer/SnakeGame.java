package ControllerLayer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import ModelLayer.BoardLayer.FoodType;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.SnakeLayer.Direction;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Score;
import ModelLayer.SnakeLayer.Snake;
import ViewLayer.GraphicalUI;
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
    private boolean isObstacleMovementAroundCenter;
    private boolean isObstacleDynamic;
    private boolean isGameOver;
    private Score score;
    private Snake snake;
    private GameBoard gameBoard;
    private RasterizationStrategy rasterizionStragety;
    private UI userInterface;

    /** Construtor para criar um jogo da cobra        
     * @param snake a cobra do jogo 
     */
    public SnakeGame (int widthBoard, int heightBoard, int headSnakeDimension,boolean isSnakeManualMovement, String rasterizationMode, int foodDimension, String foodType ,int scorePerFood, int obstaclesQuantity, Ponto obstacleRotacionPoint, boolean isObstacleMovementAroundCenter, boolean isObstacleDynamic, String UIMode) {
        this.random = new Random();
        this.isGameOver = false;
        this.widthBoard = widthBoard;
        this.heightBoard = heightBoard;
        this.headSnakeDimension = headSnakeDimension;
        this.isSnakeManualMovement = isSnakeManualMovement;
        if(rasterizationMode.equals("contorno"))
            this.rasterizionStragety = new ContourRasterization();
        else
            this.rasterizionStragety = new FullRasterization();
        this.foodDimension = foodDimension;
        if(foodType.equals("quadrado"))
            this.foodType = FoodType.SQUARE;
        else
            this.foodType = FoodType.CIRCLE;
        this.scorePerFood = scorePerFood;
        this.obstaclesQuantity = obstaclesQuantity;
        this.obstacleRotacionPoint = obstacleRotacionPoint;
        this.isObstacleMovementAroundCenter = isObstacleMovementAroundCenter;
        this.isObstacleDynamic = isObstacleDynamic;
        if(UIMode.equals("textual"))
            this.userInterface = new TextualUI();
        else
            this.userInterface = new GraphicalUI();
        runGame();
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
    public void runGame() {
        LinkedList<Quadrado> bodySnake = new LinkedList<>();
        Quadrado quadrado = new Quadrado(createSquarePoints(this.widthBoard, this.heightBoard, this.headSnakeDimension));
        this.score = new Score(0,this.scorePerFood);
        bodySnake.add(quadrado);
        this.snake = new Snake(bodySnake, isSnakeManualMovement);
        this.gameBoard = new GameBoard(this.snake, this.widthBoard, this.heightBoard, this.foodType,this.foodDimension, this.obstaclesQuantity, this.obstacleRotacionPoint, this.isObstacleMovementAroundCenter, this.isObstacleDynamic,this.random);
        if(userInterface instanceof GraphicalUI) 
            ((GraphicalUI) userInterface).addKeyListener(this);
        while(!this.isGameOver) {
            userInterface.display(gameBoard);

            if(snakeCollided()) {
                this.isGameOver = true;
                System.out.println("Game Over!Pontuação final: " + score.getScore());
                break;
            }

            if(this.isObstacleDynamic) {
                this.gameBoard.rotateObstacles();
            }

            try {
                foodContainedInSnake();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        endGame();
    }
    /** Para o jogo */
    public void endGame() {
        System.out.println("O jogo terminou");
        if(this.userInterface instanceof GraphicalUI) 
            ((GraphicalUI) userInterface).close();  
        this.rasterizionStragety = null;
        this.obstacleRotacionPoint = null;
        this.score = null;
        this.snake = null;
        this.gameBoard = null;
        this.userInterface = null; 
    }
    
    public void foodContainedInSnake() throws CloneNotSupportedException {
        if(this.gameBoard.foodContainedInSnake()) {
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
                snake.move(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                snake.move(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                snake.move(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                snake.move(Direction.RIGHT);
                break;
            default:
                break;
        }
    }

    private List<Ponto> createSquarePoints(int widthBoard, int heightBoard, int size) {
        int column = random.nextInt(widthBoard - size);
        int row = random.nextInt(heightBoard - size); 
        List<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(column, row));
        pontos.add(new Ponto(column + size, row));
        pontos.add(new Ponto(column + size, row + size));
        pontos.add(new Ponto(column, row + size));

        return pontos;
    }

    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public String toString() {
        return null;
    }

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

    public RasterizationStrategy getRasterizionStragety() {
        return rasterizionStragety;
    }

    public void setRasterizionStragety(RasterizationStrategy rasterizionStragety) {
        this.rasterizionStragety = rasterizionStragety;
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

    public boolean isObstacleMovementAroundCenter() {
        return isObstacleMovementAroundCenter;
    }

    public void setObstacleMovementAroundCenter(boolean isObstacleMovementAroundCenter) {
        this.isObstacleMovementAroundCenter = isObstacleMovementAroundCenter;
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

}
