package ModelLayer.SnakeLayer;
import java.util.List;

/** Classe que representa um quadrado 
    Responsabilidade: Representar um quadrado e verificar se os pontos formam um quadrado válido
    @version 1.0 26/03/2024
    @author Hugo Conceição
    @inv O quadrado é válido se os lados forem todos iguais 
*/
public class Quadrado extends Retangulo{

    private Direction direction;
    /** Construtor para criar um quadrado a partir de uma lista de pontos
     * @param pontos a lista de pontos
     */
    public Quadrado (List<Ponto> pontos) {
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

    public void getDirectionFromPreviousSquare(Quadrado previousSquare) {
        if(previousSquare.getCentroide().getX() > this.getCentroide().getX() && previousSquare.getCentroide().getY() == this.getCentroide().getY()) {
            this.setDirection(Direction.RIGHT);
            return;
        }
        else if(previousSquare.getCentroide().getX() < this.getCentroide().getX() && previousSquare.getCentroide().getY() == this.getCentroide().getY()) {
            this.setDirection(Direction.LEFT);
            return;
        }
        else if(previousSquare.getCentroide().getY() > this.getCentroide().getY() && previousSquare.getCentroide().getX() == this.getCentroide().getX()) {
            this.setDirection(Direction.UP);
            return;
        }
        else 
            this.setDirection(Direction.DOWN);
            return;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    } 
}
