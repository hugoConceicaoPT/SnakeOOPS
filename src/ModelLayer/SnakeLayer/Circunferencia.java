package ModelLayer.SnakeLayer;

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

    /**
     * 
     * @param that
     * @return
     * @see https://www.jeffreythompson.org/collision-detection/circle-rect.php
     */
    public boolean interseta(Poligono that) {
        double testX = that.getCentroide().getX();
        double testY = that.getCentroide().getY();
        double width = that.getMaxX() - that.getMinX();
        double height = that.getMaxY() - that.getMinY();
        if(this.centro.getX() < that.getCentroide().getX()) testX = that.getCentroide().getX();
        else if(this.centro.getX() > that.getCentroide().getX() + width) testX = that.getCentroide().getX() + width;
        if(this.centro.getY() < that.getCentroide().getY()) testY = that.getCentroide().getY();
        else if(this.centro.getY() > that.getCentroide().getY() + height) testY = that.getCentroide().getY() + height;

        double distX = this.centro.getX() - testX;
        double distY = this.centro.getY() - testY;
        double distance = Math.sqrt((distX*distX)+(distY*distY));
        
        if(distance <= this.raio) return true;
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
            if (distancia < this.raio) return false;
        }

        return true;
    }


    @Override
    public String toString() {
        return "Circunferência de centro: " + centro.toString() + "e raio: " + raio;
    }

    public Ponto getCentro() {
        return centro;
    }

    public void setCentro(Ponto centro) {
        this.centro = centro;
    }

    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }
}