package ModelLayer.SnakeLayer;

public class Score {
    private int points;
    private int scorePerFood;
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
