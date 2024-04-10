package Tests;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import SnakeLayer.Poligono;
import SnakeLayer.Ponto;
import SnakeLayer.Quadrado;
import SnakeLayer.Triangulo;

/** Classe que representa uma classe teste para testar as funcionalidades da classe Poligono
    Responsabilidade: Testar as funcionalidades da classe Poligono
    @version 1.0 26/03/2024
    @author Hugo Conceição
 */
public class PoligonoTest {
    
    @Test    
    public void testConstrutor3Pontos0() {
        String input = "2 1 0 2 1";
        assertThrows(IllegalArgumentException.class, () -> {
            new Poligono(input);
        });
    }

    @Test    
    public void testConstrutor3Pontos1() {
        String input = "1 1 0";
        assertThrows(IllegalArgumentException.class, () -> {
            new Poligono(input);
        });
    }


    @Test    
    public void testToString0() {
        String input1 = "4 5 5 8 6 8 7 5 7";
        assertEquals("Poligono de 4 vertices: [(5,5), (8,6), (8,7), (5,7)]", new Poligono(input1).toString());
        String input2 = "3 9 3 7 1 9 1";
        assertEquals("Poligono de 3 vertices: [(9,3), (7,1), (9,1)]", new Poligono(input2).toString());
        String input3 = "4 1 2 5 6 8 7 12 14";
        assertEquals("Poligono de 4 vertices: [(1,2), (5,6), (8,7), (12,14)]", new Poligono(input3).toString());
    }

    @Test    
    public void testCentroide() {
        String input1 = "4 5 5 8 6 8 7 5 7";
        Poligono poligono1 = new Poligono(input1);
        assertEquals(6.5, poligono1.getCentroide().getxDouble());
        assertEquals(6.25, poligono1.getCentroide().getyDouble());

        String input2 = "3 9 3 7 1 9 1";
        Poligono poligono2 = new Poligono(input2);
        assertEquals(8.333333333333334, poligono2.getCentroide().getxDouble());
        assertEquals(1.6666666666666667, poligono2.getCentroide().getyDouble());

        String input3 = "4 1 2 5 6 8 7 12 14";
        Poligono poligono3 = new Poligono(input3);
        assertEquals(6.5, poligono3.getCentroide().getxDouble());
        assertEquals(7.25, poligono3.getCentroide().getyDouble());
    }

    @Test    
    public void testEquals() {
        assertTrue(new Poligono("3 9 3 7 1 9 1").equals(new Triangulo("7 1 9 1 9 3")));
        assertTrue(new Quadrado("1 1 1 4 4 4 4 1").equals(new Quadrado("4 1 1 1 1 4 4 4")));
        assertFalse(new Poligono("4 1 1 3 2 5 2 7 1").equals(new Poligono("4 1 2 2 3 4 4 7 1")));
        assertFalse(new Triangulo("0 0 3 3 6 0").equals(new Triangulo("1 1 3 3 5 1")));
    }

    @Test    
    public void testRotate() {
        String input1 = "4 1 1 3 1 3 5 1 5";
        Poligono poligono1 = new Poligono(input1);
        assertEquals("Poligono de 4 vertices: [(4,2), (4,4), (0,4), (0,2)]",poligono1.rotateAngle(90).toString());
        String input2 = "3 2 2 3 4 4 2";
        Poligono poligono2 = new Poligono(input2);
        assertEquals("Poligono de 3 vertices: [(3,1), (2,3), (4,3)]", poligono2.rotateAngle(60).toString());
        String input3 = "4 1 1 3 1 3 5 1 5";
        assertEquals("Poligono de 4 vertices: [(3,3), (3,1), (7,1), (7,3)]", new Poligono(input3).rotate(-90,new Ponto(3,1)).toString());
    }

    @Test    
    public void testTranslate() {
        String input1 = "4 1 2 5 6 8 7 12 14";
        assertEquals("Poligono de 4 vertices: [(0,5), (4,9), (7,10), (11,17)]",new Poligono(input1).translate(-1, 3).toString());
        String input2 = "3 2 2 3 4 4 2";
        assertEquals("Poligono de 3 vertices: [(5,5), (6,7), (7,5)]", new Poligono(input2).translate(3,3).toString());
    }

    @Test    
    public void testTranslateCentroide() {
        String input1 = "4 1 3 1 1 5 1 5 3";
        assertEquals("Poligono de 4 vertices: [(6,3), (6,1), (10,1), (10,3)]",new Poligono(input1).translateCentroide(8, 2).toString());
        String input2 = "3 2 2 4 4 4 2";
        assertEquals("Poligono de 3 vertices: [(2,4), (4,6), (4,4)]", new Poligono(input2).translateCentroide(4,5).toString());
    }
}