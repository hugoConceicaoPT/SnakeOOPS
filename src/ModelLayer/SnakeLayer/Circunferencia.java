package ModelLayer.SnakeLayer;

public class Circunferencia {
    private Ponto<? extends Number> centro;
    private double raio;

    /** Construtor para criar uma circunferência
     * @param centro centro da circunferência
     * @param raio raio da circunferência
     */
    public Circunferencia (Ponto<? extends Number> centro, double raio) {
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
        double testX = that.getCentroide().getX().doubleValue();
        double testY = that.getCentroide().getY().doubleValue();
        double width = that.getMaxX() - that.getMinX();
        double height = that.getMaxY() - that.getMinY();
        if(this.centro.getX().doubleValue() < that.getCentroide().getX().doubleValue()) testX = that.getCentroide().getX().doubleValue();
        else if(this.centro.getX().doubleValue() > that.getCentroide().getX().doubleValue() + width) testX = that.getCentroide().getX().doubleValue() + width;
        if(this.centro.getY().doubleValue() < that.getCentroide().getY().doubleValue()) testY = that.getCentroide().getY().doubleValue();
        else if(this.centro.getY().doubleValue() > that.getCentroide().getY().doubleValue() + height) testY = that.getCentroide().getY().doubleValue() + height;

        double distX = this.centro.getX().doubleValue() - testX;
        double distY = this.centro.getY().doubleValue() - testY;
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

        if(this.centro.getX().doubleValue() - this.raio >= that.minX && this.centro.getX().doubleValue() + this.raio <= that.maxX 
            && this.centro.getY().doubleValue() - this.raio >= that.minY && this.centro.getY().doubleValue() + this.raio <= that.maxY)
            return true;
        return false;
    }


    @Override
    public String toString() {
        return "Circunferência de centro: " + centro.toString() + "e raio: " + raio;
    }

    public Ponto<? extends Number> getCentro() {
        return centro;
    }

    public void setCentro(Ponto<? extends Number> centro) {
        this.centro = centro;
    }

    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }
}