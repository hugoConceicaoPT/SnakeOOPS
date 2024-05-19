package ViewLayer;

import ControllerLayer.ML;
import ModelLayer.SnakeLayer.Retangulo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class MenuScene extends Scene {
    private BufferedImage title,play,playPressed,exit,exitPressed;
    private Retangulo playRect,exitRect,titleRect;
    private BufferedImage playCurrentImage, exitCurrentImage;
    private int widthWindow;
    private int heightWindow;
    private RasterizationGraphicStrategy rasterizationGraphicStrategy;
    public MenuScene(int widthWindow, int heightWindow, RasterizationGraphicStrategy rasterizationGraphicStrategy) {
        this.widthWindow = widthWindow;
        this.heightWindow = heightWindow;
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

    @Override
    public void update(ML mouseListener) {
        if (mouseListener.getX() >= playRect.getMinX() && mouseListener.getX() <= playRect.getMaxX() &&
                mouseListener.getY() >= playRect.getMinY() && mouseListener.getY() <= playRect.getMaxY()) {
            playCurrentImage = playPressed;
            if (mouseListener.isPressed()) {
                GraphicalUI.getInstance(rasterizationGraphicStrategy).changeState(1);
            }
        } else {
            playCurrentImage = play;
        }

        if (mouseListener.getX() >= exitRect.getMinX() && mouseListener.getX() <= exitRect.getMaxX() &&
                mouseListener.getY() >= exitRect.getMinY() && mouseListener.getY() <= exitRect.getMaxY()) {
            exitCurrentImage = exitPressed;
            if (mouseListener.isPressed()) {
                GraphicalUI.getInstance(null).close();
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

    @Override
    public JPanel getPanel() {
        return null;
    }
}
