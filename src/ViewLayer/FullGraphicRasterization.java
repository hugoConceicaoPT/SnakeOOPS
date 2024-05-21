package ViewLayer;

import ModelLayer.BoardLayer.FoodType;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.BoardLayer.Obstacle;

import java.awt.*;

public class FullGraphicRasterization extends RasterizationGraphicStrategy {
    public FullGraphicRasterization(GameBoard gameBoard) {
        super(gameBoard);
    }

    @Override
    public void updateObstacles() {
        repaint();
    }

    @Override
    public void updateSnake() {
        repaint();
    }
    @Override
    public void updateFood() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paintComponent(g2D);

        // Paint obstacles
        for(int i = 0; i < this.gameBoard.getListOfObstacles().size(); i++) {
            Obstacle obstacle = this.gameBoard.getListOfObstacles().get(i);
            g2D.setColor(Color.RED);
            g2D.fillPolygon(obstacle.getPoligono().getXPoints(),obstacle.getPoligono().getYPoints(),obstacle.getPoligono().getPontos().size());
        }

        // Paint food
        int xF = this.gameBoard.getFood().getMinX();
        int yF = this.gameBoard.getFood().getMinY();
        int sizeF = this.gameBoard.getFoodDimension();
        g2D.setColor(this.gameBoard.getFood().getColor());
        if (this.gameBoard.getFoodType() == FoodType.SQUARE) {
            g2D.fillRect(xF, yF, sizeF, sizeF);
        } else {
            g2D.fillOval(xF, yF, sizeF, sizeF);
        }

        // Paint snake
        for(int i = 0; i < this.gameBoard.getSnake().getBody().size(); i++){
            int x = (int) gameBoard.getSnake().getBody().get(i).getMinX();
            int y = (int) gameBoard.getSnake().getBody().get(i).getMinY();
            int size = gameBoard.getSnake().getArestaHeadLength();
            if(i == 0)
                g2D.setColor(new Color(20, 90, 50));
            else
                g2D.setColor(Color.GREEN);
            g2D.fillRect(x,y,size,size);
        }
    }
}
