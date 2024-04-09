package Tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import SnakeLayer.Ponto;

/** Classe que representa uma classe teste para testar as funcionalidades da classe Ponto
    Responsabilidade: Testar as funcionalidades da classe Ponto
    @version 1.0 26/03/2024
    @author Hugo Conceição
*/
public class PontoTest {

    @Test
    public void testConstrutor0() {
        new Ponto(-1,1);
    }

    @Test
    public void testConstrutor1() {
        new Ponto(1,-1);
    }

    @Test
    public void testConstrutor2() {
        new Ponto(1,1);
    }

    @Test    
    public void testToString() {
        assertEquals("(2,2)", new Ponto(2,2).toString());
        assertEquals("(3,2)", new Ponto (3,2).toString());
        assertEquals("(4,4)", new Ponto(4,4).toString());
    }

    @Test    
    public void testEquals() {
        assertTrue(new Ponto(3,3).equals(new Ponto(3,3)));
        assertFalse(new Ponto(1,1).equals(new Ponto (3,2)));
        assertTrue(new Ponto(4,4).equals(new Ponto(4,4)));
    }

    @Test
    public void testRotate() {
        assertEquals("(1,4)",new Ponto(3,3).rotate(45, new Ponto(1.0,1.0)).toString());
        assertEquals("(2,5)",new Ponto(1,2).rotate(90, new Ponto(0.0,4.0)).toString());
    }

    @Test
    public void testTranslate() {
        assertEquals("(4,5)",new Ponto(3,3).translate(1,2).toString());
        assertEquals("(3,4)",new Ponto(1,2).translate(2,2).toString());
    }

    @Test
    public void testTranslateCentroide() {
        assertEquals("(5,5)",new Ponto(3,3).translateCentroide(6,4,new Ponto(4,2)).toString());
        assertEquals("(1,5)",new Ponto(3,2).translateCentroide(4,5,new Ponto(6,2)).toString());
    }
}