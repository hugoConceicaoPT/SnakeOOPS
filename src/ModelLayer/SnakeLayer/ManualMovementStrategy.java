package ModelLayer.SnakeLayer;

import ModelLayer.BoardLayer.GameBoard;

/**
 * Classe que implementa a estratégia de movimento manual para a cobra no jogo.
 * Responsabilidade: Definir a próxima direção de movimento da cobra com base na entrada do jogador.
 * @version 1.0 10/05/2024
 * @autor Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class ManualMovementStrategy implements MovementStrategy {
    
    /**
     * Define a próxima direção de movimento da cobra com base na próxima direção
     * fornecida pela entrada do jogador. O método simplesmente retorna a direção
     * que foi definida manualmente para a cobra.
     * @param snake A cobra cujo movimento será controlado manualmente.
     * @param gameBoard O tabuleiro do jogo (não usado nesta estratégia).
     * @return A próxima direção de movimento definida pelo jogador.
     */
    @Override
    public Direction setNextDirection(Snake snake, GameBoard gameBoard) {
        // Retorna a próxima direção que já foi definida manualmente para a cobra.
        return snake.getNextDirection();
    }
}

