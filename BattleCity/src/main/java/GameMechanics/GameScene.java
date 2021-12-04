package GameMechanics;

import GameMechanics.KeyboardInput.KeyboardMonitor;
import GameObjects.*;
import GameObjects.TankRelated.AITank;
import GameObjects.TankRelated.PlayerTank;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GameScene extends JFrame implements BulletList {

    private Image doubleBuffer;
    //private List<EntityWithHealth> entities;
    //private List<Explosion> explosions;
    //private PlayerTank playerTank;
    //private AITank enemyTank;

    public GameScene(KeyboardMonitor keyboardMonitor) throws HeadlessException {
        //checkForGameStart();
        this.addKeyListener(keyboardMonitor);
        setPlayground();
        drawGameStart(getGraphics());
    }

    /*public GameScene(List<EntityWithHealth> entities, List<Explosion> explosions, PlayerTank playerTank, AITank enemyTank, KeyboardMonitor keyboardMonitor) throws HeadlessException {
        this.entities = entities;
        this.explosions = explosions;
        this.playerTank = playerTank;
        this.enemyTank = enemyTank;
        this.addKeyListener(keyboardMonitor);
        setPlayground();
    }*/

    public void setPlayground(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Battle city");
        setPreferredSize(new Dimension(550,550));
        getContentPane().setBackground(Color.BLACK);
        setLayout(new java.awt.GridLayout(1, 0));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void updateScene(List<Entity> entities) {
        Graphics g = getGraphics();
        Dimension size = getSize();
        if (doubleBuffer == null ||
                doubleBuffer.getWidth(this) != size.width ||
                doubleBuffer.getHeight(this) != size.height)
        {
            doubleBuffer = createImage(size.width, size.height);
        }
        if (doubleBuffer != null) {
            Graphics g2 = doubleBuffer.getGraphics();
            paint(g2, entities);
            g2.dispose();
            g.drawImage(doubleBuffer, 0, 0, null);
        }
        else {
            paint(g, entities);
        }
    }

    public void paint(Graphics g, List<Entity> entities) {
        super.paint(g);
        drawObjects(g, entities);
    }

    private void drawObjects(Graphics g, List<Entity> entities) {
        for(Entity b : entities){
            if(b.isVisible()){
                g.drawImage(b.getImage(),b.getX(),b.getY(),this);
            }
        }
        /*for(Bullet b : bullets){
            if(b.isVisible()) {
                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
        if(playerTank.isVisible())
            g.drawImage(playerTank.getImage(),playerTank.getX(),playerTank.getY(),this);
        if(enemyTank.isVisible())
            g.drawImage(enemyTank.getImage(),enemyTank.getX(),enemyTank.getY(),this);

        for(Explosion e : explosions){
            if(e.isVisible()){
                g.drawImage(e.getImage(),e.getX(),e.getY(),this);
            }
        }*/
    }

    public void drawGameOver(String winner) {
        Graphics g = getGraphics();
        try {
            g.setFont(loadFont(40));
            g.setColor(Color.YELLOW);
            if(winner.equals("Player")) {
                g.drawString("YOU WON", 100, 300);
            } else{
                g.drawString("YOU LOST", 100, 300);
            }
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public void drawGameStart(Graphics g) {
        try {
            g.setFont(loadFont(40));
            g.setColor(Color.YELLOW);
            g.drawString("PRESS ENTER", 55,270);
            g.drawString("TO START", 115,350);
            g.setFont(loadFont(20));
            g.drawString("Esc to close window", 80,500);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public static Font loadFont(int fontSize) throws IOException, FontFormatException {
        Font font = null;
        font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
                new File("prstart.ttf"));
        font = font.deriveFont(java.awt.Font.PLAIN, fontSize);
        GraphicsEnvironment ge
                = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        return font;
    }
}
