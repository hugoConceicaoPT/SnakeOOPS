package ModelLayer.BoardLayer;

import ModelLayer.SnakeLayer.Circunferencia;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Snake;

public class FoodCircle extends Food {

    private Circunferencia circunferencia;

    public FoodCircle(Circunferencia circunferencia) {
        super();
        this.circunferencia = circunferencia;
    }

    @Override
    public boolean foodContainedInSnakeHead(Snake snake){
        if(circunferencia.contidaNoPoligono(snake.getHead()))
            return true;
        return false;
    }

    @Override
    public boolean foodContainedInSnake(Snake snake){
        for(int i = 1; i < snake.getBody().size(); i++) {
            if(circunferencia.contidaNoPoligono(snake.getBody().get(i)))
                return true;
        }
        return false;
    }

    @Override
    public boolean foodIntersectObstacle(Obstacle obstacle){
        if (circunferencia.interseta(obstacle.getPoligono())) {
            return true;
        }     
        return false;
    }

    @Override
    public boolean foodIntersectSnake(Snake snake) {
        return this.circunferencia.interseta(snake.getHead());
    }

    @Override
    public int getMaxX() {
        return (int) (this.circunferencia.getCentro().getX() + this.circunferencia.getRaio());
    }

    @Override
    public int getMinX() {
        return (int) (this.circunferencia.getCentro().getX() - this.circunferencia.getRaio());
    }

    @Override
    public int getMaxY() {
        return (int) (this.circunferencia.getCentro().getY() + this.circunferencia.getRaio());
    }

    @Override
    public int getMinY() {
        return (int) (this.circunferencia.getCentro().getY() - this.circunferencia.getRaio());
    }
    

    public Circunferencia getCircunferencia() {
        return circunferencia;
    }

    public void setCircunferencia(Circunferencia circunferencia) {
        this.circunferencia = circunferencia;
    }

    @Override
    public Ponto getCentroide() {
        return this.circunferencia.getCentro();
    }

    @Override
    public String toString() {
        return "Comida com: " + circunferencia.toString();
    } 
}
