package ModelLayer.BoardLayer;

import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Ponto;

/**
 * Implementação da interface ObstacleMovement para obstáculos estáticos.
 * Responsabilidade: Implementar um comportamento de não rotação para obstáculos estáticos no jogo.
 * @version 1.0 10/05/2024
 * @author Hugo Conceição João Ventura Eduarda Pereira
 * @inv Esta classe não deve alterar o estado do polígono passado como argumento.
 */
public class StaticMovement implements ObstacleMovement {
    
    @Override
    public void rotateObstacle(Poligono poligono, Ponto<? extends Number> pontoPivo, int obstacleAngle) {
    }
}
