package GameLayer.BoardLayer;

import GameLayer.SnakeLayer.IFiguraGeometrica;
import GameLayer.SnakeLayer.Snake;

public class Obstacle {

    private IFiguraGeometrica figuraGeometrica;
    private boolean isDynamic;

    /** Construtor para criar um obstáculo
     * @param figuraGeometrica figura geométrica
     * @param isDynamic booleano para verificar se o obstáculo é dinâmico
     */
    public Obstacle(IFiguraGeometrica figuraGeometrica, boolean isDynamic) {
        this.figuraGeometrica = figuraGeometrica;
        this.isDynamic = isDynamic;
    }
    
    public boolean osbtacleIntersect(Snake snake){

    if (figuraGeometrica.interseta(snake.getHead())) {
        return true;
    }
    return false;
    }
    public IFiguraGeometrica getFiguraGeometrica() {
        return figuraGeometrica;
    }

    public void setFiguraGeometrica(IFiguraGeometrica figuraGeometrica) {
        this.figuraGeometrica = figuraGeometrica;
    }

    public boolean isDynamic() {
        return isDynamic;
    }

    public void setDynamic(boolean isDynamic) {
        this.isDynamic = isDynamic;
    }
}
