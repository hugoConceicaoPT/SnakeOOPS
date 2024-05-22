package ModelLayer.SnakeLayer;
import java.util.List;

/** Classe que representa um quadrado 
    Responsabilidade: Representar um quadrado e verificar se os pontos formam um quadrado válido
    @version 1.0 22/05/2024
    @author Hugo Conceição João Ventura Eduarda Pereira
    @inv O quadrado é válido se os lados forem todos iguais 
*/
public class Quadrado extends Retangulo{

    /** Construtor para criar um quadrado a partir de uma lista de pontos
     * @param pontos a lista de pontos
     */
    public Quadrado (List<Ponto<? extends Number>> pontos) {
        super(pontos);
        int lado = (int) pontos.get(0).dist(pontos.get(1));
        for(int i = 1; i < 4; i++)
        {
            double lengthAresta = pontos.get(i).dist(pontos.get((i+1) % 4));
            if(lado != lengthAresta)
            {
                throw new IllegalArgumentException("Quadrado:vi");
            }
        }
    } 

    /** Construtor para criar um quadrado a partir de um input
     * @param input String com os pontos do quadrado
     */
    public Quadrado (String input) {
        super(input);
        int lado = (int) pontos.get(0).dist(pontos.get(1));
        for(int i = 1; i < 4; i++)
        {
            double lengthAresta = pontos.get(i).dist(pontos.get((i+1) % 4));
            if(lado != lengthAresta)
            {
                throw new IllegalArgumentException("Quadrado:vi");
            }
        }
    }
}
