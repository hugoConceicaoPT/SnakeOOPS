package ModelLayer.SnakeLayer;

import ModelLayer.BoardLayer.GameBoard;
/**
 * Classe que implementa a estratégia de movimento para a cobra no jogo.
 * Responsabilidade: Definir a próxima direção de movimento da cobra com base na entrada do jogador.
 * @version 1.0 10/05/2024
 * @autor Hugo Conceição, João Ventura, Eduarda Pereira
 */

public interface MovementStrategy {
    /** Move a cobra
     * @param nexDirection próxima direção
     * @param body corpo da cobra
     * @param currentDirection direção atual
     * @param arestaHeadLength comprimento da aresta da cabeça
     */
    public Direction setNextDirection(Snake snake,GameBoard gameBoard);
}
