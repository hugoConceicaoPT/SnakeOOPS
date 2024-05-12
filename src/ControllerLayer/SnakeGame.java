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
import ModelLayer.BoardLayer.Player;
import ModelLayer.BoardLayer.Score;
import ModelLayer.SnakeLayer.AutomatedMovementStrategy;
import ModelLayer.SnakeLayer.Direction;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Snake;
import ViewLayer.ContourRasterization;
import ViewLayer.FullRasterization;
import ViewLayer.GraphicalUI;
import ViewLayer.RasterizationStrategy;
import ViewLayer.TextualUI;
import ViewLayer.UI;

/** 
 * Classe principal que gerencia o jogo da cobra.
 * Responsabilidade: Gerenciar o loop de jogo, entradas do usuario e renderizacao do estado do jogo.
 * @version 1.0 12/05/2024
 * @author Hugo Conceicao, Joao Ventura, Eduarda Pereira
 * @inv O jogo termina quando a cobra colide com um obstaculo, com ela mesma, ou a comida acaba.
 */
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
        private List<Ponto<? extends Number>> listObstacleRotacionPoint;
        private boolean isObstacleDynamic;
        private boolean isGameOver;
        private Score score;
        private Player player;
        private Snake snake;
        private GameBoard gameBoard;
        private RasterizationStrategy rasterizationStrategy;
        private UI userInterface;
        private Leaderboard leaderboard;
        private boolean isFoodEaten;
        private List<Integer> listObstacleAngles;

    /**
     * Constroi um novo jogo da cobra com configuracoes especificadas.
     * @param playerName Nome do jogador.
     * @param widthBoard Largura do tabuleiro.
     * @param heightBoard Altura do tabuleiro.
     * @param headSnakeDimension Tamanho da cabeca da cobra.
     * @param isSnakeManualMovement Se o movimento da cobra e manual.
     * @param rasterizationMode Modo de rasterizacao.
     * @param foodDimension Tamanho da comida.
     * @param foodType Tipo da comida.
     * @param scorePerFood Pontuacao por comida.
     * @param obstaclesQuantity Quantidade de obstaculos.
     * @param listObstacleRotacionPoint Lista de pontos para rotacao dos obstaculos.
     * @param listObstacleAngles Lista de pontos com os angulos de rotacao dos obstaculos
     * @param isObstacleDynamic Se os obstaculos sao dinamicos.
     * @param UIMode Modo da interface de usuario.
     * @param seed Semente para o gerador aleatorio.
     */
    public SnakeGame (String playerName, int widthBoard, int heightBoard, int headSnakeDimension,boolean isSnakeManualMovement, String rasterizationMode, int foodDimension, String foodType ,int scorePerFood, int obstaclesQuantity, List<Ponto<? extends Number>> listObstacleRotacionPoint, List<Integer> listObstacleAngles,boolean isObstacleDynamic, String UIMode, long seed) {
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
        this.listObstacleRotacionPoint = listObstacleRotacionPoint;
        this.listObstacleAngles = listObstacleAngles;
        this.isObstacleDynamic = isObstacleDynamic;
        this.score = new Score(0,this.scorePerFood);
        this.player = new Player(playerName, score);
        this.gameBoard = new GameBoard(this.snake, this.widthBoard, this.heightBoard, this.foodType,this.foodDimension, this.obstaclesQuantity, this.listObstacleRotacionPoint,this.listObstacleAngles,this.isObstacleDynamic,this.random);
        if(rasterizationMode.equals("contorno"))
            this.rasterizationStrategy = new ContourRasterization(this.gameBoard);
        else
            this.rasterizationStrategy = new FullRasterization(this.gameBoard);
        if ("textual".equals(UIMode))
            this.userInterface = new TextualUI(this.rasterizationStrategy);
        else
            this.userInterface = new GraphicalUI();
        if (this.userInterface instanceof GraphicalUI)
            ((GraphicalUI) this.userInterface).addKeyListener(this);
        this.leaderboard = new Leaderboard();
        this.isFoodEaten = false;
    }

    /** 
     * Inicializa e executa o loop principal do jogo. 
     * @param sc Scanner
     */
    public void runGame(Scanner sc) {
        int iterationCount = 0;
        while (!this.isGameOver) {
            if (iterationCount == 0) {
                if (this.gameBoard.getFood() == null) {
                    this.isGameOver = true;
                    this.score.setPoints(Integer.MAX_VALUE);
                    System.out.println("Zerou o Jogo! Pontuação final: " + this.score.getPoints());
                    if (this.isGameOver) break;
                }
                this.rasterizationStrategy.updateObstacleCells();
                this.rasterizationStrategy.updateFoodCells();
                this.rasterizationStrategy.updateSnakeCells();
                this.userInterface.display(this.score, this.gameBoard);
            }
            if (this.isSnakeManualMovement) {
                System.out.println("Pressione as teclas 'W' para cima, 'A' para esquerda, 'S' para baixo, 'D' para direita.");
                System.out.print("Digite a direção desejada ou pressione Enter para manter a direção atual: ");
                String input = sc.nextLine();
                if (!input.isEmpty()) {
                    char directionInput = input.toUpperCase().charAt(0);
                    switch (directionInput) {
                        case 'W': moveSnake(Direction.UP); break;
                        case 'A': moveSnake(Direction.LEFT); break;
                        case 'S': moveSnake(Direction.DOWN); break;
                        case 'D': moveSnake(Direction.RIGHT); break;
                        default: moveSnake(this.snake.getCurrentDirection()); break;
                    }
                } else {
                    moveSnake(this.snake.getCurrentDirection());
                }
            } else {
                moveSnake(this.snake.getCurrentDirection());
            }

            if (snakeCollided()) {
                this.isGameOver = true;
                System.out.println("Game Over! Pontuação final: " + this.score.getPoints());
                break;
            }

            if (this.isObstacleDynamic) {
                this.gameBoard.rotateObstacles();
                this.rasterizationStrategy.updateObstacleCells();
            }

            foodContainedInSnakeHead();
            if (this.isGameOver) break;
            if (this.isFoodEaten) {
                this.rasterizationStrategy.updateFoodCells();
                this.isFoodEaten = false;
            }

            if (snakeCollided()) {
                this.isGameOver = true;
                System.out.println("Game Over! Pontuação final: " + this.score.getPoints());
                break;
            }

            this.rasterizationStrategy.updateSnakeCells();

            if (!this.isSnakeManualMovement) {
                try {
                    Thread.sleep(1000); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.userInterface.display(this.score, this.gameBoard);
                iterationCount++;
            } else {
                this.userInterface.display(this.score, this.gameBoard);
                iterationCount++;
            }
        }
        sc.close();
    }

    /** 
     * Finaliza o jogo e exibe resultados.
     */
    public void endGame() {
        this.leaderboard.updateLeaderboard(this.player);
        System.out.println("Seu jogo acabou. Aqui estão os tops jogadores do jogo:");
        System.out.println(this.leaderboard.generateLeaderboard());
        System.out.println(this.gameBoard.getListOfObstacles().toString());
        if (this.userInterface instanceof GraphicalUI)
            ((GraphicalUI) this.userInterface).close();
        this.rasterizationStrategy = null;
        this.listObstacleRotacionPoint.clear();
        this.score = null;
        this.snake = null;
        this.gameBoard = null;
        this.userInterface = null;
        this.leaderboard = null;
    }

    /** 
     * Verifica se a comida esta contida na cabeca da snake, se sim aumenta o tamanho dela e o score do jogador
     */
    public void foodContainedInSnakeHead() {
        if(this.gameBoard.foodContainedInSnakeHead()) {
            this.snake.increaseSize();
            this.score.increaseScore();
            this.isFoodEaten = true;
            if(this.gameBoard.getFood() == null) {
                this.isGameOver = true;
                score.setPoints(Integer.MAX_VALUE);
                System.out.println("Zerou o Jogo! Pontuação final: " + score.getPoints());
            }
        }
    }

    /** 
     * Verifica e trata a colisao da cobra com obstaculos, consigo mesma ou saida do tabuleiro.
     * @return verdadeiro se houve colisao.
     */
    public boolean snakeCollided() {
        if (this.gameBoard.snakeIntersectsObstacle() || this.gameBoard.obstacleContainedInSnake() || this.snake.collidedWithHerself() || this.gameBoard.snakeLeftBoard()) 
            return true;
        return false;
    }

    /** 
     * Atualiza a direcao da cobra com base nas entradas do teclado.
     * @param e Evento de teclado capturado.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.snake.setNextDirection(Direction.UP);
                this.snake.move();
                this.rasterizationStrategy.updateSnakeCells();
                break;
            case KeyEvent.VK_DOWN:
                this.snake.setNextDirection(Direction.DOWN);
                this.snake.move();
                this.rasterizationStrategy.updateSnakeCells();
                break;
            case KeyEvent.VK_LEFT:
                this.snake.setNextDirection(Direction.LEFT);
                this.snake.move();
                this.rasterizationStrategy.updateSnakeCells();
                break;
            case KeyEvent.VK_RIGHT:
                this.snake.setNextDirection(Direction.RIGHT);
                this.snake.move();
                this.rasterizationStrategy.updateSnakeCells();
                break;
            default:
                break;
        }
    }

    /** 
     * Move a cobra na direcao especificada.
     * @param nextDirection Nova direcao para a cobra.
     */
    public void moveSnake(Direction nextDirection) {
        if (this.snake.getMovementStrategy() instanceof AutomatedMovementStrategy) {
            this.snake.setNextDirection(nextDirection);
            this.snake.moveAutomated(this.gameBoard);
        } else {
            this.snake.setNextDirection(nextDirection);
            this.snake.move();
            System.out.println(this.snake.toString());
        }
    }

    /** 
     * Cria pontos que formarao o quadrado da cabeca da cobra.
     * @param widthBoard Largura do tabuleiro.
     * @param heightBoard Altura do tabuleiro.
     * @param size Tamanho da cabeca da cobra.
     * @return Lista de pontos que formam o quadrado da cabeca da cobra.
     */
    private List<Ponto<? extends Number>> createSquarePoints(int widthBoard, int heightBoard, int size) {
        int x = this.random.nextInt(widthBoard - size);
        int y = this.random.nextInt(heightBoard - size);
        List<Ponto<? extends Number>> pontos = new ArrayList<>();
        pontos.add(new Ponto<Integer>(x, y));
        pontos.add(new Ponto<Integer>(x + size, y));
        pontos.add(new Ponto<Integer>(x + size, y + size));
        pontos.add(new Ponto<Integer>(x, y + size));
        return pontos;
    }

    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    /** Obtem se acabou o jogo ou nao
     * @return se acabou o jogo ou nao
     */
    public boolean isGameOver() {
        return isGameOver;
    }

    /** Atualiza o estado do gameOver
     * @param isGameOver o novo estado do gameOver
     */
    public void setGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    /** Obtem o score do jogo
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

    /** Obtem a snake do jogo                                                                       
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

    /** Obtem a width da board
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

    /** Obtem a heigth da board
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

    /**
     * Obtem a arena do jogo
     * @return a arena do jogo
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * Atualiza a arena do jogo
     * @param gameBoard a nova arena do jogo
     */
    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * Obtem o tipo de rasterização do jogo
     * @return o tipo de rasterização do jogo
     */
    public RasterizationStrategy getRasterizationStrategy() {
        return rasterizationStrategy;
    }

    /**
     * Atualiza o tipo de rasterização 
     * @param rasterizationStrategy o novo tipo de rasterização
     */
    public void setRasterizationStrategy(RasterizationStrategy rasterizationStrategy) {
        this.rasterizationStrategy = rasterizationStrategy;
    }

    /**
     * Obtem o tamanho da cabeça da cobra
     * @return o tamanho da cabeça da cobra
     */
    public int getHeadSnakeDimension() {
        return headSnakeDimension;
    }

    /**
     * Atualiza o tamanho da cabeça da cobra
     * @param headDimension o novo tamanho da cabeça da cobra
     */
    public void setHeadSnakeDimension(int headDimension) {
        this.headSnakeDimension = headDimension;
    }

    /**
     * Obtem o tamanho da comida
     * @return o tamanho da comida
     */
    public int getFoodDimension() {
        return foodDimension;
    }

    /**
     * Atualiza o tamanho da comida
     * @param foodDimension o novo tamanho da comida
     */
    public void setFoodDimension(int foodDimension) {
        this.foodDimension = foodDimension;
    }

    /**
     * Obtem o tipo de comida
     * @return o tipo de comida
     */
    public FoodType getFoodType() {
        return foodType;
    }

    /**
     * Atualiza o tipo de comida
     * @param foodType o novo tipo de comida
     */
    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    /**
     * Obtem a quantidade de obstaculos
     * @return a quantidade de obstaculos
     */
    public int getObstaclesQuantity() {
        return obstaclesQuantity;
    }

    /**
     * Atualiza a quantidade de obstaculos
     * @param obstaclesQuantity a nova quantidade e obstaculos
     */
    public void setObstaclesQuantity(int obstaclesQuantity) {
        this.obstaclesQuantity = obstaclesQuantity;
    }
    
    /**
     * Obtem se os obstáculos sao dinamicos ou nao
     * @return os obstáculos dinâmicos ou não
     */
    public boolean isObstacleDynamic() {
        return isObstacleDynamic;
    }

    /**
     * Atualiza se os obstaculos sao dinamicos ou nao 
     * @param isObstacleDynamic o novo valor logico da dinamicidade dos obstaculos
     */
    public void setObstacleDynamic(boolean isObstacleDynamic) {
        this.isObstacleDynamic = isObstacleDynamic;
    }

    /**
     * Obtem a UI do jogo
     * @return UI do jogo
     */
    public UI getUserInterface() {
        return userInterface;
    }

    /**
     * Atualiza a UI do jogo
     * @param userInterface a nova UI do jogo
     */
    public void setUserInterface(UI userInterface) {
        this.userInterface = userInterface;
    }

    /**
     * Obtem o gerador de numeros aleatorios
     * @return o gerador de numeros aleatorios
     */
    public Random getRandom() {
        return random;
    }

    /**
     * Atualiza o gerador de numeros aleatorios
     * @param random o novo gerador de numeros aleatorios
     */
    public void setRandom(Random random) {
        this.random = random;
    }

    /**
     * Obtem se o movimento da snake e manual ou automatizado
     * @return se o movimento da snake e manual ou automatizado
     */
    public boolean isSnakeManualMovement() {
        return isSnakeManualMovement;
    }

    /**
     * Atualiza se o movimento da snake e manual ou automatizado
     * @param isSnakeManualMovement o novo valor logico da automatizacao ou nao do movimento da snake
     */
    public void setSnakeManualMovement(boolean isSnakeManualMovement) {
        this.isSnakeManualMovement = isSnakeManualMovement;
    }

    /**
     * Obtem os pontos por comida do jogo
     * @return os pontos por comida do jogo
     */
    public int getScorePerFood() {
        return scorePerFood;
    }

    /**
     * Atualiza os pontos por comida 
     * @param scorePerFood os novos pontos por comida
     */
    public void setScorePerFood(int scorePerFood) {
        this.scorePerFood = scorePerFood;
    }


    /**
     * Obtem a leaderboard do jogo
     * @return a leaderboard do jogo
     */
    public Leaderboard getLeaderboard() {
        return leaderboard;
    }

    /**
     * Atualiza a leaderboard do jogo
     * @param leaderboard a nova leaderboard 
     */
    public void setLeaderboard(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
    }


    /**
     * Obtem a lista de pontos de rotacao dos obstaculos
     * @return a lista de pontos de rotacao dos obstaculos
     */
    public List<Ponto<? extends Number>> getListObstacleRotacionPoint() {
        return listObstacleRotacionPoint;
    }


    /**
     * Atualiza a lista de pontos de rotacao dos obstaculos
     * @param listObstacleRotacionPoint a nova lista de pontos de rotacao dos obstaculos
     */
    public void setListObstacleRotacionPoint(List<Ponto<? extends Number>> listObstacleRotacionPoint) {
        this.listObstacleRotacionPoint = listObstacleRotacionPoint;
    }

    /**
     * Obtem o player do jogo
     * @return o player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Atualiza o player do jogo
     * @param player o novo player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Obtem se a comida foi comida pela cobra ou nao
     * @return se a comida foi comida pela cobra ou nao
     */
    public boolean isFoodEaten() {
        return isFoodEaten;
    }

    /**
     * Atualiza se a comida foi comida pela cobra ou nao
     * @param isFoodEaten o novo valor logico se a comida foi comida pela cobra ou nao
     */
    public void setFoodEaten(boolean isFoodEaten) {
        this.isFoodEaten = isFoodEaten;
    }

    /**
     * Obtem a lista de angulos de rotacao dos obstaculos
     * @return a lista de ângulos de rotacao dos obstaculos
     */
    public List<Integer> getListObstacleAngles() {
        return listObstacleAngles;
    }

    /**
     * Atualiza a lista de angulos de rotacao dos obstaculos
     * @param listObstacleAngles a nova lista de angulos de rotacao de obstaculos
     */
    public void setListObstacleAngles(List<Integer> listObstacleAngles) {
        this.listObstacleAngles = listObstacleAngles;
    }
}
