package ViewLayer;

import ControllerLayer.ML;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.BoardLayer.Score;

/**
 * Classe que implementa a interface de usuário textual para o jogo.
 * Responsabilidade: Exibir os elementos do jogo como texto no console, incluindo o tabuleiro, direção da cobra e pontuação.
 * @version 1.0 22/05/2024
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

    /**
     * Exibe os elementos do jogo, como o tabuleiro, direção da cobra e pontuação, no console.
     * @param gameBoard O tabuleiro do jogo.
     * @param score A pontuação atual.
     */
    public void display(GameBoard gameBoard, Score score) {
        // Exibe a representação textual do tabuleiro
        System.out.println(this.rasterizationTextualStrategy.toString());

        // Determina e exibe a direção atual da cobra em graus
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

        // Exibe a direção e a pontuação atual no console
        System.out.println("Dir H: " + angle + "\t" + "Pontos: " + score.getPoints());
    }

    /**
     * Retorna a estratégia de rasterização gráfica, que neste caso é nula, pois é uma UI textual.
     * @return null.
     */
    @Override
    public RasterizationGraphicStrategy getGraphicRasterizationStrategy() {
        return null;
    }

    /**
     * Método vazio, pois a UI textual não utiliza eventos de mouse.
     * @param mouseListener O listener de mouse a ser adicionado.
     */
    @Override
    public void addMouseListener(ML mouseListener) {
        // Não implementado para UI textual
    }

    /**
     * Método vazio, pois a UI textual não utiliza eventos de movimento de mouse.
     * @param mouseListener O listener de movimento de mouse a ser adicionado.
     */
    @Override
    public void addMouseMotionListener(ML mouseListener) {
        // Não implementado para UI textual
    }

    /**
     * Retorna a estratégia de rasterização textual.
     * @return A estratégia de rasterização textual.
     */
    @Override
    public RasterizationTextualStrategy getTextualRasterizationStrategy() {
        return rasterizationTextualStrategy;
    }

    /**
     * Define a estratégia de rasterização textual.
     * @param rasterizationTextualStrategy A nova estratégia de rasterização textual.
     */
    public void setRasterizationTextualStrategy(RasterizationTextualStrategy rasterizationTextualStrategy) {
        this.rasterizationTextualStrategy = rasterizationTextualStrategy;
    }

    /**
     * Retorna a estratégia de rasterização textual.
     * @return A estratégia de rasterização textual.
     */
    public RasterizationTextualStrategy getRasterizationTextualStrategy() {
        return rasterizationTextualStrategy;
    }
}
