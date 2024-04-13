package GameLayer.SnakeLayer;

public interface IFiguraGeometrica {
    String toString();
    void translate(int dx, int dy);
    void translateCentroide(int centroX, int centroY);
    boolean interseta(Poligono that);
}
