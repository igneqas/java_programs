package GameMechanics;

import GameMechanics.KeyboardInput.KeyboardMonitor;
import GameObjects.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static ExtraUtilities.Constants.*;

public class GameScene extends JFrame implements BulletList {

    private Image doubleBuffer;

    public GameScene(KeyboardMonitor keyboardMonitor) throws HeadlessException {
        this.addKeyListener(keyboardMonitor);
        setPlayground();
        drawGameStart(getGraphics());
    }

    public void setPlayground() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Battle city");
        setPreferredSize(new Dimension(STANDARD_WINDOW_DIMENSION, STANDARD_WINDOW_DIMENSION));
        getContentPane().setBackground(Color.BLACK);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void updateScene(List<Entity> entities) {
        Graphics g = getGraphics();
        Dimension size = getSize();
        if (doubleBuffer == null ||
                doubleBuffer.getWidth(this) != size.width ||
                doubleBuffer.getHeight(this) != size.height) {
            doubleBuffer = createImage(size.width, size.height);
        }
        if (doubleBuffer != null) {
            Graphics g2 = doubleBuffer.getGraphics();
            paint(g2, entities);
            g2.dispose();
            g.drawImage(doubleBuffer, 0, 0, null);
        } else {
            paint(g, entities);
        }
    }

    public void paint(Graphics g, List<Entity> entities) {
        super.paint(g);
        drawObjects(g, entities);
    }

    private void drawObjects(Graphics g, List<Entity> entities) {
        for (Entity b : entities) {
            if (b.isVisible()) {
                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
    }

    public void drawGameOver(String winner) {
        Graphics g = getGraphics();
        try {
            g.setFont(loadFont(STANDARD_FONT_SIZE));
            g.setColor(Color.YELLOW);
            if (winner.equals("Player")) {
                g.drawString("YOU WON", GAME_OVER_TEXT_X_COORDINATE, GAME_OVER_TEXT_Y_COORDINATE);
            } else {
                g.drawString("YOU LOST", GAME_OVER_TEXT_X_COORDINATE, GAME_OVER_TEXT_Y_COORDINATE);
            }
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public void drawGameStart(Graphics g) {
        try {
            g.setFont(loadFont(STANDARD_FONT_SIZE));
            g.setColor(Color.YELLOW);
            g.drawString("PRESS ENTER", GAME_START_FIRST_LINE_TEXT_X_COORDINATE, GAME_START_FIRST_LINE_TEXT_Y_COORDINATE);
            g.drawString("TO START", GAME_START_SECOND_LINE_TEXT_X_COORDINATE, GAME_START_SECOND_LINE_TEXT_Y_COORDINATE);
            g.setFont(loadFont(SMALL_FONT_SIZE));
            g.drawString("Esc to close window", GAME_START_THIRD_LINE_TEXT_X_COORDINATE, GAME_START_THIRD_LINE_TEXT_Y_COORDINATE);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public static Font loadFont(int fontSize) throws IOException, FontFormatException {
        Font font;
        font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
                new File("prstart.ttf"));
        font = font.deriveFont(java.awt.Font.PLAIN, fontSize);
        GraphicsEnvironment ge
                = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        return font;
    }
}
