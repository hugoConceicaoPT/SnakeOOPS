package ModelLayer.SnakeLayer;

import java.util.LinkedList;
import java.util.Random;

public class Snake {
    private LinkedList<Quadrado> body;
    private Quadrado head;
    private Direction direction;
    private int arestaHeadLength;
    
    /** Construtor para criar uma cobra 
     * @param listaQuadrados a lista de quadrados que contém os quadrados da cobra
     */
    public Snake(LinkedList<Quadrado> listaQuadrados) {
        this.body = listaQuadrados;
        this.head = this.body.getFirst();
        Random random = new Random();
        this.direction = Direction.values()[random.nextInt(Direction.values().length)];
        arestaHeadLength = (int) this.head.pontos.get(0).dist(this.head.pontos.get(1));
    }

    /** Aumenta o tamanho da cobra 
     * @throws CloneNotSupportedException caso a duplicação do quadrado falhe
     */
    public void increaseSize() throws CloneNotSupportedException {
        Quadrado novoQuadrado;
        if(this.body.size() == 1) {
            novoQuadrado = (Quadrado) this.head.clone();
        }
        else {
            novoQuadrado = (Quadrado) this.body.getLast().clone();
        }
        switch (direction) {
            case UP:
                novoQuadrado.translate(0, -arestaHeadLength); 
                break;
            case DOWN:
                novoQuadrado.translate(0, arestaHeadLength);
            case LEFT:
                novoQuadrado.translate(arestaHeadLength, 0);
                break;
            case RIGHT:
                novoQuadrado.translate(-arestaHeadLength, 0);
                break;
            default:
                break;
        }
        this.body.addLast(novoQuadrado);
    }

    /** Verifica se a cobra colida consigo própria 
     * @return verdadeiro se acontecer, falso se não
     */
    public boolean collidedWithHerself() {
        for (int i = 1; i < this.body.size(); i++) {
            if(this.head.contida(this.body.get(i)))
                return true;
        }
        return false;
    }
    /** Move a cabeça da cobra
     * @param nextDirection a próxima direção que a cobra vai tomar
     */
    private void moveSquare(Quadrado quadrado, Direction nextDirection) {
        switch (this.direction) {
            case UP:
                switch (nextDirection) {
                    case RIGHT:
                        quadrado.rotateAngle(-90);
                        break; 
                    case LEFT:
                        quadrado.rotateAngle(90);
                        break;
                    default:
                        break;
            }
            break;
            case DOWN:
                switch (nextDirection) {
                    case RIGHT:
                        quadrado.rotateAngle(90);
                        break;
                    case LEFT:
                        quadrado.rotateAngle(-90);
                        break;
                    default:
                        break;
            }
            break;
            case LEFT:
                switch (nextDirection) {
                    case UP:
                        quadrado.rotateAngle(-90);
                        break;
                    case DOWN:
                        quadrado.rotateAngle(90);
                        default:
                            break;
                }
            break;
            case RIGHT:
                switch (nextDirection) {
                    case UP:
                        quadrado.rotateAngle(90);
                        break;
                    case DOWN:
                        quadrado.rotateAngle(-90);
                        default:
                            break;
                }
                break;
            default:
                break;
            
        }
    }

    /** Move a cobra 
     * @param nextDirection a próxima direção que a cobra vai tomar 
     */
    public void move(Direction nextDirection) {

        if(isOppositeDirection(nextDirection, this.direction))
            return;

        Ponto centroHeadSnake = this.head.getCentroide();

        Quadrado ultimoQuadrado = this.body.getLast();
        ultimoQuadrado.translateCentroide(((int) centroHeadSnake.getX()),(int) centroHeadSnake.getY());

        if(this.direction != nextDirection) 
            moveSquare(ultimoQuadrado, nextDirection);

        switch (nextDirection) {
            case UP:
                ultimoQuadrado.translate(0, arestaHeadLength); 
                break;
            case DOWN:
                ultimoQuadrado.translate(0, -arestaHeadLength);
                break;
            case LEFT:
                ultimoQuadrado.translate(-arestaHeadLength, 0);
                break;
            case RIGHT:
                ultimoQuadrado.translate(arestaHeadLength, 0);
                break;
            default:
                break;
        }

        this.body.removeLast();
        this.body.addFirst(ultimoQuadrado);
        this.head = this.body.getFirst();
        setDirection(nextDirection);
    }

    private boolean isOppositeDirection(Direction nextDirection, Direction currentDirection) {
        return (nextDirection == Direction.UP && currentDirection == Direction.DOWN) ||
               (nextDirection == Direction.DOWN && currentDirection == Direction.UP) ||
               (nextDirection == Direction.LEFT && currentDirection == Direction.RIGHT) ||
               (nextDirection == Direction.RIGHT && currentDirection == Direction.LEFT);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cabeça: ").append(this.head).append(" Tail: ").append(this.body.subList(1, this.body.size()));
        return sb.toString();
    }

    /** Obtém a cabeça da cobra
     * @return a cabeça da cobra
     */
    public Quadrado getHead() {
        return head;
    }

    /** Atualiza a cabeça da cobra
     * @param head a nova cabeça da cobra
     */
    public void setHead(Quadrado head) {
        this.head = head;
    }

    /** Obtém a tail da cobra
     * @return a tail da cobra
     */
    public LinkedList<Quadrado> getBody() {
        return body;
    }

    /** Atualiza a tail da cobra
     * @param tail a nova tail da cobra
     */
    public void setBody(LinkedList<Quadrado> body) {
        this.body = body;
    }

    /** Obtém a direção da cobra
     * @return a direção da cobra
     */
    public Direction getDirection() {
        return direction;
    }

    /** Atualiza a direção da cobra
     * @param direction a nova direção da cobra
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /** Obtém o comprimento de uma aresta da cabeça
     * @return o comprimento de uma aresta da cabeça
     */
    public int getArestaHeadLength() {
        return arestaHeadLength;
    }

    /** Atualiza o comprimento de uma aresta da cabeça
     * @param arestaHeadLength o comprimento de uma aresta da cabeça
     */
    public void setArestaHeadLength(int arestaHeadLength) {
        this.arestaHeadLength = arestaHeadLength;
    }
}
