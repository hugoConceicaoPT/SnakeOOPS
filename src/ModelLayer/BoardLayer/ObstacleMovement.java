package ModelLayer.BoardLayer;

import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Ponto;

/**
 * Interface para definir movimentos de obstáculos no jogo.
 * Responsabilidade: Permitir a implementação de rotação de obstáculos.
 * @version 1.0 10/05/2024
 * @author Hugo Conceição João Ventura Eduarda Pereira
 * @inv Deve ser implementada por classes que manipulam a rotação de obstáculos no tabuleiro.
 */
public interface ObstacleMovement {
    void rotateObstacle(Poligono poligono, Ponto<? extends Number> pontoPivo, int obstacleAngle);
}
