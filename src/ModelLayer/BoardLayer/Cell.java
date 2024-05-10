package ModelLayer.BoardLayer;

/**
 * Classe que representa uma célula no tabuleiro do jogo.
 * Responsabilidade: Armazenar o tipo de conteúdo da célula (cobra, comida, obstáculo, etc.).
 * @version 1.0 10/05/2024
 * @autor Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class Cell {

    // Tipo de conteúdo presente na célula, usando a enumeração `CellType`
    private CellType cellType;

    /**
     * Construtor que cria uma célula vazia para ser colocada no tabuleiro.
     * A célula é inicialmente marcada como vazia (`EMPTY`).
     */
    public Cell() {
        this.cellType = CellType.EMPTY;
    }

    /**
     * Obtém o tipo de conteúdo da célula.
     * @return O tipo de célula (por exemplo, cobra, comida, obstáculo, ou vazia).
     */
    public CellType getCellType() {
        return cellType;
    }

    /**
     * Define um novo tipo de conteúdo para a célula.
     * @param cellType O novo tipo de célula a ser definido.
     */
    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    /**
     * Representa a célula como uma string com base no tipo de conteúdo.
     * Cada tipo é representado por um caractere específico:
     * - `H`: Cabeça da cobra
     * - `T`: Corpo da cobra
     * - `F`: Comida
     * - `O`: Obstáculo
     * - `.`: Célula vazia
     * @return A representação da célula como string.
     */
    @Override
    public String toString() {
        switch (this.cellType) {
            case HEAD:
                return "H";
            case TAIL:
                return "T";
            case FOOD:
                return "F";
            case OBSTACLE:
                return "O";
            default:
                return ".";
        }
    }
}
