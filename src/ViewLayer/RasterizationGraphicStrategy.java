package ViewLayer;

import ModelLayer.BoardLayer.GameBoard;

import javax.swing.*;
import java.awt.*;
/**
 * Classe abstrata que define a estratégia de rasterização gráfica para o jogo.
 * Responsabilidade: Definir os métodos abstratos para atualização dos obstáculos, cobra e comida no tabuleiro,
 * e fornecer a base para a renderização gráfica do tabuleiro do jogo.
 * @version 1.0 22/05/2024
 */
public abstract class RasterizationGraphicStrategy extends JPanel {

    protected GameBoard gameBoard;
    protected JPanel panel;
    private final Image backgroundImage = new ImageIcon("assets/backgroundGame.png").getImage();

    /**
     * Construtor que inicializa a estratégia de rasterização gráfica com o tabuleiro de jogo especificado.
     * @param gameBoard O tabuleiro de jogo a ser renderizado.
     */
    public RasterizationGraphicStrategy(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.panel = this;
        this.panel.setPreferredSize(new Dimension(this.gameBoard.getWidthBoard(),this.gameBoard.getHeightBoard()));
    }


    /**
     * Método para renderizar os componentes gráficos do painel.
     * @param g O contexto gráfico a ser usado para desenhar.
     */
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

    public Image getBackgroundImage() {
        return backgroundImage;
    }
}
