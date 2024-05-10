package ModelLayer.SnakeLayer;
import java.util.ArrayList;
import java.util.List;

/**	Classe que representa um Triângulo
    Responsabilidade: Representar um triângulo e verificar se os pontos formam um triângulo válido
    @version 1.0 10/05/2024
    @autor Hugo Conceição, João Ventura, Eduarda Pereira
    @inv O triângulo é válido se os pontos recebidos forem 3
*/
public class Triangulo extends Poligono {


    /** Construtor para criar um triângulo a partir de uma lista de pontos
     * @param pontos a lista de pontos
     */
    public Triangulo(List<Ponto<? extends Number>> pontos) {
        super(pontos);
        if(pontos.size() != 3) {
            throw new IllegalArgumentException("Triangulo:vi");
        }
    }

    /** Construtor para criar um triângulo a partir de um input
     * @param input String com os pontos do triângulo
     */
    public Triangulo(String input) {
        super(toInt(input));
        if(pontos.size() != 3) {
            throw new IllegalArgumentException("Triangulo:vi");
        }
    }
    
    /** Converte a string recebida em uma lista de pontos 
     * @param input String com os pontos do polígono
     * @return lista de pontos
     */
    private static List<Ponto<? extends Number>> toInt (String input) {
        String [] parts = input.split(" ");
        if(parts.length-1 % 2 == 0)
            System.exit(0);
        List<Ponto<? extends Number>> pontos = new ArrayList<>(); 
        for(int i = 0; i < parts.length; i+=2) {
            if(parts[i].contains(".")) {
                pontos.add(new Ponto<Double>(Double.parseDouble(parts[i]),Double.parseDouble(parts[i+1])));   
            }
            else
                pontos.add(new Ponto<Integer>(Integer.parseInt(parts[i]),Integer.parseInt(parts[i+1])));
        }
        return pontos;
    }

    @Override
    public boolean contemPonto(Ponto<? extends Number> ponto) {
        double a = area(this.getPontos().get(0), this.getPontos().get(1),this.getPontos().get(2));
        double b = area(ponto, this.getPontos().get(1),this.getPontos().get(2));
        double c = area(this.getPontos().get(0), ponto,this.getPontos().get(2));
        double d = area(this.getPontos().get(0), this.getPontos().get(1),ponto);

        return (a == b + c + d);
    }

    private double area (Ponto<? extends Number> a, Ponto<? extends Number> b, Ponto<? extends Number> c) {
        return Math.abs((a.getX().doubleValue() * (b.getY().doubleValue()-c.getY().doubleValue()) + b.getX().doubleValue() * (c.getY().doubleValue()-a.getY().doubleValue()) + c.getX().doubleValue() * (a.getY().doubleValue()-b.getY().doubleValue()))/2.0);  
    }
}
