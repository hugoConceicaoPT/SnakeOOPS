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

    public static GameScene getInstance() {
        return instance;
    }

    public static void setInstance(GameScene instance) {
        GameScene.instance = instance;
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(JPanel gamePanel) {
        this.gamePanel = gamePanel;
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