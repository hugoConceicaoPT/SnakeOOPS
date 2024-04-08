import java.util.Objects;

/**	Classe que representa um ponto no plano cartesiano
    Responsabilidade: Armazenar as coordenadas de um ponto
    @version 1.0 26/03/2024
    @author Hugo Conceição
    @inv  As coordenadas devem estar localizadas no primeiro quadrante do plano cartesiano
*/
public class Ponto {
    private int x;
    private int y;
    private double xDouble;
    private double yDouble;
    
    public Ponto() {
        this.x = 0;
        this.y = 0;
    }

    /** Construtor para criar um ponto com as coordenadas especificadas
     * @param x A coordenada x do ponto
     * @param y A coordenada y do ponto
     */
    public Ponto(int x, int y) {
        check(x, y);
        setX(x);
        setY(y);
    }

    /** Construtor para criar um ponto com as coordenadas especificadas
     * @param xDouble A coordenada x do ponto
     * @param yDouble A coordenada y do ponto
     */
    public Ponto(double xDouble, double yDouble) {
        check(xDouble, yDouble);
        setX(xDouble);
        setY(yDouble);
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
    int dist (Ponto p) {
        double dx = x-p.x;
        double dy = y-p.y;
        return (int) Math.sqrt(dx*dx+dy*dy);
    }

    /** Obtém a coordenada x do ponto
     * @return A coordenada x do ponto
     */
    public int getX() {
        return x;
    }

    /** Obtém a coordenada y do ponto
     * @return A coordenada y do ponto
     */
    public int getY() {
        return y;
    }
    
    /** Define a coordenada x do ponto
     * @param x O novo valor para a coordenada x
     */
    public void setX(int x) {
        if(x < 0)
        {
            System.out.println("Ponto:vi");
            System.exit(0);
        }
        this.x = x;
    }

    /** Define a coordenada xDouble do ponto
     * @param xDouble O novo valor para a coordenada xDouble
     */
    public void setX(double xDouble) {
        if(xDouble < 0.0)
        {
            System.out.println("Ponto:vi");
            System.exit(0);
        }
        this.xDouble = xDouble;
    }

    /** Define a coordenada y do ponto
     * @param y O novo valor para a coordenada y
     */
    public void setY(int y) {
        if(y < 0)
        {
            System.out.println("Ponto:vi");
            System.exit(0);
        }
        this.y = y;
    }

    /** Define a coordenada yDouble do ponto
     * @param yDouble O novo valor para a coordenada yDouble
     */
    public void setY(double yDouble) {
        if(yDouble < 0.0)
        {
            System.out.println("Ponto:vi");
            System.exit(0);
        }
        this.yDouble = yDouble;
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
    public Ponto rotate(int angle, Ponto centroide) {
        double angleRadians = Math.toRadians(angle);
        int x = (int) Math.round((centroide.getxDouble() + ((this.x - centroide.getxDouble()) * Math.cos(angleRadians) - (this.y - centroide.getyDouble()) * Math.sin(angleRadians))));
        int y = (int) Math.round((centroide.getyDouble() + ((this.x - centroide.getxDouble()) * Math.sin(angleRadians) + (this.y - centroide.getyDouble()) * Math.cos(angleRadians))));
        return new Ponto(x,y);
    }

    /** Aplica-se um movimento de translação ao ponto a partir de um deslocamento dx e dy
     * @param dx
     * @param dy
     * @return um novo ponto com o movimento de translação já aplicado
     */
    public Ponto translate(int dx, int dy) {
        int x = this.x + dx;
        int y = this.y + dy;
        return new Ponto(x,y);
    }

    /** Aplica-se um movimento de translação ao ponto a partir das coordenadas x e y do novo centróide
     * @param centroX coordenada x do novo centróide
     * @param centroY coordenada y do novo centróide
     * @return um novo ponto com o movimento de translação já aplicado
     */
    public Ponto translateCentroide(int centroX, int centroY, Ponto centroide) {
        int dx = (int) (centroX - centroide.getxDouble());
        int dy = (int) (centroY - centroide.getyDouble());
        int x = this.x + dx;
        int y = this.y + dy;
        return new Ponto(x,y);
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    /** Obtém a coordenada xDouble do ponto
     * @return A coordenada xDouble do ponto
     */
    public double getxDouble() {
        return xDouble;
    }

    /** Obtém a coordenada yDouble do ponto
     * @return A coordenada yDouble do ponto
     */
    public double getyDouble() {
        return yDouble;
    }
}
