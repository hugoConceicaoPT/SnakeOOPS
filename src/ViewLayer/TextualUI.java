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

    private final RasterizationTextualStrategy rasterizationStrategy;
    private final GameBoard gameBoard;
    private final Score score;

    /**
     * Construtor que inicializa a interface de usuário textual com a estratégia de rasterização fornecida.
     * @param rasterizationStrategy A estratégia de rasterização para representar o tabuleiro.
     */
    public TextualUI(RasterizationTextualStrategy rasterizationStrategy, GameBoard gameBoard, Score score) {
        this.rasterizationStrategy = rasterizationStrategy;
        this.gameBoard = gameBoard;
        this.score = score;
    }

    public void display() {
        System.out.println(this.rasterizationStrategy.toString());

        int angle = 0;
        switch (this.gameBoard.getSnake().getCurrentDirection()) {
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

        System.out.println("Dir H: " + angle + "\t" + "Pontos: " + this.score.getPoints());
    }
}
