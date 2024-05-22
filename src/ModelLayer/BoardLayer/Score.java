package ModelLayer.BoardLayer;

/**
 * Classe que representa a pontuação de um jogador no jogo.
 * Responsabilidade: Gerenciar a pontuação do jogador, incluindo a atualização e o acesso aos pontos acumulados.
 * @version 1.0 22/05/2024
 * @author Hugo Conceição João Ventura Eduarda Pereira
 * @inv A pontuação (points) nunca deve ser negativa, e a pontuação por comida (scorePerFood) deve ser um valor positivo.
 */
public class Score {
    private int points; 
    private int scorePerFood; 

    /**
     * Construtor para criar uma pontuação.
     * @param points Os pontos iniciais.
     * @param scorePerFood Os pontos adicionados à pontuação cada vez que comida é coletada.
     */
    public Score(int points, int scorePerFood) {
        if(points < 0 || scorePerFood < 0) {
            throw new IllegalArgumentException("Tanto os pontos como os pontos por comida têm que ter um valor positivo");
        }
        this.points = points;
        this.scorePerFood = scorePerFood;
    }
    
    /**
     * Aumenta a pontuação do jogador baseada na quantidade definida pelo scorePerFood.
     */
    public void increaseScore() {
        setPoints(this.points + this.scorePerFood); 
    }

    /**
     * Obtém o total de pontos do jogador.
     * @return O total de pontos acumulados.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Atualiza o total de pontos do jogador.
     * @param points O novo total de pontos.
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Retorna a quantidade de pontos adicionados à pontuação cada vez que comida é coletada.
     * @return A quantidade de pontos por comida.
     */
    public int getScorePerFood() {
        return scorePerFood;
    }

    /**
     * Define a quantidade de pontos que são adicionados à pontuação cada vez que comida é coletada.
     * @param scorePerFood A nova quantidade de pontos por comida.
     */
    public void setScorePerFood(int scorePerFood) {
        this.scorePerFood = scorePerFood;
    }
}
