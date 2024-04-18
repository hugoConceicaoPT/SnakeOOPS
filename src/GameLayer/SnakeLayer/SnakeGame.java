package GameLayer.SnakeLayer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import GameLayer.BoardLayer.GameBoard;
public class SnakeGame implements KeyListener {
    private int widthBoard;
    private int heightBoard;
    private boolean gameOver;
    private Score score;
    private Snake snake;
    private GameBoard gameBoard;

    /** Construtor para criar um jogo da cobra        
     * @param snake a cobra do jogo 
     */
    public SnakeGame (Snake snake, GameBoard gameBoard) {
        this.snake = snake;
        this.score = new Score(0);
        this.gameOver = false;
        this.gameBoard = gameBoard;
    }

    /** Obtém se acabou o jogo ou não
     * @return se acabou o jogo ou não
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /** Atualiza o estado do gameOver
     * @param gameOver o novo estado do gameOver
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
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
    public void runGame() {}
    /** Para o jogo */
    public void endGame() {}
    
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

    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public String toString() {
        return "a";
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

}
