package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ModelLayer.SnakeLayer.Circunferencia;
import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;

public class CircurferenciaTest {
    @Test
    public void intersectaTest(){
        Circunferencia circulo = new Circunferencia(new Ponto(3, 3), 2);
        Poligono poligono = new Poligono("4 0 0 4 0 4 4 0 4");
        assertTrue(circulo.interseta(poligono));
        assertFalse(circulo.interseta(new Quadrado("6 3 9 3 9 0 6 0")));
        assertFalse(circulo.interseta(new Quadrado("5 3 5 1 7 1 7 3")));

    }
    @Test
    public void contidaNaCircunferenciaTest(){
        Circunferencia maiorCirculo = new Circunferencia(new Ponto(5, 5), 5);
        Circunferencia menorCirculo = new Circunferencia(new Ponto(7, 7), 2);
        assertTrue(menorCirculo.contidaNaCircunferencia(maiorCirculo));
        Circunferencia foraCirculo = new Circunferencia(new Ponto(10, 10), 1);
        assertFalse(foraCirculo.contidaNaCircunferencia(maiorCirculo));
        Circunferencia intersectaBorda = new Circunferencia(new Ponto(2, 5), 4);
        assertFalse(intersectaBorda.contidaNaCircunferencia(maiorCirculo));
        Circunferencia igualCirculo = new Circunferencia(new Ponto(5, 5), 5);
        assertTrue(igualCirculo.contidaNaCircunferencia(maiorCirculo));
    }

    @Test
    public void contidaNoPoligonoTest(){
        Poligono poligono = new Poligono("4 0 0 10 0 10 10 0 10");
        Circunferencia circuloInterno = new Circunferencia(new Ponto(5, 5), 2);
        assertTrue(circuloInterno.contidaNoPoligono(poligono));
        Circunferencia circuloParcial = new Circunferencia(new Ponto(8, 8), 4);
        assertFalse(circuloParcial.contidaNoPoligono(poligono));
        Circunferencia circuloExterno = new Circunferencia(new Ponto(15, 15), 1);
        assertFalse(circuloExterno.contidaNoPoligono(poligono));
        Quadrado quadrado = new Quadrado("8 0 10 0 10 2 8 2");
        Circunferencia circunferencia1 = new Circunferencia(new Ponto(8.5,0.5), 0.5);
        assertTrue(circunferencia1.contidaNoPoligono(quadrado));

    }


}
