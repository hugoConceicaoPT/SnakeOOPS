package ModelLayer.SnakeLayer;

import java.io.File;

public class Score {
    private int points;
    private int scorePerFood;
    private File fileRank;
    /** Construtor para criar um score
     * @param score o score
     */
    public Score(int points, int scorePerFood) {
        this.points = points;
        this.scorePerFood = scorePerFood;
    }
    
    /** Aumenta o score por um */
    public void increaseScore() {
        setPoints(this.points+this.scorePerFood);
    }

    /** Obtém o rank a partir de um ficheiro
     * @param file ficheiro com o top n
     * @return o rank com top
     */
    public String getTop(File file) {return null;}
    
    /** Atualiza o rank para um ficheiro
     * @return o ficheiro atualiza com o ranking
     */
    public File setTop() {return null;}

    public File getFileRank() {
        return fileRank;
    }

    public void setFileRank(File fileRank) {
        this.fileRank = fileRank;
    }

      /** Obtém o valor dos pontos
     * @return o valor dos pontos
     */
    public int getPoints() {
        return points;
    }

    /** Atualiza o valor dos pontos
     * @param points o valor dos pontos
     */
    public void setPoints(int points) {
        this.points = points;
    }

    public int getScorePerFood() {
        return scorePerFood;
    }

    public void setScorePerFood(int scorePerFood) {
        this.scorePerFood = scorePerFood;
    };

    
}
