package ViewLayer;

/**
 * Enumeração que define os tipos de células possíveis no tabuleiro do jogo.
 * Responsabilidade: Representar diferentes tipos de células para elementos como comida, obstáculos, etc.
 * @version 1.0 22/05/2024
 * @author Hugo Conceição, João Ventura, Eduarda Pereira
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
     * Representa a célula ocupada pela cabeça da cobra.
     */
    HEAD,
    
    /**
     * Representa uma célula ocupada pela cauda da cobra.
     */
    TAIL
}

