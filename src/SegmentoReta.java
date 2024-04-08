import java.util.Objects;

/**	Classe que representa um segmento de reta no plano cartesiano
    Responsabilidade: Representar um segmento de reta no plano cartesiano e 
    verificar se os pontos formam um segmento de reta válido
    @version 1.0 26/03/2024
    @author Hugo Conceição
    @inv  Os pontos não podem ser iguais
*/
public class SegmentoReta {
    private Ponto a;
    private Ponto b;

    /** Construtor para criar um segmento de reta com dois pontos
     * @param a O ponto inicial do segmento de reta
     * @param b O ponto final do segmento de reta
     */
    public SegmentoReta(Ponto a, Ponto b) {
        check(a, b);
        this.a = a;
        this.b = b;
    }

     /** Verifica se dois pontos são iguais 
     * @param ponto1 primeiro ponto do segmento de reta
     * @param ponto2 segundo ponto do segmento de reta
     */
    private void check(Ponto ponto1,Ponto ponto2){
        if (ponto1.getX() == ponto2.getX() && ponto1.getY() == ponto2.getY()){
            System.out.println("Segmento:vi");
            System.exit(0);
        }
    }

    /** Calcula o comprimento do segmento de reta
     * @return O comprimento do segmento de reta
     */
    double length () {
        return this.a.dist(this.b);
    }

    /** Calcula o produto vetorial entre três pontos
     * @param a o ponto a no plano cartesiano 
     * @param b o ponto b no plano cartesiano
     * @param c o ponto c no plano cartesiano
     * @return o produto vetorial entre os três pontos
     */
    private double produtoVetorial(Ponto a, Ponto b, Ponto c) {
        return (b.getX() - a.getX()) * (c.getY() - a.getY()) - (b.getY() - a.getY()) * (c.getX() - a.getX());
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

    @Override
    public int hashCode() {
        return Objects.hash(this.a,this.b);
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