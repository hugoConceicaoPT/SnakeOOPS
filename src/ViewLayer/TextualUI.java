package ViewLayer;

import ControllerLayer.RasterizationStrategy;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.SnakeLayer.Score;

public class TextualUI implements UI {

    private RasterizationStrategy rasterizationStrategy;

    public TextualUI(RasterizationStrategy rasterizationStrategy) {
        this.rasterizationStrategy = rasterizationStrategy;
    }

    public void display(Score score, GameBoard gameBoard) {
        this.rasterizationStrategy.rasterization(gameBoard, score);
    }
}
