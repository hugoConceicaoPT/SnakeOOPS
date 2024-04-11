package SnakeLayer;

import java.util.List;

public class Snake {
    private Quadrado head;
    private List<Quadrado> listaQuadrados;
    private Direction direction;
    
    public Snake(List<Quadrado> listaQuadrados) {
        this.listaQuadrados = listaQuadrados;
        this.head = listaQuadrados.get(0);
    }

    public void increaseSize() {
        Quadrado ultimoQuadrado = this.listaQuadrados.get(this.listaQuadrados.size()-1);
        Ponto ponto1 = ultimoQuadrado.pontos.get(0);
        Ponto ponto2 = ultimoQuadrado.pontos.get(1);
        int arestaLength = ponto1.dist(ponto2);
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
        }
        this.listaQuadrados.add(novoQuadrado);
    }
    public boolean collidedWithHerself() {return true;}
    public void move(Direction direction) {

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
}
