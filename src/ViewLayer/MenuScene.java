package ViewLayer;

import ControllerLayer.ML;
import ModelLayer.SnakeLayer.Retangulo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Classe responsável pela tela do menu
 * Responsabilidade: Gerenciar a tela do menu, entradas do usuario e renderizacao do estado do jogo.
 * @version 1.0 12/05/2024
 * @author Hugo Conceicao, Joao Ventura, Eduarda Pereira
 * @inv O jogo termina quando a cobra colide com um obstaculo, com ela mesma, ou a comida acaba.
 */
public class MenuScene extends Scene {
    private static MenuScene instance;
    private BufferedImage title,play,playPressed,exit,exitPressed;
    private Retangulo playRect,exitRect,titleRect;
    private BufferedImage playCurrentImage, exitCurrentImage;
    private int widthWindow;
    private int heightWindow;
    private RasterizationGraphicStrategy rasterizationGraphicStrategy;
    public MenuScene(int widthWindow, int heightWindow, RasterizationGraphicStrategy rasterizationGraphicStrategy) {
        this.widthWindow = widthWindow + widthWindow/40;
        this.heightWindow = heightWindow + widthWindow/40;
        this.rasterizationGraphicStrategy = rasterizationGraphicStrategy;
        try {
            BufferedImage spritesheet = ImageIO.read(new File("assets/menuSprite.png"));
            title = spritesheet.getSubimage(0, 242, 960, 240);
            play = spritesheet.getSubimage(0, 121, 261, 121);
            playPressed = spritesheet.getSubimage(264, 121, 261, 121);
            exit = spritesheet.getSubimage(0, 0, 233, 93);
            exitPressed = spritesheet.getSubimage(264, 0, 233, 93);

        } catch(Exception e) {
            e.printStackTrace();
        }

        playCurrentImage = play;
        exitCurrentImage = exit;

        titleRect = new Retangulo(widthWindow/4.0, heightWindow/5.0, widthWindow/4.0 + widthWindow/2.0, heightWindow/5.0 + heightWindow/6.0);
        playRect = new Retangulo(widthWindow/2.5, heightWindow/3.0 + heightWindow/8.0, widthWindow/2.5 + widthWindow/5.0, heightWindow/3.0 + (heightWindow/8.0)*2);
        exitRect = new Retangulo(widthWindow/2.5, heightWindow/3.0 + (heightWindow/8.0)*2 + heightWindow/40.0, widthWindow/2.5 + widthWindow/5.0, heightWindow/3.0 + (heightWindow/8.0)*3);
    }


    public static MenuScene getInstance(int width, int height, RasterizationGraphicStrategy strategy) {
        if (instance == null) {
            instance = new MenuScene(width, height, strategy);
        }
        return instance;
    }

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

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(176, 248, 255));
        g.fillRect(0, 0, this.widthWindow, this.heightWindow);

        g.drawImage(title, (int)titleRect.getMinX(), (int)titleRect.getMinY(), (int)titleRect.getMaxX() - (int) titleRect.getMinX(), (int)titleRect.getMaxY() - (int) titleRect.getMinY(), null);
        g.drawImage(playCurrentImage, (int)playRect.getMinX(), (int)playRect.getMinY(), (int)playRect.getMaxX() - (int)playRect.getMinX(), (int)playRect.getMaxY() - (int)playRect.getMinY(), null);
        g.drawImage(exitCurrentImage, (int)exitRect.getMinX(), (int)exitRect.getMinY(), (int)exitRect.getMaxX() - (int)exitRect.getMinX(), (int)exitRect.getMaxY() - (int)exitRect.getMinY(), null);
    }


    public static MenuScene getInstance() {
        return instance;
    }

    public static void setInstance(MenuScene instance) {
        MenuScene.instance = instance;
    }

    public BufferedImage getTitle() {
        return title;
    }

    public void setTitle(BufferedImage title) {
        this.title = title;
    }

    public BufferedImage getPlay() {
        return play;
    }

    public void setPlay(BufferedImage play) {
        this.play = play;
    }

    public BufferedImage getPlayPressed() {
        return playPressed;
    }

    public void setPlayPressed(BufferedImage playPressed) {
        this.playPressed = playPressed;
    }

    public BufferedImage getExit() {
        return exit;
    }

    public void setExit(BufferedImage exit) {
        this.exit = exit;
    }

    public BufferedImage getExitPressed() {
        return exitPressed;
    }

    public void setExitPressed(BufferedImage exitPressed) {
        this.exitPressed = exitPressed;
    }

    public Retangulo getPlayRect() {
        return playRect;
    }

    public void setPlayRect(Retangulo playRect) {
        this.playRect = playRect;
    }

    public Retangulo getExitRect() {
        return exitRect;
    }

    public void setExitRect(Retangulo exitRect) {
        this.exitRect = exitRect;
    }

    public Retangulo getTitleRect() {
        return titleRect;
    }

    public void setTitleRect(Retangulo titleRect) {
        this.titleRect = titleRect;
    }

    public BufferedImage getPlayCurrentImage() {
        return playCurrentImage;
    }

    public void setPlayCurrentImage(BufferedImage playCurrentImage) {
        this.playCurrentImage = playCurrentImage;
    }

    public BufferedImage getExitCurrentImage() {
        return exitCurrentImage;
    }

    public void setExitCurrentImage(BufferedImage exitCurrentImage) {
        this.exitCurrentImage = exitCurrentImage;
    }

    public int getHeightWindow() {
        return heightWindow;
    }

    public void setHeightWindow(int heightWindow) {
        this.heightWindow = heightWindow;
    }

    public int getWidthWindow() {
        return widthWindow;
    }

    public void setWidthWindow(int widthWindow) {
        this.widthWindow = widthWindow;
    }

    public RasterizationGraphicStrategy getRasterizationGraphicStrategy() {
        return rasterizationGraphicStrategy;
    }

    public void setRasterizationGraphicStrategy(RasterizationGraphicStrategy rasterizationGraphicStrategy) {
        this.rasterizationGraphicStrategy = rasterizationGraphicStrategy;
    }

    @Override
    public JPanel getPanel() {
        return null;
    }
}
