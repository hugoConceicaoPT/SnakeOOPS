import java.util.List;

/** Classe que representa um quadrado 
    Responsabilidade: Representar um quadrado e verificar se os pontos formam um quadrado válido
    @version 1.0 26/03/2024
    @author Hugo Conceição
    @inv O quadrado é válido se os lados forem todos iguais 
*/
public class Quadrado extends Retangulo{

    /** Construtor para criar um quadrado a partir de uma lista de pontos
     * @param pontos a lista de pontos
     */
    public Quadrado (List<Ponto> pontos) {
        super(pontos);
        int lado = pontos.get(0).dist(pontos.get(1));
        for(int i = 1; i < 4; i++)
        {
            double lengthAresta = pontos.get(i).dist(pontos.get((i+1) % 4));
            if(lado != lengthAresta)
            {
                System.out.println("Quadrado:vi");
                System.exit(0);
            }
        }
    } 

    /** Construtor para criar um quadrado a partir de um input
     * @param input String com os pontos do quadrado
     */
    public Quadrado (String input) {
        super(input);
        int lado = pontos.get(0).dist(pontos.get(1));
        for(int i = 1; i < 4; i++)
        {
            double lengthAresta = pontos.get(i).dist(pontos.get((i+1) % 4));
            if(lado != lengthAresta)
            {
                System.out.println("Quadrado:vi");
                System.exit(0);
            }
        }
    } 

    @Override
    public Quadrado rotate(int angle, Ponto pontoPivo) {
        Poligono poligonoRodado = super.rotate(angle, pontoPivo);
        return new Quadrado(poligonoRodado.getPontos());
    }

    @Override
    public Quadrado rotateAngle(int angle) {
        Poligono poligonoRodado = super.rotateAngle(angle);
        return new Quadrado(poligonoRodado.getPontos());
    }

    @Override
    public Quadrado translate(int dx, int dy) {
        Poligono poligonoTranslado = super.translate(dx, dy);
        return new Quadrado(poligonoTranslado.getPontos());
    }

    @Override
    public Quadrado translateCentroide(int centroX, int centroY) {
        Poligono poligonoTranslado = super.translateCentroide(centroX, centroY);
        return new Quadrado(poligonoTranslado.getPontos());
    }

    @Override
    public String toString() {
        return "Quadrado: " + pontos.toString();
    }
}
