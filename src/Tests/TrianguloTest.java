package Tests;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ModelLayer.SnakeLayer.Triangulo;

/** Classe que representa uma classe teste para testar as funcionalidades da classe Triângulo
    Responsabilidade: Testar as funcionalidades da classe Triângulo
    @version 1.0 26/03/2024
    @author Hugo Conceição
 */
public class TrianguloTest {
    @Test
    public void testConstrutor0() {
        String input = "2 0 4 0 6 0";
        assertThrows(IllegalArgumentException.class, () -> {
            new Triangulo(input);
        });
    }

    @Test
    public void testConstrutor1() {
        String input = "2 0 4 0 6 0 6 0";
        assertThrows(IllegalArgumentException.class, () -> {
            new Triangulo(input);
        });
    }
}
