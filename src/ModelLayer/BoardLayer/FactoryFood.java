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
 * @autor Hugo Conceição, João Ventura, Eduarda Pereira
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
            // Cria comida do tipo círculo, representada por uma circunferência
            return new FoodCircle(new Circunferencia(new Ponto<>(x, y), foodDimension / 2));
        } else if (foodType == FoodType.SQUARE) {
            // Cria comida do tipo quadrado, representada por um polígono
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
    private List<Ponto<? extends Number>> createSquarePoints(int x, int y, double size) {
        List<Ponto<? extends Number>> pontos = new ArrayList<>();
        double halfSize = size / 2.0;

        // Adiciona os quatro vértices do quadrado, começando no canto superior esquerdo e seguindo no sentido horário
        pontos.add(new Ponto<>(x - halfSize, y - halfSize));
        pontos.add(new Ponto<>(x - halfSize, y + halfSize));
        pontos.add(new Ponto<>(x + halfSize, y + halfSize));
        pontos.add(new Ponto<>(x + halfSize, y - halfSize));

        return pontos;
    }
}
