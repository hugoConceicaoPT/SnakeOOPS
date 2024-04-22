package Tests;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import ModelLayer.SnakeLayer.Score;

public class ScoreTest {
    @Test
    public void increaseScoreTest(){
        Score score = new Score(0);
        score.increaseScore();
        assertEquals(1, score);
        score.increaseScore();
        score.increaseScore();
        score.increaseScore();
        assertEquals(4, score);
    }
}

