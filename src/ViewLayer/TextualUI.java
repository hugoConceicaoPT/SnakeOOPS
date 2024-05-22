package ViewLayer;

import ControllerLayer.ML;
import ControllerLayer.SnakeGame;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.BoardLayer.Score;

/**
 * Classe que implementa a interface de usuário textual para o jogo.
 * Responsabilidade: Exibir os elementos do jogo como texto no console, incluindo o tabuleiro, direção da cobra e pontuação.
 * @version 1.0 12/05/2024
 * @author Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class TextualUI implements UI {

    private RasterizationTextualStrategy rasterizationTextualStrategy;

    /**
     * Construtor que inicializa a interface de usuário textual com a estratégia de rasterização fornecida.
     * @param rasterizationTextualStrategy A estratégia de rasterização para representar o tabuleiro.
     */
    public TextualUI(RasterizationTextualStrategy rasterizationTextualStrategy) {
        this.rasterizationTextualStrategy = rasterizationTextualStrategy;
    }

    public void display(GameBoard gameBoard, Score score) {
        System.out.println(this.rasterizationTextualStrategy.toString());

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

    @Override
    public RasterizationGraphicStrategy getGraphicRasterizationStrategy() {
        return null;
    }

    @Override
    public void addMouseListener(ML mouseListener) {

    }

    @Override
    public void addMouseMotionListener(ML mouseListener) {

    }

    @Override
    public RasterizationTextualStrategy getTextualRasterizationStrategy() {
        return rasterizationTextualStrategy;
    }

    public void setRasterizationTextualStrategy(RasterizationTextualStrategy rasterizationTextualStrategy) {
        this.rasterizationTextualStrategy = rasterizationTextualStrategy;
    }

    public RasterizationTextualStrategy getRasterizationTextualStrategy() {
        return rasterizationTextualStrategy;
    }
}
