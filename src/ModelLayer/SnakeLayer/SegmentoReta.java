package ModelLayer.SnakeLayer;
import java.util.Objects;

/**	Classe que representa um segmento de reta no plano cartesiano
    Responsabilidade: Representar um segmento de reta no plano cartesiano e 
    verificar se os pontos formam um segmento de reta válido
    @version 1.0 26/03/2024
    @author Hugo Conceição
    @inv  Os pontos não podem ser iguais
*/
public class SegmentoReta implements Cloneable {
    private Ponto<? extends Number> a;
    private Ponto<? extends Number> b;

    /** Construtor para criar um segmento de reta com dois pontos
     * @param a O ponto inicial do segmento de reta
     * @param b O ponto final do segmento de reta
     */
    public SegmentoReta(Ponto<? extends Number> a, Ponto<? extends Number> b) {
        check(a, b);
        this.a = a;
        this.b = b;
    }

     /** Verifica se dois pontos são iguais 
     * @param ponto1 primeiro ponto do segmento de reta
     * @param ponto2 segundo ponto do segmento de reta
     */
    private void check(Ponto<? extends Number> ponto1,Ponto<? extends Number> ponto2){
        if (ponto1.getX() == ponto2.getX() && ponto1.getY() == ponto2.getY()) {
            throw new IllegalArgumentException("Segmento:vi");
        }
    }

    /** Calcula o comprimento do segmento de reta
     * @return O comprimento do segmento de reta
     */
    public double length () {
        return this.a.dist(this.b);
    }

    /** Calcula o produto vetorial entre três pontos
     * @param a o ponto a no plano cartesiano 
     * @param b o ponto b no plano cartesiano
     * @param c o ponto c no plano cartesiano
     * @return o produto vetorial entre os três pontos
     */
    private double produtoVetorial(Ponto<? extends Number> a, Ponto<? extends Number> b, Ponto<? extends Number> c) {
        return ((b.getX().doubleValue() - a.getX().doubleValue()) * (c.getY().doubleValue() - a.getY().doubleValue())) - ((b.getY().doubleValue() - a.getY().doubleValue()) * (c.getX().doubleValue() - a.getX().doubleValue()));
    }

    /** Verifica se dois segmentos se cruzam
     * @param segmentoDeReta segmento de reta no plano cartesiano
     * @return um booleano que nos diz se os dois segmentos de reta se cruzam ou não
     */
    public boolean seCruzam(SegmentoReta segmentoDeReta){
        double abac = produtoVetorial(this.b,this.a,segmentoDeReta.a);
        double abad = produtoVetorial(this.b,this.a,segmentoDeReta.b);
        double cdca = produtoVetorial(segmentoDeReta.b,segmentoDeReta.a,this.a);
        double cdcb = produtoVetorial(segmentoDeReta.b,segmentoDeReta.a,this.b);
        return abac * abad < 0 && cdca * cdcb < 0;
    }
    
    @Override
    public boolean equals (Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        SegmentoReta that = (SegmentoReta) obj;
        if(this.a.equals(that.a) && this.b.equals(that.b) || (this.a.equals(that.b) && this.b.equals(that.a))) {
            return true;
        }
        return false;
    }

    public boolean contemPonto(Ponto<Integer> ponto) {
        // Primeiro verifica se o ponto está na linha usando o produto vetorial
        if (produtoVetorial(a, b, ponto) != 0) {
            return false;
        }

        // Verifica se o ponto está dentro dos limites dos extremos do segmento
        boolean dentroDosLimitesX = Math.min(a.getX().doubleValue(), b.getX().doubleValue()) <= ponto.getX().doubleValue() && ponto.getX().doubleValue() <= Math.max(a.getX().doubleValue(), b.getX().doubleValue());
        boolean dentroDosLimitesY = Math.min(a.getY().doubleValue(), b.getY().doubleValue()) <= ponto.getY().doubleValue() && ponto.getY().doubleValue() <= Math.max(a.getY().doubleValue(), b.getY().doubleValue());

        // Retorna true se estiver dentro dos limites tanto em X quanto em Y
        return dentroDosLimitesX && dentroDosLimitesY;
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.a,this.b);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
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