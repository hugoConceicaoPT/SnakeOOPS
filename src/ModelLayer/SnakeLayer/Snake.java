package ModelLayer.SnakeLayer;

import java.util.List;
import java.util.Random;

public class Snake {
    private Quadrado head;
    private List<Quadrado> tail;
    private Direction direction;
    private int arestaHeadLength;
    
    /** Construtor para criar uma cobra 
     * @param listaQuadrados a lista de quadrados que contém os quadrados da cobra
     */
    public Snake(List<Quadrado> listaQuadrados) {
        this.tail = listaQuadrados.subList(1, listaQuadrados.size());
        this.head = listaQuadrados.get(0);
        Random random = new Random();
        this.direction = Direction.values()[random.nextInt(Direction.values().length)];
        arestaHeadLength = (int) this.head.pontos.get(0).dist(this.head.pontos.get(1));
    }

    /** Aumenta o tamanho da cobra 
     * @throws CloneNotSupportedException caso a duplicação do quadrado falhe
     */
    public void increaseSize() throws CloneNotSupportedException {
        Quadrado novoQuadrado;
        if(this.tail.isEmpty()) {
            novoQuadrado = (Quadrado) head.clone();
        }
        else {
            novoQuadrado = (Quadrado) this.tail.get(this.tail.size()-1).clone();
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
        this.tail.add(novoQuadrado);
    }

    /** Verifica se a cobra colida consigo própria 
     * @return verdadeiro se acontecer, falso se não
     */
    public boolean collidedWithHerself() {
        for (int i = 0; i < tail.size(); i++) {
            if(head.contida(tail.get(i)))
                return true;
        }
        return false;
    }
    /** Move a cabeça da cobra
     * @param nextDirection a próxima direção que a cobra vai tomar
     */
    private void moveHead(Direction nextDirection) {
        switch (this.direction) {
            case UP:
                switch (nextDirection) {
                    case RIGHT:
                        head.rotateAngle(-90);
                        break; 
                    case LEFT:
                        head.rotateAngle(90);
                        break;
                    default:
                        break;
            }
            break;
            case DOWN:
                switch (nextDirection) {
                    case RIGHT:
                        head.rotateAngle(90);
                        break;
                    case LEFT:
                        head.rotateAngle(-90);
                        break;
                    default:
                        break;
            }
            break;
            case LEFT:
                switch (nextDirection) {
                    case UP:
                        head.rotateAngle(-90);
                        break;
                    case DOWN:
                        head.rotateAngle(90);
                        default:
                            break;
                }
            break;
            case RIGHT:
                switch (nextDirection) {
                    case UP:
                        head.rotateAngle(90);
                        break;
                    case DOWN:
                        head.rotateAngle(-90);
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
        Ponto centroHeadSnake = head.getCentroide();
        if(this.direction != nextDirection) 
            moveHead(nextDirection);

        switch (nextDirection) {
            case UP:
                head.translate(0, arestaHeadLength); 
                break;
            case DOWN:
                head.translate(0, -arestaHeadLength);
                break;
            case LEFT:
                head.translate(-arestaHeadLength, 0);
                break;
            case RIGHT:
                head.translate(arestaHeadLength, 0);
                break;
            default:
                break;
        }
        setDirection(nextDirection);

        for (int i = tail.size() - 1; i > 0; i--) {
            Quadrado previousSquare = tail.get(i - 1);
            Quadrado currentSquare = tail.get(i);
            currentSquare.translateCentroide((int) previousSquare.getCentroide().getX(), (int) previousSquare.getCentroide().getY());
        }
        tail.get(0).translateCentroide((int) centroHeadSnake.getX(), (int) centroHeadSnake.getY());
    }

    @Override
    public String toString() {
        return "Cabeça: " + head.toString() + " Tail: " + tail.toString();
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
    public List<Quadrado> getTail() {
        return tail;
    }

    /** Atualiza a tail da cobra
     * @param tail a nova tail da cobra
     */
    public void setTail(List<Quadrado> tail) {
        this.tail = tail;
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
