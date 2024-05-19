package ViewLayer;

import java.awt.*;
import java.awt.event.*;

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
    private static GraphicalUI instance;
    private RasterizationGraphicStrategy rasterizationGraphicStrategy;
    private int currentState;
    private Scene currentScene;
    private final int windowWidth;
    private final int windowHeight;
    private ML mouseListener;
    /**
     * Construtor que inicializa a interface gráfica.
     * Pode ser configurado posteriormente para definir a aparência do jogo.
     */
    public GraphicalUI(RasterizationGraphicStrategy rasterizationGraphicStrategy) {
        this.rasterizationGraphicStrategy = rasterizationGraphicStrategy;
        this.windowWidth = this.rasterizationGraphicStrategy.getGameBoard().getWidthBoard() + this.rasterizationGraphicStrategy.getGameBoard().getWidthBoard()/this.rasterizationGraphicStrategy.getGameBoard().getSnake().getArestaHeadLength();
        this.windowHeight = this.rasterizationGraphicStrategy.getGameBoard().getHeightBoard() + this.rasterizationGraphicStrategy.getGameBoard().getHeightBoard()/this.rasterizationGraphicStrategy.getGameBoard().getSnake().getArestaHeadLength();
        changeState(0);
        setTitle("SnakeOOPS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setPreferredSize(new Dimension(this.windowWidth,this.windowHeight));
        pack();
        setLocationRelativeTo(null);
        setFocusable(true);
        setVisible(true);
    }

    public static GraphicalUI getInstance(RasterizationGraphicStrategy rasterizationGraphicStrategy) {
        if (instance == null) {
            instance = new GraphicalUI(rasterizationGraphicStrategy);
        }
        return instance;
    }

    public void changeState(int newState) {
        currentState = newState;
        switch(currentState) {
            case 0:
                currentScene = new MenuScene(this.windowWidth,this.windowHeight,this.rasterizationGraphicStrategy);
                break;
            case 1:
                currentScene = new GameScene(this.rasterizationGraphicStrategy);
                add(currentScene.getPanel());
                break;
            default:
                System.out.println("Unknown scene.");
                currentScene = null;
                break;
        }
        if(currentState == 1) {
            SnakeGame.setIsRunning(true);
            revalidate();
            repaint();
        }
    }

    @Override
    public void display(GameBoard gameBoard,Score score) {
        if(currentState == 0) {
            Image dbImage = createImage(getWidth(), getHeight());
            Graphics dbg = dbImage.getGraphics();
            this.draw(dbg);
            getGraphics().drawImage(dbImage, 0, 0, this);
            this.mouseListener = (ML) getMouseListeners()[0];
            currentScene.update(this.mouseListener);
        }
        else {
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
}


