package ModelLayer.BoardLayer;

import ModelLayer.SnakeLayer.Circunferencia;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Snake;

/**
 * Classe que representa comida no formato circular no tabuleiro do jogo.
 * Responsabilidade: Fornecer métodos para verificar colisões e contenção de comida circular.
 * @version 1.0 10/05/2024
 * @autor Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class FoodCircle extends Food {

    private Circunferencia circunferencia;

    /**
     * Construtor que inicializa a comida circular com uma circunferência fornecida.
     * @param circunferencia A circunferência que define a comida.
     */
    public FoodCircle(Circunferencia circunferencia) {
        super();
        this.circunferencia = circunferencia;
    }

    @Override
    public boolean foodContainedInSnakeHead(Snake snake) {
        return circunferencia.contidaNoPoligono(snake.getHead());
    }

    @Override
    public boolean foodIntersectObstacle(Obstacle obstacle) {
        return circunferencia.interseta(obstacle.getPoligono());
    }

    @Override
    public boolean foodContainedObstacle(Obstacle obstacle) {
        return circunferencia.contidaNoPoligono(obstacle.getPoligono()) || obstacle.getPoligono().contidaNaCircunferencia(circunferencia);
    }

    @Override
    public boolean foodIntersectSnake(Snake snake) {
        for (int i = 0; i < snake.getBody().size(); i++) {
            if (circunferencia.interseta(snake.getBody().get(i))) {
                return true;
            }
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

    @Override
    public Ponto<? extends Number> getCentroide() {
        return this.circunferencia.getCentro();
    }

    @Override
    public String toString() {
        return "Comida com: " + circunferencia.toString();
    }
}
