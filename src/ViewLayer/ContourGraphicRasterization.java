package ViewLayer;

import ModelLayer.BoardLayer.FoodType;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.BoardLayer.Obstacle;

import java.awt.*;

/**
 * Classe que representa a rasterização gráfica do contorno dos objetos no tabuleiro do jogo.
 * Responsabilidade: Renderizar graficamente o contorno dos obstáculos, comida e cobra no tabuleiro.
 * @version 1.0 22/05/2024
 */
public class ContourGraphicRasterization extends RasterizationGraphicStrategy {

    /**
     * Construtor para criar uma instância de ContourGraphicRasterization com o tabuleiro de jogo especificado.
     * @param gameBoard O tabuleiro de jogo a ser renderizado.
     */
    public ContourGraphicRasterization(GameBoard gameBoard) {
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

        // Renderiza os obstáculos
        for(int i = 0; i < this.gameBoard.getListOfObstacles().size(); i++) {
            Obstacle obstacle = this.gameBoard.getListOfObstacles().get(i);
            g2D.setColor(Color.RED);
            g2D.setStroke(new BasicStroke(5));
            g2D.drawPolygon(obstacle.getPoligono().getXPoints(),obstacle.getPoligono().getYPoints(),obstacle.getPoligono().getPontos().size());
        }

        // Renderiza a comida
        int xF = this.gameBoard.getFood().getMinX();
        int yF = this.gameBoard.getFood().getMinY();
        int sizeF = this.gameBoard.getFoodDimension();
        g2D.setColor(this.gameBoard.getFood().getColor());
        g2D.setStroke(new BasicStroke(5));
        if (this.gameBoard.getFoodType() == FoodType.SQUARE) {
            g2D.drawRect(xF, yF, sizeF, sizeF);
        } else {
            g2D.drawOval(xF, yF, sizeF, sizeF);
        }

        // Renderiza a cobra
        for(int i = 0; i < this.gameBoard.getSnake().getBody().size(); i++){
            int x = (int) gameBoard.getSnake().getBody().get(i).getMinX();
            int y = (int) gameBoard.getSnake().getBody().get(i).getMinY();
            int size = gameBoard.getSnake().getArestaHeadLength();
            if(i == 0)
                g2D.setColor(new Color(20, 90, 50)); // Cabeça da cobra
            else
                g2D.setColor(Color.GREEN); // Corpo da cobra
            g2D.setStroke(new BasicStroke(5));
            g2D.drawRect(x,y,size,size);
        }
    }
}
