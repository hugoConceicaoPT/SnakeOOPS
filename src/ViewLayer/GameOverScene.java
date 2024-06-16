package ViewLayer;

import ControllerLayer.Leaderboard;
import ControllerLayer.ML;

import javax.swing.*;
import java.awt.*;

public class GameOverScene extends Scene{

    private static GameOverScene instance;
    private Leaderboard leaderboard;
    private int widthWindow;
    private int heightWindow;

    public GameOverScene(int widthWindow, int heightWindow) {
        this.widthWindow = widthWindow;
        this.heightWindow = heightWindow;
    }

    public static GameOverScene getInstance(int widthWindow,int heightWindow) {
        if (instance == null) {
            instance = new GameOverScene(widthWindow,heightWindow);
        }
        return instance;
    }

    public static void setInstance(GameOverScene instance) {
        GameOverScene.instance = instance;
    }

    @Override
    public void update(ML mouseListener) {

    }

    @Override
    public void setLeaderboard(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(new Color(0,0,0));
        g2D.fillRect(0,0,this.widthWindow,this.heightWindow);
        int x;
        int y;
        String text;
        g2D.setFont(g2D.getFont().deriveFont(Font.BOLD,50f));
        text = "Game Over";
        x = this.widthWindow/2;
        y = this.heightWindow/2;
        g2D.setColor(Color.white);
        g2D.drawString(text,x-this.widthWindow/4,y-this.heightWindow/4);

        if (leaderboard != null) {
            g2D.setFont(g2D.getFont().deriveFont(Font.BOLD, 20f));
            String leaderboardText = leaderboard.generateLeaderboard();
            g2D.setColor(Color.white);
            int lineHeight = g2D.getFontMetrics().getHeight();
            x = this.widthWindow/2 - this.widthWindow/4;
            y = this.heightWindow / 2 + this.heightWindow/4;
            for (String line : leaderboardText.split("\n")) {
                g2D.drawString(line, x, y);
                y += lineHeight;
            }
        }
    }

    @Override
    public JPanel getPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                draw(g);
            }
        };
        panel.setPreferredSize(new Dimension(widthWindow, heightWindow));
        return panel;
    }
}
