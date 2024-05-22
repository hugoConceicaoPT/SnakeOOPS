package ModelLayer.BoardLayer;

import java.util.ArrayList;
import java.util.List;

import ModelLayer.SnakeLayer.Circunferencia;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Quadrado;

/**
 * Classe que representa uma fábrica para criar diferentes tipos de comida no jogo.
 * Responsabilidade: Fornecer métodos para criar comida de diferentes tipos, como quadrados e círculos.
 * @version 1.0 10/05/2024
 * @author Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class FactoryFood {
    /**
     * Cria um item de comida no jogo com base no tipo fornecido.
     * Aceita como entrada as coordenadas do ponto central, o tipo de comida e a dimensão desejada.
     * @param x A coordenada x do centro da comida.
     * @param y A coordenada y do centro da comida.
     * @param foodType O tipo de comida a ser criado (círculo ou quadrado).
     * @param foodDimension O tamanho ou diâmetro do item de comida.
     * @return Um objeto `Food` representando a comida criada.
     * @throws IllegalArgumentException Se o tipo de comida for inválido.
     */
    public Food createFood(int x, int y, FoodType foodType, int foodDimension) {
        if (foodType == FoodType.CIRCLE) {
            double xCenter = x + foodDimension/2.0;
            double yCenter = y + foodDimension/2.0;
            return new FoodCircle(new Circunferencia(new Ponto<>(xCenter, yCenter), foodDimension / 2.0));
        } else if (foodType == FoodType.SQUARE) {
            return new FoodSquare(new Quadrado(createSquarePoints(x, y, foodDimension)));
        } else {
            throw new IllegalArgumentException("Parâmetro inválido para criar a Food");
        }
    }

    /**
     * Cria uma lista de pontos que definem os vértices de um quadrado.
     * O quadrado é centrado no ponto fornecido, com o tamanho especificado.
     * @param x A coordenada x do centro do quadrado.
     * @param y A coordenada y do centro do quadrado.
     * @param size O tamanho do quadrado (comprimento da aresta).
     * @return Uma lista de pontos que representam os vértices do quadrado.
     */
    private List<Ponto<? extends Number>> createSquarePoints(int x, int y, int size) {
        List<Ponto<? extends Number>> pontos = new ArrayList<>();
        pontos.add(new Ponto<Integer>(x, y));
        pontos.add(new Ponto<Integer>(x + size, y));
        pontos.add(new Ponto<Integer>(x + size, y + size));
        pontos.add(new Ponto<Integer>(x, y + size));
        return pontos;
    }
}
