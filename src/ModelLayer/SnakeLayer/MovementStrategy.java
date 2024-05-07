package ModelLayer.SnakeLayer;

import ModelLayer.BoardLayer.GameBoard;

public interface MovementStrategy {
    /** Move a cobra
     * @param nexDirection próxima direção
     * @param body corpo da cobra
     * @param currentDirection direção atual
     * @param arestaHeadLength comprimento da aresta da cabeça
     */
    public Direction setNextDirection(Snake snake,GameBoard gameBoard);
}
