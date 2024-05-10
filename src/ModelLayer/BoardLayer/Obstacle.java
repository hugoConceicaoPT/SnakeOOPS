package ModelLayer.BoardLayer;

import ModelLayer.SnakeLayer.Poligono;
import ModelLayer.SnakeLayer.Ponto;
import ModelLayer.SnakeLayer.Snake;

/**
 * Classe que representa um obstáculo no jogo da cobra
 * Responsabilidade: Gerenciar a forma geométrica do obstáculo e sua dinâmica
 * @version 1.0 10/05/2024
 * @author Hugo Conceição João Ventura Eduarda Pereira
 */
public class Obstacle {

    private Poligono poligono; // Representa a forma geométrica do obstáculo
    private ObstacleMovement obstacleMovement; // Define o comportamento do movimento do obstáculo
    private Ponto<? extends Number> rotacionPoint; // Ponto de rotação usado em transformações geométricas

    /** 
     * Construtor para criar um obstáculo com características especificadas
     * @param poligono Figura geométrica do obstáculo
     * @param rotacionPoint Ponto de rotação para transformações geométricas
     * @param isDynamic Indica se o obstáculo possui movimento dinâmico
     */
    public Obstacle(Poligono poligono, Ponto<? extends Number> rotacionPoint, boolean isDynamic) {
        this.poligono = poligono;
        if(isDynamic)
            this.obstacleMovement = new DynamicMovement();
        else
            this.obstacleMovement = new StaticMovement();
        this.rotacionPoint = (rotacionPoint == null) ? poligono.getCentroide() : rotacionPoint;
    }

    /** Verifica se o obstáculo intersecta a cabeça da cobra
     * @param snake Cobra do jogo
     * @return true se houver intersecção, false caso contrário
     */
    public boolean obstacleIntersect(Snake snake) {
        return snake.getHead().interseta(this.poligono);
    }

    /** Verifica se a cabeça da cobra está contida dentro do polígono do obstáculo
     * @param snake Cobra do jogo
     * @return true se a cabeça está contida, false caso contrário
     */
    public boolean obstacleContained(Snake snake) {
        return this.poligono.contida(snake.getHead());
    }

    /** Verifica se um ponto específico intersecta o obstáculo
     * @param point Ponto para verificar a intersecção
     * @return true se o ponto intersecta o obstáculo, false caso contrário
     */
    public boolean obstacleIntersect(Ponto<? extends Number> point) {
        double x = point.getX().doubleValue();
        double y = point.getY().doubleValue();
        return (x >= this.poligono.getMinX() && x <= this.poligono.getMaxX() &&
            y >= this.poligono.getMinY() && y <= this.poligono.getMaxY());
    }

    /** Rotaciona o obstáculo em torno do ponto de rotação
     */
    public void rotateObstacle() {
        this.obstacleMovement.rotateObstacle(this.poligono, this.rotacionPoint);
    }

    public Poligono getPoligono() {
        return poligono;
    }

    public void setPoligono(Poligono poligono) {
        this.poligono = poligono;
    }

    @Override
    public String toString() {
        return "Obstáculo: " + poligono.toString();
    }

    public ObstacleMovement getObstacleMovement() {
        return obstacleMovement;
    }

    public void setObstacleMovement(ObstacleMovement obstacleMovement) {
        this.obstacleMovement = obstacleMovement;
    }

    public Ponto<? extends Number> getRotacionPoint() {
        return rotacionPoint;
    }

    public void setRotacionPoint(Ponto<? extends Number> rotacionPoint) {
        this.rotacionPoint = rotacionPoint;
    }
}
