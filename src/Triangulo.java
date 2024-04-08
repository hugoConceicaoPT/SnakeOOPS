import java.util.ArrayList;
import java.util.List;

/**	Classe que representa um Triângulo
    Responsabilidade: Representar um triângulo e verificar se os pontos formam um triângulo válido
    @version 1.0 26/03/2024
    @author Hugo Conceição
    @inv O triângulo é válido se os pontos recebidos forem 3
*/
public class Triangulo extends Poligono {


    /** Construtor para criar um triângulo a partir de uma lista de pontos
     * @param pontos a lista de pontos
     */
    public Triangulo(List<Ponto> pontos) {
        super(pontos);
        if(pontos.size() != 3) {
            System.out.println("Triangulo:vi");
            System.exit(0);
        }
    }

    /** Construtor para criar um triângulo a partir de um input
     * @param input String com os pontos do triângulo
     */
    public Triangulo(String input) {
        super(toInt(input));
        if(pontos.size() != 3) {
            System.out.println("Triangulo:vi");
            System.exit(0);
        }
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

    @Override
    public Triangulo rotate(int angle, Ponto pontoPivo) {
        Poligono poligonoRodado = super.rotate(angle, pontoPivo);
        return new Triangulo(poligonoRodado.getPontos());
    }

    @Override
    public Triangulo rotateAngle(int angle) {
        Poligono poligonoRodado = super.rotateAngle(angle);
        return new Triangulo(poligonoRodado.getPontos());
    }

    @Override
    public Triangulo translate(int dx, int dy) {
        Poligono poligonoTranslado = super.translate(dx, dy);
        return new Triangulo(poligonoTranslado.getPontos());
    }

    @Override
    public Triangulo translateCentroide(int centroX, int centroY) {
        Poligono poligonoTranslado = super.translateCentroide(centroX, centroY);
        return new Triangulo(poligonoTranslado.getPontos());
    }

    @Override
    public String toString() {
        return "Triangulo: " + pontos.toString();
    }
}
