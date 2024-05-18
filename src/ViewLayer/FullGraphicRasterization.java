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
        super.paintComponent(g);

        // Paint obstacles
        for(int i = 0; i < this.gameBoard.getListOfObstacles().size(); i++) {
            Obstacle obstacle = this.gameBoard.getListOfObstacles().get(i);
            g.setColor(Color.RED);
            g.fillPolygon(obstacle.getPoligono().getXPoints(),obstacle.getPoligono().getYPoints(),obstacle.getPoligono().getPontos().size());
        }

        // Paint food
        int xF = this.gameBoard.getFood().getCentroide().getX().intValue();
        int yF = this.gameBoard.getFood().getCentroide().getY().intValue();
        int sizeF = this.gameBoard.getFoodDimension();
        g.setColor(this.gameBoard.getFood().getColor());
        if (this.gameBoard.getFoodType() == FoodType.SQUARE) {
            g.fillRect(xF, yF, sizeF, sizeF);
        } else {
            g.fillOval(xF, yF, sizeF, sizeF);
        }

        // Paint snake
        for(int i = 0; i < this.gameBoard.getSnake().getBody().size(); i++){
            int x = gameBoard.getSnake().getBody().get(i).getCentroide().getX().intValue();
            int y = gameBoard.getSnake().getBody().get(i).getCentroide().getY().intValue();
            int size = gameBoard.getSnake().getArestaHeadLength();
            if(i == 0)
                g.setColor(Color.BLACK);
            else
                g.setColor(Color.GREEN);
            g.fillRect(x,y,size,size);
        }
    }
}
