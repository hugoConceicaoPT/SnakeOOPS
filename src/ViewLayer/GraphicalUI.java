package ViewLayer;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;

import ControllerLayer.ML;
import ControllerLayer.SnakeGame;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.BoardLayer.Score;

/**
 * Classe que implementa a interface gráfica para o jogo, utilizando a biblioteca Swing.
 * Responsabilidade: Exibir o tabuleiro do jogo e o placar, além de capturar os eventos do teclado.
 * @version 1.0 22/05/2024
 * Autor: Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class GraphicalUI extends JFrame implements UI {
    private RasterizationGraphicStrategy rasterizationGraphicStrategy;
    private static int currentState;
    private Scene currentScene;
    private final int windowWidth;
    private final int windowHeight;
    private ML mouseListener;
    private JLabel currentDirectionLabel;
    private JLabel currentScoreLabel;
    private JPanel informationPanel;

    /**
     * Construtor que inicializa a interface gráfica.
     * Pode ser configurado posteriormente para definir a aparência do jogo.
     * @param rasterizationGraphicStrategy A estratégia de rasterização gráfica a ser usada.
     */
    public GraphicalUI(RasterizationGraphicStrategy rasterizationGraphicStrategy) {
        this.rasterizationGraphicStrategy = rasterizationGraphicStrategy;
        this.windowWidth = this.rasterizationGraphicStrategy.getGameBoard().getWidthBoard();
        this.windowHeight = this.rasterizationGraphicStrategy.getGameBoard().getHeightBoard();
        currentState = 0;
        this.informationPanel = new JPanel(new GridLayout(1, 3));
        this.currentDirectionLabel = new JLabel();
        this.currentScoreLabel = new JLabel();
        this.currentDirectionLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        this.currentScoreLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        this.currentDirectionLabel.setForeground(Color.white);
        this.currentScoreLabel.setForeground(Color.white);
        this.informationPanel.setBackground(new Color (20, 90, 50));
        this.informationPanel.add(currentDirectionLabel);
        this.informationPanel.add(currentScoreLabel);
        add(this.informationPanel, BorderLayout.NORTH);
        setTitle("SnakeOOPS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setPreferredSize(new Dimension(this.windowWidth + 10, this.windowHeight + 10));
        pack();
        setLocationRelativeTo(null);
        setFocusable(true);
        setVisible(true);
    }

    /**
     * Método para exibir o tabuleiro do jogo e o placar.
     * @param gameBoard O tabuleiro do jogo.
     * @param score A pontuação atual.
     */
    @Override
    public void display(GameBoard gameBoard, Score score) {
        if (currentState == 0) {
            if (SnakeGame.isIsRunning())
                dispose();
            currentScene = MenuScene.getInstance(this.windowWidth, this.windowHeight, this.rasterizationGraphicStrategy);
            Image dbImage = createImage(getWidth(), getHeight());
            Graphics dbg = dbImage.getGraphics();
            this.draw(dbg);
            getGraphics().drawImage(dbImage, 0, 0, this);
            this.mouseListener = (ML) getMouseListeners()[0];
            currentScene.update(this.mouseListener);
        } else {
            SnakeGame.setIsRunning(true);
            currentScene = GameScene.getInstance(this.rasterizationGraphicStrategy);
            add(currentScene.getPanel(), BorderLayout.CENTER);
            int angle = 0;
            switch (gameBoard.getSnake().getCurrentDirection()) {
                case UP:
                    angle = 90;
                    break;
                case DOWN:
                    angle = 270;
                    break;
                case LEFT:
                    angle = 180;
                    break;
                case RIGHT:
                    angle = 0;
                    break;
                default:
                    break;
            }
            currentDirectionLabel.setText("DIR H: " + angle);
            currentScoreLabel.setText("Pontos: " + score.getPoints());
            revalidate();
            repaint();
        }
    }

    /**
     * Obtém a estratégia de rasterização textual.
     * @return A estratégia de rasterização textual (sempre null).
     */
    @Override
    public RasterizationTextualStrategy getTextualRasterizationStrategy() {
        return null;
    }

    /**
     * Obtém a estratégia de rasterização gráfica.
     * @return A estratégia de rasterização gráfica atual.
     */
    @Override
    public RasterizationGraphicStrategy getGraphicRasterizationStrategy() {
        return this.rasterizationGraphicStrategy;
    }

    /**
     * Adiciona um listener para eventos de mouse.
     * @param l O listener de mouse a ser adicionado.
     */
    @Override
    public void addMouseListener(ML l) {
        this.mouseListener = l;
        super.addMouseListener(l);
    }

    /**
     * Adiciona um listener para eventos de movimento de mouse.
     * @param mouseListener O listener de movimento de mouse a ser adicionado.
     */
    @Override
    public void addMouseMotionListener(ML mouseListener) {
        this.mouseListener = mouseListener;
        super.addMouseMotionListener(mouseListener);
    }

    /**
     * Método para desenhar a cena atual.
     * @param g O contexto gráfico a ser usado para desenhar.
     */
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        currentScene.draw(g2);
    }

    /**
     * Fecha a janela do jogo.
     * Pode ser melhorado para limpar recursos ou exibir uma mensagem final antes de sair.
     */
    public void close() {
        dispose();
    }

    /**
     * Obtém a estratégia de rasterização gráfica atual.
     * @return A estratégia de rasterização gráfica.
     */
    public RasterizationGraphicStrategy getRasterizationGraphicStrategy() {
        return rasterizationGraphicStrategy;
    }

    /**
     * Define uma nova estratégia de rasterização gráfica.
     * @param rasterizationGraphicStrategy A nova estratégia de rasterização gráfica.
     */
    public void setRasterizationStrategy(RasterizationGraphicStrategy rasterizationGraphicStrategy) {
        this.rasterizationGraphicStrategy = rasterizationGraphicStrategy;
    }

    /**
     * Define uma nova estratégia de rasterização gráfica.
     * @param rasterizationGraphicStrategy A nova estratégia de rasterização gráfica.
     */
    public void setRasterizationGraphicStrategy(RasterizationGraphicStrategy rasterizationGraphicStrategy) {
        this.rasterizationGraphicStrategy = rasterizationGraphicStrategy;
    }

    /**
     * Obtém o estado atual do jogo.
     * @return O estado atual do jogo.
     */
    public static int getCurrentState() {
        return currentState;
    }

    /**
     * Define o estado atual do jogo.
     * @param currentState O novo estado do jogo.
     */
    public static void setCurrentState(int currentState) {
        GraphicalUI.currentState = currentState;
    }

    /**
     * Obtém a cena atual.
     * @return A cena atual.
     */
    public Scene getCurrentScene() {
        return currentScene;
    }

    /**
     * Define a cena atual.
     * @param currentScene A nova cena a ser definida.
     */
    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
    }

    /**
     * Obtém a largura da janela.
     * @return A largura da janela.
     */
    public int getWindowWidth() {
        return windowWidth;
    }

    /**
     * Obtém a altura da janela.
     * @return A altura da janela.
     */
    public int getWindowHeight() {
        return windowHeight;
    }

    /**
     * Obtém o listener de mouse.
     * @return O listener de mouse.
     */
    public ML getMouseListener() {
        return mouseListener;
    }

    /**
     * Define um novo listener de mouse.
     * @param mouseListener O novo listener de mouse a ser definido.
     */
    public void setMouseListener(ML mouseListener) {
        this.mouseListener = mouseListener;
    }
}
