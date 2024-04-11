package GameLayer.SnakeLayer;

import java.util.List;
import java.util.Random;

public class Snake {
    private Quadrado head;
    private List<Quadrado> listaQuadrados;
    private Direction direction;
    private int arestaLength;
    
    public Snake(List<Quadrado> listaQuadrados) {
        this.head = listaQuadrados.get(0);
        listaQuadrados.remove(0);
        this.listaQuadrados = listaQuadrados;
        Random random = new Random();
        this.direction = Direction.values()[random.nextInt(Direction.values().length)];
        arestaLength = this.listaQuadrados.get(this.listaQuadrados.size()-1).pontos.get(0).dist(this.listaQuadrados.get(this.listaQuadrados.size()-1).pontos.get(1));
    }

    public void increaseSize() {
        Quadrado ultimoQuadrado = this.listaQuadrados.get(this.listaQuadrados.size()-1);
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
        this.listaQuadrados.add(novoQuadrado);
    }
    public boolean collidedWithHerself() {return true;}
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

        listaQuadrados.get(0).translateCentroide((int) centroHeadSnake.getxDouble(), (int) centroHeadSnake.getyDouble());
        for(int i = 1; i < listaQuadrados.size()-2; i++) {
            Quadrado previousSquare = listaQuadrados.get(i-1);
            Quadrado currentSquare = listaQuadrados.get(i);
            currentSquare.translateCentroide((int) previousSquare.getCentroide().getxDouble(), (int) previousSquare.getCentroide().getyDouble());
        }
    }

    public Quadrado getHead() {
        return head;
    }

    public void setHead(Quadrado head) {
        this.head = head;
    }

    public List<Quadrado> getListaQuadrados() {
        return listaQuadrados;
    }

    public void setListaQuadrados(List<Quadrado> listaQuadrados) {
        this.listaQuadrados = listaQuadrados;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Cabeça: " + head.toString() + "Tail: " + listaQuadrados.toString();
    }
}
