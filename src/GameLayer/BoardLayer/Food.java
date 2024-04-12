package GameLayer.BoardLayer;

import GameLayer.SnakeLayer.IFiguraGeometrica;
import GameLayer.SnakeLayer.Snake;
import GameLayer.SnakeLayer.SnakeGame;
import javafx.scene.paint.Color;
public class Food {
    private Color color;
    private IFiguraGeometrica figuraGeometrica;

    public Food(IFiguraGeometrica figuraGeometrica) {
        this.color = Color.YELLOW;
        this.figuraGeometrica = figuraGeometrica;
    }

    public boolean FoodIntersetaHead(Snake snake){
        if(figuraGeometrica.interseta(snake.getHead())) {
           return true; 
        }
        return false;
    }
}
