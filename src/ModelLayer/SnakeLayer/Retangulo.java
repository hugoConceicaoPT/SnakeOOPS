package ModelLayer.SnakeLayer;
import java.util.ArrayList;
import java.util.List;

/**	Classe que representa um retângulo no plano cartesiano
    Responsabilidade: Representar um retângulo e verificar se os pontos formam um retângulo válido
    @version 1.0 07/03/2024
    @author Hugo Conceição
    @inv O retângulo é válido se todos os ângulos internos forem 90 graus e se houver 4 pontos
*/
public class Retangulo extends Poligono {

    /** Construtor para criar um retângulo a partir de uma lista de pontos
     * @param pontos a lista de pontos
     */
    public Retangulo(List<Ponto> pontos) {
        super(pontos);
        if(pontos.size() != 4) {
            throw new IllegalArgumentException("Retangulo:vi");
        }
        for(int i = 0; i < getPontos().size(); i++) {
            Ponto a = getPontos().get(i);
            Ponto b = getPontos().get((i+1) % getPontos().size());
            Ponto c = getPontos().get((i+2) % getPontos().size());
            if((int) calcularAngulo(a, b,c) != 90) {
                throw new IllegalArgumentException("Retângulo:vi");
            }
        }
    }

    /** Construtor para criar um retângulo a partir de um input
     * @param input String com os pontos do retângulo
     */
    public Retangulo(String input) {
        super(toInt(input));
    }

    /** Converte a string recebida em uma lista de pontos 
     * @param input String com os pontos do polígono
     * @return lista de pontos
     */
    private static List<Ponto> toInt (String input) {
        String [] parts = input.split(" ");
        if(parts.length-1 % 2 == 0)
            System.exit(0);
        List<Ponto> pontos = new ArrayList<>(); 
        for(int i = 0; i < parts.length; i+=2)
            pontos.add(new Ponto(Integer.parseInt(parts[i]),Integer.parseInt(parts[i+1])));
        return pontos;
    }

     /** Calcula o angulo interno do retângulo a partir de três pontos
     * @param a primeiro ponto do retângulo
     * @param b segundo ponto do retângulo
     * @param c terceiro ponto do retângulo
     * @return ângulo em radianos
     */
    private double calcularAngulo(Ponto a, Ponto b, Ponto c) {
        double lado1x = b.getX() - a.getX();
        double lado1y = b.getY() - a.getY();
        double lado2x = c.getX() - b.getX();
        double lado2y = c.getY() - b.getY();
        
        double produtoEscalar = (lado1x * lado2x) + (lado1y * lado2y);

        double normaLado1 = Math.sqrt((lado1x * lado1x) + (lado1y * lado1y));
        double normaLado2 = Math.sqrt((lado2x * lado2x) + (lado2y * lado2y));
        

        double cosenoAngulo = produtoEscalar / (normaLado1 * normaLado2);
        
        double angulo = Math.acos(cosenoAngulo) * (180 / Math.PI);
        
        return angulo;
    }
}