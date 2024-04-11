package GameLayer.SnakeLayer;

import java.io.File;

public class Score {
    private int score;
    
    public Score(int score) {
        this.score = score;
    }
    

    public void increaseScore() {
        setScore(score+1);
    }
    public String getRank(File file) {return "a";}
    public File setRank() {return null;}


    public int getScore() {
        return score;
    }


    public void setScore(int score) {
        this.score = score;
    };
}
