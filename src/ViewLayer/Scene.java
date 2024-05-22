package ViewLayer;

import ControllerLayer.ML;

import javax.swing.*;
import java.awt.Graphics;

public abstract class Scene {
    public abstract void update(ML mouseListener);
    public abstract void draw(Graphics g);
    public abstract JPanel getPanel();
}
