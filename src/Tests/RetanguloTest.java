package Tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import GameLayer.SnakeLayer.Ponto;
import GameLayer.SnakeLayer.Retangulo;

/** Classe que representa uma classe teste para testar as funcionalidades da classe Retângulo
    Responsabilidade: Testar as funcionalidades da classe Retângulo
    @version 1.0 26/03/2024
    @author Hugo Conceição
 */
public class RetanguloTest {
    
    @Test
    public void testConstrutor0() {
        String input = "4 0 4 2 0 2 2 0";
        assertThrows(IllegalArgumentException.class, () -> {
            new Retangulo(input);
        });
    }

    @Test
    public void testConstrutor1() {
        String input = "4 0 4 2 0 2";
        assertThrows(IllegalArgumentException.class, () -> {
            new Retangulo(input);
        });
    }

    @Test
    public void testConstrutor2() {
        String input = "1 1 4 1 4 3 1 3";
        new Retangulo(input);
    }

    @Test    
    public void testToString0() {
        String input1 = "1 1 3 1 3 5 1 5";
        assertEquals("Retangulo: [(1,1), (3,1), (3,5), (1,5)]", new Retangulo(input1).toString());
        String input2 = "1 1 5 1 5 3 1 3";
        assertEquals("Retangulo: [(1,1), (5,1), (5,3), (1,3)]", new Retangulo(input2).toString());
        String input3 = "1 2 5 2 5 4 1 4";
        assertEquals("Retangulo: [(1,2), (5,2), (5,4), (1,4)]", new Retangulo(input3).toString());
    }

    @Test    
    public void testRotate() {
        String input1 = "1 1 3 1 3 5 1 5";
        Retangulo retangulo = new Retangulo(input1);
        retangulo.rotateAngle(90);
        assertEquals("Retangulo: [(4,2), (4,4), (0,4), (0,2)]",retangulo.toString());
        Retangulo retangulo2 = new Retangulo(input1);
        retangulo2.rotate(270, new Ponto(3.0,1.0));
        assertEquals("Retangulo: [(3,3), (3,1), (7,1), (7,3)]", retangulo2.toString());
    }

    @Test    
    public void testTranslate() {
        String input1 = "1 1 3 1 3 5 1 5";
        Retangulo retangulo = new Retangulo(input1);
        assertEquals("Retangulo: [(3,3), (5,3), (5,7), (3,7)]",retangulo.toString());
        String input2 = "1 1 3 1 3 5 1 5";
        Retangulo retangulo2 = new Retangulo(input2);
        assertEquals("Retangulo: [(2,2), (4,2), (4,6), (2,6)]", retangulo2.toString());
    }

    @Test    
    public void testTranslateCentroide() {
        String input1 = "1 1 3 1 3 5 1 5";
        Retangulo retangulo = new Retangulo(input1);
        retangulo.translateCentroide(6,4);
        assertEquals("Retangulo: [(5,2), (7,2), (7,6), (5,6)]",retangulo.toString());
        String input2 = "1 1 3 1 3 5 1 5";
        Retangulo retangulo2 = new Retangulo(input2);
        retangulo2.translateCentroide(7,2);
        assertEquals("Retangulo: [(6,0), (8,0), (8,4), (6,4)]", retangulo2.toString());
    }
}

