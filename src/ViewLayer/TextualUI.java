package ViewLayer;

import ModelLayer.BoardLayer.GameBoard;
import ModelLayer.BoardLayer.Score;

public class TextualUI implements UI {

    private RasterizationStrategy rasterizationStrategy;

    public TextualUI(RasterizationStrategy rasterizationStrategy) {
        this.rasterizationStrategy = rasterizationStrategy;
    }

    public void display(Score score, GameBoard gameBoard) {
        System.out.println(this.rasterizationStrategy.toString());
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
        System.out.println("Dir H: " + angle + "\t" + "Pontos: " + score.getPoints());
    }
}
