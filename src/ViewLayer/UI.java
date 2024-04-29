package ViewLayer;

import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.SnakeLayer.Score;

public interface UI {
    void display(Score score,GameBoard gameBoard);
}
