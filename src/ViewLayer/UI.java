package ViewLayer;

import ControllerLayer.ML;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.BoardLayer.Score;

/**
 * Interface para implementar uma interface de usuário (UI) para o jogo.
 * Responsabilidade: Definir os métodos necessários para exibir os elementos do jogo e adicionar listeners de mouse.
 * @version 1.0 10/05/2024
 */
public interface UI {

    /**
     * Exibe os elementos do jogo, como o placar e o tabuleiro.
     * @param gameBoard O tabuleiro do jogo.
     * @param score A pontuação atual.
     */
    void display(GameBoard gameBoard, Score score);

    /**
     * Obtém a estratégia de rasterização textual.
     * @return A estratégia de rasterização textual.
     */
    RasterizationTextualStrategy getTextualRasterizationStrategy();

    /**
     * Obtém a estratégia de rasterização gráfica.
     * @return A estratégia de rasterização gráfica.
     */
    RasterizationGraphicStrategy getGraphicRasterizationStrategy();

    /**
     * Adiciona um listener para eventos de mouse.
     * @param mouseListener O listener de mouse a ser adicionado.
     */
    void addMouseListener(ML mouseListener);

    /**
     * Adiciona um listener para eventos de movimento de mouse.
     * @param mouseListener O listener de movimento de mouse a ser adicionado.
     */
    void addMouseMotionListener(ML mouseListener);
}
