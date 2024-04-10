package SnakeLayer;

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

    @Override
    public String toString() {
        return "Círcunferencia de centro: " + centro.toString() + "e raio: " + raio;
    }
}