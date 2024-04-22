package Tests;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import ModelLayer.SnakeLayer.Retangulo;

/** Classe que representa uma classe teste para testar as funcionalidades da classe Retângulo
    Responsabilidade: Testar as funcionalidades da classe Retângulo
    @version 1.0 26/03/2024
    @author Hugo Conceição
 */
public class RetanguloTest {
    
    @Test
    public void testConstrutor0() {
        String input = "4 0 4 2 0 2 2 0";
        assertThrows(IllegalArgumentException.class, () -> {
            new Retangulo(input);
        });
    }

    @Test
    public void testConstrutor1() {
        String input = "4 0 4 2 0 2";
        assertThrows(IllegalArgumentException.class, () -> {
            new Retangulo(input);
        });
    }

    @Test
    public void testConstrutor2() {
        String input = "1 1 4 1 4 3 1 3";
        new Retangulo(input);
    }
}

