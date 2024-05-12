package Tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ModelLayer.SnakeLayer.Ponto;

/** Classe que representa uma classe teste para testar as funcionalidades da classe Ponto
    Responsabilidade: Testar as funcionalidades da classe Ponto
    @version 1.0 12/05/2024
    @author Hugo Conceição, João Ventura, Eduarda Pereira
*/
public class PontoTest {
    @Test    
    public void testToString() {
        assertEquals("(2,2)", new Ponto<Integer>(2,2).toString());
        assertEquals("(3,2)", new Ponto<Integer> (3,2).toString());
        assertEquals("(4,4)", new Ponto<Integer>(4,4).toString());
    }

    @Test    
    public void testEquals() {
        assertTrue(new Ponto<Integer>(3,3).equals(new Ponto<Integer>(3,3)));
        assertFalse(new Ponto<Integer>(1,1).equals(new Ponto<Integer> (3,2)));
        assertTrue(new Ponto<Integer>(4,4).equals(new Ponto<Integer>(4,4)));
    }

    @Test
    public void testRotate() {
        Ponto<Integer> ponto1 = new Ponto<Integer>(3,3);
        ponto1.rotate(45, new Ponto<Integer>(1,1));
        assertEquals("(1.0000000000000002,3.82842712474619)",ponto1.toString());
        Ponto<Integer> ponto2 = new Ponto<Integer>(1,2);
        ponto2.rotate(90, new Ponto<Double>(0.0, 4.0));
        assertEquals("(2.0,5.0)",ponto2.toString());
    }

    @Test
    public void testTranslate() {
        Ponto<Integer> ponto1 = new Ponto<Integer>(4,5);
        ponto1.translate(1, 2);
        assertEquals("(5,7)",ponto1.toString());
        Ponto<Integer> ponto2 = new Ponto<Integer>(1,2);
        ponto2.translate(2, 2);
        assertEquals("(3,4)",ponto2.toString());
    }

    @Test
    public void testTranslateCentroide() {
        Ponto<Integer> ponto1 = new Ponto<Integer>(3,3);
        ponto1.translateCentroide(6, 4, new Ponto<Integer>(4,2));
        assertEquals("(5.0,5.0)",ponto1.toString());
        Ponto<Integer>ponto2 = new Ponto<Integer>(3,2);
        ponto2.translateCentroide(4, 5, new Ponto<Integer>(6,2));
        assertEquals("(1.0,5.0)",ponto2.toString());
    }
}