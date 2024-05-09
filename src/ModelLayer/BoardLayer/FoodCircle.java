package ModelLayer.BoardLayer;

import java.util.ArrayList;
import java.util.List;

import ModelLayer.SnakeLayer.Circunferencia;
import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Snake;

public class FoodCircle extends Food {

    private Circunferencia circunferencia;

    public FoodCircle(Circunferencia circunferencia) {
        super();
        this.circunferencia = circunferencia;
    }

    @Override
    public boolean foodContainedInSnake(Snake snake){
        if(snake.getBody().size() == 1) {
            if(circunferencia.contidaNoPoligono(snake.getHead()))
                return true;
            return false;
        }
        else {
            List<Ponto<? extends Number>> pontos = new ArrayList<>();
            Poligono bodySnake = null;
            switch (snake.getCurrentDirection()) {
                case UP:
                    pontos.add(new Ponto<Integer>((int) snake.getBody().getFirst().getMaxX(), (int) snake.getBody().getFirst().getMaxY()));
                    pontos.add(new Ponto<Integer>((int) snake.getBody().getFirst().getMinX(), (int) snake.getBody().getFirst().getMaxY()));
                    pontos.add(new Ponto<Integer>((int) snake.getBody().get(1).getMinX(), (int) snake.getBody().get(1).getMinY()));
                    pontos.add(new Ponto<Integer>((int) snake.getBody().get(1).getMaxX(),(int) snake.getBody().get(1).getMinY()));
                    bodySnake = new Poligono(pontos);
                    break;
                case DOWN:
                    pontos.add(new Ponto<Integer>((int) snake.getBody().getFirst().getMinX(), (int) snake.getBody().getFirst().getMinY()));
                    pontos.add(new Ponto<Integer>((int) snake.getBody().getFirst().getMaxX(), (int) snake.getBody().getFirst().getMinY()));
                    pontos.add(new Ponto<Integer>((int) snake.getBody().get(1).getMaxX(), (int) snake.getBody().get(1).getMaxY()));
                    pontos.add(new Ponto<Integer>((int) snake.getBody().get(1).getMinX(), (int) snake.getBody().get(1).getMaxY()));
                    bodySnake = new Poligono(pontos);
                    break;
                case LEFT:
                    pontos.add(new Ponto<Integer>((int) snake.getBody().getFirst().getMinX(), (int) snake.getBody().getFirst().getMaxY()));
                    pontos.add(new Ponto<Integer>((int) snake.getBody().getFirst().getMinX(), (int) snake.getBody().getFirst().getMinY()));
                    pontos.add(new Ponto<Integer>((int) snake.getBody().get(1).getMaxX(), (int) snake.getBody().get(1).getMinY()));
                    pontos.add(new Ponto<Integer>((int) snake.getBody().get(1).getMaxX(), (int) snake.getBody().get(1).getMaxY()));
                    bodySnake = new Poligono(pontos);
                    break;
                case RIGHT:
                    pontos.add(new Ponto<Integer>((int) snake.getBody().getFirst().getMaxX(), (int) snake.getBody().getFirst().getMaxY()));
                    pontos.add(new Ponto<Integer>((int) snake.getBody().getFirst().getMaxX(),(int) snake.getBody().getFirst().getMinY()));
                    pontos.add(new Ponto<Integer>((int) snake.getBody().get(1).getMinX(), (int) snake.getBody().get(1).getMinY()));
                    pontos.add(new Ponto<Integer>((int) snake.getBody().get(1).getMinX(), (int) snake.getBody().get(1).getMaxY()));
                    bodySnake = new Poligono(pontos);
                    break;
                default:
                    break;
            }
            if(circunferencia.contidaNoPoligono(bodySnake))
                    return true;
            return false;
        }
    }

    @Override
    public boolean foodIntersectObstacle(Obstacle obstacle){
        if (circunferencia.interseta(obstacle.getPoligono())) {
            return true;
        }     
        return false;
    }

    @Override
    public boolean foodContainedObstacle(Obstacle obstacle) {
        if(circunferencia.contidaNoPoligono(obstacle.getPoligono()) || obstacle.getPoligono().contidaNaCircunferencia(circunferencia))
            return true;
        return false;
    }

    @Override
    public boolean foodIntersectSnake(Snake snake) {
        for(int i = 0; i < snake.getBody().size(); i++) {
            if(circunferencia.interseta(snake.getBody().get(i)))
                return true;
        }
        return false;
    }

    @Override
    public int getMaxX() {
        return (int) (this.circunferencia.getCentro().getX().doubleValue() + this.circunferencia.getRaio());
    }

    @Override
    public int getMinX() {
        return (int) (this.circunferencia.getCentro().getX().doubleValue() - this.circunferencia.getRaio());
    }

    @Override
    public int getMaxY() {
        return (int) (this.circunferencia.getCentro().getY().doubleValue() + this.circunferencia.getRaio());
    }

    @Override
    public int getMinY() {
        return (int) (this.circunferencia.getCentro().getY().doubleValue() - this.circunferencia.getRaio());
    }
    

    public Circunferencia getCircunferencia() {
        return circunferencia;
    }

    public void setCircunferencia(Circunferencia circunferencia) {
        this.circunferencia = circunferencia;
    }

    @Override
    public Ponto<? extends Number> getCentroide() {
        return this.circunferencia.getCentro();
    }

    @Override
    public String toString() {
        return "Comida com: " + circunferencia.toString();
    } 
}
