package GameMechanics;

import GameObjects.Factories.AbstractEntityFactory;
import GameObjects.Factories.EntityFactoryProvider;
import GameMechanics.KeyboardInput.KeyboardMonitor;
import GameMechanics.KeyboardInput.Observer;
import GameObjects.*;
import GameObjects.TankRelated.AITank;
import GameObjects.TankRelated.PlayerTank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GameEngine extends JFrame implements Observer, BulletList {

    public List<EntityWithHealth> entitiesWithHealth;
    private boolean startGame;
    private PlayerTank playerTank;
    private AITank enemyTank;
    private JFrame f;
    private Timer timer;
    //private List<Bullet> allBullets;
    private Image doubleBuffer;
    private List<Explosion> explosions;
    private CollisionService collisionService;
    private KeyboardMonitor keyboardMonitor;
    private GameScene gameScene;

    public GameEngine() {
        keyboardMonitor = new KeyboardMonitor();
        keyboardMonitor.AddObserver(this);
        gameScene = new GameScene(keyboardMonitor);
        checkForGameStart();
        entitiesWithHealth = new ArrayList<>();
        explosions = new ArrayList<>();
        //gameScene.setPlayground();
        //gameScene.drawGameStart();

        //this.addKeyListener(keyboardMonitor);
        initializeObjects();
        //gameScene = new GameScene(entities, explosions, playerTank, enemyTank, keyboardMonitor);
        //setPlayground();
        addTimer();
        //super.addKeyListener(this);
    }

    /*public void update(Graphics g) {
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
    }*/

    private void addTimer(){
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGameObjectStates();
                //update(getGraphics());
                gameScene.updateScene(getAllEntities());
                checkGameOver();
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
    }

    private void initializeObjects(){
        AbstractEntityFactory factory = EntityFactoryProvider.getFactory("EntityWithHealth");
        assert factory != null;
        for (int i=0; i<Map.map.length; i++){
            for(int j=0; j<Map.map[0].length; j++){
                switch (Map.map[i][j]){
                    case '#':
                        entitiesWithHealth.add((EntityWithHealth) factory.createEntity("Border",j*16, i*16));//new Border(i*16,j*16));
                        break;
                    case '&':
                        entitiesWithHealth.add((EntityWithHealth) factory.createEntity("Brick",j*16, i*16));
                        break;
                    case '%':
                        entitiesWithHealth.add((EntityWithHealth) factory.createEntity("Leaves",j*16, i*16));
                        break;
                    case '@':
                        playerTank = (PlayerTank) factory.createEntity("PlayerTank",j*16, i*16);
                        break;
                    case '+':
                        entitiesWithHealth.add((EntityWithHealth) factory.createEntity("Steel",j*16, i*16));
                        break;
                    case '*':
                        enemyTank = (AITank) factory.createEntity("AITank",j*16, i*16, 30);
                        break;
                    default:
                        break;
                }
            }
        }
        collisionService = new CollisionService(entitiesWithHealth, explosions);
        playerTank.initializeTankMovement(collisionService, enemyTank);
        enemyTank.initializeTankMovement(collisionService, playerTank);

    }

    /*private void setPlayground(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Battle city");
        setPreferredSize(new Dimension(550,550));
        getContentPane().setBackground(Color.BLACK);
        setLayout(new java.awt.GridLayout(1, 0));
        super.pack();
        super.setLocationRelativeTo(null);
        super.setVisible(true);
    }*/

    /*private void drawObjects(Graphics g) {
        for(Entity b : entities){
            if(b.isVisible()){
                g.drawImage(b.getImage(),b.getX(),b.getY(),this);
            }
        }
        for(Bullet b : bullets){
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
    }*/

    private void updateGameObjectStates(){
        enemyTank.decideAction();
        //allBullets.removeAll(allBullets);
        //allBullets.addAll(playerTank.getBullets());
        //allBullets.addAll(enemyTank.getBullets());
        for(Bullet b : bullets){
            b.move();
        }
        collisionService.checkCollisionBetweenBulletAndObject();
        collisionService.checkCollisionBetweenBulletAndTank(playerTank);
        collisionService.checkCollisionBetweenBulletAndTank(enemyTank);

        for(Explosion e : explosions){
            e.updateImage();
        }
    }

    private void checkGameOver(){
        if(!playerTank.isVisible() || !enemyTank.isVisible()) {
            System.out.println("Game over");
            timer.stop();
            gameScene.drawGameOver("Player");
            /*try {
                g.setFont(loadFont());
                g.setColor(Color.YELLOW);
                if(!playerTank.isVisible()) {
                    g.drawString("YOU LOST", 100, 300);
                } else{
                    g.drawString("YOU WON", 100, 300);
                }
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }*/
        }
    }

    /*public static Font loadFont() throws IOException, FontFormatException {
        Font font = null;
            font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
                    new File("prstart.ttf"));
            font = font.deriveFont(java.awt.Font.PLAIN, 40);
            GraphicsEnvironment ge
                    = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

        return font;
    }*/

    private void checkForGameStart(){
        while(!startGame) {
            System.out.println("Press enter");
        }
    }

    @Override
    public void processKeyInput(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == 27){
            System.exit(0);
        } else if (keyEvent.getKeyCode() == 10) {
            startGame = true;
        }
        else {playerTank.processUserCommand(keyEvent);}
    }

    private List<Entity> getAllEntities() {
        List<Entity> allEntities = new ArrayList<>(entitiesWithHealth);
        allEntities.addAll(bullets);
        allEntities.add(playerTank);
        allEntities.add(enemyTank);
        allEntities.addAll(explosions);
        return allEntities;
    }
}
