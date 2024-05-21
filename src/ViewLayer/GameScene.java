package ViewLayer;

import ControllerLayer.ML;

import javax.swing.*;
import java.awt.*;

public class GameScene extends Scene {
    private static GameScene instance;
    private JPanel gamePanel;
    private RasterizationGraphicStrategy rasterizationGraphicStrategy;

    public GameScene(RasterizationGraphicStrategy rasterizationGraphicStrategy) {
        this.rasterizationGraphicStrategy = rasterizationGraphicStrategy;
        this.gamePanel = this.rasterizationGraphicStrategy.getPanel();
    }

    public static GameScene getInstance(RasterizationGraphicStrategy strategy) {
        if (instance == null) {
            instance = new GameScene(strategy);
        }
        return instance;
    }

    @Override
    public void update(ML mouseListener) {

    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public JPanel getPanel() {
        return gamePanel;
    }

    public RasterizationGraphicStrategy getRasterizationGraphicStrategy() {
        return rasterizationGraphicStrategy;
    }

    public void setRasterizationGraphicStrategy(RasterizationGraphicStrategy rasterizationGraphicStrategy) {
        this.rasterizationGraphicStrategy = rasterizationGraphicStrategy;
    }

    public void setPrincipalPanel(JPanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}