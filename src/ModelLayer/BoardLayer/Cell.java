package ModelLayer.BoardLayer;


public class Cell {

    private CellType cellType;
    /** Construtor para criar uma célula para ser colocada na board 
     * @param row a linha da célula
     * @param column a coluna da célula
     */
    public Cell() {
        this.cellType = CellType.EMPTY;
    }
    
    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

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
            case SNAKEFOOD:
                return "#";
            default:
                return ".";
        }
    }
}
