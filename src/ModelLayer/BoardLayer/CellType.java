package ModelLayer.BoardLayer;

/**
 * Enumeração que define os tipos de células possíveis no tabuleiro do jogo.
 * Responsabilidade: Representar diferentes tipos de células para elementos como comida, obstáculos, etc.
 * @version 1.0 10/05/2024
 * @autor Hugo Conceição, João Ventura, Eduarda Pereira
 */
public enum CellType {
    /**
     * Representa uma célula vazia no tabuleiro.
     */
    EMPTY,

    /**
     * Representa uma célula contendo comida.
     */
    FOOD,

    /**
     * Representa uma célula contendo um obstáculo.
     */
    OBSTACLE,

    /**
     * Representa a célula contendo a cabeça da cobra.
     */
    HEAD,

    /**
     * Representa as células contendo o corpo da cobra.
     */
    TAIL
}
