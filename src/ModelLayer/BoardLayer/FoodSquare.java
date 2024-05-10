package ModelLayer.BoardLayer;

import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Snake;

/**
 * Classe que representa comida no formato de quadrado no tabuleiro do jogo.
 * Responsabilidade: Gerenciar as interações da comida quadrada com outros elementos do jogo, como cobra e obstáculos.
 * @version 1.0 10/05/2024
 * @autor Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class FoodSquare extends Food {

    // Objeto Quadrado que define a forma e posição da comida no tabuleiro
    private Quadrado quadrado;

    /**
     * Construtor que inicializa a comida quadrada com um objeto Quadrado.
     * @param quadrado Objeto Quadrado que especifica a localização e o tamanho da comida.
     */
    public FoodSquare(Quadrado quadrado) {
        super();
        this.quadrado = quadrado;
    }

    /**
     * Verifica se a comida quadrada está intersectando um obstáculo.
     * @param obstacle O obstáculo a ser verificado.
     * @return verdadeiro se há interseção, falso caso contrário.
     */
    @Override
    public boolean foodIntersectObstacle(Obstacle obstacle) {
        return quadrado.interseta(obstacle.getPoligono());
    }

    /**
     * Verifica se a comida quadrada está contida dentro da cabeça da cobra.
     * @param snake A cobra para verificação.
     * @return verdadeiro se a comida estiver contida, falso caso contrário.
     */
    @Override
    public boolean foodContainedInSnakeHead(Snake snake) {
        return quadrado.contida(snake.getHead());
    }

    /**
     * Verifica se a comida quadrada está intersectando qualquer parte da cobra.
     * @param snake A cobra para verificação.
     * @return verdadeiro se há interseção, falso caso contrário.
     */
    @Override
    public boolean foodIntersectSnake(Snake snake) {
        for (int i = 0; i < snake.getBody().size(); i++) {
            if (quadrado.interseta(snake.getBody().get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se a comida quadrada está contida ou contém um obstáculo.
     * @param obstacle O obstáculo para verificação.
     * @return verdadeiro se houver contenção de qualquer forma, falso caso contrário.
     */
    @Override
    public boolean foodContainedObstacle(Obstacle obstacle) {
        return quadrado.contida(obstacle.getPoligono()) || obstacle.getPoligono().contida(quadrado);
    }

    /**
     * Obtém a coordenada máxima X da comida quadrada.
     * @return A coordenada máxima X.
     */
    @Override
    public int getMaxX() {
        return (int) quadrado.getMaxX();
    }

    /**
     * Obtém a coordenada mínima X da comida quadrada.
     * @return A coordenada mínima X.
     */
    @Override
    public int getMinX() {
        return (int) quadrado.getMinX();
    }

    /**
     * Obtém a coordenada máxima Y da comida quadrada.
     * @return A coordenada máxima Y.
     */
    @Override
    public int getMaxY() {
        return (int) quadrado.getMaxY();
    }

    /**
     * Obtém a coordenada mínima Y da comida quadrada.
     * @return A coordenada mínima Y.
     */
    @Override
    public int getMinY() {
        return (int) quadrado.getMinY();
    }

    /**
     * Obtém o centróide da comida quadrada.
     * @return O ponto centróide.
     */
    @Override
    public Ponto<? extends Number> getCentroide() {
        return quadrado.getCentroide();
    }

    /**
     * Representa a comida quadrada como uma string.
     * @return Descrição textual da comida.
     */
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
