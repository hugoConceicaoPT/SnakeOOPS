package ModelLayer.SnakeLayer;

import java.util.LinkedList;
import java.util.Random;

public class Snake implements Cloneable {
    private LinkedList<Quadrado> body;
    private Quadrado head;
    private Direction currentDirection;
    private MovementStrategy movementStrategy;
    private int arestaHeadLength;
    private Random random;
    private Direction nextDirection;
    
    /** Construtor para criar uma cobra 
     * @param listaQuadrados a lista de quadrados que contém os quadrados da cobra
     */
    public Snake(LinkedList<Quadrado> listaQuadrados, boolean isManualMovement, Random random) {
        this.body = listaQuadrados;
        this.head = this.body.getFirst();
        this.random = random;
        this.currentDirection = Direction.values()[random.nextInt(Direction.values().length)];
        this.head.setDirection(currentDirection);
        arestaHeadLength = (int) this.head.pontos.get(0).dist(this.head.pontos.get(1));
        if(isManualMovement)
            this.movementStrategy = new ManualMovementStrategy();
        else
            this.movementStrategy = new AutomatedMovementStrategy();
    }

    /** Aumenta o tamanho da cobra 
     * @throws CloneNotSupportedException caso a duplicação do quadrado falhe
     */
    public void increaseSize() throws CloneNotSupportedException {
        Quadrado novoQuadrado;
        if(this.body.size() == 1) {
            novoQuadrado = (Quadrado) this.head.clone();
            switch (this.currentDirection) {
                case UP:
                    novoQuadrado.translate(0, -arestaHeadLength); 
                    break;
                case DOWN:
                    novoQuadrado.translate(0, arestaHeadLength);
                    break;
                case LEFT:
                    novoQuadrado.translate(arestaHeadLength, 0);
                    break;
                case RIGHT:
                    novoQuadrado.translate(-arestaHeadLength, 0);
                    break;
                default:
                    break;
            }
        }
        else {
            novoQuadrado = (Quadrado) this.body.getLast().clone();
            novoQuadrado.getDirectionFromPreviousSquare(this.body.get(this.body.size()-2));
            switch (novoQuadrado.getDirection()) {
                case UP:
                    novoQuadrado.translate(0, -arestaHeadLength); 
                    break;
                case DOWN:
                    novoQuadrado.translate(0, arestaHeadLength);
                    break;
                case LEFT:
                    novoQuadrado.translate(arestaHeadLength, 0);
                    break;
                case RIGHT:
                    novoQuadrado.translate(-arestaHeadLength, 0);
                    break;
                default:
                    break;
            }
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
    private void moveSquare(Quadrado quadrado,Direction currentDirection, Direction nextDirection) {
        switch (currentDirection) {
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
    
    public void move() {
        this.nextDirection = this.movementStrategy.setNextDirection(this);
        if(isOppositeDirection(currentDirection, nextDirection))
            return;
        Ponto centroHeadSnake = this.head.getCentroide();

        Quadrado ultimoQuadrado = body.getLast();
        ultimoQuadrado.translateCentroide(((int) centroHeadSnake.getX()),(int) centroHeadSnake.getY());

        if(this.currentDirection != this.nextDirection) 
            moveSquare(ultimoQuadrado,currentDirection, nextDirection);

        switch (this.nextDirection) {
            case UP:
                ultimoQuadrado.translate(0, this.arestaHeadLength); 
                break;
            case DOWN:
                ultimoQuadrado.translate(0, -this.arestaHeadLength);
                break;
            case LEFT:
                ultimoQuadrado.translate(-this.arestaHeadLength, 0);
                break;
            case RIGHT:
                ultimoQuadrado.translate(this.arestaHeadLength, 0);
                break;
            default:
                break;
        }

        this.body.removeLast();
        this.body.addFirst(ultimoQuadrado);
        this.head = this.body.getFirst();
        setCurrentDirection(nextDirection);
    }

    private boolean isOppositeDirection(Direction currentDirection, Direction nextDirection) {
        return (currentDirection == Direction.UP && nextDirection == Direction.DOWN) ||
               (currentDirection == Direction.DOWN && nextDirection == Direction.UP) ||
               (currentDirection == Direction.LEFT && nextDirection == Direction.RIGHT) ||
               (currentDirection == Direction.RIGHT && nextDirection == Direction.LEFT);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Snake clonedSnake = (Snake) super.clone();
        clonedSnake.body = new LinkedList<>();
        for (Quadrado quadrado : this.body) {
            clonedSnake.body.add((Quadrado) quadrado.clone());
        }
        clonedSnake.head = clonedSnake.body.getFirst();
        return clonedSnake;
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

    public MovementStrategy getMovementStrategy() {
        return movementStrategy;
    }

    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public Direction getNextDirection() {
        return nextDirection;
    }

    public void setNextDirection(Direction nextDirection) {
        this.nextDirection = nextDirection;
    }
}
