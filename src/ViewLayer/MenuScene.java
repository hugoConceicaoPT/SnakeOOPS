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
                GraphicalUI.setCurrentState(2);
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
     * Atualiza a instância da classe MenuScene
     * @param instance a nova instância da classe MenuScene
     */
    public static void setInstance(MenuScene instance) {
        MenuScene.instance = instance;
    }

    /**
     * imagem com o titulo do jogo
     * @return a imagem com o titulo do jogo
     */
    public BufferedImage getTitle() {
        return title;
    }

    /**
     * Atualiza a imagem titulo
     * @param title a nova imagem titulo
     */
    public void setTitle(BufferedImage title) {
        this.title = title;
    }

    /**
     * Obtém a imagem play
     * @return a imagem play
     */
    public BufferedImage getPlay() {
        return play;
    }

    /**
     * Atualiza a imagem play
     * @param play a nova imagem play
     */
    public void setPlay(BufferedImage play) {
        this.play = play;
    }

    /**
     * Obtém a imagem playPressed
     * @return a imagem playPressed
     */
    public BufferedImage getPlayPressed() {
        return playPressed;
    }

    /**
     * Atualiza a imagem playPressed
     * @param playPressed a nova imagem playPressed
     */
    public void setPlayPressed(BufferedImage playPressed) {
        this.playPressed = playPressed;
    }

    /**
     * Obtém a imagem exit
     * @return a imagem exit
     */
    public BufferedImage getExit() {
        return exit;
    }

    /**
     * Atualiza a imagem exit
     * @param exit a nova imagem exit
     */
    public void setExit(BufferedImage exit) {
        this.exit = exit;
    }

    /**
     * Obtém a imagem exitPressed
     * @return a imagem exitPressed
     */
    public BufferedImage getExitPressed() {
        return exitPressed;
    }

    /**
     * Atualiza a imagem exitPressed
     * @param exitPressed a nova imagem exitPressed
     */
    public void setExitPressed(BufferedImage exitPressed) {
        this.exitPressed = exitPressed;
    }


    /**
     * Obtém o retângulo delimitador do botão "Play".
     * @return O retângulo delimitador do botão "Play".
     */
    public Retangulo getPlayRect() {
        return playRect;
    }

    /**
     * Define o retângulo delimitador do botão "Play".
     * @param playRect O novo retângulo delimitador do botão "Play".
     */

    public void setPlayRect(Retangulo playRect) {
        this.playRect = playRect;
    }

    /**
     * Obtém o retângulo delimitador do título.
     * @return O retângulo delimitador do título.
     */
    public Retangulo getTitleRect() {
        return titleRect;
    }

    /**
     * Define o retângulo delimitador do título.
     * @param titleRect O novo retângulo delimitador do título.
     */
    public void setTitleRect(Retangulo titleRect) {
        this.titleRect = titleRect;
    }

    /**
     * Obtém o retângulo delimitador do botão "Exit".
     * @return O retângulo delimitador do botão "Exit".
     */
    public Retangulo getExitRect() {
        return exitRect;
    }

    /**
     * Define o retângulo delimitador do botão "Exit".
     * @param exitRect O novo retângulo delimitador do botão "Exit".
     */
    public void setExitRect(Retangulo exitRect) {
        this.exitRect = exitRect;
    }

    /**
     * Obtém a imagem atual do botão "Play".
     * @return A imagem atual do botão "Play".
     */
    public BufferedImage getPlayCurrentImage() {
        return playCurrentImage;
    }

    /**
     * Define a imagem atual do botão "Play".
     * @param playCurrentImage A nova imagem atual do botão "Play".
     */
    public void setPlayCurrentImage(BufferedImage playCurrentImage) {
        this.playCurrentImage = playCurrentImage;
    }

    /**
     * Obtém a imagem atual do botão "Exit".
     * @return A imagem atual do botão "Exit".
     */
    public BufferedImage getExitCurrentImage() {
        return exitCurrentImage;
    }

    /**
     * Define a imagem atual do botão "Exit".
     * @param exitCurrentImage A nova imagem atual do botão "Exit".
     */
    public void setExitCurrentImage(BufferedImage exitCurrentImage) {
        this.exitCurrentImage = exitCurrentImage;
    }

    /**
     * Obtém a largura da janela do menu.
     * @return A largura da janela do menu.
     */
    public int getWidthWindow() {
        return widthWindow;
    }

    /**
     * Define a largura da janela do menu.
     * @param widthWindow A nova largura da janela do menu.
     */
    public void setWidthWindow(int widthWindow) {
        this.widthWindow = widthWindow;
    }

    /**
     * Obtém a altura da janela do menu.
     * @return A altura da janela do menu.
     */
    public int getHeightWindow() {
        return heightWindow;
    }

    /**
     * Define a altura da janela do menu.
     * @param heightWindow A nova altura da janela do menu.
     */
    public void setHeightWindow(int heightWindow) {
        this.heightWindow = heightWindow;
    }

    /**
     * Obtém a estratégia de rasterização gráfica.
     * @return A estratégia de rasterização gráfica.
     */
    public RasterizationGraphicStrategy getRasterizationGraphicStrategy() {
        return rasterizationGraphicStrategy;
    }

    /**
     * Define uma nova estratégia de rasterização gráfica.
     * @param rasterizationGraphicStrategy A nova estratégia de rasterização gráfica.
     */
    public void setRasterizationGraphicStrategy(RasterizationGraphicStrategy rasterizationGraphicStrategy) {
        this.rasterizationGraphicStrategy = rasterizationGraphicStrategy;
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
