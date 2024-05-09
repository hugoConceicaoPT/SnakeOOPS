package ModelLayer.SnakeLayer;
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
public class Poligono implements Cloneable {
    protected List<Ponto<? extends Number>> pontos;
    protected List<SegmentoReta> aresta;
    protected Ponto<? extends Number> centroide;
    protected double minX;
    protected double minY;
    protected double maxX;
    protected double maxY;


    /** Costrutor para criar um polígono com uma lista de pontos
     * @param pontos A lista de pontos que define o polígono
     */
    public Poligono(List<Ponto<? extends Number>> pontos) {
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
        setMaxCoordinates();
        this.centroide = getCentroide();
    }

    /** Construtor para criar um poligono com uma string representado os pontos do polígono
     * @param input A string com os pontos do polígono
     */
    public Poligono(String input) {
        this(toPoint(input)); 
    }

    /** Converte a string recebida em uma lista de pontos 
     * @param input String com os pontos do polígono
     * @return lista de pontos
     */
    private static List<Ponto<? extends Number>> toPoint (String input) {
        String [] parts = input.split(" ");
        if(parts.length-2 % 2 == 0)
            System.exit(0);
        int n = Integer.parseInt(parts[0]);
        List<Ponto<? extends Number>> pontos = new ArrayList<>(); 
        int i = 1;
        int count = 0;
        while(count < n)
        {
            if(parts[i].contains(".")) {
                pontos.add(new Ponto<Double>(Double.parseDouble(parts[i]),Double.parseDouble(parts[i+1])));
            }
            else {
                pontos.add(new Ponto<Integer>(Integer.parseInt(parts[i]),Integer.parseInt(parts[i+1])));
            }
            i+=2;
            count++;
        }

        return pontos;
    }
    
    private void setMaxCoordinates() {
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;
        for (Ponto<? extends Number> ponto : this.pontos) {
            minX = Math.min(minX, ponto.getX().doubleValue());
            maxX = Math.max(maxX, ponto.getX().doubleValue());
            minY = Math.min(minY, ponto.getY().doubleValue());
            maxY = Math.max(maxY, ponto.getY().doubleValue());
        }
        setMaxX(maxX);
        setMaxY(maxY);
        setMinX(minX);
        setMinY(minY);
    }

    private void updateAresta() {
        this.aresta.clear();

        for(int i = 0; i < pontos.size(); i++)
        {
            Reta reta = new Reta(pontos.get(i), pontos.get((i+1) % pontos.size()));
            if(reta.colineares(pontos.get((i+2) % pontos.size())))
            {
                throw new IllegalArgumentException("Poligono:vi");
            }
            this.aresta.add(new SegmentoReta(pontos.get(i), pontos.get((i+1) % pontos.size())));
        }

        for(int i = 0; i < aresta.size(); i++)
        {
            if(this.aresta.get(i).seCruzam(this.aresta.get((i+2) % aresta.size())))
            {
                throw new IllegalArgumentException("Poligono:vi");
            }
        }

    }

    public boolean interseta (Poligono that)
    {
        for(SegmentoReta aresta1 : this.aresta)
        {
            for(SegmentoReta aresta2 : that.aresta)
            {
                if (aresta1.seCruzam(aresta2))
                    return true;
            }
        }

        if(this.pontos.get(0).dist(this.pontos.get(1)) == that.pontos.get(0).dist(that.pontos.get(1))) {
            if(this.centroide.dist(that.centroide) < this.pontos.get(0).dist(this.pontos.get(1)))
                return true;
        }

        for(Ponto<? extends Number> ponto : that.pontos) {
            if(ponto.getX().doubleValue() > this.minX && ponto.getX().doubleValue() < this.maxX
            && ponto.getY().doubleValue() > this.minY && ponto.getY().doubleValue() < this.maxY)
                return true;
        }
        return false;
    }

    /** Verifica se um retângulo está contido noutro
     * @param that o outro retângulo
     * @return true se os retângulos se intersetarem, false se não
     */
    public boolean contida (Poligono that)
    {

        boolean overlapX = this.minX >= that.minX && this.maxX <= that.maxX;
        boolean overlapY = this.minY >= that.minY && this.maxY <= that.maxY;
    
        return overlapX && overlapY;
    }

    public boolean contidaNaCircunferencia (Circunferencia that)
    {

        boolean overlapX = this.minX >= that.getCentro().getX().doubleValue() - that.getRaio() && this.maxX <= that.getCentro().getX().doubleValue() + that.getRaio();
        boolean overlapY =  this.minY >= that.getCentro().getY().doubleValue() - that.getRaio() && this.maxY <= that.getCentro().getY().doubleValue() + that.getRaio();
    
        return overlapX && overlapY;
    }

    /** Calcula o centróide de um poligono
     * @see https://math.stackexchange.com/questions/90463/how-can-i-calculate-the-centroid-of-polygon
     * @return o centróide 
     */
    public Ponto<Double> getCentroide() {
        double centroX = 0;
        double centroY = 0;
        for(Ponto<? extends Number> ponto : pontos)
        {
           centroX += ponto.getX().doubleValue();
           centroY += ponto.getY().doubleValue();
        }
        centroX /=  pontos.size();
        centroY /=  pontos.size();
        return new Ponto<Double>(centroX,centroY);
    }

    /** Aplica-se um movimento de rotação ao polígono a partir do ângulo de rotaçãos
     * @param angle o ângulo de rotação 
     * @return um novo polígono com o movimento de rotação já aplicado
     * @see http://www.java2s.com/example/java-utility-method/polygon-rotate/rotatepolygon-polygon-pg-double-rotangle-point-centroid-polygon-original-ee480.html
     */
    public void rotateAngle(int angle) {
        for (Ponto<? extends Number> ponto : pontos) 
            ponto.rotate(angle,this.centroide);  
        setCentroide(getCentroide());
        updateAresta();
        setMaxCoordinates(); 
    }

    /** Aplica-se um movimento de rotação ao polígono a partir do ângulo de rotação e um ponto de rotação 
     * @param angle o ângulo de rotação 
     * @param pontoPivo ponto de rotação 
     * @return um novo polígono com o movimento de rotação já aplicado
     * @see http://www.java2s.com/example/java-utility-method/polygon-rotate/rotatepolygon-polygon-pg-double-rotangle-point-centroid-polygon-original-ee480.html
     */
    public void rotate(int angle, Ponto<? extends Number> pontoPivo) {
        for (Ponto<? extends Number> ponto : pontos)
            ponto.rotate(angle, pontoPivo);
        setCentroide(getCentroide());
        updateAresta();
        setMaxCoordinates();
    }

    public void translate(int dx, int dy) {
        for (Ponto<? extends Number> ponto : pontos) 
            ponto.translate(dx, dy);
        setCentroide(getCentroide());
        updateAresta();
        setMaxCoordinates();
    }

    public void translateCentroide(int centroX, int centroY) {
        for (Ponto<? extends Number> ponto : pontos) 
            ponto.translateCentroide(centroX, centroY, this.centroide);
        setCentroide(getCentroide());
        updateAresta();
        setMaxCoordinates();
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

    public boolean contemPonto(Ponto<? extends Number> ponto) {
        double x = ponto.getX().doubleValue();
        double y = ponto.getY().doubleValue();
        int numVertices = pontos.size();
        boolean inside = false;
    
        for (int i = 0, j = numVertices - 1; i < numVertices; j = i++) {
            double xi = pontos.get(i).getX().doubleValue();
            double yi = pontos.get(i).getY().doubleValue();
            double xj = pontos.get(j).getX().doubleValue();
            double yj = pontos.get(j).getY().doubleValue();
    
            // Verifica se o ponto está no segmento de reta ou à esquerda da aresta
            if ((yi > y) != (yj > y) && (x < (xj - xi) * (y - yi) / (yj - yi) + xi)) {
                inside = !inside;
            }
        }
    
        return inside;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.pontos,this.aresta);
    }

    @Override
    public String toString() {
        return pontos.toString();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Poligono novoPoligono = (Poligono) super.clone();
        novoPoligono.pontos = new ArrayList<>();
        for (Ponto<? extends Number> ponto : this.pontos) {
            novoPoligono.pontos.add((Ponto<?>) ponto.clone());
        }

        novoPoligono.aresta = new ArrayList<>();
        for (SegmentoReta aresta : this.aresta) {
            novoPoligono.aresta.add((SegmentoReta) aresta.clone());
        }

        novoPoligono.centroide = (Ponto<?>) this.centroide.clone();

        return novoPoligono;
    }
    /** Obtém a lista de pontos do polígono
     * @return A lista de pontos do polígono
     */
    public List<Ponto<? extends Number>> getPontos() {
        return pontos;
    }

    /** Obtém a lista de arestas do polígono
     * @return A lista de arestas do polígono
     */
    public List<SegmentoReta> getAresta() {
        return aresta;
    }

    public void setPontos(List<Ponto<? extends Number>> pontos) {
        this.pontos = pontos;
    }

    public void setAresta(List<SegmentoReta> aresta) {
        this.aresta = aresta;
    }

    public void setCentroide(Ponto<? extends Number> centroide) {
        this.centroide = centroide;
    }

    public double getMinX() {
        return minX;
    }

    public void setMinX(double minX) {
        this.minX = minX;
    }

    public double getMinY() {
        return minY;
    }

    public void setMinY(double minY) {
        this.minY = minY;
    }

    public double getMaxX() {
        return maxX;
    }

    public void setMaxX(double maxX) {
        this.maxX = maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }
}
