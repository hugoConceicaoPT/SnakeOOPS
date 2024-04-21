package Tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import GameLayer.SnakeLayer.Ponto;

/** Classe que representa uma classe teste para testar as funcionalidades da classe Ponto
    Responsabilidade: Testar as funcionalidades da classe Ponto
    @version 1.0 26/03/2024
    @author Hugo Conceição
*/
public class PontoTest {
    @Test    
    public void testToString() {
        assertEquals("(2.0,2.0)", new Ponto(2,2).toString());
        assertEquals("(3.0,2.0)", new Ponto (3,2).toString());
        assertEquals("(4.0,4.0)", new Ponto(4,4).toString());
    }

    @Test    
    public void testEquals() {
        assertTrue(new Ponto(3,3).equals(new Ponto(3,3)));
        assertFalse(new Ponto(1,1).equals(new Ponto (3,2)));
        assertTrue(new Ponto(4,4).equals(new Ponto(4,4)));
    }

    @Test
    public void testRotate() {
        Ponto ponto1 = new Ponto(3,3);
        ponto1.rotate(45, new Ponto(1.0,1.0));
        assertEquals("(1.0,4.0)",ponto1.toString());
        Ponto ponto2 = new Ponto(1,2);
        ponto2.rotate(90, new Ponto(0.0, 4.0));
        assertEquals("(2.0,5.0)",ponto2.toString());
    }

    @Test
    public void testTranslate() {
        Ponto ponto1 = new Ponto(4,5);
        ponto1.translate(1, 2);
        assertEquals("(5.0,7.0)",ponto1.toString());
        Ponto ponto2 = new Ponto(1,2);
        ponto2.translate(2, 2);
        assertEquals("(3.0,4.0)",ponto2.toString());
    }

    @Test
    public void testTranslateCentroide() {
        Ponto ponto1 = new Ponto(3,3);
        ponto1.translateCentroide(6, 4, new Ponto(4,2));
        assertEquals("(5.0,5.0)",ponto1.toString());
        Ponto ponto2 = new Ponto(3,2);
        ponto2.translateCentroide(4, 5, new Ponto(6,2));
        assertEquals("(1.0,5.0)",ponto2.toString());
    }
}