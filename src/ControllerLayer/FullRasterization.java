package ControllerLayer;

import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.SnakeLayer.Score;

public class FullRasterization implements RasterizationStrategy{
    public void rasterization(GameBoard gameBoard, Score score) {
        System.out.println(gameBoard.toString());
        int angle = 0;
        switch (gameBoard.getSnake().getDirection()) {
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
        System.out.println("Dir H: " + angle + "\t" + "Pontos: " + score.getPoints());
    }
}
