package ModelLayer.SnakeLayer;
/**	Classe que representa uma reta no plano cartesiano
    Responsabilidade: Representar uma reta no plano cartesiano 
    e verificar se os pontos formam uma reta válida
    @version 1.0 22/05/2024
    @author Hugo Conceição João Ventura Eduarda Pereira
    @inv  Os pontos não podem ser iguais
*/
public class Reta {
    private Ponto<? extends Number> a;
    private Ponto<? extends Number> b;


    /** Construtor para criar uma reta com dois pontos
     * @param a O primeiro ponto da reta
     * @param b O segundo ponto da reta
     */
    public Reta(Ponto<? extends Number> a, Ponto<? extends Number> b) {
        check(a, b);
        this.a = a;
        this.b = b;
    }

    /** Verifica se dois pontos são iguais 
     * @param ponto1 primeiro ponto da reta
     * @param ponto2 segundo ponto da reta
     */
    private void check(Ponto<? extends Number> ponto1,Ponto<? extends Number> ponto2){
        if (ponto1.getX() == ponto2.getX() && ponto1.getY() == ponto2.getY()){
            throw new IllegalArgumentException("Reta:vi");
        }
    }

    /** Verifica se três pontos consecutivos são colineares
     * @param ponto Ponto no plano cartesiano
     * @return verdadeiro se são colineares, falso caso contrário
     */
    public boolean colineares (Ponto<? extends Number> ponto)
    {
        double dy = b.getY().doubleValue() - a.getY().doubleValue();
        double dx = b.getX().doubleValue() - a.getX().doubleValue();
        double declive = dy/dx;
        double b = a.getY().doubleValue() - declive*a.getX().doubleValue();
        if(ponto.getY().doubleValue() == ponto.getX().doubleValue()*declive + b)
        {
            return true;
        }
        return false;
    }

    /** Obtém o ponto a da reta
     * @return O ponto a da reta
     */
    public Ponto<? extends Number> getA() {
        return a;
    }

    /** Obtém o ponto b da reta
     * @return O ponto b da reta
     */
    public Ponto<? extends Number> getB() {
        return b;
    }

    /** Define o ponto a da reta
     * @param a O novo valor para o ponto a
     */
    public void setA(Ponto<? extends Number> a) {
        this.a = a;
    }

    /** Define o ponto b da reta
     * @param b O novo valor para o ponto b
     */
    public void setB(Ponto<? extends Number> b) {
        this.b = b;
    }
}
