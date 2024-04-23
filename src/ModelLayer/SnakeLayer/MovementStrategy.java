package ModelLayer.SnakeLayer;

import java.util.LinkedList;

public interface MovementStrategy {
      /** Move a cobra 
     * @param nextDirection a próxima direção que a cobra vai tomar 
     */
    public void move(Direction nexDirection, LinkedList<Quadrado> body, Direction currentDirection, int arestaHeadLength);
}
