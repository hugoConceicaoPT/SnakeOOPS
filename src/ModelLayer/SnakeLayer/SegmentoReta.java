package ModelLayer.SnakeLayer;

import java.util.Objects;

/**
 * Classe que representa um segmento de reta no plano cartesiano.
 * Responsabilidade: Representar um segmento de reta e verificar se os pontos formam um segmento válido.
 * @version 1.0 10/05/2024
 * @autor Hugo Conceição, João Ventura, Eduarda Pereira
 * @inv Os pontos não podem ser iguais para formar um segmento de reta válido.
 */
public class SegmentoReta implements Cloneable {
    private Ponto<? extends Number> a;
    private Ponto<? extends Number> b;
    private double minX, minY, maxX, maxY;

    /**
     * Construtor que cria um segmento de reta com dois pontos fornecidos.
     * @param a O ponto inicial do segmento de reta.
     * @param b O ponto final do segmento de reta.
     */
    public SegmentoReta(Ponto<? extends Number> a, Ponto<? extends Number> b) {
        check(a, b);
        this.a = a;
        this.b = b;
        setMaxCoordinates();
    }

    /**
     * Verifica se dois pontos são iguais, lançando uma exceção caso sejam.
     * @param ponto1 O primeiro ponto do segmento de reta.
     * @param ponto2 O segundo ponto do segmento de reta.
     */
    private void check(Ponto<? extends Number> ponto1, Ponto<? extends Number> ponto2) {
        if (ponto1.getX().equals(ponto2.getX()) && ponto1.getY().equals(ponto2.getY())) {
            throw new IllegalArgumentException("Segmento:vi");
        }
    }

    /**
     * Calcula o comprimento do segmento de reta.
     * @return O comprimento do segmento de reta.
     */
    public double length() {
        return this.a.dist(this.b);
    }

    /**
     * Calcula o produto vetorial entre três pontos fornecidos.
     * O produto vetorial indica a orientação dos pontos no plano.
     * @param a O primeiro ponto no plano cartesiano.
     * @param b O segundo ponto no plano cartesiano.
     * @param c O terceiro ponto no plano cartesiano.
     * @return O produto vetorial entre os três pontos.
     */
    private double produtoVetorial(Ponto<? extends Number> a, Ponto<? extends Number> b, Ponto<? extends Number> c) {
        return ((b.getX().doubleValue() - a.getX().doubleValue()) * (c.getY().doubleValue() - a.getY().doubleValue())) - ((b.getY().doubleValue() - a.getY().doubleValue()) * (c.getX().doubleValue() - a.getX().doubleValue()));
    }

    /**
     * Verifica se dois segmentos de reta se cruzam no plano cartesiano.
     * @param segmentoDeReta Outro segmento de reta a ser comparado.
     * @return true se os segmentos se cruzam, false caso contrário.
     */
    public boolean seCruzam(SegmentoReta segmentoDeReta) {
        double abac = produtoVetorial(this.b, this.a, segmentoDeReta.a);
        double abad = produtoVetorial(this.b, this.a, segmentoDeReta.b);
        double cdca = produtoVetorial(segmentoDeReta.b, segmentoDeReta.a, this.a);
        double cdcb = produtoVetorial(segmentoDeReta.b, segmentoDeReta.a, this.b);
        return abac * abad < 0 && cdca * cdcb < 0;
    }

    /**
     * Verifica se um ponto está contido dentro do segmento de reta.
     * @param ponto O ponto a ser verificado.
     * @return true se o ponto estiver contido no segmento, false caso contrário.
     */
    public boolean contemPonto(Ponto<? extends Number> ponto) {
        if (produtoVetorial(a, b, ponto) != 0) {
            return false;
        }

        boolean dentroDosLimitesX = Math.min(a.getX().doubleValue(), b.getX().doubleValue()) <= ponto.getX().doubleValue() && ponto.getX().doubleValue() <= Math.max(a.getX().doubleValue(), b.getX().doubleValue());
        boolean dentroDosLimitesY = Math.min(a.getY().doubleValue(), b.getY().doubleValue()) <= ponto.getY().doubleValue() && ponto.getY().doubleValue() <= Math.max(a.getY().doubleValue(), b.getY().doubleValue());
        return dentroDosLimitesX && dentroDosLimitesY;
    }

    /**
     * Calcula as coordenadas mínimas e máximas do segmento de reta.
     */
    public void setMaxCoordinates() {
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;

        minX = Math.min(minX, a.getX().doubleValue());
        maxX = Math.max(maxX, a.getX().doubleValue());
        minY = Math.min(minY, a.getY().doubleValue());
        maxY = Math.max(maxY, a.getY().doubleValue());

        minX = Math.min(minX, b.getX().doubleValue());
        maxX = Math.max(maxX, b.getX().doubleValue());
        minY = Math.min(minY, b.getY().doubleValue());
        maxY = Math.max(maxY, b.getY().doubleValue());

        setMaxX(maxX);
        setMaxY(maxY);
        setMinX(minX);
        setMinY(minY);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        SegmentoReta that = (SegmentoReta) obj;
        return (this.a.equals(that.a) && this.b.equals(that.b)) || (this.a.equals(that.b) && this.b.equals(that.a));
    }

    /**
     * Retorna o código hash do segmento de reta, baseado nos pontos.
     * @return O código hash do segmento de reta.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.a, this.b);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Obtém o ponto a do segmento de reta.
     * @return O ponto a do segmento de reta.
     */
    public Ponto<? extends Number> getA() {
        return a;
    }

    /**
     * Obtém o ponto b do segmento de reta.
     * @return O ponto b do segmento de reta.
     */
    public Ponto<? extends Number> getB() {
        return b;
    }

    /**
     * Define o ponto a do segmento de reta.
     * @param a O novo valor para o ponto a.
     */
    public void setA(Ponto<? extends Number> a) {
        this.a = a;
    }

    /**
     * Define o ponto b do segmento de reta.
     * @param b O novo valor para o ponto b.
     */
    public void setB(Ponto<? extends Number> b) {
        this.b = b;
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
