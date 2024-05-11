package ModelLayer.SnakeLayer;

/**
 * Classe que representa uma circunferência no plano cartesiano.
 * Responsabilidade: Armazenar o centro e o raio de uma circunferência,
 * e fornecer métodos para translação, interseção e verificação de contenção.
 * @version 1.0 10/05/2024
 * @autor Hugo Conceição, João Ventura, Eduarda Pereira
 * @inv O raio deve ser positivo para representar uma circunferência válida.
 */
public class Circunferencia {
    // Coordenadas do centro da circunferência
    private Ponto<? extends Number> centro;
    // Raio da circunferência, que deve ser positivo
    private double raio;

    /**
     * Construtor que inicializa a circunferência com um centro e um raio fornecidos.
     * Lança uma exceção se o raio for negativo ou zero.
     * @param centro O ponto representando o centro da circunferência.
     * @param raio O raio da circunferência, deve ser positivo.
     */
    public Circunferencia(Ponto<? extends Number> centro, double raio) {
        this.centro = centro;
        this.raio = raio;
        if (raio <= 0) {
            throw new IllegalArgumentException("Não é uma circunferência");
        }
    }

    /**
     * Move a circunferência pelo plano através de um deslocamento especificado.
     * @param dx Deslocamento ao longo do eixo x.
     * @param dy Deslocamento ao longo do eixo y.
     */
    public void translate(int dx, int dy) {
        this.centro.translate(dx, dy);
    }

    /**
     * Move a circunferência para um novo centróide, deslocando-a pelo valor
     * necessário para que seu centro coincida com as novas coordenadas do centróide.
     * @param centroX Coordenada x do novo centróide.
     * @param centroY Coordenada y do novo centróide.
     */
    public void translateCentroide(int centroX, int centroY) {
        this.centro.translateCentroide(centroX, centroY, this.centro);
    }

    /**
     * Verifica se esta circunferência intersecta um polígono fornecido.
     * O método usa a aproximação de colisão entre círculo e retângulo.
     * @param that O polígono a ser verificado.
     * @return true se houver interseção entre a circunferência e o polígono, false caso contrário.
     * @see https://www.jeffreythompson.org/collision-detection/circle-rect.php
     */
    public boolean interseta(Poligono that) {
        double testX = that.getCentroide().getX().doubleValue();
        double testY = that.getCentroide().getY().doubleValue();
        double width = that.getMaxX() - that.getMinX();
        double height = that.getMaxY() - that.getMinY();

        // Ajuste das coordenadas de teste baseando-se na posição do centro da circunferência
        if(this.centro.getX().doubleValue() < that.getCentroide().getX().doubleValue()) testX = that.getCentroide().getX().doubleValue();
        else if(this.centro.getX().doubleValue() > that.getCentroide().getX().doubleValue() + width) testX = that.getCentroide().getX().doubleValue() + width;
        if(this.centro.getY().doubleValue() < that.getCentroide().getY().doubleValue()) testY = that.getCentroide().getY().doubleValue();
        else if(this.centro.getY().doubleValue() > that.getCentroide().getY().doubleValue() + height) testY = that.getCentroide().getY().doubleValue() + height;

        double distX = this.centro.getX().doubleValue() - testX;
        double distY = this.centro.getY().doubleValue() - testY;
        double distance = Math.sqrt((distX * distX) + (distY * distY));

        // Retorna se a distância entre o ponto ajustado e o centro da circunferência é menor ou igual ao raio
        return distance <= this.raio;
    }

    /**
     * Verifica se esta circunferência está contida dentro de outra circunferência.
     * @param that A circunferência a ser verificada.
     * @return true se esta circunferência estiver contida na outra, false caso contrário.
     */
    public boolean contidaNaCircunferencia(Circunferencia that) {
        double distanciaCentros = this.centro.dist(that.centro);
        return distanciaCentros + this.raio <= that.raio;
    }

    /**
     * Verifica se esta circunferência está contida dentro de um polígono fornecido.
     * A circunferência é considerada contida se o centro está dentro do polígono
     * e se todos os pontos na borda da circunferência também estiverem dentro do polígono.
     * @param that O polígono a ser verificado.
     * @return true se a circunferência estiver contida no polígono, false caso contrário.
     */
    public boolean contidaNoPoligono(Poligono that) {
        if (!that.contemPonto(this.centro)) {
            return false;
        }

        boolean dentroDosLimites = this.centro.getX().doubleValue() - this.raio >= that.getMinX()
            && this.centro.getX().doubleValue() + this.raio <= that.getMaxX()
            && this.centro.getY().doubleValue() - this.raio >= that.getMinY()
            && this.centro.getY().doubleValue() + this.raio <= that.getMaxY();

        return dentroDosLimites;
    }

    /**
     * Retorna a representação em String da circunferência, incluindo seu centro e raio.
     * @return String representando a circunferência.
     */
    @Override
    public String toString() {
        return "Circunferência de centro: " + centro.toString() + " e raio: " + raio;
    }

    /**
     * Obtém o centro da circunferência.
     * @return O ponto representando o centro.
     */
    public Ponto<? extends Number> getCentro() {
        return centro;
    }

    /**
     * Define um novo ponto como centro da circunferência.
     * @param centro O novo ponto a ser utilizado como centro.
     */
    public void setCentro(Ponto<? extends Number> centro) {
        this.centro = centro;
    }

    /**
     * Obtém o valor do raio da circunferência.
     * @return O valor do raio.
     */
    public double getRaio() {
        return raio;
    }

    /**
     * Define um novo valor para o raio da circunferência.
     * @param raio O novo valor do raio, deve ser positivo.
     */
    public void setRaio(double raio) {
        this.raio = raio;
    }
}
