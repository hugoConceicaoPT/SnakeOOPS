package Tests;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ModelLayer.SnakeLayer.Quadrado;


/** Classe que representa uma classe teste para testar as funcionalidades da classe Quadrado
    Responsabilidade: Testar as funcionalidades da classe Quadrado
    @version 1.0 26/03/2024
    @author Hugo Conceição
 */
public class QuadradoTest {
    @Test
    public void testConstrutor0() {
        String input = "1 0 1 1 5 1 5 0";
        assertThrows(IllegalArgumentException.class, () -> {
            new Quadrado(input);
        });
    }

    @Test
    public void testConstrutor1() {
        String input = "1 1 3 1 3 3 1 3";
        new Quadrado(input);
    }
}
