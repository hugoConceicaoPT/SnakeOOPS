package ViewLayer;

import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.BoardLayer.Score;

public interface UI {
    void display(Score score,GameBoard gameBoard);
}
