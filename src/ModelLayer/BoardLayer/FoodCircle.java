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

    /**
     * Verifica se a cabeça da cobra contém a comida.
     * @param snake A cobra a ser verificada.
     * @return true se a cabeça da cobra contém a comida, caso contrário false.
     */
    @Override
    public boolean foodContainedInSnakeHead(Snake snake) {
        return circunferencia.contidaNoPoligono(snake.getHead());
    }

    /**
     * Verifica se a comida intersecta com um obstáculo.
     * @param obstacle O obstáculo a ser verificado.
     * @return true se a comida intersecta o obstáculo, caso contrário false.
     */
    @Override
    public boolean foodIntersectObstacle(Obstacle obstacle) {
        return circunferencia.interseta(obstacle.getPoligono());
    }

    /**
     * Verifica se a comida está contida em um obstáculo.
     * @param obstacle O obstáculo a ser verificado.
     * @return true se a comida está contida no obstáculo ou vice-versa, caso contrário false.
     */
    @Override
    public boolean foodContainedObstacle(Obstacle obstacle) {
        return circunferencia.contidaNoPoligono(obstacle.getPoligono()) || obstacle.getPoligono().contidaNaCircunferencia(circunferencia);
    }

    /**
     * Verifica se a comida intersecta com qualquer parte do corpo da cobra.
     * @param snake A cobra a ser verificada.
     * @return true se a comida intersecta qualquer parte do corpo da cobra, caso contrário false.
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
     * Obtém a coordenada máxima X da comida.
     * @return A coordenada máxima X.
     */
    @Override
    public int getMaxX() {
        return (int) (this.circunferencia.getCentro().getX().doubleValue() + this.circunferencia.getRaio());
    }

    /**
     * Obtém a coordenada mínima X da comida.
     * @return A coordenada mínima X.
     */
    @Override
    public int getMinX() {
        return (int) (this.circunferencia.getCentro().getX().doubleValue() - this.circunferencia.getRaio());
    }

    /**
     * Obtém a coordenada máxima Y da comida.
     * @return A coordenada máxima Y.
     */
    @Override
    public int getMaxY() {
        return (int) (this.circunferencia.getCentro().getY().doubleValue() + this.circunferencia.getRaio());
    }

    /**
     * Obtém a coordenada mínima Y da comida.
     * @return A coordenada mínima Y.
     */
    @Override
    public int getMinY() {
        return (int) (this.circunferencia.getCentro().getY().doubleValue() - this.circunferencia.getRaio());
    }

    /**
     * Obtém o centroide da comida.
     * @return O centroide da comida.
     */
    @Override
    public Ponto<? extends Number> getCentroide() {
        return this.circunferencia.getCentro();
    }

    /**
     * Retorna uma representação em string da comida circular.
     * @return Uma string que representa a comida circular.
     */
    @Override
    public String toString() {
        return "Comida com: " + circunferencia.toString();
    }

    /**
     * Obtém a circunferência que define a comida.
     * @return A circunferência que define a comida.
     */
    public Circunferencia getCircunferencia() {
        return circunferencia;
    }

    /**
     * Define uma nova circunferência para a comida.
     * @param circunferencia A nova circunferência a ser definida.
     */
    public void setCircunferencia(Circunferencia circunferencia) {
        this.circunferencia = circunferencia;
    }
}
