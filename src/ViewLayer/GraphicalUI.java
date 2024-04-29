package ViewLayer;

import java.awt.event.KeyListener;

import javax.swing.JFrame;

import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.SnakeLayer.Score;

public class GraphicalUI extends JFrame implements UI {

    public GraphicalUI() {
    }

    public void display(Score score, GameBoard gameBoard) {
        
    }

    public void addKeyListener(KeyListener listener) {
        super.addKeyListener(listener);
    }

    public void close() {
    }
}
