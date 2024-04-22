package ModelLayer.BoardLayer;

import ModelLayer.SnakeLayer.Snake;
import javafx.scene.paint.Color;
public abstract class Food {
    private Color color;

    /** Construtor para criar uma comida na board
     */
    public Food() {
        this.color = Color.YELLOW;
    }

    /** Verifica se a comida está contido na cobra
     * @param snake o objeto snake
     * @return verdadeiro se estiver contido, falso se não
     */
    public abstract boolean foodIntersetaHead(Snake snake);
    

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
