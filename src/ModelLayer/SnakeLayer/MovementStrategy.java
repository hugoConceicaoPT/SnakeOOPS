package ModelLayer.SnakeLayer;

import java.util.LinkedList;

public interface MovementStrategy {
    /** Move a cobra
     * @param nexDirection próxima direção
     * @param body corpo da cobra
     * @param currentDirection direção atual
     * @param arestaHeadLength comprimento da aresta da cabeça
     */
    public void move(Direction nexDirection, LinkedList<Quadrado> body, Direction currentDirection, int arestaHeadLength);
}
