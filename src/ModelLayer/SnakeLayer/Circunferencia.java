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
        if (this.centro.getX() < that.getMinX()) testX = that.getMinX();
        else if (this.centro.getX() > that.getMaxX()) testX = that.getMaxX();

        if(this.centro.getY() < that.getMinY()) testY = that.getMinY();
        else if(this.centro.getY() > that.getMaxY()) testY = that.getMaxY();

        double distX = this.centro.getX() - testX;
        double distY = this.centro.getY() - testY;
        double distance = Math.sqrt((distX*distX)+(distY*distY));
        
        return distance <= this.raio;
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

        if(this.centro.getX() - this.raio >= that.minX && this.centro.getX() + this.raio <= that.maxX 
            && this.centro.getY() - this.raio >= that.minY && this.centro.getY() + this.raio <= that.maxY)
            return true;
        return false;
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