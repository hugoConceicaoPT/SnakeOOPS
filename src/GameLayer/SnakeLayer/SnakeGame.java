package GameLayer.SnakeLayer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class SnakeGame implements KeyListener {
    private boolean gameOver;
    private Score score;
    private Snake snake;

    public SnakeGame (Snake snake) {
        this.snake = snake;
        this.score = new Score(0);
        this.gameOver = false;
    }

    public void runGame() {}
    public void endGame() {}
    
    @Override
    public void keyReleased(KeyEvent e) {
        System.out.printf("Tecla Solta!");
    }
    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

}
