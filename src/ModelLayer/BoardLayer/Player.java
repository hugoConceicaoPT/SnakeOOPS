package ModelLayer.BoardLayer;

/**
 * Classe que representa um jogador no jogo.
 * Responsabilidade: Armazenar e gerenciar as informações do jogador, incluindo seu nome e pontuação.
 * @version 1.0 22/05/2024
 * @author Hugo Conceição João Ventura Eduarda Pereira
 * @inv O nome do jogador não deve ser nulo ou vazio, e o objeto Score associado deve sempre ser válido.
 */
public class Player {
    private String name; 
    private Score score; 

    /**
     * Construtor para criar um novo jogador.
     * @param name Nome do jogador.
     * @param score Objeto Score contendo a pontuação inicial do jogador.
     */
    public Player(String name, Score score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Retorna o nome do jogador.
     * @return O nome do jogador.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do jogador.
     * @param name O novo nome do jogador.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna o objeto Score do jogador.
     * @return O objeto Score que representa a pontuação do jogador.
     */
    public Score getScore() {
        return score;
    }

    /**
     * Define a pontuação do jogador.
     * @param score O novo objeto Score contendo a pontuação do jogador.
     */
    public void setScore(Score score) {
        this.score = score;
    }
}
