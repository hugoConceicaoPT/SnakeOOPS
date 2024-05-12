package ViewLayer;

import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.BoardLayer.Score;

/**
 * Classe que implementa a interface de usuário textual para o jogo.
 * Responsabilidade: Exibir os elementos do jogo como texto no console, incluindo o tabuleiro, direção da cobra e pontuação.
 * @version 1.0 12/05/2024
 * @autor Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class TextualUI implements UI {

    private RasterizationStrategy rasterizationStrategy;

    /**
     * Construtor que inicializa a interface de usuário textual com a estratégia de rasterização fornecida.
     * @param rasterizationStrategy A estratégia de rasterização para representar o tabuleiro.
     */
    public TextualUI(RasterizationStrategy rasterizationStrategy) {
        this.rasterizationStrategy = rasterizationStrategy;
    }

    public void display(Score score, GameBoard gameBoard) {
        System.out.println(this.rasterizationStrategy.toString());

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

        System.out.println("Dir H: " + angle + "\t" + "Pontos: " + score.getPoints());
    }
}
