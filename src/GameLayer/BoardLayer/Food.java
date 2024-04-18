package GameLayer.BoardLayer;

import GameLayer.SnakeLayer.Snake;
import javafx.scene.paint.Color;
public abstract class Food {
    private Color color;

    /** Construtor para criar uma comida na board
     * @param figuraGeometrica uma figura geométrica
     */
    public Food() {
        this.color = Color.YELLOW;
    }

    /** Verifica se a comida está contido na cobra
     * @param snake o objeto snake
     * @return verdadeiro se estiver contido, falso se não
     */
    public abstract boolean FoodIntersetaHead(Snake snake);

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
