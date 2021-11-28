package GameMechanics;

import GameObjects.*;
import GameObjects.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameEngine extends JFrame implements KeyListener {

    public static ArrayList<Entity> entities;
    private Tank playerTank;
    private TankAI enemyTank;
    private JFrame f;
    private Timer timer;
    ArrayList<Bullet> allBullets;
    private Image doubleBuffer;
    private ArrayList<Explosion> explosions;

    public GameEngine() {
        entities = new ArrayList<>();
        allBullets = new ArrayList<>();
        explosions = new ArrayList<>();
        initializeObjects();
        setPlayground();
        addTimer();
        super.addKeyListener(this);
    }

    public void update(Graphics g) {
        Dimension size = getSize();
        if (doubleBuffer == null ||
                doubleBuffer.getWidth(this) != size.width ||
                doubleBuffer.getHeight(this) != size.height)
        {
            doubleBuffer = createImage(size.width, size.height);
        }
        if (doubleBuffer != null) {
            Graphics g2 = doubleBuffer.getGraphics();
            paint(g2);
            g2.dispose();
            g.drawImage(doubleBuffer, 0, 0, null);
        }
        else {
            paint(g);
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawObjects(g);
    }

    private void addTimer(){
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTanks();
                update(getGraphics());
                checkGameOver(getGraphics());
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
    }

    private void initializeObjects(){
        for (int i=0; i<Map.map.length; i++){
            for(int j=0; j<Map.map[0].length; j++){
                switch (Map.map[i][j]){
                    case '#':
                        entities.add(new Border(i*16,j*16));
                        break;
                    case '&':
                        entities.add(new Brick(i*16,j*16));
                        break;
                    case '%':
                        entities.add(new Leaves(i*16,j*16));
                        break;
                    case '@':
                        playerTank = new Tank(i*16,j*16);
                        break;
                    case '+':
                        entities.add(new Steel(i*16, j*16));
                        break;
                    case '*':
                        enemyTank = new TankAI(i*16, j*16);
                        break;
                    default:
                        break;
                }
            }
        }
        CollisionService.initializeCollisionService(entities, explosions, enemyTank);
        playerTank.setEnemyTank(enemyTank);
        enemyTank.setEnemyTank(playerTank);
    }

    private void setPlayground(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Battle city");
        setPreferredSize(new Dimension(550,550));
        getContentPane().setBackground(Color.BLACK);
        setLayout(new java.awt.GridLayout(1, 0));
        super.pack();
        super.setLocationRelativeTo(null);
        super.setVisible(true);
    }

    private void drawObjects(Graphics g) {
        for(Entity b : entities){
            if(b.isVisible()){
                g.drawImage(b.getImage(),b.getX(),b.getY(),this);
            }
        }
        for(Bullet b : allBullets){
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
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 27){
            System.exit(0);
        }
        else {playerTank.keyPressed(e);}
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void updateTanks(){
        /*ArrayList<Object> allObjects = objects;
        allObjects.add(playerTank);
        allObjects.add(enemyTank);*/
        enemyTank.decideMove(playerTank);
        allBullets.removeAll(allBullets);
        allBullets.addAll(playerTank.getBullets());
        allBullets.addAll(enemyTank.getBullets());
        for(Bullet b : allBullets){
            b.move();
        }
        CollisionService.checkCollisionBetweenBulletAndObject(allBullets);
        CollisionService.checkCollisionBetweenBulletAndTank(enemyTank.getBullets(), playerTank);
        CollisionService.checkCollisionBetweenBulletAndTank(playerTank.getBullets(), enemyTank);

        for(Explosion e : explosions){
            e.updateImage();
        }
    }

    private void checkGameOver(Graphics g){
        if(!playerTank.isVisible() || !enemyTank.isVisible()) {
            System.out.println("Game over");
            timer.stop();
            try {
                g.setFont(loadFont());
                g.setColor(Color.YELLOW);
                if(!playerTank.isVisible()) {
                    g.drawString("YOU LOST", 100, 300);
                } else{
                    g.drawString("YOU WON", 100, 300);
                }
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }
        }
    }

    public static Font loadFont() throws IOException, FontFormatException {
        Font font = null;
            font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
                    new File("prstart.ttf"));
            font = font.deriveFont(java.awt.Font.PLAIN, 40);
            GraphicsEnvironment ge
                    = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

        return font;
    }
}
