package ViewLayer;

import ControllerLayer.ML;
import ModelLayer.SnakeLayer.Retangulo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Classe que representa a cena do menu do jogo.
 * Responsabilidade: Exibir o menu do jogo e capturar eventos de mouse para navegação.
 * @version 1.0 22/05/2024
 */
public class MenuScene extends Scene {
    private static MenuScene instance;
    private BufferedImage title, play, playPressed, exit, exitPressed;
    private Retangulo playRect, exitRect, titleRect;
    private BufferedImage playCurrentImage, exitCurrentImage;
    private int widthWindow;
    private int heightWindow;
    private RasterizationGraphicStrategy rasterizationGraphicStrategy;

    /**
     * Construtor privado para inicializar a cena do menu.
     * @param widthWindow Largura da janela do menu.
     * @param heightWindow Altura da janela do menu.
     * @param rasterizationGraphicStrategy Estratégia de rasterização gráfica a ser usada.
     */
    private MenuScene(int widthWindow, int heightWindow, RasterizationGraphicStrategy rasterizationGraphicStrategy) {
        this.widthWindow = widthWindow + widthWindow / 40;
        this.heightWindow = heightWindow + widthWindow / 40;
        this.rasterizationGraphicStrategy = rasterizationGraphicStrategy;
        try {
            BufferedImage spritesheet = ImageIO.read(new File("assets/menuSprite.png"));
            title = spritesheet.getSubimage(0, 242, 960, 240);
            play = spritesheet.getSubimage(0, 121, 261, 121);
            playPressed = spritesheet.getSubimage(264, 121, 261, 121);
            exit = spritesheet.getSubimage(0, 0, 233, 93);
            exitPressed = spritesheet.getSubimage(264, 0, 233, 93);
        } catch (Exception e) {
            e.printStackTrace();
        }

        playCurrentImage = play;
        exitCurrentImage = exit;

        titleRect = new Retangulo(widthWindow / 4.0, heightWindow / 5.0, widthWindow / 4.0 + widthWindow / 2.0, heightWindow / 5.0 + heightWindow / 6.0);
        playRect = new Retangulo(widthWindow / 2.5, heightWindow / 3.0 + heightWindow / 8.0, widthWindow / 2.5 + widthWindow / 5.0, heightWindow / 3.0 + (heightWindow / 8.0) * 2);
        exitRect = new Retangulo(widthWindow / 2.5, heightWindow / 3.0 + (heightWindow / 8.0) * 2 + heightWindow / 40.0, widthWindow / 2.5 + widthWindow / 5.0, heightWindow / 3.0 + (heightWindow / 8.0) * 3);
    }

    /**
     * Método para obter a instância singleton da cena do menu.
     * @param width Largura da janela do menu.
     * @param height Altura da janela do menu.
     * @param strategy Estratégia de rasterização gráfica a ser usada.
     * @return A instância singleton da cena do menu.
     */
    public static MenuScene getInstance(int width, int height, RasterizationGraphicStrategy strategy) {
        if (instance == null) {
            instance = new MenuScene(width, height, strategy);
        }
        return instance;
    }

    /**
     * Atualiza a cena do menu com base nos eventos de mouse.
     * @param mouseListener O listener de mouse que captura os eventos.
     */
    @Override
    public void update(ML mouseListener) {
        if (mouseListener.getX() >= playRect.getMinX() && mouseListener.getX() <= playRect.getMaxX() &&
                mouseListener.getY() >= playRect.getMinY() && mouseListener.getY() <= playRect.getMaxY()) {
            playCurrentImage = playPressed;
            if (mouseListener.isPressed()) {
                GraphicalUI.setCurrentState(1);
            }
        } else {
            playCurrentImage = play;
        }

        if (mouseListener.getX() >= exitRect.getMinX() && mouseListener.getX() <= exitRect.getMaxX() &&
                mouseListener.getY() >= exitRect.getMinY() && mouseListener.getY() <= exitRect.getMaxY()) {
            exitCurrentImage = exitPressed;
            if (mouseListener.isPressed()) {
                GraphicalUI.setCurrentState(0);
            }
        } else {
            exitCurrentImage = exit;
        }
    }

    /**
     * Desenha a cena do menu.
     * @param g O contexto gráfico a ser usado para desenhar.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(176, 248, 255));
        g.fillRect(0, 0, this.widthWindow, this.heightWindow);

        g.drawImage(title, (int) titleRect.getMinX(), (int) titleRect.getMinY(), (int) titleRect.getMaxX() - (int) titleRect.getMinX(), (int) titleRect.getMaxY() - (int) titleRect.getMinY(), null);
        g.drawImage(playCurrentImage, (int) playRect.getMinX(), (int) playRect.getMinY(), (int) playRect.getMaxX() - (int) playRect.getMinX(), (int) playRect.getMaxY() - (int) playRect.getMinY(), null);
        g.drawImage(exitCurrentImage, (int) exitRect.getMinX(), (int) exitRect.getMinY(), (int) exitRect.getMaxX() - (int) exitRect.getMinX(), (int) exitRect.getMaxY() - (int) exitRect.getMinY(), null);
    }

    /**
     * Obtém o painel da cena (não utilizado neste caso).
     * @return Sempre retorna null.
     */
    @Override
    public JPanel getPanel() {
        return null;
    }
}
