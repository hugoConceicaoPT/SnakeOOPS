package Tests;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.SegmentoReta;

/** Classe que representa uma classe teste para testar as funcionalidades da classe SegmentoReta
    Responsabilidade: Testar as funcionalidades da classe SegmentoReta
    @version 1.0 26/03/2024
    @author Hugo Conceição
 */
public class SegmentoRetaTest {
    
    @Test
    public void testConstrutorPontosIguais0() {

        Ponto<Integer> ponto = new Ponto<Integer>(2, 2);
        assertThrows(IllegalArgumentException.class, () -> {
            new SegmentoReta(ponto,ponto);
        });   
    }

    @Test
    public void testConstrutorPontosIguais1() {

        Ponto<Integer> ponto1 = new Ponto<Integer>(2, 2);
        Ponto<Integer> ponto2 = new Ponto<Integer>(2, 3);
        new SegmentoReta(ponto1,ponto2);     
    }

    @Test    
    public void testEquals() {
        assertFalse(new SegmentoReta(new Ponto<Integer>(3,4),new Ponto<Integer>(5,3)).equals(new SegmentoReta(new Ponto<Integer>(5,5), new Ponto<Integer>(10,3))));
        assertTrue(new SegmentoReta(new Ponto<Integer>(2,3), new Ponto<Integer>(5,3)).equals(new SegmentoReta(new Ponto<Integer>(5,3), new Ponto<Integer>(2,3))));
    }

    @Test    
    public void testArestasSeCruzam() {
        SegmentoReta segmentoReta1 = new SegmentoReta(new Ponto<Integer>(0,2), new Ponto<Integer>(4,2));
        SegmentoReta segmentoReta2 = new SegmentoReta(new Ponto<Integer>(2,0), new Ponto<Integer>(2,3));
        assertTrue(segmentoReta1.seCruzam(segmentoReta2));
        SegmentoReta segmentoReta3 = new SegmentoReta(new Ponto<Integer>(2,0), new Ponto<Integer>(7,2));
        assertFalse(segmentoReta1.seCruzam(segmentoReta3));
        SegmentoReta segmentoReta4 = new SegmentoReta(new Ponto<Integer>(5,0), new Ponto<Integer>(5,4));
        assertFalse(segmentoReta1.seCruzam(segmentoReta4));
        assertTrue(segmentoReta3.seCruzam(segmentoReta4));
    }

    @Test
    public void contemPontoTest() {
        SegmentoReta segmentoReta1 = new SegmentoReta(new Ponto<Integer>(0,2), new Ponto<Integer>(4,2));
        assertTrue(segmentoReta1.contemPonto(new Ponto<Integer>(2,2)));
        assertFalse(segmentoReta1.contemPonto(new Ponto<Integer>(2,3)));
        assertTrue(segmentoReta1.contemPonto(new Ponto<Integer>(3,2)));
        assertFalse(segmentoReta1.contemPonto(new Ponto<Integer>(3,1)));
        
    }
}
