package ModelLayer.BoardLayer;

import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Ponto;

/**
 * Classe que implementa o movimento dinâmico dos obstáculos no tabuleiro do jogo.
 * Responsabilidade: Fornecer uma implementação para rotacionar com um certo ângulo, obstáculos dinamicamente ao redor de um ponto pivô.
 * @version 1.0 22/05/2024
 * @author Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class DynamicMovement implements ObstacleMovement {

    @Override
    public void rotateObstacle(Poligono poligono, Ponto<? extends Number> pontoPivo, int obstacleAngle) {     
        poligono.rotate(obstacleAngle, pontoPivo);   
    }   
}
