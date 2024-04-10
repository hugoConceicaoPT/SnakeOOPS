package BoardLayer;

import SnakeLayer.IFiguraGeometrica;

public class Obstacle {
    private IFiguraGeometrica figuraGeometrica;
    private boolean isDynamic;

    public Obstacle(IFiguraGeometrica figuraGeometrica, boolean isDynamic) {
        this.figuraGeometrica = figuraGeometrica;
        this.isDynamic = isDynamic;
    }
}
