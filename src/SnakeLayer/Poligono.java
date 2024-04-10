package SnakeLayer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**	Classe que representa um poligono formado por uma lista de pontos
    Responsabilidade: Representar um poligono e verificar se os pontos formam um poligono válido
    @version 1.0 26/03/2024
    @author Hugo Conceição
    @inv  O poligono é válido se não existirem três pontos consecutivos colineares 
    ,se cada par de aresta não se cruzar e se tiver pelo menos 3 pontos
*/
public class Poligono implements IFiguraGeometrica {
    protected final List<Ponto> pontos;
    protected final List<SegmentoReta> aresta;
    protected final Ponto centroide;


    /** Costrutor para criar um polígono com uma lista de pontos
     * @param pontos A lista de pontos que define o polígono
     */
    public Poligono(List<Ponto> pontos) {
        if (pontos.size() < 3)
        {
            throw new IllegalArgumentException("Poligono:vi");
        }
        this.aresta = new ArrayList<>();
        for(int i = 0; i < pontos.size(); i++)
        {
            Reta reta = new Reta(pontos.get(i), pontos.get((i+1) % pontos.size()));
            if(reta.colineares(pontos.get((i+2) % pontos.size())))
            {
                throw new IllegalArgumentException("Poligono:vi");
            }
            aresta.add(new SegmentoReta(pontos.get(i), pontos.get((i+1) % pontos.size())));
        }
        for(int i = 0; i < aresta.size(); i++)
        {
            if(aresta.get(i).seCruzam(aresta.get((i+2) % aresta.size())))
            {
                throw new IllegalArgumentException("Poligono:vi");
            }
        }
        this.pontos = pontos;
        this.centroide = getCentroide();
    }

    /** Construtor para criar um poligono com uma string representado os pontos do polígono
     * @param input A string com os pontos do polígono
     */
    public Poligono(String input) {
        this(toInt(input)); 
    }

    /** Converte a string recebida em uma lista de pontos 
     * @param input String com os pontos do polígono
     * @return lista de pontos
     */
    private static List<Ponto> toInt (String input) {
        String [] parts = input.split(" ");
        if(parts.length-2 % 2 == 0)
            System.exit(0);
        int n = Integer.parseInt(parts[0]);
        List<Ponto> pontos = new ArrayList<>(); 
        int i = 1;
        int count = 0;
        while(count < n)
        {
            pontos.add(new Ponto(Integer.parseInt(parts[i]),Integer.parseInt(parts[i+1])));
            i+=2;
            count++;
        }

        return pontos;
    }
    
    /** Calcula o centróide de um poligono
     * @see https://math.stackexchange.com/questions/90463/how-can-i-calculate-the-centroid-of-polygon
     * @return o centróide 
     */
    public Ponto getCentroide() {
        double centroX = 0;
        double centroY = 0;
        for(Ponto ponto : pontos)
        {
           centroX += ponto.getX();
           centroY += ponto.getY();
        }
        centroX /=  pontos.size();
        centroY /=  pontos.size();
        return new Ponto(centroX,centroY);
    }

    /** Aplica-se um movimento de rotação ao polígono a partir do ângulo de rotaçãos
     * @param angle o ângulo de rotação 
     * @return um novo polígono com o movimento de rotação já aplicado
     * @see http://www.java2s.com/example/java-utility-method/polygon-rotate/rotatepolygon-polygon-pg-double-rotangle-point-centroid-polygon-original-ee480.html
     */
    public Poligono rotateAngle(int angle) {
        List<Ponto> pontosPoligono = new ArrayList<>();
        for (Ponto ponto : pontos) 
            pontosPoligono.add(ponto.rotate(angle,this.centroide));
        return new Poligono(pontosPoligono);
    }

    /** Aplica-se um movimento de rotação ao polígono a partir do ângulo de rotação e um ponto de rotação 
     * @param angle o ângulo de rotação 
     * @param pontoPivo ponto de rotação 
     * @return um novo polígono com o movimento de rotação já aplicado
     * @see http://www.java2s.com/example/java-utility-method/polygon-rotate/rotatepolygon-polygon-pg-double-rotangle-point-centroid-polygon-original-ee480.html
     */
    public Poligono rotate(int angle, Ponto pontoPivo) {
        List<Ponto> pontosPoligono = new ArrayList<>();
        for (Ponto ponto : pontos)
            pontosPoligono.add(ponto.rotate(angle, pontoPivo));
        return new Poligono(pontosPoligono);
    }

    /** Aplica-se um movimento de translação ao polígono a partir de um deslocamento x e um deslocamento y
     * @param dx deslocamento x
     * @param dy deslocamento y
     * @return um novo polígono com o movimento de translação já aplicado
     */
    public Poligono translate(int dx, int dy) {
        List<Ponto> pontosPoligono = new ArrayList<>();
        for (Ponto ponto : pontos) {
            pontosPoligono.add(ponto.translate(dx, dy));
        }  
        return new Poligono(pontosPoligono);
    }

    /** Aplica-se um movimento de translação ao polígono a partir das coordenadas x e y do novo centróide
     * @param centroX coordenada x do novo centróide
     * @param centroY coordenada y do novo centróide
     * @return um novo polígono com o movimento de translação já aplicado
     */
    public Poligono translateCentroide(int centroX, int centroY) {
        List<Ponto> pontosPoligono = new ArrayList<>();
        for (Ponto ponto : pontos) 
            pontosPoligono.add(ponto.translateCentroide(centroX, centroY, centroide));
        return new Poligono(pontosPoligono);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        Poligono that = (Poligono) obj;

        if (this.aresta.size() != that.aresta.size()) {
            return false;
        }

        List<SegmentoReta> thatArestasCopy = new ArrayList<>(that.aresta);

        for (int i = 0; i < this.aresta.size(); i++) {
            for(int j = 0; j < that.aresta.size(); j++) {
                if (this.aresta.get(i).equals(that.aresta.get(j))) {
                    thatArestasCopy.remove(j % thatArestasCopy.size());
                    break;
                }
            }
        }

        return thatArestasCopy.isEmpty();
    }      

    @Override
    public int hashCode() {
        return Objects.hash(this.pontos,this.aresta);
    }

    @Override
    public String toString() {
        return "Poligono de " + pontos.size() + " vertices: " + pontos.toString();
    }

    /** Obtém a lista de pontos do polígono
     * @return A lista de pontos do polígono
     */
    public List<Ponto> getPontos() {
        return pontos;
    }

    /** Obtém a lista de arestas do polígono
     * @return A lista de arestas do polígono
     */
    public List<SegmentoReta> getAresta() {
        return aresta;
    }
}
