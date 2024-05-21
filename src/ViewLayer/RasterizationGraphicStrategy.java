package ViewLayer;

import ModelLayer.BoardLayer.GameBoard;

import javax.swing.*;
import java.awt.*;

public abstract class RasterizationGraphicStrategy extends JPanel {

    protected GameBoard gameBoard;
    protected JPanel panel;
    private final Image backgroundImage = new ImageIcon("assets/backgroundGame.png").getImage();
    public RasterizationGraphicStrategy(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.panel = this;
        this.panel.setPreferredSize(new Dimension(this.gameBoard.getWidthBoard(),this.gameBoard.getHeightBoard()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        g.drawImage(backgroundImage, 0, 0, gameBoard.getWidthBoard(), gameBoard.getHeightBoard(), this);
        updateObstacles();
        updateSnake();
        updateFood();
    }

    /**
     * Atualiza as células que representam os obstáculos no tabuleiro.
     * Cada estratégia de rasterização deve implementar esta lógica conforme necessário.
     */
    public abstract void updateObstacles();
    /**
     * Atualiza as células que representam a cobra no tabuleiro.
     * Cada estratégia de rasterização deve implementar esta lógica conforme necessário.
     */
    public abstract void updateSnake();
    /**
     * Atualiza as células que representam a comida no tabuleiro.
     * Cada estratégia de rasterização deve implementar esta lógica conforme necessário.
     */
    public abstract void updateFood();

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
