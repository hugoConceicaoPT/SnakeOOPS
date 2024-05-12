package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ModelLayer.BoardLayer.Cell;
import ModelLayer.BoardLayer.CellType;

/** Classe que representa uma classe teste para testar as funcionalidades da classe Cell
    Responsabilidade: Testar as funcionalidades da classe Cell
    @version 1.0 12/05/2024
    @author Hugo Conceição, João Ventura, Eduarda Pereira
 */
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
