package ModelLayer.BoardLayer;

import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Ponto;

public class DynamicMovement implements ObstacleMovement {
    @Override
    public void rotateObstacle(Poligono poligono, Ponto<? extends Number> pontoPivo) {     
        poligono.rotate(90, pontoPivo);   
    }   
}
