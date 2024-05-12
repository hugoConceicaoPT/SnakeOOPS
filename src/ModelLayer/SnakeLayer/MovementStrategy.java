package ModelLayer.SnakeLayer;

import ModelLayer.BoardLayer.GameBoard;
/**
 * Classe que implementa a estratégia de movimento para a cobra no jogo.
 * Responsabilidade: Definir a próxima direção de movimento da cobra com base na entrada do jogador.
 * @version 1.0 10/05/2024
 * @autor Hugo Conceição, João Ventura, Eduarda Pereira
 */

public interface MovementStrategy {
    /**
     *  Define a próxima direção de movimento da cobra
     * @param snake a cobra do jogo
     * @param gameBoard a arena do jogo
     * @return a próxima direção que a cobra se vai mover
     */
    public Direction setNextDirection(Snake snake,GameBoard gameBoard);
}
