package ViewLayer;

import ModelLayer.BoardLayer.FoodType;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.BoardLayer.Obstacle;
import ModelLayer.SnakeLayer.Direction;

import javax.swing.*;
import java.awt.*;

/**
 * Classe que representa a rasterização gráfica completa dos objetos no tabuleiro do jogo.
 * Responsabilidade: Renderizar graficamente os obstáculos, comida e cobra no tabuleiro com preenchimento completo.
 * @version 1.0 22/05/2024
 */
public class FullGraphicRasterization extends RasterizationGraphicStrategy {

    private final Image snakeHeadDown = new ImageIcon("assets/snake_head.png").getImage();
    private final Image snakeHeadRight = new ImageIcon("assets/snake_head_left.png").getImage();
    private final Image snakeHeadLeft = new ImageIcon("assets/snake_head_right.png").getImage();
    private final Image snakeHeadUp = new ImageIcon("assets/snake_head_up.png").getImage();
    /**
     * Construtor para criar uma instância de FullGraphicRasterization com o tabuleiro de jogo especificado.
     * @param gameBoard O tabuleiro de jogo a ser renderizado.
     */
    public FullGraphicRasterization(GameBoard gameBoard) {
        super(gameBoard);
    }

    /**
     * Atualiza a renderização dos obstáculos no tabuleiro.
     */
    @Override
    public void updateObstacles() {
        repaint();
    }

    /**
     * Atualiza a renderização da cobra no tabuleiro.
     */
    @Override
    public void updateSnake() {
        repaint();
    }

    /**
     * Atualiza a renderização da comida no tabuleiro.
     */
    @Override
    public void updateFood() {
        repaint();
    }

    /**
     * Método chamado para renderizar os componentes gráficos no tabuleiro.
     * @param g O contexto gráfico a ser usado para desenhar os componentes.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paintComponent(g2D);


        for(int i = 0; i < this.gameBoard.getListOfObstacles().size(); i++) {
            Obstacle obstacle = this.gameBoard.getListOfObstacles().get(i);
            g2D.setColor(Color.RED);
            g2D.fillPolygon(obstacle.getPoligono().getXPoints(),obstacle.getPoligono().getYPoints(),obstacle.getPoligono().getPontos().size());
        }


        int xF = this.gameBoard.getFood().getMinX();
        int yF = this.gameBoard.getFood().getMinY();
        int sizeF = this.gameBoard.getFoodDimension();
        g2D.setColor(this.gameBoard.getFood().getColor());
        if (this.gameBoard.getFoodType() == FoodType.SQUARE) {
            g2D.fillRect(xF, yF, sizeF, sizeF);
        } else {
            g2D.fillOval(xF, yF, sizeF, sizeF);
        }


        for(int i = 0; i < this.gameBoard.getSnake().getBody().size(); i++){
            int x = (int) gameBoard.getSnake().getBody().get(i).getMinX();
            int y = (int) gameBoard.getSnake().getBody().get(i).getMinY();
            int size = gameBoard.getSnake().getArestaHeadLength();
            if(i == 0) {
                g2D.setColor(new Color(20, 90, 50));
            }
            else {
                g2D.setColor(Color.GREEN);
            }
            g2D.fillRect(x, y, size, size);
        }
    }
}
