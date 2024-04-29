package ViewLayer;

import ControllerLayer.ContourRasterization;
import ControllerLayer.RasterizationStrategy;
import ControllerLayer.SnakeGame;
import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.SnakeLayer.Score;

public class TextualUI implements UI {

    private RasterizationStrategy rasterizationStrategy;

    public TextualUI(RasterizationStrategy rasterizationStrategy) {
        this.rasterizationStrategy = rasterizationStrategy;
    }

    public void display(Score score, GameBoard gameBoard) {
        if(this.rasterizationStrategy instanceof ContourRasterization) {
            renderContour(score,gameBoard);
        }
        else
            renderFull(score,gameBoard);
    }

    private void renderContour(Score score,GameBoard gameBoard) {

    }

    private void renderFull(Score score, GameBoard gameBoard) {
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
        System.out.println("Dir H: " + angle + "\t" + "Pontos: " + score.getScore());
    }
}
