package ViewLayer;

import ControllerLayer.ML;
import ControllerLayer.SnakeGame;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.BoardLayer.Score;

/**
 * Interface para implementar uma interface de usuário (UI) para o jogo.
 * Define o método `display` para exibir os elementos do jogo.
 */
public interface UI {
     /**
     * Exibe os elementos do jogo, como o placar e o tabuleiro.
     */
    void display(GameBoard gameBoard, Score score);
    RasterizationTextualStrategy getTextualRasterizationStrategy();
    RasterizationGraphicStrategy getGraphicRasterizationStrategy();

    void addMouseListener(ML mouseListener);

    void addMouseMotionListener(ML mouseListener);
}
