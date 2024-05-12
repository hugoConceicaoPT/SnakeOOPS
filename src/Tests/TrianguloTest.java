package Tests;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ModelLayer.SnakeLayer.Triangulo;

/** Classe que representa uma classe teste para testar as funcionalidades da classe Triangulo
    Responsabilidade: Testar as funcionalidades da classe Triangulo
    @version 1.0 12/05/2024
    @author Hugo Conceição, João Ventura, Eduarda Pereira
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
