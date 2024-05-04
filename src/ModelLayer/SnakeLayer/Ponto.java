package ModelLayer.SnakeLayer;
import java.util.Objects;

/**	Classe que representa um ponto no plano cartesiano
    Responsabilidade: Armazenar as coordenadas de um ponto
    @version 1.0 26/03/2024
    @author Hugo Conceição
    @inv  As coordenadas devem estar localizadas no primeiro quadrante do plano cartesiano
*/
public class Ponto implements Cloneable {
    private double x;
    private double y;
    
    public Ponto() {
        this.x = 0.0;
        this.y = 0.0;
    }

    /** Construtor para criar um ponto com as coordenadas especificadas
     * @param xDouble A coordenada x do ponto
     * @param yDouble A coordenada y do ponto
     */
    public Ponto(double x, double y) {
        check(x, y);
        setX(x);
        setY(y);
    }

    /** Verifica se a diferença entre as duas coordenadas é inferior a 10^-9, caso seja 
     * considera as duas coordenadas iguais
     * @param x
     * @param y
     */
    private void check(double x, double y)
    {
        if(Math.abs(x-y) < Math.pow(10, -9))
            x = y;
    }

    /** Calcula a distância entre 2 pontos do plano cartesiano
     * @param p Ponto no plano cartesiano
     * @return distância entre os 2 pontos
     */
    public double dist (Ponto p) {
        double dx = x-p.x;
        double dy = y-p.y;
        return Math.sqrt(dx*dx+dy*dy);
    }
    

    /** Define a coordenada x do ponto
     * @param xDouble O novo valor para a coordenada x
     */
    public void setX(double x) {
        this.x = x;
    }

    /** Define a coordenada yDouble do ponto
     * @param yDouble O novo valor para a coordenada yDouble
     */
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Ponto that = (Ponto) obj;

        return this.x == that.x && this.y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x,this.y);
    }

    /** Aplica-se um movimento de rotação ao ponto a partir do ângulo de rotação e um ponto de rotação 
     * @param angle o ângulo de rotação 
     * @param pontoPivo ponto de rotação 
     * @return um novo ponto com o movimento de rotação já aplicado
     * @see http://www.java2s.com/example/java-utility-method/polygon-rotate/rotatepolygon-polygon-pg-double-rotangle-point-centroid-polygon-original-ee480.html
     */
    public void rotate(int angle, Ponto centroide) {
        double angleRadians = Math.toRadians(angle);
        double x = Math.round(centroide.getX() + ((this.x - centroide.getX()) * Math.cos(angleRadians) - (this.y - centroide.getY()) * Math.sin(angleRadians)));
        double y = Math.round(centroide.getY() + ((this.x - centroide.getX()) * Math.sin(angleRadians) + (this.y - centroide.getY()) * Math.cos(angleRadians)));
        setX(x);
        setY(y);
    }

    /** Aplica-se um movimento de translação ao ponto a partir de um deslocamento dx e dy
     * @param dx
     * @param dy
     * @return um novo ponto com o movimento de translação já aplicado
     */
    public void translate(int dx, int dy) {
        double x = this.x + dx;
        double y = this.y + dy;
        setX(x);
        setY(y);
    }

    /** Aplica-se um movimento de translação ao ponto a partir das coordenadas x e y do novo centróide
     * @param centroX coordenada x do novo centróide
     * @param centroY coordenada y do novo centróide
     * @return um novo ponto com o movimento de translação já aplicado
     */
    public void translateCentroide(int centroX, int centroY, Ponto centroide) {
        double dx = centroX - centroide.getX();
        double dy = centroY - centroide.getY();
        double x = Math.round(this.x + dx);
        double y = Math.round(this.y + dy);
        setX(x);
        setY(y);
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    /** Obtém a coordenada x do ponto
     * @return A coordenada x do ponto
     */
    public double getX() {
        return x;
    }

    /** Obtém a coordenada y do ponto
     * @return A coordenada y do ponto
     */
    public double getY() {
        return y;
    }
}
