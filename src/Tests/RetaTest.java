package Tests;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Reta;

/** Classe que representa uma classe teste para testar as funcionalidades da classe Reta
    Responsabilidade: Testar as funcionalidades da classe Reta
    @version 1.0 12/05/2024
    @author Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class RetaTest {
    
    @Test
    public void testConstrutorPontosIguais0() {

        Ponto<Integer> ponto = new Ponto<Integer>(1, 1);
        assertThrows(IllegalArgumentException.class, () -> {
            new Reta(ponto,ponto);
        });
    }

    @Test
    public void testConstrutorPontosIguais1() {

        Ponto<Integer> ponto1 = new Ponto<Integer>(1, 1);
        Ponto<Integer> ponto2 = new Ponto<Integer>(2, 1);
        new Reta(ponto1,ponto2);
    }

    @Test    
    public void testColinearidade() {
        Reta reta1 = new Reta(new Ponto<Integer>(0,2), new Ponto<Integer>(3,2));
        assertTrue(reta1.colineares(new Ponto<Integer>(6,2)));
        assertFalse(reta1.colineares(new Ponto<Integer>(5,4)));
        assertFalse(reta1.colineares(new Ponto<Integer>(5,0)));
        assertTrue(reta1.colineares(new Ponto<Integer>(8,2)));
    }
}
