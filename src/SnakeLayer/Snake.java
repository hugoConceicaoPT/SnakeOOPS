package SnakeLayer;

import java.util.List;

public class Snake {
    private Quadrado head;
    private List<Quadrado> listaQuadrados;
    
    public Snake(List<Quadrado> listaQuadrados) {
        this.listaQuadrados = listaQuadrados;
        this.head = listaQuadrados.get(0);
    }

    public void increaseSize() {}
    public boolean collidedWithHerself() {return true;}
    public void move() {}
}
