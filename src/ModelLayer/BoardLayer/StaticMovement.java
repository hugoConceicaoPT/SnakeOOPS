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
    
    /**
     * Implementação do método para rotação de obstáculos que, neste caso, não realiza nenhuma operação.
     * Este método é invocado para simular a "rotação" de um obstáculo estático, que efetivamente não muda sua orientação ou posição.
     * @param poligono O polígono representando o obstáculo que não será rotacionado.
     * @param pontoPivo O ponto de pivô que seria usado para a rotação, não utilizado neste contexto.
     */
    @Override
    public void rotateObstacle(Poligono poligono, Ponto<? extends Number> pontoPivo, int obstacleAngle) {
    }
}
