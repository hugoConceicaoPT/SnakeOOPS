package GameLayer.SnakeLayer;

import java.io.File;

public class Score {
    private int score;
    
    /** Construtor para criar um score
     * @param score o score
     */
    public Score(int score) {
        this.score = score;
    }
    
    /** Aumenta o score por um */
    public void increaseScore() {
        setScore(score+1);
    }

    /** Obtém o rank a partir de um ficheiro
     * @param file ficheiro com o top n
     * @return o rank com top
     */
    public String getRank(File file) {return "a";}
    /** Atualiza o rank para um ficheiro
     * @return o ficheiro atualiza com o ranking
     */
    public File setRank() {return null;}

    /** Obtém o valor do score
     * @return o valor do score
     */
    public int getScore() {
        return score;
    }

    /** Atualiza o valor do score
     * @param score o valor do score
     */
    public void setScore(int score) {
        this.score = score;
    };
}
