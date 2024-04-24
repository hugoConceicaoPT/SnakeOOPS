package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ModelLayer.BoardLayer.Cell;
import ModelLayer.BoardLayer.CellType;

public class CellTest {
    @Test
    public void toStringTest() {
        Cell cell = new Cell();
        assertEquals(".", cell.toString());
        cell.setCellType(CellType.FOOD);
        assertEquals("F", cell.toString());
        cell.setCellType(CellType.HEAD);
        assertEquals("H", cell.toString());
    }
}
