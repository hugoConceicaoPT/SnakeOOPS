package GameLayer.SnakeLayer;

import java.util.List;
import java.util.Random;

public class Snake {
    private Quadrado head;
    private List<Quadrado> tail;
    private Direction direction;
    private int arestaLength;
    
    public Snake(List<Quadrado> tail) {
        this.head = tail.get(0);
        tail.remove(0);
        this.tail = tail;
        Random random = new Random();
        this.direction = Direction.values()[random.nextInt(Direction.values().length)];
        arestaLength = this.tail.get(this.tail.size()-1).pontos.get(0).dist(this.tail.get(this.tail.size()-1).pontos.get(1));
    }

    public void increaseSize() {
        Quadrado ultimoQuadrado = this.tail.get(this.tail.size()-1);
        Quadrado novoQuadrado = new Quadrado(ultimoQuadrado.getPontos());
        switch (direction) {
            case UP:
                novoQuadrado.translate(0, -arestaLength); 
                break;
            case DOWN:
                novoQuadrado.translate(0, arestaLength);
            case LEFT:
                novoQuadrado.translate(arestaLength, 0);
                break;
            case RIGHT:
                novoQuadrado.translate(-arestaLength, 0);
                break;
            default:
                break;
        }
        this.tail.add(novoQuadrado);
    }
    public boolean collidedWithHerself() {
        for (int i = 0; i < tail.size(); i++) {
            if(head.interseta(tail.get(i)))
            return true;
        }
        return false;
    }
    private void moveHead(Direction nextDirection) {
        switch (this.direction) {
            case UP:
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
            case DOWN:
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

    public void move(Direction nextDirection) {
        Ponto centroHeadSnake = head.getCentroide();
        if(this.direction != nextDirection) 
            moveHead(nextDirection);

        switch (nextDirection) {
            case UP:
                head.translate(0, arestaLength); 
                break;
            case DOWN:
                head.translate(0, -arestaLength);
                break;
            case LEFT:
                head.translate(-arestaLength, 0);
                break;
            case RIGHT:
                head.translate(arestaLength, 0);
                break;
            default:
                break;
        }
        setDirection(nextDirection);

        tail.get(0).translateCentroide((int) centroHeadSnake.getxDouble(), (int) centroHeadSnake.getyDouble());
        for(int i = 1; i < tail.size()-2; i++) {
            Quadrado previousSquare = tail.get(i-1);
            Quadrado currentSquare = tail.get(i);
            currentSquare.translateCentroide((int) previousSquare.getCentroide().getxDouble(), (int) previousSquare.getCentroide().getyDouble());
        }
    }

    public Quadrado getHead() {
        return head;
    }

    public void setHead(Quadrado head) {
        this.head = head;
    }

    public List<Quadrado> gettail() {
        return tail;
    }

    public void settail(List<Quadrado> tail) {
        this.tail = tail;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Cabeça: " + head.toString() + "Tail: " + tail.toString();
    }
}
