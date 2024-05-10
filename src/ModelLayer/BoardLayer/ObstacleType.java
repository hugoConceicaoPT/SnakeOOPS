package ModelLayer.BoardLayer;

/**
 * Enumeração dos tipos de obstáculos disponíveis no jogo.
 * Responsabilidade: Representar as diferentes formas que os obstáculos podem assumir no tabuleiro.
 * @version 1.0 10/05/2024
 * @author Hugo Conceição João Ventura Eduarda Pereira
 * @inv Cada constante representa uma forma única de obstáculo.
 */
public enum ObstacleType {
    POLYGON,   // Representa um obstáculo poligonal.
    RECTANGLE, // Representa um obstáculo retangular.
    TRIANGLE,  // Representa um obstáculo triangular.
    SQUARE     // Representa um obstáculo quadrado.
}

