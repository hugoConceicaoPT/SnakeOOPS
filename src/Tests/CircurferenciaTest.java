package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import ModelLayer.SnakeLayer.Circunferencia;
import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;

/** Classe que representa uma classe teste para testar as funcionalidades da classe Circunferencia
    Responsabilidade: Testar as funcionalidades da classe Circunferencia
    @version 1.0 12/05/2024
    @author Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class CircurferenciaTest {

    @Test    
    public void testConstrutor0() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Circunferencia(new Ponto<Integer>(2,2),-1);
        });
    }

    @Test    
    public void testConstrutor1() {
        new Circunferencia(new Ponto<Integer>(2,2),1);
    }

    @Test
    public void intersectaTest(){
        Circunferencia circulo = new Circunferencia(new Ponto<Integer>(3, 3), 2);
        Poligono poligono = new Poligono("4 0 0 4 0 4 4 0 4");
        assertTrue(circulo.interseta(poligono));
        assertFalse(circulo.interseta(new Quadrado("6 3 9 3 9 0 6 0")));
        assertFalse(circulo.interseta(new Quadrado("5 3 5 1 7 1 7 3")));

    }
    @Test
    public void contidaNaCircunferenciaTest(){
        Circunferencia maiorCirculo = new Circunferencia(new Ponto<Integer>(5, 5), 5);
        Circunferencia menorCirculo = new Circunferencia(new Ponto<Integer>(7, 7), 2);
        assertTrue(menorCirculo.contidaNaCircunferencia(maiorCirculo));
        Circunferencia foraCirculo = new Circunferencia(new Ponto<Integer>(10, 10), 1);
        assertFalse(foraCirculo.contidaNaCircunferencia(maiorCirculo));
        Circunferencia intersectaBorda = new Circunferencia(new Ponto<Integer>(2, 5), 4);
        assertFalse(intersectaBorda.contidaNaCircunferencia(maiorCirculo));
        Circunferencia igualCirculo = new Circunferencia(new Ponto<Integer>(5, 5), 5);
        assertTrue(igualCirculo.contidaNaCircunferencia(maiorCirculo));
    }

    @Test
    public void contidaNoPoligonoTest(){
        Poligono poligono = new Poligono("4 0 0 10 0 10 10 0 10");
        Circunferencia circuloInterno = new Circunferencia(new Ponto<Integer>(5, 5), 2);
        assertTrue(circuloInterno.contidaNoPoligono(poligono));
        Circunferencia circuloParcial = new Circunferencia(new Ponto<Integer>(8, 8), 4);
        assertFalse(circuloParcial.contidaNoPoligono(poligono));
        Circunferencia circuloExterno = new Circunferencia(new Ponto<Integer>(15, 15), 1);
        assertFalse(circuloExterno.contidaNoPoligono(poligono));
        Quadrado quadrado = new Quadrado("8 0 10 0 10 2 8 2");
        Circunferencia circunferencia1 = new Circunferencia(new Ponto<Double>(8.5,0.5), 0.5);
        assertTrue(circunferencia1.contidaNoPoligono(quadrado));

    }
    @Test
    public void toStringTest(){
        Circunferencia circulo = new Circunferencia(new Ponto<Integer>(5, 5), 5);
        assertEquals("Circunferência de centro: (5,5) e raio: 5.0", circulo.toString());
    }


}
