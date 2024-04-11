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

}
