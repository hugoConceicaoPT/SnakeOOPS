package GameLayer.BoardLayer;


public class Cell {

    private CellType cellType;
    private int row;
    private int column;
    /** Construtor para criar uma célula para ser colocada na board 
     * @param row a linha da célula
     * @param column a coluna da célula
     */
    public Cell(int row, int column){
        this.row = row;
        this.column = column;
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
        return null;
    }
}
