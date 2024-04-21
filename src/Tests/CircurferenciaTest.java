package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import GameLayer.SnakeLayer.Circunferencia;
import GameLayer.SnakeLayer.Poligono;
import GameLayer.SnakeLayer.Ponto;

public class CircurferenciaTest {
    @Test
    public void intersectaTest(){
        Circunferencia circulo = new Circunferencia(new Ponto(3, 3), 2);
        List<Ponto> verticesPoligono = new ArrayList<>();
        verticesPoligono.add(new Ponto(0, 0));
        verticesPoligono.add(new Ponto(4, 0));
        verticesPoligono.add(new Ponto(4, 4));
        verticesPoligono.add(new Ponto(0, 4));
        Poligono poligono = new Poligono(verticesPoligono);
        assertTrue(circulo.interseta(poligono));
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
    List<Ponto> verticesPoligono = new ArrayList<>();
    verticesPoligono.add(new Ponto(0, 0));
    verticesPoligono.add(new Ponto(10, 0));
    verticesPoligono.add(new Ponto(10, 10));
    verticesPoligono.add(new Ponto(0, 10));
    Poligono poligono = new Poligono(verticesPoligono);
    Circunferencia circuloInterno = new Circunferencia(new Ponto(5, 5), 2);
    assertTrue(circuloInterno.contidaNoPoligono(poligono));
    Circunferencia circuloParcial = new Circunferencia(new Ponto(8, 8), 4);
    assertFalse(circuloParcial.contidaNoPoligono(poligono));
    Circunferencia circuloExterno = new Circunferencia(new Ponto(15, 15), 1);
    assertFalse(circuloExterno.contidaNoPoligono(poligono));
}


}
