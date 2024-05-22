package ControllerLayer;

import java.awt.event.*;
import java.util.Scanner;

import ModelLayer.BoardLayer.*;
import ModelLayer.SnakeLayer.*;
import ViewLayer.*;

import javax.swing.*;

/**
 * Classe principal que gerencia o jogo da cobra.
 * Responsabilidade: Gerenciar o loop de jogo, entradas do usuario e renderizacao do estado do jogo.
 * @version 1.0 22/05/2024
 * @author Hugo Conceição, João Ventura, Eduarda Pereira
 * @inv O jogo termina quando a cobra colide com um obstaculo, com ela mesma, ou a comida acaba.
 */
public class SnakeGame implements KeyListener,ActionListener {
    private boolean isGameOver;
    private GameBoard gameBoard;
    private Player player;
    private UI userInterface;
    private Leaderboard leaderboard;
    private boolean isKeyPressed;
    private boolean isFoodEaten;
    private Score score;
    private Timer gameLoop;
    private Timer menuLoop;
    private ML mouseListener = new ML();
    private static boolean isRunning = false;

    /**
     * Constroi um novo jogo da cobra com configuracoes especificadas.
     * @param playerName Nome do jogador.
     * @param gameBoard Tabuleiro do jogo.
     * @param userInterface Interface do utilizador.
     * @param foodScore Pontos por comida
     */
    public SnakeGame (String playerName,GameBoard gameBoard, UI userInterface, int foodScore) {
        this.leaderboard = new Leaderboard();
        this.gameBoard = gameBoard;
        this.userInterface = userInterface;
        this.score = new Score(0,foodScore);
        this.player = new Player(playerName,this.score);
        this.isFoodEaten = false;
        if(this.userInterface instanceof JFrame frame) {
            frame.addKeyListener(this);
            this.userInterface.addMouseListener(this.mouseListener);
            this.userInterface.addMouseMotionListener(this.mouseListener);
            this.gameLoop = new Timer(250,this);
            this.gameLoop.start();
        }
    }

    /**
     * Inicializa e executa o loop principal do jogo.
     * @param sc Scanner
     */
    public void runGame(Scanner sc) {
        int iterationCount = 0;
        while (!this.isGameOver) {
            if (iterationCount == 0) {
                this.userInterface.getTextualRasterizationStrategy().updateObstacles();
                this.userInterface.getTextualRasterizationStrategy().updateFood();
                this.userInterface.getTextualRasterizationStrategy().updateSnake();
                displayGame();
            }
            if (this.gameBoard.getSnake().getMovementStrategy() instanceof ManualMovementStrategy) {
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
                        default: moveSnake(this.gameBoard.getSnake().getCurrentDirection()); break;
                    }
                } else {
                    moveSnake(this.gameBoard.getSnake().getCurrentDirection());
                }
            }
            else {
                moveSnake(this.gameBoard.getSnake().getCurrentDirection());
            }

            if (snakeCollided()) {
                this.isGameOver = true;
                System.out.println("Game Over! Pontuação final: " + this.score.getPoints());
                break;
            }

            if (this.gameBoard.getListOfObstacles().getFirst().getObstacleMovement() instanceof DynamicMovement) {
                this.gameBoard.rotateObstacles();
                this.userInterface.getTextualRasterizationStrategy().updateObstacles();
            }

            foodContainedInSnakeHead();
            if (this.gameBoard.getFood() == null) {
                this.isGameOver = true;
                this.score.setPoints(Integer.MAX_VALUE);
                System.out.println("Zerou o Jogo! Pontuação final: " + this.score.getPoints());
                if (this.isGameOver) break;
            }
            if (this.isFoodEaten) {
                this.userInterface.getTextualRasterizationStrategy().updateFood();
                this.isFoodEaten = false;
            }

            if (snakeCollided()) {
                this.isGameOver = true;
                System.out.println("Game Over! Pontuação final: " + this.score.getPoints());
                break;
            }

            this.userInterface.getTextualRasterizationStrategy().updateSnake();

            if (this.gameBoard.getSnake().getMovementStrategy() instanceof AutomatedMovementStrategy) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            displayGame();
            iterationCount++;
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
        if (this.userInterface instanceof GraphicalUI)
            ((GraphicalUI) this.userInterface).close();
        this.gameBoard = null;
        this.userInterface = null;
        this.score = null;
        this.leaderboard = null;
    }

    /**
     * Verifica se a comida esta contida na cabeca da snake, se sim aumenta o tamanho dela e o score do jogador
     */
    public void foodContainedInSnakeHead() {
        if(this.gameBoard.foodContainedInSnakeHead()) {
            this.gameBoard.getSnake().increaseSize();
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
        if (this.gameBoard.snakeIntersectsObstacle() || this.gameBoard.obstacleContainedInSnake() || this.gameBoard.getSnake().collidedWithHerself() || this.gameBoard.snakeLeftBoard())
            return true;
        return false;
    }

    /**
     * Dá disçay do jogo em modo gráfico
     */
    public void displayGame() {
        this.userInterface.display(this.gameBoard,this.score);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        displayGame();
        if(!isGameOver) {
            if (isRunning) {
                if(this.gameBoard.getSnake().getMovementStrategy() instanceof ManualMovementStrategy) {
                    if (!this.isKeyPressed)
                        this.gameBoard.getSnake().setNextDirection(this.gameBoard.getSnake().getCurrentDirection());
                    else
                        this.isKeyPressed = false;
                    this.gameBoard.getSnake().move();
                }
                else {
                    moveSnake(this.gameBoard.getSnake().getCurrentDirection());
                }

                if (this.gameBoard.getListOfObstacles().getFirst().getObstacleMovement() instanceof DynamicMovement) {
                    this.gameBoard.rotateObstacles();
                }

                foodContainedInSnakeHead();
                if (this.gameBoard.getFood() == null) {
                    this.isGameOver = true;
                    this.score.setPoints(Integer.MAX_VALUE);
                }

                if (snakeCollided()) {
                    this.isGameOver = true;
                    System.out.println("Game Over! Pontuação final: " + this.score.getPoints());
                }

                displayGame();
            }
        }
        else {
            gameLoop.stop();
            endGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    /**
     * Move a cobra na direcao especificada.
     * @param nextDirection Nova direcao para a cobra.
     */
    public void moveSnake(Direction nextDirection) {
        if (this.gameBoard.getSnake().getMovementStrategy() instanceof AutomatedMovementStrategy) {
            this.gameBoard.getSnake().setNextDirection(nextDirection);
            this.gameBoard.getSnake().moveAutomated(this.gameBoard);
        } else {
            this.gameBoard.getSnake().setNextDirection(nextDirection);
            this.gameBoard.getSnake().move();
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.gameBoard.getSnake().setNextDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_DOWN:
                this.gameBoard.getSnake().setNextDirection(Direction.UP);
                break;
            case KeyEvent.VK_LEFT:
                this.gameBoard.getSnake().setNextDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                this.gameBoard.getSnake().setNextDirection(Direction.RIGHT);
                break;
        }
        this.isKeyPressed = true;
    }
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
    /**
     * Atualiza se a comida foi comida pela cobra ou não.
     * @param isFoodEaten o novo valor lógico se a comida foi comida pela cobra ou não.
     */
    public void setFoodEaten(boolean isFoodEaten) {
        this.isFoodEaten = isFoodEaten;
    }

    /**
     * Verifica se uma tecla foi liberada.
     * @return true se uma tecla foi liberada, caso contrário false.
     */
    public boolean isKeyPressed() {
        return isKeyPressed;
    }

    /**
     * Atualiza o estado de liberação de tecla.
     * @param keyPressed o novo estado de liberação de tecla.
     */
    public void setKeyPressed(boolean keyPressed) {
        isKeyPressed = keyPressed;
    }

    /**
     * Obtém o loop do jogo.
     * @return O timer do loop do jogo.
     */
    public Timer getGameLoop() {
        return gameLoop;
    }

    /**
     * Define um novo loop de jogo.
     * @param gameLoop O novo timer do loop do jogo.
     */
    public void setGameLoop(Timer gameLoop) {
        this.gameLoop = gameLoop;
    }

    /**
     * Verifica se o jogo está em execução.
     * @return true se o jogo está em execução, caso contrário false.
     */
    public static boolean isIsRunning() {
        return isRunning;
    }

    /**
     * Atualiza o estado de execução do jogo.
     * @param isRunning o novo estado de execução do jogo.
     */
    public static void setIsRunning(boolean isRunning) {
        SnakeGame.isRunning = isRunning;
    }

    /**
     * Obtém o listener de eventos de mouse.
     * @return O listener de eventos de mouse.
     */
    public ML getMouseListener() {
        return mouseListener;
    }

    /**
     * Define um novo listener de eventos de mouse.
     * @param mouseListener O novo listener de eventos de mouse.
     */
    public void setMouseListener(ML mouseListener) {
        this.mouseListener = mouseListener;
    }

}
