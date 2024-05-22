package ControllerLayer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Classe que lida com eventos de mouse
 * Responsabilidade: Monitorar o estado do mouse (pressionado ou não) e registrar a posição do cursor
 * @version 1.0 22/05/2024
 */
public class ML extends MouseAdapter implements MouseMotionListener {
    public boolean isPressed = false;
    public double x = 0.0, y = 0.0;

    @Override
    public void mousePressed(MouseEvent e) {
        isPressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isPressed = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX(); // Atualiza a coordenada x com a posição atual do mouse
        this.y = e.getY(); // Atualiza a coordenada y com a posição atual do mouse
    }

    /**
     * Obtém a coordenada x atual do cursor do mouse
     * @return a coordenada x atual do cursor do mouse
     */
    public double getX() { return this.x; }

    /**
     * Obtém a coordenada y atual do cursor do mouse
     * @return a coordenada y atual do cursor do mouse
     */
    public double getY() { return this.y; }

    /**
     * Verifica se o botão do mouse está pressionado
     * @return true se o botão do mouse está pressionado, caso contrário false
     */
    public boolean isPressed() { return this.isPressed; }
}
