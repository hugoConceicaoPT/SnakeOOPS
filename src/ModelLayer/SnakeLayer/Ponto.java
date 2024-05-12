package ModelLayer.SnakeLayer;
import java.util.Objects;

/**	Classe que representa um ponto no plano cartesiano
    Responsabilidade: Armazenar as coordenadas de um ponto
    @version 1.0 10/05/2024
    @author Hugo Conceição João Ventura Eduarda Pereira
*/
public class Ponto<T extends Number> implements Cloneable {
    private T x;
    private T y;

    /** Construtor para criar um ponto com as coordenadas especificadas
     * @param xDouble A coordenada x do ponto
     * @param yDouble A coordenada y do ponto
     */
    public Ponto(T x, T y) {
        check(x, y);
        setX(x);
        setY(y);
    }

    /** Verifica se a diferença entre as duas coordenadas é inferior a 10^-9, caso seja 
     * considera as duas coordenadas iguais
     * @param x
     * @param y
     */
    private void check(T x, T y)
    {
        if(x instanceof Integer) {
            if(Math.abs(x.intValue()-y.intValue()) < Math.pow(10, -9))
                x = y;
        }
        else {
            if(Math.abs(x.doubleValue()-y.doubleValue()) < Math.pow(10, -9))
            x = y;
        }
    }

    /** Calcula a distância entre 2 pontos do plano cartesiano
     * @param p Ponto no plano cartesiano
     * @return distância entre os 2 pontos
     */
    public double dist (Ponto<? extends Number> p) {
        double dx = this.x.doubleValue()-p.getX().doubleValue();
        double dy = this.y.doubleValue()-p.getY().doubleValue();
        return Math.sqrt(dx*dx+dy*dy);
    }
    

    /** Define a coordenada x do ponto
     * @param xDouble O novo valor para a coordenada x
     */
    public void setX(T x) {
        this.x = x;
    }

    /** Define a coordenada yDouble do ponto
     * @param yDouble O novo valor para a coordenada yDouble
     */
    public void setY(T y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        if (!(obj instanceof Ponto)) {
            return false;
        }
    

        Ponto<?> that = (Ponto<?>) obj;

        return this.x.equals(that.x) && this.y.equals(that.y);
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
    @SuppressWarnings("unchecked")
    public void rotate(int angle, Ponto<? extends Number> centroide) {
        double angleRadians = Math.toRadians(angle);
        double x = centroide.getX().doubleValue() + ((this.x.doubleValue() - centroide.getX().doubleValue()) * Math.cos(angleRadians) - (this.y.doubleValue() - centroide.getY().doubleValue()) * Math.sin(angleRadians));
        double y = centroide.getY().doubleValue() + ((this.x.doubleValue() - centroide.getX().doubleValue()) * Math.sin(angleRadians) + (this.y.doubleValue() - centroide.getY().doubleValue()) * Math.cos(angleRadians));
        
        setX((T) Double.valueOf(x));
        setY((T) Double.valueOf(y));
    }


    /** Aplica-se um movimento de translação ao ponto a partir de um deslocamento dx e dy
     * @param dx
     * @param dy
     * @return um novo ponto com o movimento de translação já aplicado
     */
    @SuppressWarnings("unchecked")
    public void translate(int dx, int dy) {
        double x = this.x.doubleValue() + dx;
        double y = this.y.doubleValue() + dy;
        if(this.x instanceof Integer) {
            setX((T) Integer.valueOf((int) Math.round(x)));
        }
        else {
            setX((T) Double.valueOf(x));
        }

        if(this.y instanceof Integer) {
            setY((T) Integer.valueOf((int) Math.round(y)));
        }
        else {
            setY((T) Double.valueOf(y));
        }
    }

    /** Aplica-se um movimento de translação ao ponto a partir das coordenadas x e y do novo centróide
     * @param centroX coordenada x do novo centróide
     * @param centroY coordenada y do novo centróide
     * @return um novo ponto com o movimento de translação já aplicado
     */
    @SuppressWarnings("unchecked")
    public void translateCentroide(double centroX, double centroY, Ponto<? extends Number> centroide) {
        double dx = centroX - centroide.getX().doubleValue();
        double dy = centroY - centroide.getY().doubleValue();
        double x = this.x.doubleValue() + dx;
        double y = this.y.doubleValue() + dy;
        setX((T) Double.valueOf(x));
        setY((T) Integer.valueOf((int) Math.round(y)));
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
     * @return a coordenada x do ponto
     */
    public T getX() {
        return x;
    }

    /** Obtém a coordenada y do ponto
     * @return a coordenada y do ponto
     */
    public T getY() {
        return y;
    }
}
