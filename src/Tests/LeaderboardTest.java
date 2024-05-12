package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import ControllerLayer.Leaderboard;
import ModelLayer.BoardLayer.Player;
import ModelLayer.BoardLayer.Score;

/** Classe que representa uma classe teste para testar as funcionalidades da classe Leaderboard
    Responsabilidade: Testar as funcionalidades da classe Leaderboard
    @version 1.0 12/05/2024
    @author Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class LeaderboardTest {
    
    @Test
    public void testGenerateLeaderboard() {
        Leaderboard leaderboard = new Leaderboard();
        String expected = "1º Bordeua 2147483647 Pontos\n" + 
                        "2º Player 2147483647 Pontos\n" +
                        "3º kdaj 6 Pontos\n" + 
                        "4º joao 2 Pontos\n" +
                        "5º Hugo 0 Pontos\n" + 
                        "6º Tete 0 Pontos\n" +
                        "7º clear 0 Pontos\n";
        assertEquals(expected, leaderboard.generateLeaderboard());
    }

    @Test
    public void testUpdateLeaderboard() {
        Leaderboard leaderboard = new Leaderboard();
        Player newPlayer = new Player("John", new Score(1500, 0));
        leaderboard.updateLeaderboard(newPlayer);

        try {
            String leaderboardContent = new String(Files.readAllBytes(Paths.get("src//ranking.txt")));
            assertTrue(leaderboardContent.contains("John 1500 Pontos"));
        } catch (Exception e) {
            fail("Erro ao ler o arquivo de leaderboard: " + e.getMessage());
        }
    }


}
