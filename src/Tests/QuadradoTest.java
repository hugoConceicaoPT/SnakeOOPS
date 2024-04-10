package Tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import SnakeLayer.Poligono;
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
        assertEquals("Quadrado: [(2,0), (2,2), (0,2), (0,0)]", quadrado1.rotateAngle(90).toString());
        String input2 = "1 1 3 1 3 3 1 3";
        Quadrado quadrado2 = new Quadrado(input2);
        assertEquals("Quadrado: [(2,1), (3,2), (2,3), (1,2)]", quadrado2.rotateAngle(45).toString());
        assertEquals("Quadrado: [(1,4), (2,3), (4,4), (2,6)]", new Quadrado(input2).rotate(-45, new Ponto(5,3)).toString());
    }

    @Test    
    public void testTranslate() {
        String input1 = "0 0 2 0 2 2 0 2";
        assertEquals("Quadrado: [(2,0), (4,0), (4,2), (2,2)]", new Poligono(input1).translate(2,0).toString());
        String input2 = "1 1 3 1 3 3 1 3";
        assertEquals("Quadrado: [(1,6), (3,6), (3,8), (1,8)]", new Poligono(input2).translate(0,5).toString());
    }

    @Test    
    public void testTranslateCentroide() {
        String input1 = "0 0 2 0 2 2 0 2";
        assertEquals("Quadrado: [(3,2), (5,2), (5,4), (3,4)]", new Quadrado(input1).translateCentroide(4,3).toString());
        String input2 = "1 1 3 1 3 3 1 3";
        assertEquals("Quadrado: [(6,1), (8,1), (8,3), (6,3)]", new Quadrado(input2).translateCentroide(7,2).toString());
    }
}
