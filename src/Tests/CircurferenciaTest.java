package Tests;

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
}
