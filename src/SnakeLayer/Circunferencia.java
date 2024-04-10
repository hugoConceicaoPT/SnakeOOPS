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
    
    public Circunferencia translate(int dx, int dy) {
        Ponto novoCentro = this.centro.translate(dx, dy);
        return new Circunferencia(novoCentro,this.raio);
    }

    public Circunferencia translateCentroide(int centroX, int centroY) {
        Ponto novoCentro = this.centro.translateCentroide(centroX, centroY, this.centro);
        return new Circunferencia(novoCentro, this.raio);
    }

    @Override
    public String toString() {
        return "Círcunferencia de centro: " + centro.toString() + "e raio: " + raio;
    }
}