package ViewLayer;

import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.BoardLayer.Score;

/**
 * Classe que implementa a interface de usuário textual para o jogo.
 * Responsabilidade: Exibir os elementos do jogo como texto no console, incluindo o tabuleiro, direção da cobra e pontuação.
 * @version 1.0 10/05/2024
 * @autor Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class TextualUI implements UI {

    // Estratégia de rasterização usada para converter o tabuleiro em texto
    private RasterizationStrategy rasterizationStrategy;

    /**
     * Construtor que inicializa a interface de usuário textual com a estratégia de rasterização fornecida.
     * @param rasterizationStrategy A estratégia de rasterização para representar o tabuleiro.
     */
    public TextualUI(RasterizationStrategy rasterizationStrategy) {
        this.rasterizationStrategy = rasterizationStrategy;
    }

    /**
     * Exibe o tabuleiro e as informações do jogo, incluindo a direção da cobra e a pontuação.
     * A representação do tabuleiro é fornecida pela estratégia de rasterização.
     * @param score O placar atual do jogo.
     * @param gameBoard O tabuleiro do jogo, contendo os elementos a serem exibidos.
     */
    public void display(Score score, GameBoard gameBoard) {
        // Imprime a representação do tabuleiro como texto
        System.out.println(this.rasterizationStrategy.toString());

        // Obtém o ângulo de direção da cabeça da cobra, baseado na direção atual
        int angle = 0;
        switch (gameBoard.getSnake().getCurrentDirection()) {
            case UP:
                angle = 90;
                break;
            case DOWN:
                angle = 270;
                break;
            case LEFT:
                angle = 180;
                break;
            case RIGHT:
                angle = 0;
                break;
            default:
                break;
        }

        // Imprime a direção e a pontuação do jogador
        System.out.println("Dir H: " + angle + "\t" + "Pontos: " + score.getPoints());
    }
}
