package ViewLayer;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import ControllerLayer.SnakeGame;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.BoardLayer.Score;

/**
 * Classe que implementa a interface gráfica para o jogo, utilizando a biblioteca Swing.
 * Responsabilidade: Exibir o tabuleiro do jogo e o placar, além de capturar os eventos do teclado.
 * @version 1.0 10/05/2024
 * @autor Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class GraphicalUI extends JFrame implements UI {

    private RasterizationGraphicStrategy rasterizationStrategy;
    private GameBoard gameBoard;
    private Score score;

    /**
     * Construtor que inicializa a interface gráfipca.
     * Pode ser configurado posteriormente para definir a aparência do jogo.
     */
    public GraphicalUI(RasterizationGraphicStrategy rasterizationStrategy,GameBoard gameBoard, Score score) {
        this.rasterizationStrategy = rasterizationStrategy;
        this.gameBoard = gameBoard;
        this.score = score;
        setTitle("SnakeOOPS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setPreferredSize(new Dimension(this.gameBoard.getWidthBoard() + this.gameBoard.getWidthBoard()/this.gameBoard.getSnake().getArestaHeadLength() ,this.gameBoard.getHeightBoard() + this.gameBoard.getHeightBoard()/this.gameBoard.getSnake().getArestaHeadLength()));
        setLayout(new BorderLayout());
        setFocusable(true);
        JPanel gamePanel = this.rasterizationStrategy.getPanel();
        JPanel borderNorth = new JPanel();
        JPanel borderWest = new JPanel();
        JPanel borderEast = new JPanel();
        JPanel borderSouth = new JPanel();
        borderNorth.setBackground(Color.DARK_GRAY);
        borderSouth.setBackground(Color.DARK_GRAY);
        borderWest.setBackground(Color.DARK_GRAY);
        borderEast.setBackground(Color.DARK_GRAY);

        borderNorth.setPreferredSize(new Dimension(gameBoard.getWidthBoard(),this.gameBoard.getHeightBoard()/this.gameBoard.getSnake().getArestaHeadLength()));
        borderWest.setPreferredSize(new Dimension(this.gameBoard.getWidthBoard()/this.gameBoard.getSnake().getArestaHeadLength(), gameBoard.getHeightBoard()));
        borderEast.setPreferredSize(new Dimension(this.gameBoard.getHeightBoard()/this.gameBoard.getSnake().getArestaHeadLength(), gameBoard.getHeightBoard()));
        borderSouth.setPreferredSize(new Dimension(gameBoard.getWidthBoard(), this.gameBoard.getHeightBoard()/this.gameBoard.getSnake().getArestaHeadLength()));

        add(borderNorth, BorderLayout.NORTH);
        add(borderWest, BorderLayout.WEST);
        add(borderEast, BorderLayout.EAST);
        add(borderSouth, BorderLayout.SOUTH);
        add(gamePanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void display() {
        repaint();
    }

    /**
     * Fecha a janela do jogo.
     * Pode ser melhorado para limpar recursos ou exibir uma mensagem final antes de sair.
     */
    public void close() {
        dispose();
    }

    public RasterizationGraphicStrategy getRasterizationStrategy() {
        return rasterizationStrategy;
    }

    public void setRasterizationStrategy(RasterizationGraphicStrategy rasterizationStrategy) {
        this.rasterizationStrategy = rasterizationStrategy;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}


