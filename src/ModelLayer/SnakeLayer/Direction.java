package ModelLayer.SnakeLayer;

/**
 * Enumeração das direções possíveis para a movimentação da cobra no jogo.
 * Responsabilidade: Definir as direções de movimento que a cobra pode adotar.
 * @version 1.0 10/05/2024
 * @author Hugo Conceição João Ventura Eduarda Pereira
 * @inv As direções são utilizadas para controlar a movimentação da cobra no plano do jogo, não sendo alteráveis.
 */
public enum Direction implements Cloneable {
    UP,    // Direção para cima.
    DOWN,  // Direção para baixo.
    LEFT,  // Direção para a esquerda.
    RIGHT; // Direção para a direita.
}

