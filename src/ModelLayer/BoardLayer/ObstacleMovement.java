package ModelLayer.BoardLayer;

import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Ponto;

public interface ObstacleMovement {
    void rotateObstacle(Poligono poligono, Ponto pontoPivo);
}
