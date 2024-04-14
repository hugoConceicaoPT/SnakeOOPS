package GameLayer.SnakeLayer;
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
                throw new IllegalArgumentException("Quadrado:vi");
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
                throw new IllegalArgumentException("Quadrado:vi");
            }
        }
    } 

    /** Verifica se um retângulo está contido noutro
     * @param that o outro retângulo
     * @return true se os retângulos se intersetarem, false se não
     */
    public boolean contido (Quadrado that)
    {

        int thisMinX = Integer.MAX_VALUE;
        int thisMinY = Integer.MAX_VALUE;
        int thisMaxX = Integer.MIN_VALUE;
        int thisMaxY = Integer.MIN_VALUE;
        int thatMinX = Integer.MAX_VALUE;
        int thatMinY = Integer.MAX_VALUE;
        int thatMaxX = Integer.MIN_VALUE;
        int thatMaxY = Integer.MIN_VALUE;

        for (Ponto ponto : this.pontos) {
            thisMinX = Math.min(thisMinX, ponto.getX());
            thisMaxX = Math.max(thisMaxX, ponto.getX());
            thisMinY = Math.min(thisMinY, ponto.getY());
            thisMaxY = Math.max(thisMaxY, ponto.getY());
        }

        for (Ponto ponto : that.pontos) {
            thatMinX = Math.min(thatMinX, ponto.getX());
            thatMaxX = Math.max(thatMaxX, ponto.getX());
            thatMinY = Math.min(thatMinY, ponto.getY());
            thatMaxY = Math.max(thatMaxY, ponto.getY());
    }

        boolean overlapX = thisMaxX > thatMinX && thisMinX < thatMaxX;
        boolean overlapY = thisMaxY > thatMinY && thisMinY < thatMaxY;
    
        return overlapX && overlapY;
    }
}
