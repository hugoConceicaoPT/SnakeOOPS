package GameLayer.BoardLayer;


public class Cell {

    private CellType cellType;
    private int row;
    private int column;
    public Cell(int row, int column){
        this.row = row;
        this.column = column;
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
