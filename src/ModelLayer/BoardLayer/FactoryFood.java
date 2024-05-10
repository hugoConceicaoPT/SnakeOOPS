package ModelLayer.BoardLayer;

import java.util.ArrayList;
import java.util.List;

import ModelLayer.SnakeLayer.Circunferencia;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;

public class FactoryFood {
    public Food createFood(int x, int y, FoodType foodType, int foodDimension) {
        if(foodType == FoodType.CIRCLE) {
            return new FoodCircle(new Circunferencia(new Ponto<Integer>(x,y), foodDimension/2));
        }
        else if(foodType == FoodType.SQUARE) {
            return new FoodSquare(new Quadrado(createSquarePoints(x, y, foodDimension)));
        }
        else {
            throw new IllegalArgumentException("Parâmetro inválido para criar a Food");
        }
    }    

    private List<Ponto<? extends Number>> createSquarePoints(int x, int y, double size) {
        List<Ponto<? extends Number>> pontos = new ArrayList<>();
        pontos.add(new Ponto<Number>(x - (size/2.0) , y - (size/2.0)));
        pontos.add(new Ponto<Number>(x - (size/2.0),y + (size/2.0)));
        pontos.add(new Ponto<Number>(x + (size/2.0), y + (size/2.0)));
        pontos.add(new Ponto<Number>(x + (size/2.0), y - (size/2.0)));

        return pontos;
    }
}
