package SnakeLayer;

public class SnakeGame {
    private boolean gameOver;
    private Score score;
    private Snake snake;

    public SnakeGame (Snake snake, Score score, boolean gameOver) {
        this.snake = snake;
        this.score = score;
        this.gameOver = gameOver;
    }

    public void runGame(boolean gameOver) {}
    public void endGame() {}
    public Snake changeSnakeDirection() {return null;}
}
