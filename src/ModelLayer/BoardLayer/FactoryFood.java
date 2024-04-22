package ModelLayer.BoardLayer;

import ModelLayer.SnakeLayer.Circunferencia;
import ModelLayer.SnakeLayer.Quadrado;

public class FactoryFood {
    public Food createFood(Object shape) {
        if(shape instanceof Circunferencia) {
            return new FoodCircle((Circunferencia) shape);
        }
        else if(shape instanceof Quadrado) {
            return new FoodSquare((Quadrado) shape);
        }
        else {
            throw new IllegalArgumentException("Parâmetro inválido para criar a Food");
        }
    }    
}
