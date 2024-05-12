package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Triangulo;

/** Classe que representa uma classe teste para testar as funcionalidades da classe Poligono
    Responsabilidade: Testar as funcionalidades da classe Poligono
    @version 1 12/05/2024
    @author Hugo Conceição, João Ventura, Eduarda Pereira
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
        assertEquals(6.5, poligono1.getCentroide().getX());
        assertEquals(6.25, poligono1.getCentroide().getY());

        String input2 = "3 9 3 7 1 9 1";
        Poligono poligono2 = new Poligono(input2);
        assertEquals(8.333333333333334, poligono2.getCentroide().getX());
        assertEquals(1.6666666666666667, poligono2.getCentroide().getY());

        String input3 = "4 1 2 5 6 8 7 12 14";
        Poligono poligono3 = new Poligono(input3);
        assertEquals(6.5, poligono3.getCentroide().getX());
        assertEquals(7.25, poligono3.getCentroide().getY());
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
        assertEquals("[(4.0,1.9999999999999998), (4.0,4.0), (0.0,4.0), (0.0,2.0)]",poligono1.toString());
        String input2 = "3 2 2 3 4 4 2";
        Poligono poligono2 = new Poligono(input2);
        poligono2.rotateAngle(60);
        assertEquals("[(3.0773502691896253,1.4673079295488947), (1.8452994616207483,3.3333333333333335), (4.077350269189626,3.199358737117772)]", poligono2.toString());
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
        assertEquals("[(6.0,3.0), (6.0,1.0), (10.0,1.0), (10.0,3.0)]",poligono1.toString());
        String input2 = "3 2 2 4 4 4 2";
        Poligono poligono2 = new Poligono(input2);
        poligono2.translateCentroide(4, 5);
        assertEquals("[(2.6666666666666665,4.333333333333334), (4.666666666666666,6.333333333333334), (4.666666666666666,4.333333333333334)]", poligono2.toString());
    }
    @Test
    public void testInterseta() {
        String input1 = "4 1 3 1 1 5 1 5 3";
        Poligono Poligono1 = new Poligono(input1);
        String input2 = "3 2 2 4 4 4 2";
        Poligono Poligono2 = new Poligono(input2);
        assertTrue(Poligono2.interseta(Poligono1));
        String input3 = "4 1 3 1 1 5 1 5 3";
        Poligono Poligono3 = new Poligono(input3);
        String input4 = "3 0 4 0 5 3 4 3 5";
        Poligono Poligono4 = new Poligono(input4);
        assertFalse(Poligono4.interseta(Poligono3));
        assertTrue(new Poligono("4 2 4 4 4 4 2 2 2").interseta(new Poligono("4 2 3 4 3 4 1 2 1")));
    }

    @Test
    public void testContemPonto() {
        String input1 = "4 1 1 1 5 5 5 5 1";
        Poligono poligono1 = new Poligono(input1);
        Ponto<Integer> ponto1 = new Ponto<Integer>(3, 3);
        assertTrue(poligono1.contemPonto(ponto1));
        assertTrue(poligono1.contemPonto(new Ponto<Integer>(2,1)));
        assertFalse(poligono1.contemPonto(new Ponto<Integer>(2,0))); 
        assertTrue(poligono1.contemPonto(new Ponto<Integer>(1,1))); 
        Ponto<Integer> ponto2 = new Ponto<Integer>(0, 0);
        assertFalse(poligono1.contemPonto(ponto2));
        assertTrue(poligono1.contemPonto(new Ponto<Integer>(2,2)));
        assertTrue(poligono1.contemPonto(new Ponto<Integer>(3,1)));
        String input2 = "4 10 10 10 15 15 15 15 10";
        Poligono poligono2 = new Poligono(input2);
        Ponto<Integer> ponto3 = new Ponto<Integer>(12, 12);
        assertTrue(poligono2.contemPonto(ponto3));
        Ponto<Integer> ponto4 = new Ponto<Integer>(10, 12);
        assertTrue(poligono2.contemPonto(ponto4));
        Ponto<Integer> ponto5 = new Ponto<Integer>(16, 16);
        assertFalse(poligono2.contemPonto(ponto5));
        assertTrue(new Triangulo("2 2 4 2 3 0").contemPonto(new Ponto<Integer>(3,0)));
        assertTrue(new Triangulo("2 2 4 2 3 0").contemPonto(new Ponto<Integer>(3,1)));
    }
    @Test
    public void testContida() {
        String input1 = "4 1 1 1 5 5 5 5 1";
        Poligono Poligono1 = new Poligono(input1);
        String input2 = "4 2 2 2 4 4 4 4 2";
        Poligono Poligono2 = new Poligono(input2);
        assertTrue(Poligono2.contida(Poligono1));
    
        String input3 = "4 10 10 10 15 15 15 15 10";
        Poligono Poligono3 = new Poligono(input3);
        String input4 = "4 12 12 12 13 13 13 13 12";
        Poligono Poligono4 = new Poligono(input4);
        assertTrue(Poligono4.contida(Poligono3));

        String input5 = "4 1 1 1 5 5 5 5 1";
        Poligono Poligono5 = new Poligono(input5);
        String input6 = "4 10 10 10 15 15 15 15 10";
        Poligono Poligono6 = new Poligono(input6);
        assertFalse(Poligono5.contida(Poligono6)); 
        assertFalse(new Poligono("4 7 6 7 4 9 4 9 6").contida(new Poligono("4 5 8 5 5 8 5 8 8")));
    }
    

    @Test
    public void testClone() throws CloneNotSupportedException {
        String input1 = "4 1 3 1 1 5 1 5 3";
        Poligono poligono1 = new Poligono(input1);
        Poligono poligono2 = (Poligono) poligono1.clone();
        assertTrue(poligono2.equals(poligono1));
        String input2 = "3 2 2 4 4 4 2";
        Poligono poligono3 = new Poligono(input2);
        assertFalse(poligono2.equals(poligono3));
    }
}