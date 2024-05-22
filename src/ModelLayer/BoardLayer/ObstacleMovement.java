package ModelLayer.BoardLayer;

import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Ponto;

/**
 * Interface para definir movimentos de obstáculos no jogo.
 * Responsabilidade: Permitir a implementação de rotação de obstáculos.
 * @version 1.0 22/05/2024
 * @author Hugo Conceição João Ventura Eduarda Pereira
 * @inv Deve ser implementada por classes que manipulam a rotação de obstáculos no tabuleiro.
 */
public interface ObstacleMovement {
    /**
     * Rotaciona o polígono (obstáculo) ao redor de um ponto pivô em 90 graus.
     * A implementação pode ser expandida para suportar outras formas de movimento dinâmico.
     * @param poligono O polígono que representa o obstáculo a ser rotacionado.
     * @param pontoPivo O ponto pivô ao redor do qual o obstáculo deve girar.
     */
    void rotateObstacle(Poligono poligono, Ponto<? extends Number> pontoPivo, int obstacleAngle);
}
