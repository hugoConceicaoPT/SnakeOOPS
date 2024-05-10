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

    // Representa a circunferência da comida circular
    private Circunferencia circunferencia;

    /**
     * Construtor que inicializa a comida circular com uma circunferência fornecida.
     * @param circunferencia A circunferência que define a comida.
     */
    public FoodCircle(Circunferencia circunferencia) {
        super();
        this.circunferencia = circunferencia;
    }

    /**
     * Verifica se a comida está contida dentro da cabeça da cobra.
     * @param snake O objeto `Snake` representando a cobra.
     * @return verdadeiro se estiver contida, falso caso contrário.
     */
    @Override
    public boolean foodContainedInSnakeHead(Snake snake) {
        return circunferencia.contidaNoPoligono(snake.getHead());
    }

    /**
     * Verifica se a comida está intersectando um obstáculo.
     * @param obstacle O obstáculo a ser verificado.
     * @return verdadeiro se houver interseção, falso caso contrário.
     */
    @Override
    public boolean foodIntersectObstacle(Obstacle obstacle) {
        return circunferencia.interseta(obstacle.getPoligono());
    }

    /**
     * Verifica se a comida está contida dentro de um obstáculo.
     * @param obstacle O obstáculo a ser verificado.
     * @return verdadeiro se estiver contida, falso caso contrário.
     */
    @Override
    public boolean foodContainedObstacle(Obstacle obstacle) {
        return circunferencia.contidaNoPoligono(obstacle.getPoligono()) || obstacle.getPoligono().contidaNaCircunferencia(circunferencia);
    }

    /**
     * Verifica se a comida circular está intersectando qualquer parte da cobra.
     * @param snake O objeto `Snake` representando a cobra.
     * @return verdadeiro se houver interseção, falso caso contrário.
     */
    @Override
    public boolean foodIntersectSnake(Snake snake) {
        for (int i = 0; i < snake.getBody().size(); i++) {
            if (circunferencia.interseta(snake.getBody().get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtém a coordenada máxima no eixo X ocupada pela comida circular.
     * @return O valor máximo no eixo X.
     */
    @Override
    public int getMaxX() {
        return (int) (this.circunferencia.getCentro().getX().doubleValue() + this.circunferencia.getRaio());
    }

    /**
     * Obtém a coordenada mínima no eixo X ocupada pela comida circular.
     * @return O valor mínimo no eixo X.
     */
    @Override
    public int getMinX() {
        return (int) (this.circunferencia.getCentro().getX().doubleValue() - this.circunferencia.getRaio());
    }

    /**
     * Obtém a coordenada máxima no eixo Y ocupada pela comida circular.
     * @return O valor máximo no eixo Y.
     */
    @Override
    public int getMaxY() {
        return (int) (this.circunferencia.getCentro().getY().doubleValue() + this.circunferencia.getRaio());
    }

    /**
     * Obtém a coordenada mínima no eixo Y ocupada pela comida circular.
     * @return O valor mínimo no eixo Y.
     */
    @Override
    public int getMinY() {
        return (int) (this.circunferencia.getCentro().getY().doubleValue() - this.circunferencia.getRaio());
    }

    /**
     * Obtém o ponto central da comida circular.
     * @return O ponto representando o centróide da comida.
     */
    @Override
    public Ponto<? extends Number> getCentroide() {
        return this.circunferencia.getCentro();
    }

    /**
     * Representa a comida circular como uma string.
     * @return A representação textual da comida circular.
     */
    @Override
    public String toString() {
        return "Comida com: " + circunferencia.toString();
    }
}
