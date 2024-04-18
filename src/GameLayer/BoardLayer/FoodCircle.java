package GameLayer.BoardLayer;

import GameLayer.SnakeLayer.Circunferencia;
import GameLayer.SnakeLayer.Snake;

public class FoodCircle extends Food {

    private Circunferencia circunferencia;

    public FoodCircle(Circunferencia circunferencia) {
        super();
        this.circunferencia = circunferencia;
    }

    @Override
    public boolean FoodIntersetaHead(Snake snake){
        if(circunferencia.contidaNoPoligono(snake.getHead()))
            return true;
        return false;
    }

}
