package ModelLayer.SnakeLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe que representa um polígono formado por uma lista de pontos.
 * Responsabilidade: Representar um polígono e verificar se os pontos formam um polígono válido.
 * @version 1.0 22/05/2024
 * @author Hugo Conceição João Ventura Eduarda Pereira
 * @inv O polígono é válido se não existirem três pontos consecutivos colineares,
 * se as arestas não se cruzarem entre si, e se tiver pelo menos 3 pontos.
 */
public class Poligono implements Cloneable {
    protected List<Ponto<? extends Number>> pontos;
    protected List<SegmentoReta> aresta;
    protected Ponto<? extends Number> centroide;
    protected double minX, minY, maxX, maxY;

    /**
     * Construtor que cria um polígono a partir de uma lista de pontos.
     * Verifica a validade do polígono, garantindo que não haja arestas que se cruzem.
     * @param pontos Lista de pontos que define o polígono.
     */
    public Poligono(List<Ponto<? extends Number>> pontos) {
        if (pontos.size() < 3) {
            throw new IllegalArgumentException("Poligono:vi");
        }

        this.aresta = new ArrayList<>();
        for (int i = 0; i < pontos.size(); i++) {
            Reta reta = new Reta(pontos.get(i), pontos.get((i + 1) % pontos.size()));
            if (reta.colineares(pontos.get((i + 2) % pontos.size()))) {
                throw new IllegalArgumentException("Poligono:vi");
            }
            aresta.add(new SegmentoReta(pontos.get(i), pontos.get((i + 1) % pontos.size())));
        }

        for (int i = 0; i < aresta.size(); i++) {
            if (aresta.get(i).seCruzam(aresta.get((i + 2) % aresta.size()))) {
                throw new IllegalArgumentException("Poligono:vi");
            }
        }

        this.pontos = pontos;
        setMaxCoordinates();
        this.centroide = getCentroide();
    }

    /**
     * Construtor que cria um polígono das coordenadas máximas.
     * @param minX x mínimo
     * @param minY y mínimo
     * @param maxX x máximo
     * @param maxY y máximo
     */
    public Poligono(double minX,double minY,double maxX,double maxY) {
        this(getPointsFromMax(minX,minY,maxX,maxY));
    }

    /**
     * Obtém uma lista de pontos com as coordenadas máximas
     * @param minX x minimo
     * @param minY y minimo
     * @param maxX x máximo
     * @param maxY y máximo
     * @return a lista de pontos
     */
    private static List<Ponto<? extends Number>> getPointsFromMax(double minX,double minY,double maxX,double maxY) {
        List<Ponto<? extends Number>> pontos = new ArrayList<>();
        pontos.add(new Ponto<Number>(minX,minY));
        pontos.add(new Ponto<Number>(maxX,minY));
        pontos.add(new Ponto<Number>(maxX,maxY));
        pontos.add(new Ponto<Number>(minX,maxY));
        return pontos;
    }
    /**
     * Construtor que cria um polígono a partir de uma string representando seus pontos.
     * @param input A string contendo os pontos do polígono.
     */
    public Poligono(String input) {
        this(toPoint(input));
    }

    /**
     * Converte uma string fornecida em uma lista de pontos.
     * A string deve conter um número de pontos seguido por coordenadas.
     * @param input String com os pontos do polígono.
     * @return Uma lista de pontos.
     */
    private static List<Ponto<? extends Number>> toPoint(String input) {
        String[] parts = input.split(" ");
        if ((parts.length - 2) % 2 == 0) {
            System.exit(0);
        }

        int n = Integer.parseInt(parts[0]);
        List<Ponto<? extends Number>> pontos = new ArrayList<>();
        int i = 1, count = 0;

        // Itera sobre os pares de coordenadas e cria pontos
        while (count < n) {
            if (parts[i].contains(".")) {
                pontos.add(new Ponto<>(Double.parseDouble(parts[i]), Double.parseDouble(parts[i + 1])));
            } else {
                pontos.add(new Ponto<>(Integer.parseInt(parts[i]), Integer.parseInt(parts[i + 1])));
            }
            i += 2;
            count++;
        }

        return pontos;
    }

    /**
     * Atualiza as coordenadas mínimas e máximas do polígono.
     */
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

    /**
     * Atualiza a lista de arestas do polígono, garantindo que sejam válidas.
     */
    private void updateAresta() {
        this.aresta.clear();

        for (int i = 0; i < pontos.size(); i++) {
            Reta reta = new Reta(pontos.get(i), pontos.get((i + 1) % pontos.size()));
            if (reta.colineares(pontos.get((i + 2) % pontos.size()))) {
                throw new IllegalArgumentException("Poligono:vi");
            }
            this.aresta.add(new SegmentoReta(pontos.get(i), pontos.get((i + 1) % pontos.size())));
        }

        for (int i = 0; i < aresta.size(); i++) {
            if (this.aresta.get(i).seCruzam(this.aresta.get((i + 2) % aresta.size()))) {
                throw new IllegalArgumentException("Poligono:vi");
            }
        }
    }

    /**
     * Verifica se este polígono se intersecta com outro polígono.
     * @param that Outro polígono para verificar a interseção.
     * @return true se os polígonos se intersectarem, false caso contrário.
     */
    public boolean interseta(Poligono that) {
        for (SegmentoReta aresta1 : this.aresta) {
            for (SegmentoReta aresta2 : that.aresta) {
                if (aresta1.seCruzam(aresta2)) {
                    return true;
                }
            }
        }

        if (this.pontos.get(0).dist(this.pontos.get(1)) == that.pontos.get(0).dist(that.pontos.get(1))) {
            if (this.centroide.dist(that.centroide) < this.pontos.get(0).dist(this.pontos.get(1))) {
                return true;
            }
        }

        for (Ponto<? extends Number> ponto : that.pontos) {
            if (ponto.getX().doubleValue() > this.minX && ponto.getX().doubleValue() < this.maxX
                && ponto.getY().doubleValue() > this.minY && ponto.getY().doubleValue() < this.maxY) {
                return true;
            }
        }

        return false;
    }

    /**
     * Verifica se este polígono está contido em outro polígono.
     * @param that Outro polígono.
     * @return true se este polígono estiver contido no outro, false caso contrário.
     */
    public boolean contida(Poligono that) {
        boolean overlapX = this.minX >= that.minX && this.maxX <= that.maxX;
        boolean overlapY = this.minY >= that.minY && this.maxY <= that.maxY;

        return overlapX && overlapY;
    }

    /**
     * Verifica se este polígono está contido em uma circunferência.
     * @param that A circunferência.
     * @return true se este polígono estiver contido na circunferência, false caso contrário.
     */
    public boolean contidaNaCircunferencia(Circunferencia that) {
        boolean overlapX = this.minX >= that.getCentro().getX().doubleValue() - that.getRaio()
            && this.maxX <= that.getCentro().getX().doubleValue() + that.getRaio();
        boolean overlapY = this.minY >= that.getCentro().getY().doubleValue() - that.getRaio()
            && this.maxY <= that.getCentro().getY().doubleValue() + that.getRaio();

        return overlapX && overlapY;
    }

    /**
     * Calcula o centróide do polígono.
     * @see <a href="https://math.stackexchange.com/questions/90463/how-can-i-calculate-the-centroid-of-polygon">Calcular o centroide do poligono</a>
     * @return O centróide do polígono.
     */
    public Ponto<Double> getCentroide() {
        double centroX = 0, centroY = 0;

        for (Ponto<? extends Number> ponto : pontos) {
            centroX += ponto.getX().doubleValue();
            centroY += ponto.getY().doubleValue();
        }

        centroX /=  pontos.size();
        centroY /=  pontos.size();
        return new Ponto<Double>(centroX,centroY);
    }

    /**
     * Aplica um movimento de rotação ao polígono, usando o centróide como ponto pivô.
     * @param angle O ângulo de rotação, em graus.
     */
    public void rotateAngle(int angle) {
        for (Ponto<? extends Number> ponto : pontos) {
            ponto.rotate(angle, this.centroide);
        }

        setCentroide(getCentroide());
        updateAresta();
        setMaxCoordinates();
    }

    /**
     * Aplica um movimento de rotação ao polígono, usando um ponto pivô específico.
     * @param angle O ângulo de rotação, em graus.
     * @param pontoPivo O ponto pivô da rotação.
     */
    public void rotate(int angle, Ponto<? extends Number> pontoPivo) {
        for (Ponto<? extends Number> ponto : pontos) {
            ponto.rotate(angle, pontoPivo);
        }

        setCentroide(getCentroide());
        updateAresta();
        setMaxCoordinates();
    }

    /**
     * Move o polígono no plano através de um deslocamento ao longo dos eixos x e y.
     * @param dx Deslocamento ao longo do eixo x.
     * @param dy Deslocamento ao longo do eixo y.
     */
    public void translate(int dx, int dy) {
        for (Ponto<? extends Number> ponto : pontos) {
            ponto.translate(dx, dy);
        }

        setCentroide(getCentroide());
        updateAresta();
        setMaxCoordinates();
    }

    /**
     * Move o polígono para um novo centróide, deslocando todos os pontos conforme necessário.
     * @param centroX A coordenada x do novo centróide.
     * @param centroY A coordenada y do novo centróide.
     */
    public void translateCentroide(double centroX, double centroY) {
        for (Ponto<? extends Number> ponto : pontos) {
            ponto.translateCentroide(centroX, centroY, this.centroide);
        }

        setCentroide(getCentroide());
        updateAresta();
        setMaxCoordinates();
    }

    /**
     * Verifica se um ponto está contido dentro do polígono.
     * @param ponto O ponto a ser verificado.
     * @return true se o ponto estiver contido no polígono, false caso contrário.
     * @see <a href="https://github.com/viniciusfinger/java-ray-casting/blob/main/RayCasting.java">RayCasting</a>
     */
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

            if ((yi > y) != (yj > y) && (x < (xj - xi) * (y - yi) / (yj - yi) + xi)) {
                inside = !inside;
            }
        }

        return inside;
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
            for (int j = 0; j < that.aresta.size(); j++) {
                if (this.aresta.get(i).equals(that.aresta.get(j))) {
                    thatArestasCopy.remove(j % thatArestasCopy.size());
                    break;
                }
            }
        }

        return thatArestasCopy.isEmpty();
    }

    /**
     * Retorna o código hash do polígono, baseado nos pontos e arestas.
     * @return O código hash do polígono.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.pontos, this.aresta);
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

    /**
     * Obtém a lista de pontos do polígono.
     * @return A lista de pontos do polígono.
     */
    public List<Ponto<? extends Number>> getPontos() {
        return pontos;
    }

    /**
     * Obtém a lista de arestas do polígono.
     * @return A lista de arestas do polígono.
     */
    public List<SegmentoReta> getAresta() {
        return aresta;
    }

    /**
     * Obtém os pontos x através de uma lista de pontos
     * @return um array de ints
     */
    public int[] getXPoints() {
        int [] xPoints = new int[this.pontos.size()];
        for (int i = 0; i < this.pontos.size(); i++) {
            xPoints[i] = this.pontos.get(i).getX().intValue();
        }
        return xPoints;
    }

    /**
     * Obtém os pontos y através de uma lista de pontos
     * @return um array de ints
     */
    public int[] getYPoints() {
        int [] yPoints = new int[this.pontos.size()];
        for (int i = 0; i < this.pontos.size(); i++) {
            yPoints[i] = this.pontos.get(i).getY().intValue();
        }
        return yPoints;
    }

    /**
     * Define uma nova lista de pontos para o polígono.
     * @param pontos A nova lista de pontos.
     */
    public void setPontos(List<Ponto<? extends Number>> pontos) {
        this.pontos = pontos;
    }

    /**
     * Define uma nova lista de arestas para o polígono.
     * @param aresta A nova lista de arestas.
     */
    public void setAresta(List<SegmentoReta> aresta) {
        this.aresta = aresta;
    }

    /**
     * Define um novo centróide para o polígono.
     * @param centroide O novo centróide.
     */
    public void setCentroide(Ponto<? extends Number> centroide) {
        this.centroide = centroide;
    }

    /**
     * Obtém a coordenada mínima no eixo x.
     * @return A coordenada mínima no eixo x.
     */
    public double getMinX() {
        return minX;
    }

    /**
     * Define a coordenada mínima no eixo x.
     * @param minX A nova coordenada mínima no eixo x.
     */
    public void setMinX(double minX) {
        this.minX = minX;
    }

    /**
     * Obtém a coordenada mínima no eixo y.
     * @return A coordenada mínima no eixo y.
     */
    public double getMinY() {
        return minY;
    }

    /**
     * Define a coordenada mínima no eixo y.
     * @param minY A nova coordenada mínima no eixo y.
     */
    public void setMinY(double minY) {
        this.minY = minY;
    }

    /**
     * Obtém a coordenada máxima no eixo x.
     * @return A coordenada máxima no eixo x.
     */
    public double getMaxX() {
        return maxX;
    }

    /**
     * Define a coordenada máxima no eixo x.
     * @param maxX A nova coordenada máxima no eixo x.
     */
    public void setMaxX(double maxX) {
        this.maxX = maxX;
    }

    /**
     * Obtém a coordenada máxima no eixo y.
     * @return A coordenada máxima no eixo y.
     */
    public double getMaxY() {
        return maxY;
    }

    /**
     * Define a coordenada máxima no eixo y.
     * @param maxY A nova coordenada máxima no eixo y.
     */
    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }
}
