package Tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import SnakeLayer.Ponto;
import SnakeLayer.Quadrado;

/** Classe que representa uma classe teste para testar as funcionalidades da classe Quadrado
    Responsabilidade: Testar as funcionalidades da classe Quadrado
    @version 1.0 26/03/2024
    @author Hugo Conceição
 */
public class QuadradoTest {
    @Test
    public void testConstrutor0() {
        String input = "1 0 1 1 5 1 5 0";
        assertThrows(IllegalArgumentException.class, () -> {
            new Quadrado(input);
        });
    }

    @Test
    public void testConstrutor1() {
        String input = "1 1 3 1 3 3 1 3";
        new Quadrado(input);
    }

    @Test    
    public void testToString0() {
        String input1 = "1 1 1 4 4 4 4 1";
        assertEquals("Quadrado: [(1,1), (1,4), (4,4), (4,1)]", new Quadrado(input1).toString());
        String input2 = "0 0 0 2 2 2 2 0";
        assertEquals("Quadrado: [(0,0), (0,2), (2,2), (2,0)]", new Quadrado(input2).toString());
        String input3 = "3 3 3 6 6 6 6 3";
        assertEquals("Quadrado: [(3,3), (3,6), (6,6), (6,3)]", new Quadrado(input3).toString());
    }

    @Test    
    public void testRotate() {
        String input1 = "0 0 2 0 2 2 0 2";
        Quadrado quadrado1 = new Quadrado(input1);
        quadrado1.rotateAngle(90);
        assertEquals("Quadrado: [(2,0), (2,2), (0,2), (0,0)]", quadrado1.toString());
        String input2 = "1 1 3 1 3 3 1 3";
        Quadrado quadrado2 = new Quadrado(input2);
        quadrado2.rotate(180,new Ponto(5.0,3.0));
        assertEquals("Quadrado: [(9,5), (7,5), (7,3), (9,3)]", quadrado2.toString());
    }

    @Test    
    public void testTranslate() {
        String input1 = "0 0 2 0 2 2 0 2";
        Quadrado quadrado1 = new Quadrado(input1);
        quadrado1.translate(2, 0);
        assertEquals("Quadrado: [(2,0), (4,0), (4,2), (2,2)]", quadrado1.toString());
        String input2 = "1 1 3 1 3 3 1 3";
        Quadrado quadrado2 = new Quadrado(input2);
        quadrado2.translate(0, 5);
        assertEquals("Quadrado: [(1,6), (3,6), (3,8), (1,8)]", quadrado2.toString());
    }

    @Test    
    public void testTranslateCentroide() {
        String input1 = "0 0 2 0 2 2 0 2";
        Quadrado quadrado1 = new Quadrado(input1);
        quadrado1.translateCentroide(4, 3);
        assertEquals("Quadrado: [(3,2), (5,2), (5,4), (3,4)]", quadrado1.toString());
        String input2 = "1 1 3 1 3 3 1 3";
        Quadrado quadrado2 = new Quadrado(input2);
        quadrado2.translateCentroide(7, 2);
        assertEquals("Quadrado: [(6,1), (8,1), (8,3), (6,3)]", quadrado2.toString());
    }
}
