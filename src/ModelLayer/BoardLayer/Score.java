package ModelLayer.BoardLayer;

/**
 * Classe que representa a pontuação de um jogador no jogo.
 * Responsabilidade: Gerenciar a pontuação do jogador, incluindo a atualização e o acesso aos pontos acumulados.
 * @version 1.0 10/05/2024
 * @author Hugo Conceição João Ventura Eduarda Pereira
 * @inv A pontuação (points) nunca deve ser negativa, e a pontuação por comida (scorePerFood) deve ser um valor positivo.
 */
public class Score {
    private int points; // Pontos totais acumulados pelo jogador.
    private int scorePerFood; // Pontos ganhos por cada comida coletada.

    /**
     * Construtor para criar uma pontuação.
     * @param points Os pontos iniciais.
     * @param scorePerFood Os pontos adicionados à pontuação cada vez que comida é coletada.
     */
    public Score(int points, int scorePerFood) {
        this.points = points;
        this.scorePerFood = scorePerFood;
    }
    
    /**
     * Aumenta a pontuação do jogador baseada na quantidade definida por scorePerFood.
     */
    public void increaseScore() {
        setPoints(this.points + this.scorePerFood); // Atualiza a pontuação adicionando o scorePerFood aos pontos atuais.
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
