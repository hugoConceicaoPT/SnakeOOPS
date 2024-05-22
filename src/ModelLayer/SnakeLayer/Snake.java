package ModelLayer.SnakeLayer;

import java.util.LinkedList;
import java.util.Random;

import ModelLayer.BoardLayer.GameBoard;

/**
 * Classe que representa uma cobra no jogo.
 * Responsabilidade: Representar a cobra, mover seu corpo, aumentar seu tamanho e verificar colisões.
 * @version 1.0 22/05/2024
 * @author Hugo Conceição, João Ventura, Eduarda Pereira
 */
public class Snake implements Cloneable {
    private LinkedList<Quadrado> body;
    private Quadrado head;
    private Direction currentDirection;
    private MovementStrategy movementStrategy;
    private int arestaHeadLength;
    private Random random;
    private Direction nextDirection;
    private Quadrado ultimoQuadradoAntesDeMover;

    /**
     * Construtor que cria uma cobra a partir de uma lista de quadrados.
     * A direção inicial é determinada aleatoriamente, e a estratégia de movimento pode ser manual ou automatizada.
     * @param listaQuadrados A lista de quadrados que representa os segmentos do corpo da cobra.
     * @param isManualMovement Indica se a cobra será controlada manualmente.
     * @param random O gerador de números aleatórios para definir a direção inicial.
     */
    public Snake(LinkedList<Quadrado> listaQuadrados, boolean isManualMovement, Random random) {
        this.body = listaQuadrados;
        this.head = this.body.getFirst();
        this.random = random;
        this.currentDirection = Direction.values()[random.nextInt(Direction.values().length)];
        this.arestaHeadLength = (int) this.head.getPontos().get(0).dist(this.head.getPontos().get(1));
        if (isManualMovement) 
            this.movementStrategy = new ManualMovementStrategy();
        else 
            this.movementStrategy = new AutomatedMovementStrategy();

        try {
            this.ultimoQuadradoAntesDeMover = (Quadrado) this.head.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Aumenta o tamanho da cobra adicionando o último quadrado antes de mover.
     */
    public void increaseSize() {
        this.body.addLast(this.ultimoQuadradoAntesDeMover);
    }

    /**
     * Verifica se a cobra colidiu consigo mesma.
     * @return true se a cobra colidiu com seu próprio corpo, false caso contrário.
     */
    public boolean collidedWithHerself() {
        for (int i = 1; i < this.body.size(); i++) {
            if (this.head.contida(this.body.get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Move a cabeça da cobra na direção especificada.
     * Aplica rotações para que a cabeça fique orientada na direção correta.
     * @param quadrado O quadrado da cabeça da cobra.
     * @param currentDirection A direção atual da cobra.
     * @param nextDirection A próxima direção que a cobra deve tomar.
     */
    public void moveSquare(Quadrado quadrado, Direction currentDirection, Direction nextDirection) {
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
                        break;
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
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    /**
     * Move a cobra de forma automatizada, usando a estratégia de movimento configurada.
     * A direção é determinada com base na posição de obstáculos e comida.
     * @param gameBoard O tabuleiro do jogo, contendo comida e obstáculos.
     */
    public void moveAutomated(GameBoard gameBoard) {
        this.nextDirection = this.movementStrategy.setNextDirection(this, gameBoard);
        move();
    }

    /**
     * Move a cobra na direção configurada, respeitando as restrições de movimento.
     * Gira a cabeça conforme necessário e move os outros segmentos de acordo.
     */
    public void move() {
        if (this.movementStrategy instanceof ManualMovementStrategy) {
            this.nextDirection = this.movementStrategy.setNextDirection(this, null);
        }
        if (isOppositeDirection(currentDirection, nextDirection)) {
            return;
        }

        Ponto<? extends Number> centroHeadSnake = this.head.getCentroide();
        Quadrado ultimoQuadrado = this.body.getLast();

        try {
            this.ultimoQuadradoAntesDeMover = (Quadrado) this.body.getLast().clone();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ultimoQuadrado.translateCentroide(centroHeadSnake.getX().doubleValue(), centroHeadSnake.getY().doubleValue());

        if (this.currentDirection != this.nextDirection) {
            moveSquare(ultimoQuadrado, currentDirection, nextDirection);
        }

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

    /**
     * Verifica se a direção atual é oposta à próxima direção fornecida.
     * @param currentDirection A direção atual.
     * @param nextDirection A próxima direção.
     * @return true se as direções forem opostas, false caso contrário.
     */
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

    /**
     * Obtém a cabeça da cobra.
     * @return A cabeça da cobra.
     */
    public Quadrado getHead() {
        return head;
    }

    /**
     * Define uma nova cabeça para a cobra.
     * @param head A nova cabeça da cobra.
     */
    public void setHead(Quadrado head) {
        this.head = head;
    }

    /**
     * Obtém o corpo da cobra como uma lista de quadrados.
     * @return O corpo da cobra.
     */
    public LinkedList<Quadrado> getBody() {
        return body;
    }

    /**
     * Define um novo corpo para a cobra.
     * @param body A nova lista de quadrados que forma o corpo da cobra.
     */
    public void setBody(LinkedList<Quadrado> body) {
        this.body = body;
    }

    /**
     * Obtém o comprimento de uma aresta da cabeça da cobra.
     * @return O comprimento de uma aresta da cabeça.
     */
    public int getArestaHeadLength() {
        return arestaHeadLength;
    }

    /**
     * Define um novo comprimento de aresta para a cabeça da cobra.
     * @param arestaHeadLength O novo comprimento da aresta.
     */
    public void setArestaHeadLength(int arestaHeadLength) {
        this.arestaHeadLength = arestaHeadLength;
    }

    /**
     * Obtém a estratégia de movimento configurada para a cobra.
     * @return A estratégia de movimento.
     */
    public MovementStrategy getMovementStrategy() {
        return movementStrategy;
    }

    /**
     * Define uma nova estratégia de movimento para a cobra.
     * @param movementStrategy A nova estratégia de movimento.
     */
    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    /**
     * Obtém o gerador de números aleatórios configurado para a cobra.
     * @return O gerador de números aleatórios.
     */
    public Random getRandom() {
        return random;
    }

    /**
     * Define um novo gerador de números aleatórios para a cobra.
     * @param random O novo gerador de números aleatórios.
     */
    public void setRandom(Random random) {
        this.random = random;
    }

    /**
     * Obtém a direção atual de movimento da cobra.
     * @return A direção atual de movimento.
     */
    public Direction getCurrentDirection() {
        return currentDirection;
    }

    /**
     * Define a direção atual de movimento da cobra.
     * @param currentDirection A nova direção de movimento.
     */
    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    /**
     * Obtém a próxima direção de movimento desejada para a cobra.
     * @return A próxima direção de movimento.
     */
    public Direction getNextDirection() {
        return nextDirection;
    }

    /**
     * Define a próxima direção de movimento para a cobra.
     * @param nextDirection A nova direção de movimento.
     */
    public void setNextDirection(Direction nextDirection) {
        this.nextDirection = nextDirection;
    }

    /**
     * Obtém o ultimo quadrado antes de mover
     * @return o ultimo quadrado antes de mover
     */
    public Quadrado getUltimoQuadradoAntesDeMover() {
        return ultimoQuadradoAntesDeMover;
    }

    /**
     * Atualiza o ultimo quadrados antes de mover
     * @param ultimoQuadradoAntesDeMover o novo ultimo quadrado antes de mover
     */
    public void setUltimoQuadradoAntesDeMover(Quadrado ultimoQuadradoAntesDeMover) {
        this.ultimoQuadradoAntesDeMover = ultimoQuadradoAntesDeMover;
    }
}
