package ModelLayer.BoardLayer;

import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Ponto;

/**
 * Classe que implementa o movimento dinâmico dos obstáculos no tabuleiro do jogo.
 * Responsabilidade: Fornecer uma implementação para rotacionar obstáculos dinamicamente ao redor de um ponto pivô.
 * @version 1.0 10/05/2024
 * @autor Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class DynamicMovement implements ObstacleMovement {

    /**
     * Rotaciona o polígono (obstáculo) ao redor de um ponto pivô em 90 graus.
     * A implementação pode ser expandida para suportar outras formas de movimento dinâmico.
     * @param poligono O polígono que representa o obstáculo a ser rotacionado.
     * @param pontoPivo O ponto pivô ao redor do qual o obstáculo deve girar.
     */
    @Override
    public void rotateObstacle(Poligono poligono, Ponto<? extends Number> pontoPivo) {
        // Rotaciona o polígono ao redor do ponto pivô em 90 graus
        poligono.rotate(90, pontoPivo);
    }
}
