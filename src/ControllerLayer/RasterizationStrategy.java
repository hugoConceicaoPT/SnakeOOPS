package ControllerLayer;

import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.SnakeLayer.Score;

public interface RasterizationStrategy {
    void rasterization(GameBoard gameBoard, Score score);
}
