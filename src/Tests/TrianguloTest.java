package Tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import SnakeLayer.Ponto;
import SnakeLayer.Triangulo;

/** Classe que representa uma classe teste para testar as funcionalidades da classe Triângulo
    Responsabilidade: Testar as funcionalidades da classe Triângulo
    @version 1.0 26/03/2024
    @author Hugo Conceição
 */
public class TrianguloTest {
    @Test
    public void testConstrutor0() {
        String input = "2 0 4 0 6 0";
        assertThrows(IllegalArgumentException.class, () -> {
            new Triangulo(input);
        });
    }

    @Test
    public void testConstrutor1() {
        String input = "2 0 4 0 6 0 6 0";
        assertThrows(IllegalArgumentException.class, () -> {
            new Triangulo(input);
        });
    }
    
    @Test       
    public void testToString0() {
        String input1 = "7 1 9 1 9 3";
        assertEquals("Triangulo: [(7,1), (9,1), (9,3)]", new Triangulo(input1).toString());
        String input2 = "5 5 8 5 8 7";
        assertEquals("Triangulo: [(5,5), (8,5), (8,7)]", new Triangulo(input2).toString());
        String input3 = "2 1 4 1 3 4";
        assertEquals("Triangulo: [(2,1), (4,1), (3,4)]", new Triangulo(input3).toString());
    }

    @Test       
    public void testRotate() {
        String input1 = "2 1 4 1 3 4";
        Triangulo triangulo1 = new Triangulo(input1);
        triangulo1.rotateAngle(180);
        assertEquals("Triangulo: [(4,3), (2,3), (3,0)]", triangulo1.toString());
        String input2 = "5 5 8 5 8 7";
        Triangulo triangulo2 = new Triangulo(input2);
        triangulo2.rotateAngle(90);
        assertEquals("Triangulo: [(7,3), (7,6), (5,6)]", triangulo2.toString());
    }

    @Test       
    public void testTranslate() {
        String input1 = "2 1 4 1 3 4";
        Triangulo triangulo1 = new Triangulo(input1);
        triangulo1.translate(0,0);
        assertEquals("Triangulo: [(2,1), (4,1), (3,4)]", triangulo1.toString());
        String input2 = "5 5 8 5 8 7";
        Triangulo triangulo2 = new Triangulo(input2);
        triangulo2.translate(3,7);
        assertEquals("Triangulo: [(8,12), (11,12), (11,14)]", triangulo2.toString());
    }

    @Test       
    public void testTranslateCentroide() {
        String input1 = "2 1 4 1 4 3";
        Triangulo triangulo1 = new Triangulo(input1);
        triangulo1.translateCentroide(7,1);
        assertEquals("Triangulo: [(5,1), (7,1), (7,3)]", triangulo1.toString());
    }
}
