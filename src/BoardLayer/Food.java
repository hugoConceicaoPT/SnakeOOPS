package BoardLayer;

import javafx.scene.paint.Color;
import SnakeLayer.IFiguraGeometrica;
public class Food {
    private Color color;
    private IFiguraGeometrica figuraGeometrica;

    public Food(IFiguraGeometrica figuraGeometrica) {
        this.color = Color.YELLOW;
        this.figuraGeometrica = figuraGeometrica;
    }
}
