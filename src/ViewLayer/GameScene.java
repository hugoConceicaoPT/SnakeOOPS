package ViewLayer;

import ControllerLayer.Leaderboard;
import ControllerLayer.ML;

import javax.swing.*;
import java.awt.*;

/**
 * Classe que representa a cena do jogo.
 * Responsabilidade: Gerenciar a renderização gráfica da cena do jogo e sua atualização.
 * @version 1.0 22/05/2024
 */
public class GameScene extends Scene {
    private static GameScene instance;
    private JPanel gamePanel;

    private RasterizationGraphicStrategy rasterizationGraphicStrategy; // Estratégia de rasterização gráfica

    /**
     * Construtor que inicializa a cena do jogo com a estratégia de rasterização fornecida.
     * @param rasterizationGraphicStrategy A estratégia de rasterização gráfica a ser usada.
     */
    public GameScene(RasterizationGraphicStrategy rasterizationGraphicStrategy) {
        this.rasterizationGraphicStrategy = rasterizationGraphicStrategy;
        this.gamePanel = this.rasterizationGraphicStrategy.getPanel();
    }

    /**
     * Método para obter a instância singleton da cena do jogo.
     * @param strategy A estratégia de rasterização gráfica a ser usada.
     * @return A instância singleton da cena do jogo.
     */
    public static GameScene getInstance(RasterizationGraphicStrategy strategy) {
        if (instance == null) {
            instance = new GameScene(strategy);
        }
        return instance;
    }

    /**
     * Método para obter a instância singleton da cena do jogo.
     * @return A instância singleton da cena do jogo.
     */
    public static GameScene getInstance() {
        return instance;
    }

    /**
     * Define a instância singleton da cena do jogo.
     * @param instance A nova instância da cena do jogo.
     */
    public static void setInstance(GameScene instance) {
        GameScene.instance = instance;
    }

    /**
     * Obtém o painel do jogo.
     * @return O painel do jogo.
     */
    public JPanel getGamePanel() {
        return gamePanel;
    }

    /**
     * Define um novo painel para o jogo.
     * @param gamePanel O novo painel do jogo a ser definido.
     */
    public void setGamePanel(JPanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * Atualiza a cena do jogo com base nos eventos de mouse.
     * @param mouseListener O listener de mouse que captura os eventos.
     */
    @Override
    public void update(ML mouseListener) {
    }

    /**
     * Desenha a cena do jogo.
     * @param g O contexto gráfico a ser usado para desenhar.
     */
    @Override
    public void draw(Graphics g) {
    }

    @Override
    public void setLeaderboard(Leaderboard leaderboard) {}

    /**
     * Obtém o painel associado à cena.
     * @return O painel da cena.
     */
    @Override
    public JPanel getPanel() {
        return gamePanel;
    }

    /**
     * Obtém a estratégia de rasterização gráfica.
     * @return A estratégia de rasterização gráfica.
     */
    public RasterizationGraphicStrategy getRasterizationGraphicStrategy() {
        return rasterizationGraphicStrategy;
    }

    /**
     * Define uma nova estratégia de rasterização gráfica.
     * @param rasterizationGraphicStrategy A nova estratégia de rasterização gráfica.
     */
    public void setRasterizationGraphicStrategy(RasterizationGraphicStrategy rasterizationGraphicStrategy) {
        this.rasterizationGraphicStrategy = rasterizationGraphicStrategy;
    }

    /**
     * Define o painel principal do jogo.
     * @param gamePanel O novo painel principal a ser definido.
     */
    public void setPrincipalPanel(JPanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
