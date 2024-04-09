package Tests;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import SnakeLayer.Ponto;
import SnakeLayer.Reta;

/** Classe que representa uma classe teste para testar as funcionalidades da classe Reta
    Responsabilidade: Testar as funcionalidades da classe Reta
    @version 1.0 26/03/2024
    @author Hugo Conceição
 */
public class RetaTest {
    
    @Test
    public void testConstrutorPontosIguais0() {

        Ponto ponto = new Ponto(1, 1);
        new Reta(ponto,ponto);
    }

    @Test
    public void testConstrutorPontosIguais1() {

        Ponto ponto1 = new Ponto(1, 1);
        Ponto ponto2 = new Ponto(2, 1);
        new Reta(ponto1,ponto2);
    }

    @Test    
    public void testColinearidade() {
        Reta reta1 = new Reta(new Ponto(0,2), new Ponto(3,2));
        assertTrue(reta1.colineares(new Ponto(6,2)));
        assertFalse(reta1.colineares(new Ponto(5,4)));
        assertFalse(reta1.colineares(new Ponto(5,0)));
        assertTrue(reta1.colineares(new Ponto(8,2)));
    }
}
