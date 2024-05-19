package ViewLayer;

import ControllerLayer.ML;
import ModelLayer.BoardLayer.GameBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class GameScene extends Scene {
    private JPanel principalPanel;
    private RasterizationGraphicStrategy rasterizationGraphicStrategy;

    public GameScene(RasterizationGraphicStrategy rasterizationGraphicStrategy) {
        this.rasterizationGraphicStrategy = rasterizationGraphicStrategy;
        initializeGamePanel();

    }

    private void initializeGamePanel() {
        this.principalPanel = new JPanel();
        JPanel gamePanel = rasterizationGraphicStrategy.getPanel();

        JPanel borderNorth = new JPanel();
        JPanel borderWest = new JPanel();
        JPanel borderEast = new JPanel();
        JPanel borderSouth = new JPanel();

        borderNorth.setBackground(Color.DARK_GRAY);
        borderSouth.setBackground(Color.DARK_GRAY);
        borderWest.setBackground(Color.DARK_GRAY);
        borderEast.setBackground(Color.DARK_GRAY);

        GameBoard gameBoard = rasterizationGraphicStrategy.getGameBoard();
        int borderThickness = gameBoard.getWidthBoard() / gameBoard.getSnake().getArestaHeadLength();

        borderNorth.setPreferredSize(new Dimension(gameBoard.getWidthBoard(), borderThickness));
        borderWest.setPreferredSize(new Dimension(borderThickness, gameBoard.getHeightBoard()));
        borderEast.setPreferredSize(new Dimension(borderThickness, gameBoard.getHeightBoard()));
        borderSouth.setPreferredSize(new Dimension(gameBoard.getWidthBoard(), borderThickness));

        principalPanel.setLayout(new BorderLayout());
        principalPanel.add(borderNorth, BorderLayout.NORTH);
        principalPanel.add(borderWest, BorderLayout.WEST);
        principalPanel.add(borderEast, BorderLayout.EAST);
        principalPanel.add(borderSouth, BorderLayout.SOUTH);
        assert gamePanel != null;
        principalPanel.add(gamePanel, BorderLayout.CENTER);
    }

    @Override
    public void update(ML mouseListener) {

    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public JPanel getPanel() {
        return principalPanel;
    }

    public RasterizationGraphicStrategy getRasterizationGraphicStrategy() {
        return rasterizationGraphicStrategy;
    }

    public void setRasterizationGraphicStrategy(RasterizationGraphicStrategy rasterizationGraphicStrategy) {
        this.rasterizationGraphicStrategy = rasterizationGraphicStrategy;
    }

    public void setPrincipalPanel(JPanel principalPanel) {
        this.principalPanel = principalPanel;
    }
}