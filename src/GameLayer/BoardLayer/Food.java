package GameLayer.BoardLayer;

import GameLayer.SnakeLayer.IFiguraGeometrica;
import javafx.scene.paint.Color;
public class Food {
    private Color color;
    private IFiguraGeometrica figuraGeometrica;

    public Food(IFiguraGeometrica figuraGeometrica) {
        this.color = Color.YELLOW;
        this.figuraGeometrica = figuraGeometrica;
    }
}
