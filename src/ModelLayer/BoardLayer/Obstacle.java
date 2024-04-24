package ModelLayer.BoardLayer;

import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Snake;

public class Obstacle {

    private Poligono poligono;
    private ObstacleMovement obstacleMovement;

    /** Construtor para criar um obstáculo
     * @param figuraGeometrica figura geométrica
     * @param isDynamic booleano para verificar se o obstáculo é dinâmico
     */
    public Obstacle(Poligono poligono, boolean isDynamic) {
        this.poligono = poligono;
        if(isDynamic)
            this.obstacleMovement = new DynamicMovement();
        else
            this.obstacleMovement = new StaticMovement();
    }
    
    public boolean obstacleIntersect(Snake snake){

        if (poligono.interseta(snake.getHead())) 
            return true;
        return false;
    }

    public void rotateObstacle(boolean isDynamic){
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

    public ObstacleMovement getObstacleMovement() {
        return obstacleMovement;
    }

    public void setObstacleMovement(ObstacleMovement obstacleMovement) {
        this.obstacleMovement = obstacleMovement;
    }
}
