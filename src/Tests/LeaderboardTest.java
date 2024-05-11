package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.Test;

import ControllerLayer.Leaderboard;


public class LeaderboardTest {
    @Test
    public void testGenerateLeaderboard() {
        Leaderboard leaderboard = new Leaderboard();
        String expected = "1º Carol 520 Pontos\n" +
                          "2º Alice 450 Pontos\n" +
                          "3º Bob 340 Pontos\n";
        assertEquals(expected, leaderboard.generateLeaderboard());
    }


}
