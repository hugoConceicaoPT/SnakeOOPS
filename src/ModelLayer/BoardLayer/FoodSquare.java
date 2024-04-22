package ModelLayer.BoardLayer;

import ModelLayer.SnakeLayer.Quadrado;
import ModelLayer.SnakeLayer.Snake;

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

    public Quadrado getQuadrado() {
        return quadrado;
    }

    public void setQuadrado(Quadrado quadrado) {
        this.quadrado = quadrado;
    }
}
