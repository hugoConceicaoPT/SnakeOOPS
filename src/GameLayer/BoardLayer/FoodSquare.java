package GameLayer.BoardLayer;

import GameLayer.SnakeLayer.Quadrado;
import GameLayer.SnakeLayer.Snake;

public class FoodSquare extends Food {

    private Quadrado quadrado;

    public FoodSquare(Quadrado quadrado) {
        super();
        this.quadrado = quadrado;
    }

    @Override
    public boolean foodIntersetaHead(Snake snake){
        if(quadrado.contida(snake.getHead())) {
           return true; 
        }
        return false;
    }
}
