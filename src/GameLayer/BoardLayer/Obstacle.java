package GameLayer.BoardLayer;

<<<<<<< Updated upstream
import GameLayer.SnakeLayer.IFiguraGeometrica;
import GameLayer.SnakeLayer.Snake;
=======
import GameLayer.SnakeLayer.Poligono;
>>>>>>> Stashed changes

public class Obstacle {

    private Poligono poligono;
    private boolean isDynamic;

    /** Construtor para criar um obstáculo
     * @param figuraGeometrica figura geométrica
     * @param isDynamic booleano para verificar se o obstáculo é dinâmico
     */
    public Obstacle(Poligono poligono, boolean isDynamic) {
        this.poligono = poligono;
        this.isDynamic = isDynamic;
    }
    
    public boolean osbtacleIntersect(Snake snake){

<<<<<<< Updated upstream
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

=======
>>>>>>> Stashed changes
    public boolean isDynamic() {
        return isDynamic;
    }

    public void setDynamic(boolean isDynamic) {
        this.isDynamic = isDynamic;
    }

    public Poligono getPoligono() {
        return poligono;
    }

    public void setPoligono(Poligono poligono) {
        this.poligono = poligono;
    }
}
