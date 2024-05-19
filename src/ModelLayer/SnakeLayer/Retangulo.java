package ModelLayer.SnakeLayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um retângulo no plano cartesiano.
 * Responsabilidade: Representar um retângulo e verificar se os pontos formam um retângulo válido.
 * @version 1.0 10/05/2024
 * @author Hugo Conceição, João Ventura, Eduarda Pereira
 * @inv O retângulo é válido se todos os ângulos internos forem 90 graus e se houver 4 pontos.
 */
public class Retangulo extends Poligono {

    /**
     * Construtor que cria um retângulo a partir de uma lista de pontos.
     * Verifica a validade do retângulo, garantindo que haja exatamente 4 pontos
     * e que cada ângulo interno seja de 90 graus.
     * @param pontos A lista de pontos que definem o retângulo.
     */
    public Retangulo(List<Ponto<? extends Number>> pontos) {
        super(pontos);
        if (pontos.size() != 4) {
            throw new IllegalArgumentException("Retangulo:vi");
        }
        for (int i = 0; i < getPontos().size(); i++) {
            Ponto<? extends Number> a = getPontos().get(i);
            Ponto<? extends Number> b = getPontos().get((i + 1) % getPontos().size());
            Ponto<? extends Number> c = getPontos().get((i + 2) % getPontos().size());
            if ((int) calcularAngulo(a, b, c) != 90) {
                throw new IllegalArgumentException("Retângulo:vi");
            }
        }
    }

    public Retangulo(double minX, double minY, double maxX, double maxY) {
        super(minX,minY,maxX,maxY);
    }

    /**
     * Construtor que cria um retângulo a partir de uma string com os pontos.
     * @param input String contendo os pontos do retângulo.
     */
    public Retangulo(String input) {
        this(toInt(input));
    }

    /**
     * Converte a string fornecida em uma lista de pontos.
     * A string deve conter um número par de coordenadas, representando 4 pontos.
     * @param input String com os pontos do polígono.
     * @return Lista de pontos que definem o retângulo.
     */
    private static List<Ponto<? extends Number>> toInt(String input) {
        String[] parts = input.split(" ");
        if ((parts.length - 1) % 2 == 0) {
            System.exit(0);
        }

        List<Ponto<? extends Number>> pontos = new ArrayList<>();
        for (int i = 0; i < parts.length; i += 2) {
            if (parts[i].contains(".")) {
                pontos.add(new Ponto<>(Double.parseDouble(parts[i]), Double.parseDouble(parts[i + 1])));
            } else {
                pontos.add(new Ponto<>(Integer.parseInt(parts[i]), Integer.parseInt(parts[i + 1])));
            }
        }

        return pontos;
    }

    /**
     * Calcula o ângulo interno entre três pontos consecutivos.
     * @param a Primeiro ponto do retângulo.
     * @param b Segundo ponto do retângulo (vértice).
     * @param c Terceiro ponto do retângulo.
     * @return O ângulo em graus.
     */
    private double calcularAngulo(Ponto<? extends Number> a, Ponto<? extends Number> b, Ponto<? extends Number> c) {
        double lado1x = b.getX().doubleValue() - a.getX().doubleValue();
        double lado1y = b.getY().doubleValue() - a.getY().doubleValue();
        double lado2x = c.getX().doubleValue() - b.getX().doubleValue();
        double lado2y = c.getY().doubleValue() - b.getY().doubleValue();

        double produtoEscalar = (lado1x * lado2x) + (lado1y * lado2y);

        double normaLado1 = Math.sqrt((lado1x * lado1x) + (lado1y * lado1y));
        double normaLado2 = Math.sqrt((lado2x * lado2x) + (lado2y * lado2y));

        double cosenoAngulo = produtoEscalar / (normaLado1 * normaLado2);

        double angulo = Math.acos(cosenoAngulo) * (180 / Math.PI);

        return angulo;
    }

    /**
     * Verifica se um ponto está contido dentro do retângulo.
     * O ponto é considerado contido se suas coordenadas estiverem dentro
     * dos limites do retângulo, baseando-se nos eixos x e y.
     * @param ponto O ponto a ser verificado.
     * @return true se o ponto estiver contido no retângulo, false caso contrário.
     */
    @Override
    public boolean contemPonto(Ponto<? extends Number> ponto) {
        return ponto.getX().doubleValue() >= this.minX && ponto.getX().doubleValue() <= this.maxX
            && ponto.getY().doubleValue() >= this.minY && ponto.getY().doubleValue() <= this.maxY;
    }
}
