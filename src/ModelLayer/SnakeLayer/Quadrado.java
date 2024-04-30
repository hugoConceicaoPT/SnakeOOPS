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

    public void getDirectionFromPreviousSquare(Quadrado previousSquare) throws CloneNotSupportedException {
        for(int i = 0; i < previousSquare.getPontos().size(); i++) {
            for(int j = 0; j < this.getPontos().size(); j++) {
                if(previousSquare.getPontos().get(i).getX() > this.getPontos().get(j).getX() && previousSquare.getPontos().get(i).getY() == this.getPontos().get(j).getY()) {
                    this.setDirection(Direction.RIGHT);
                }
                else if(previousSquare.getPontos().get(i).getX() < this.getPontos().get(j).getX() && previousSquare.getPontos().get(i).getY() == this.getPontos().get(j).getY()) {
                    this.setDirection(Direction.LEFT);
                }
                else if(previousSquare.getPontos().get(i).getY() > this.getPontos().get(j).getY() && previousSquare.getPontos().get(i).getX() == this.getPontos().get(j).getX()) {
                    this.setDirection(Direction.UP);
                }
                else if(previousSquare.getPontos().get(i).getY() < this.getPontos().get(j).getY() && previousSquare.getPontos().get(i).getX() == this.getPontos().get(j).getX())
                    this.setDirection(Direction.DOWN);
            }   
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    } 
}
