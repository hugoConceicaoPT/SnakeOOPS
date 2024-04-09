package SnakeLayer;
/**	Classe que representa uma reta no plano cartesiano
    Responsabilidade: Representar uma reta no plano cartesiano 
    e verificar se os pontos formam uma reta válida
    @version 1.0 26/03/2024
    @author Hugo Conceição
    @inv  Os pontos não podem ser iguais
*/
public class Reta {
    private Ponto a;
    private Ponto b;


    /** Construtor para criar uma reta com dois pontos
     * @param a O primeiro ponto da reta
     * @param b O segundo ponto da reta
     */
    public Reta(Ponto a, Ponto b) {
        check(a, b);
        this.a = a;
        this.b = b;
    }

    /** Verifica se dois pontos são iguais 
     * @param ponto1 primeiro ponto da reta
     * @param ponto2 segundo ponto da reta
     */
    private void check(Ponto ponto1,Ponto ponto2){
        if (ponto1.getX() == ponto2.getX() && ponto1.getY() == ponto2.getY()){
            System.out.println("Reta:vi");
            System.exit(0);
        }
    }

    /** Verifica se três pontos consecutivos são colineares
     * @param ponto Ponto no plano cartesiano
     */
    public boolean colineares (Ponto ponto)
    {
        double dy = b.getY() - a.getY();
        double dx = b.getX() - a.getX();
        double declive = dy/dx;
        double b = a.getY()- declive*a.getX();
        if(ponto.getY() == ponto.getX()*declive + b)
        {
            return true;
        }
        return false;
    }

    /** Obtém o ponto a da reta
     * @return O ponto a da reta
     */
    public Ponto getA() {
        return a;
    }

    /** Obtém o ponto b da reta
     * @return O ponto b da reta
     */
    public Ponto getB() {
        return b;
    }

    /** Define o ponto a da reta
     * @param a O novo valor para o ponto a
     */
    public void setA(Ponto a) {
        this.a = a;
    }

    /** Define o ponto b da reta
     * @param b O novo valor para o ponto b
     */
    public void setB(Ponto b) {
        this.b = b;
    }
}
