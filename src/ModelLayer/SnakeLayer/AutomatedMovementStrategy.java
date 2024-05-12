package ModelLayer.SnakeLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.BoardLayer.Obstacle;

/**
 * Classe que implementa a estratégia de movimento automatizado para a cobra no jogo.
 * Responsabilidade: Definir a próxima direção de movimento da cobra
 * baseando-se em obstáculos e na localização da comida.
 * @version 1.0 10/05/2024
 * @autor Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class AutomatedMovementStrategy implements MovementStrategy {

    @Override
    public Direction setNextDirection(Snake snake, GameBoard gameBoard) {
        if (gameBoard != null && gameBoard.getFood() != null) {
            return calculateDirectionToFood(snake, (Ponto<?>) gameBoard.getFood().getCentroide());
        }
        return avoidObstacles(snake, gameBoard);
    }

    /**
     * Calcula a direção da cobra para alcançar a comida no tabuleiro.
     * A direção é calculada de modo a evitar mudanças de direção opostas consecutivas.
     * @param snake A cobra que será movida.
     * @param foodCentroide O ponto representando o centro da comida.
     * @return A direção para alcançar a comida.
     */
    private Direction calculateDirectionToFood(Snake snake, Ponto<? extends Number> foodCentroide) {
        double headX = snake.getHead().getCentroide().getX().doubleValue();
        double headY = snake.getHead().getCentroide().getY().doubleValue();
        double foodX = foodCentroide.getX().doubleValue();
        double foodY = foodCentroide.getY().doubleValue();

        if (foodX < headX && snake.getCurrentDirection() != Direction.RIGHT) {
            return Direction.LEFT;
        } else if (foodX > headX && snake.getCurrentDirection() != Direction.LEFT) {
            return Direction.RIGHT;
        } else if (foodY < headY && snake.getCurrentDirection() != Direction.UP) {
            return Direction.DOWN;
        } else {
            return Direction.UP;
        }
    }

    /**
     * Tenta evitar obstáculos ao mover a cobra no tabuleiro.
     * Verifica todos os obstáculos disponíveis para determinar a direção segura.
     * @param snake A cobra que será movida.
     * @param gameBoard O tabuleiro do jogo contendo os obstáculos.
     * @return Uma nova direção que evita os obstáculos, ou a direção atual.
     */
    private Direction avoidObstacles(Snake snake, GameBoard gameBoard) {
        if (gameBoard != null && gameBoard.getListOfObstacles() != null) {
            for (Obstacle obstacle : gameBoard.getListOfObstacles()) {
                if (willCollideWithObstacleAfterMovingInDirection(snake, obstacle, snake.getCurrentDirection()) ||
                    willCollideWithBorderAfterMovingInDirection(snake, gameBoard, snake.getCurrentDirection())) {
                    Direction nextDirection = chooseRandomDirectionWithoutCollision(snake, obstacle);
                    if (nextDirection != null) {
                        return nextDirection;
                    }
                }
            }
        }
        return snake.getCurrentDirection();
    }

    /**
     * Verifica se a cobra irá colidir com a borda do tabuleiro após mover-se em uma direção específica.
     * @param snake A cobra que será movida.
     * @param gameBoard O tabuleiro do jogo contendo as dimensões.
     * @param direction A direção em que a cobra planeja se mover.
     * @return true se a cobra irá colidir com a borda, false caso contrário.
     */
    private boolean willCollideWithBorderAfterMovingInDirection(Snake snake, GameBoard gameBoard, Direction direction) {
        int nextX = snake.getHead().getCentroide().getX().intValue();
        int nextY = snake.getHead().getCentroide().getY().intValue();

        switch (direction) {
            case UP:
                nextY += snake.getArestaHeadLength();
                break;
            case DOWN:
                nextY -= snake.getArestaHeadLength();
                break;
            case LEFT:
                nextX -= snake.getArestaHeadLength();
                break;
            case RIGHT:
                nextX += snake.getArestaHeadLength();
                break;
        }
        return nextX < 0 || nextX > gameBoard.getWidthBoard() || nextY < 0 || nextY > gameBoard.getHeightBoard();
    }
    

    /**
     * Verifica se a cobra irá colidir com um obstáculo após mover-se em uma direção específica.
     * @param snake A cobra que será movida.
     * @param obstacle O obstáculo a ser evitado.
     * @param direction A direção em que a cobra planeja se mover.
     * @return true se a cobra irá colidir com o obstáculo, false caso contrário.
     */
    private boolean willCollideWithObstacleAfterMovingInDirection(Snake snake, Obstacle obstacle, Direction direction) {
        Snake snakeClone;
        try {
            snakeClone = (Snake) snake.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return true;
        }
        snakeClone.moveSquare(snakeClone.getHead(), snake.getCurrentDirection(), direction);
        return snakeClone.getHead().interseta(obstacle.getPoligono()) || snakeClone.getHead().contida(obstacle.getPoligono());
    }

    /**
     * Escolhe uma direção aleatória para a cobra mover-se que não resulte em colisão com o obstáculo.
     * @param snake A cobra que será movida.
     * @param obstacle O obstáculo a ser evitado.
     * @return Uma direção aleatória segura ou null se todas resultarem em colisão.
     */
    private Direction chooseRandomDirectionWithoutCollision(Snake snake, Obstacle obstacle) {
        List<Direction> availableDirections = new ArrayList<>();
        for (Direction dir : Direction.values()) {
            if (!willCollideWithObstacleAfterMovingInDirection(snake, obstacle, dir)) {
                availableDirections.add(dir);
            }
        }
        if (!availableDirections.isEmpty()) {
            Random rand = new Random();
            int index = rand.nextInt(availableDirections.size());
            return availableDirections.get(index);
        } else {
            return null;
        }
    }
}
