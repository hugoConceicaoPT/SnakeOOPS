package GameLayer.SnakeLayer;

public interface IFiguraGeometrica {
    String toString();
    
    /** Aplica-se um movimento de translação ao polígono a partir de um deslocamento x e um deslocamento y
     * @param dx deslocamento x
     * @param dy deslocamento y
     * @return um novo polígono com o movimento de translação já aplicado
    */
    void translate(int dx, int dy);
    
    /** Aplica-se um movimento de translação ao polígono a partir das coordenadas x e y do novo centróide
     * @param centroX coordenada x do novo centróide
     * @param centroY coordenada y do novo centróide
     * @return um novo polígono com o movimento de translação já aplicado
     */
    void translateCentroide(int centroX, int centroY);
    
    /** Verifica se a arestas de um polígono intersetam com outro
     * @param that o outro polígono
     * @return true se houver interseção, false se não houver
     */
    boolean interseta(Poligono that);
}
