package ModelLayer.SnakeLayer;

import ModelLayer.BoardLayer.GameBoard;

/**
 * Classe que implementa a estratégia de movimento manual para a cobra no jogo.
 * Responsabilidade: Definir a próxima direção de movimento da cobra com base na entrada do jogador.
 * @version 1.0 10/05/2024
 * @autor Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class ManualMovementStrategy implements MovementStrategy {
    
    @Override
    public Direction setNextDirection(Snake snake, GameBoard gameBoard) {
        return snake.getNextDirection();
    }
}

