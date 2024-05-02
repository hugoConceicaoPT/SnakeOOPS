package ModelLayer.BoardLayer;

import ModelLayer.SnakeLayer.Circunferencia;
import ModelLayer.SnakeLayer.Snake;

public class FoodCircle extends Food {

    private Circunferencia circunferencia;

    public FoodCircle(Circunferencia circunferencia) {
        super();
        this.circunferencia = circunferencia;
    }

    @Override
    public boolean foodContainedInHead(Snake snake){
        if(circunferencia.contidaNoPoligono(snake.getHead()))
            return true;
        return false;
    }
    @Override
    public boolean foodIntersectObstacle(Obstacle obstacle){
        if (circunferencia.interseta(obstacle.getPoligono())) {
            return true;
        }     
        return false;
    }

    public Circunferencia getCircunferencia() {
        return circunferencia;
    }

    public void setCircunferencia(Circunferencia circunferencia) {
        this.circunferencia = circunferencia;
    }

    @Override
    public String toString() {
        return "Comida com: " + circunferencia.toString();
    } 
}
