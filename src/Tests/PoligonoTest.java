package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import GameLayer.SnakeLayer.Poligono;
import GameLayer.SnakeLayer.Quadrado;
import GameLayer.SnakeLayer.Triangulo;

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
        assertEquals("[(5,5), (8,6), (8,7), (5,7)]", new Poligono(input1).toString());
        String input2 = "3 9 3 7 1 9 1";
        assertEquals("[(9,3), (7,1), (9,1)]", new Poligono(input2).toString());
        String input3 = "4 1 2 5 6 8 7 12 14";
        assertEquals("[(1,2), (5,6), (8,7), (12,14)]", new Poligono(input3).toString());
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
        poligono1.rotateAngle(90);
        assertEquals("[(4,2), (4,4), (0,4), (0,2)]",poligono1.toString());
        String input2 = "3 2 2 3 4 4 2";
        Poligono poligono2 = new Poligono(input2);
        poligono2.rotateAngle(60);
        assertEquals("[(3,1), (2,3), (4,3)]", poligono2.toString());
    }

    @Test    
    public void testTranslate() {
        String input1 = "4 1 2 5 6 8 7 12 14";
        Poligono poligono1 = new Poligono(input1);
        poligono1.translate(-1, 3);
        assertEquals("[(0,5), (4,9), (7,10), (11,17)]",poligono1.toString());
        String input2 = "3 2 2 3 4 4 2";
        Poligono poligono2 = new Poligono(input2);
        poligono2.translate(3, 3);
        assertEquals("[(5,5), (6,7), (7,5)]", poligono2.toString());
    }

    @Test    
    public void testTranslateCentroide() {
        String input1 = "4 1 3 1 1 5 1 5 3";
        Poligono poligono1 = new Poligono(input1);
        poligono1.translateCentroide(8, 2);
        assertEquals("[(6,3), (6,1), (10,1), (10,3)]",poligono1.toString());
        String input2 = "3 2 2 4 4 4 2";
        Poligono poligono2 = new Poligono(input2);
        poligono2.translateCentroide(4, 5);
        assertEquals("[(2,4), (4,6), (4,4)]", poligono2.toString());
    }
    @Test
    public void testInterseta() {
        String input1 = "4 1 3 1 1 5 1 5 3";
        Poligono Poligono1 = new Poligono(input1);
        String input2 = "3 2 2 4 4 4 2";
        Poligono Poligono2 = new Poligono(input2);
        Assertions.assertTrue(Poligono2.interseta(Poligono1));

        String input3 = "4 1 3 1 1 5 1 5 3";
        Poligono Poligono3 = new Poligono(input3);
        String input4 = "3 0 4 0 5 3 4 3 5";
        Poligono Poligono4 = new Poligono(input4);
        assertFalse(Poligono4.interseta(Poligono3));
    }
}