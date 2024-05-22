package ModelLayer.BoardLayer;

/**
 * Enumeração dos tipos de obstáculos disponíveis no jogo.
 * Responsabilidade: Representar as diferentes formas que os obstáculos podem assumir no tabuleiro.
 * @version 1.0 22/05/2024
 * @author Hugo Conceição João Ventura Eduarda Pereira
 * @inv Cada constante representa uma forma única de obstáculo.
 */
public enum ObstacleType {
	/**
     * Representa um obstáculo em formato de polígono.
     */
    POLYGON,
    
    /**
     * Representa um obstáculo em formato de retângulo.
     */
    RECTANGLE,
    
    /**
     * Representa um obstáculo em formato de triângulo.
     */
    TRIANGLE,
    
    /**
     * Representa um obstáculo em formato de quadrado.
     */
    SQUARE
}

