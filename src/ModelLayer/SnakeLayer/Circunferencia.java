package ModelLayer.SnakeLayer;

import java.util.List;

public class Circunferencia {
    private Ponto centro;
    private double raio;

    /** Construtor para criar uma circunferência
     * @param centro centro da circunferência
     * @param raio raio da circunferência
     */
    public Circunferencia (Ponto centro, double raio) {
        this.centro = centro;
        this.raio = raio;
        if(raio <= 0) {
            throw new IllegalArgumentException("Não é uma circunferência");
        }
    }
    
    public void translate(int dx, int dy) {
        this.centro.translate(dx, dy);
    }
    
    
    public void translateCentroide(int centroX, int centroY) {
        this.centro.translateCentroide(centroX, centroY, this.centro);
    }

    private boolean pontoDentroCirculo(Ponto centro, double raio, Ponto ponto) {
        double distancia = centro.dist(ponto);
        return distancia <= raio;
    }

    public boolean interseta(Poligono that) {
        List<Ponto> verticesPoligono = that.getPontos();

        for (int i = 0; i < verticesPoligono.size(); i++) {
            Ponto ponto1 = verticesPoligono.get(i);
            Ponto ponto2 = verticesPoligono.get((i + 1) % verticesPoligono.size());

            if (pontoDentroCirculo(this.centro, this.raio, ponto1) ||
                    pontoDentroCirculo(this.centro, this.raio, ponto2)) {
                return true;
            }

            double d = ponto1.dist(ponto2);
            double u = ((this.centro.getX() - ponto1.getX()) * (ponto2.getX() - ponto1.getX()) + (this.centro.getY() - ponto1.getY()) * (ponto2.getY() - ponto1.getY())) / (d * d);
            double xInterseccao = ponto1.getX() + u * (ponto2.getX() - ponto1.getX());
            double yInterseccao = ponto1.getY() + u * (ponto2.getY() - ponto1.getY());
            Ponto interseccaoPonto = new Ponto(xInterseccao,yInterseccao);

            if (pontoDentroCirculo(this.centro, this.raio, interseccaoPonto)) {
                return true;
            }
        }
        return false;
    }

    /** Verifica se uma circunferência está contido noutra
     * @param that a outra circunferência 
     * @return true se a circunferencia estiver contida noutra, false se não
     */
    public boolean contidaNaCircunferencia(Circunferencia that) { 

        double distanciaCentros = this.centro.dist(that.centro);
        boolean contida = distanciaCentros + this.raio <= that.raio;

        return contida;
    }


    public boolean contidaNoPoligono(Poligono that) { 

        if(!that.contemPonto(this.centro))
            return false;

        for (Ponto ponto : that.getPontos()) {
            double distancia = this.centro.dist(ponto);
            if (distancia < this.raio) {
                return false; 
            }
        }

        return true;
    }


    @Override
    public String toString() {
        return "Círcunferencia de centro: " + centro.toString() + "e raio: " + raio;
    }
}