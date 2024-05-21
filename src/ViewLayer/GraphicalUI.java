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
 * @version 1.0 10/05/2024
 * @author Hugo Conceição, João Ventura, Eduarda Pereira
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
        add(this.informationPanel,BorderLayout.NORTH);
        setTitle("SnakeOOPS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setPreferredSize(new Dimension(this.windowWidth + 10,this.windowHeight + 10));
        pack();
        setLocationRelativeTo(null);
        setFocusable(true);
        setVisible(true);
    }

    @Override
    public void display(GameBoard gameBoard,Score score) {
        if(currentState == 0) {
            if(SnakeGame.isIsRunning())
                dispose();
            currentScene = MenuScene.getInstance(this.windowWidth, this.windowHeight, this.rasterizationGraphicStrategy);
            Image dbImage = createImage(getWidth(),getHeight());
            Graphics dbg = dbImage.getGraphics();
            this.draw(dbg);
            getGraphics().drawImage(dbImage, 0, 0, this);
            this.mouseListener = (ML) getMouseListeners()[0];
            currentScene.update(this.mouseListener);
        }
        else {
            SnakeGame.setIsRunning(true);
            currentScene = GameScene.getInstance(this.rasterizationGraphicStrategy);
            add(currentScene.getPanel(),BorderLayout.CENTER);
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

    @Override
    public RasterizationTextualStrategy getTextualRasterizationStrategy() {
        return null;
    }

    @Override
    public RasterizationGraphicStrategy getGraphicRasterizationStrategy() {
        return this.rasterizationGraphicStrategy;
    }

    @Override
    public void addMouseListener(ML l) {
        this.mouseListener = l;
        super.addMouseListener(l);
    }


    @Override
    public void addMouseMotionListener(ML mouseListener) {
        this.mouseListener = mouseListener;
        super.addMouseMotionListener(mouseListener);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        currentScene.draw(g2);
    }
    /**
     * Fecha a janela do jogo.
     * Pode ser melhorado para limpar recursos ou exibir uma mensagem final antes de sair.
     */
    public void close() {
        dispose();
    }

    public RasterizationGraphicStrategy getRasterizationGraphicStrategy() {
        return rasterizationGraphicStrategy;
    }

    public void setRasterizationStrategy(RasterizationGraphicStrategy rasterizationGraphicStrategy) {
        this.rasterizationGraphicStrategy = rasterizationGraphicStrategy;
    }

    public void setRasterizationGraphicStrategy(RasterizationGraphicStrategy rasterizationGraphicStrategy) {
        this.rasterizationGraphicStrategy = rasterizationGraphicStrategy;
    }

    public static int getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(int currentState) {
        GraphicalUI.currentState = currentState;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public ML getMouseListener() {
        return mouseListener;
    }

    public void setMouseListener(ML mouseListener) {
        this.mouseListener = mouseListener;
    }

}


