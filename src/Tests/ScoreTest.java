package Tests;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import ModelLayer.BoardLayer.Score;

/** Classe que representa uma classe teste para testar as funcionalidades da classe Score
    Responsabilidade: Testar as funcionalidades da classe Score
    @version 1.0 12/05/2024
    @author Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class ScoreTest {
    @Test
    public void increaseScoreTest(){
        Score score = new Score(0,1);
        score.increaseScore();
        assertEquals(1, score.getPoints());
        score.increaseScore();
        score.increaseScore();
        score.increaseScore();
        assertEquals(4, score.getPoints());
    }
}

