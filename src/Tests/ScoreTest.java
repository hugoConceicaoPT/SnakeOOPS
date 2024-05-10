package Tests;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import ModelLayer.BoardLayer.Score;

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

