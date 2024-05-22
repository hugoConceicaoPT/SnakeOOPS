package ModelLayer.SnakeLayer;

/**
 * Enumeração das direções possíveis para a movimentação da cobra no jogo.
 * Responsabilidade: Definir as direções de movimento que a cobra pode adotar.
 * @version 1.0 22/05/2024
 * @author Hugo Conceição João Ventura Eduarda Pereira
 * @inv As direções são utilizadas para controlar a movimentação da cobra no plano do jogo, não sendo alteráveis.
 */
public enum Direction implements Cloneable {
	/**
     * Representa a direção para cima.
     */
    UP,
    
    /**
     * Representa a direção para baixo.
     */
    DOWN,
    
    /**
     * Representa a direção para a esquerda.
     */
    LEFT,
    
    /**
     * Representa a direção para a direita.
     */
    RIGHT;
}

