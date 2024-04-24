package ModelLayer.BoardLayer;

import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Snake;

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
    
    public boolean obstacleIntersect(Snake snake){

        if (poligono.interseta(snake.getHead())) 
            return true;
        return false;
    }

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

    @Override
    public String toString() {
        return "Obstáculo: " + poligono.toString();
    }
}
