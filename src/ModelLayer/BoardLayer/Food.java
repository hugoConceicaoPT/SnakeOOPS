package ModelLayer.BoardLayer;

import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Snake;
import javafx.scene.paint.Color;

/**
 * Classe abstrata que representa comida no tabuleiro do jogo.
 * Responsabilidade: Fornecer métodos base para identificar e manipular comida no jogo.
 * @version 1.0 10/05/2024
 * @autor Hugo Conceição, João Ventura, Eduarda Pereira
 */
public abstract class Food {
    protected Color color;

    /**
     * Construtor que inicializa a cor padrão da comida.
     * Pode ser alterada posteriormente usando o método `setColor`.
     */
    public Food() {
        this.color = Color.YELLOW;
    }

    /**
     * Verifica se a comida está intersectando um obstáculo.
     * Cada subclasse deve implementar a lógica específica de interseção.
     * @param obstacle O obstáculo a ser verificado.
     * @return verdadeiro se houver interseção, falso caso contrário.
     */
    public abstract boolean foodIntersectObstacle(Obstacle obstacle);

    /**
     * Verifica se a comida está contida dentro de um obstáculo.
     * Cada subclasse deve implementar a lógica específica de contenção.
     * @param obstacle O obstáculo a ser verificado.
     * @return verdadeiro se estiver contida, falso caso contrário.
     */
    public abstract boolean foodContainedObstacle(Obstacle obstacle);

    /**
     * Verifica se a comida está contida dentro da cabeça da cobra.
     * Cada subclasse deve implementar a lógica específica de contenção.
     * @param snake O objeto `Snake` representando a cobra.
     * @return verdadeiro se estiver contida, falso caso contrário.
     */
    public abstract boolean foodContainedInSnakeHead(Snake snake);

    /**
     * Obtém o centróide (centro geométrico) da comida.
     * Cada subclasse deve implementar o cálculo específico do centróide.
     * @return O ponto representando o centróide da comida.
     */
    public abstract Ponto<? extends Number> getCentroide();

    /**
     * Verifica se a comida está intersectando qualquer parte da cobra.
     * Cada subclasse deve implementar a lógica específica de interseção.
     * @param snake O objeto `Snake` representando a cobra.
     * @return verdadeiro se houver interseção, falso caso contrário.
     */
    public abstract boolean foodIntersectSnake(Snake snake);

    /**
     * Obtém a coordenada mínima no eixo X ocupada pela comida.
     * @return O valor mínimo no eixo X.
     */
    public abstract int getMinX();

    /**
     * Obtém a coordenada máxima no eixo X ocupada pela comida.
     * @return O valor máximo no eixo X.
     */
    public abstract int getMaxX();

    /**
     * Obtém a coordenada mínima no eixo Y ocupada pela comida.
     * @return O valor mínimo no eixo Y.
     */
    public abstract int getMinY();

    /**
     * Obtém a coordenada máxima no eixo Y ocupada pela comida.
     * @return O valor máximo no eixo Y.
     */
    public abstract int getMaxY();

    /**
     * Representa a comida como uma string.
     * Cada subclasse deve implementar uma representação textual específica.
     * @return A representação textual da comida.
     */
    @Override
    public abstract String toString();

    /**
     * Obtém a cor atual da comida.
     * @return A cor da comida.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Define uma nova cor para a comida.
     * @param color A nova cor a ser atribuída.
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
