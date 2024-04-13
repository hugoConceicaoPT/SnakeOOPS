package GameLayer.SnakeLayer;

import java.util.List;

public class Circunferencia implements IFiguraGeometrica {
    private Ponto centro;
    private int raio;

    public Circunferencia (Ponto centro, int raio) {
        this.centro = centro;
        this.raio = raio;
        if(raio <= 0) {
            throw new IllegalArgumentException("Não é uma circunferência");
        }
    }
    
    @Override
    public void translate(int dx, int dy) {
        this.centro.translate(dx, dy);
    }
    
    @Override
    public void translateCentroide(int centroX, int centroY) {
        this.centro.translateCentroide(centroX, centroY, this.centro);
    }

    private double distanciaEntrePontos(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    private boolean pontoDentroCirculo(double xCirculo, double yCirculo, double raio, double xPonto, double yPonto) {
        double distancia = distanciaEntrePontos(xCirculo, yCirculo, xPonto, yPonto);
        return distancia <= raio;
    }
    @Override
    public boolean interseta(Poligono poligono) {
        List<Ponto> verticesPoligono = poligono.getPontos();

        for (int i = 0; i < verticesPoligono.size(); i++) {
            Ponto ponto1 = verticesPoligono.get(i);
            Ponto ponto2 = verticesPoligono.get((i + 1) % verticesPoligono.size());

            if (pontoDentroCirculo(this.centro.getX(), this.centro.getY(), this.raio, ponto1.getX(), ponto1.getY()) ||
                    pontoDentroCirculo(this.centro.getX(), this.centro.getY(), this.raio, ponto2.getX(), ponto2.getY())) {
                return true;
            }

            double d = distanciaEntrePontos(ponto1.getX(), ponto1.getY(), ponto2.getX(), ponto2.getY());
            double u = ((this.centro.getX() - ponto1.getX()) * (ponto2.getX() - ponto1.getX()) + (this.centro.getY() - ponto1.getY()) * (ponto2.getY() - ponto1.getY())) / (d * d);
            double xInterseccao = ponto1.getX() + u * (ponto2.getX() - ponto1.getX());
            double yInterseccao = ponto1.getY() + u * (ponto2.getY() - ponto1.getY());

            if (pontoDentroCirculo(this.centro.getX(), this.centro.getY(), this.raio, xInterseccao, yInterseccao)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Círcunferencia de centro: " + centro.toString() + "e raio: " + raio;
    }
}