package ModelLayer.BoardLayer;

import java.util.ArrayList;
import java.util.List;

import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Snake;

public class FoodSquare extends Food {

    private Quadrado quadrado;

    public FoodSquare(Quadrado quadrado) {
        super();
        this.quadrado = quadrado;
    }

    @Override
    public boolean foodContainedInSnake(Snake snake) {
        if(snake.getBody().size() == 1) {
            if(quadrado.contida(snake.getHead()))
                return true;
            return false;
        }
        else {
            List<Ponto<? extends Number>> pontos = new ArrayList<>();
            Poligono bodySnake = null;
            switch (snake.getCurrentDirection()) {
                case UP:
                    pontos.add(new Ponto<Integer>((int) snake.getBody().getFirst().getMaxX(), (int) snake.getBody().getFirst().getMaxY()));
                    pontos.add(new Ponto<Integer>((int) snake.getBody().getFirst().getMinX(),(int) snake.getBody().getFirst().getMaxY()));
                    pontos.add(new Ponto<Integer>((int) snake.getBody().get(1).getMinX(),(int) snake.getBody().get(1).getMinY()));
                    pontos.add(new Ponto<Integer>((int) snake.getBody().get(1).getMaxX(),(int) snake.getBody().get(1).getMinY()));
                    bodySnake = new Poligono(pontos);
                    break;
                case DOWN:
                    pontos.add(new Ponto<Integer>((int) snake.getBody().getFirst().getMinX(),(int) snake.getBody().getFirst().getMinY()));
                    pontos.add(new Ponto<Integer>((int) snake.getBody().getFirst().getMaxX(),(int) snake.getBody().getFirst().getMinY()));
                    pontos.add(new Ponto<Integer>((int) snake.getBody().get(1).getMaxX(),(int) snake.getBody().get(1).getMaxY()));
                    pontos.add(new Ponto<Integer>((int) snake.getBody().get(1).getMinX(),(int) snake.getBody().get(1).getMaxY()));
                    bodySnake = new Poligono(pontos);
                    break;
                case LEFT:
                    pontos.add(new Ponto<Integer>((int) snake.getBody().getFirst().getMinX(),(int) snake.getBody().getFirst().getMaxY()));
                    pontos.add(new Ponto<Integer>((int) snake.getBody().getFirst().getMinX(),(int) snake.getBody().getFirst().getMinY()));
                    pontos.add(new Ponto<Integer>((int) snake.getBody().get(1).getMaxX(),(int) snake.getBody().get(1).getMinY()));
                    pontos.add(new Ponto<Integer>((int) snake.getBody().get(1).getMaxX(),(int) snake.getBody().get(1).getMaxY()));
                    bodySnake = new Poligono(pontos);
                    break;
                case RIGHT:
                    pontos.add(new Ponto<Integer>((int) snake.getBody().getFirst().getMaxX(),(int) snake.getBody().getFirst().getMaxY()));
                    pontos.add(new Ponto<Integer>((int) snake.getBody().getFirst().getMaxX(),(int) snake.getBody().getFirst().getMinY()));
                    pontos.add(new Ponto<Integer>((int) snake.getBody().get(1).getMinX(),(int) snake.getBody().get(1).getMinY()));
                    pontos.add(new Ponto<Integer>((int) snake.getBody().get(1).getMinX(),(int) snake.getBody().get(1).getMaxY()));
                    bodySnake = new Poligono(pontos);
                    break;
            }
            if(quadrado.contida(bodySnake))
                return true;
            return false;
        }
    }

    @Override
    public boolean foodIntersectObstacle(Obstacle obstacle){
        if (quadrado.interseta(obstacle.getPoligono())) {
            return true;
        }     
        return false;
    }

    @Override
    public boolean foodIntersectSnake(Snake snake) {
        for(int i = 0; i < snake.getBody().size(); i++) {
            if(quadrado.interseta(snake.getBody().get(i)))
                return true;
        }
        return false;
    }

    
    @Override
    public boolean foodContainedObstacle(Obstacle obstacle) {
        if(quadrado.contida(obstacle.getPoligono()) || obstacle.getPoligono().contida(quadrado))
            return true;
        return false;
    }

    @Override
    public int getMaxX() {
        return (int) this.quadrado.getMaxX();
    }

    @Override
    public int getMinX() {
        return (int) this.quadrado.getMinX();
    }

    @Override
    public int getMaxY() {
        return (int) this.quadrado.getMaxY();
    }

    @Override
    public int getMinY() {
        return (int) this.quadrado.getMinY();
    }


    @Override
    public String toString() {
        return "Comida com: " + quadrado.toString();
    } 

    @Override
    public Ponto<? extends Number> getCentroide() {
        return this.quadrado.getCentroide();
    }

    public Quadrado getQuadrado() {
        return quadrado;
    }

    public void setQuadrado(Quadrado quadrado) {
        this.quadrado = quadrado;
    }
}
