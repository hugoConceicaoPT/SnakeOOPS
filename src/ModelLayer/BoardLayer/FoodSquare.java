package ModelLayer.BoardLayer;

import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Snake;

/**
 * Classe que representa comida no formato de quadrado no tabuleiro do jogo.
 * Responsabilidade: Gerenciar as interações da comida quadrada com outros elementos do jogo, como cobra e obstáculos.
 * @version 1.0 10/05/2024
 * @author Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class FoodSquare extends Food {

    private Quadrado quadrado;

    /**
     * Construtor que inicializa a comida quadrada com um objeto Quadrado.
     * @param quadrado Objeto Quadrado que especifica a localização e o tamanho da comida.
     */
    public FoodSquare(Quadrado quadrado) {
        super();
        this.quadrado = quadrado;
    }

    @Override
    public boolean foodIntersectObstacle(Obstacle obstacle) {
        return quadrado.interseta(obstacle.getPoligono());
    }

    @Override
    public boolean foodContainedInSnakeHead(Snake snake) {
        return quadrado.contida(snake.getHead());
    }

    @Override
    public boolean foodIntersectSnake(Snake snake) {
        for (int i = 0; i < snake.getBody().size(); i++) {
            if (quadrado.interseta(snake.getBody().get(i))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean foodContainedObstacle(Obstacle obstacle) {
        return quadrado.contida(obstacle.getPoligono()) || obstacle.getPoligono().contida(quadrado);
    }

    @Override
    public int getMaxX() {
        return (int) quadrado.getMaxX();
    }

    @Override
    public int getMinX() {
        return (int) quadrado.getMinX();
    }

    @Override
    public int getMaxY() {
        return (int) quadrado.getMaxY();
    }

    @Override
    public int getMinY() {
        return (int) quadrado.getMinY();
    }

    @Override
    public Ponto<? extends Number> getCentroide() {
        return quadrado.getCentroide();
    }

    @Override
    public String toString() {
        return "Comida com: " + quadrado.toString();
    }

    /**
     * Obtém o objeto Quadrado que define a comida.
     * @return O objeto Quadrado.
     */
    public Quadrado getQuadrado() {
        return quadrado;
    }

    /**
     * Define um novo objeto Quadrado para a comida.
     * @param quadrado o antigo objeto Quadrado para a comida.
     */
    public void setQuadrado(Quadrado quadrado) {
        this.quadrado = quadrado;
    }
}
