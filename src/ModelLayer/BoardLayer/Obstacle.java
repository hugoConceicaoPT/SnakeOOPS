package ModelLayer.BoardLayer;

import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Snake;

public class Obstacle {

    private Poligono poligono;
    private ObstacleMovement obstacleMovement;
    private Ponto<? extends Number> rotacionPoint;

    /** Construtor para criar um obstáculo
     * @param figuraGeometrica figura geométrica
     * @param isDynamic booleano para verificar se o obstáculo é dinâmico
     */
    public Obstacle(Poligono poligono, Ponto<? extends Number> rotacionPoint, boolean isDynamic) {
        this.poligono = poligono;
        if(isDynamic)
            this.obstacleMovement = new DynamicMovement();
        else
            this.obstacleMovement = new StaticMovement();
        if(rotacionPoint == null)
            this.rotacionPoint = poligono.getCentroide();
        else
            this.rotacionPoint = rotacionPoint;
    }

    public boolean obstacleIntersect(Snake snake) {
        if (snake.getHead().interseta(this.poligono)) 
            return true;
        return false;
    }

    public boolean obstacleContained(Snake snake) {
        if (this.poligono.contida(snake.getHead())) {
                return true;
        }
        return false;
    }

    public boolean obstacleIntersect(Ponto<? extends Number> point) {
        double x = point.getX().doubleValue();
        double y = point.getY().doubleValue();
        return (x >= this.poligono.getMinX() && x <= this.poligono.getMaxX() &&
            y >= this.poligono.getMinY() && y <= this.poligono.getMaxY());
    }

    public void rotateObstacle() {
        this.obstacleMovement.rotateObstacle(this.poligono,this.rotacionPoint);
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

    public Ponto<? extends Number> getRotacionPoint() {
        return rotacionPoint;
    }

    public void setRotacionPoint(Ponto<? extends Number> rotacionPoint) {
        this.rotacionPoint = rotacionPoint;
    }
}
