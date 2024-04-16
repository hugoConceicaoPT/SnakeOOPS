package GameLayer.BoardLayer;

import GameLayer.SnakeLayer.IFiguraGeometrica;
import GameLayer.SnakeLayer.Snake;
import javafx.scene.paint.Color;
public class Food {
    private Color color;
    private IFiguraGeometrica figuraGeometrica;

    /** Construtor para criar uma comida na board
     * @param figuraGeometrica uma figura geométrica
     */
    public Food(IFiguraGeometrica figuraGeometrica) {
        this.color = Color.YELLOW;
        this.figuraGeometrica = figuraGeometrica;
    }

    /** Verifica se a comida está contido na cobra
     * @param snake o objeto snake
     * @return verdadeiro se estiver contido, falso se não
     */
    public boolean FoodIntersetaHead(Snake snake){
        if(figuraGeometrica.interseta(snake.getHead())) {
           return true; 
        }
        return false;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public IFiguraGeometrica getFiguraGeometrica() {
        return figuraGeometrica;
    }

    public void setFiguraGeometrica(IFiguraGeometrica figuraGeometrica) {
        this.figuraGeometrica = figuraGeometrica;
    }
}
