package GameMechanics;

import GameObjects.Factories.AbstractEntityFactory;
import GameObjects.Factories.EntityFactoryProvider;
import GameMechanics.KeyboardInput.KeyboardMonitor;
import GameMechanics.KeyboardInput.Observer;
import GameObjects.*;
import GameObjects.TankRelated.AITank;
import GameObjects.TankRelated.PlayerTank;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GameEngine implements Observer, BulletList {

    public List<EntityWithHealth> blocks;
    private boolean startGame;
    private PlayerTank playerTank;
    private AITank enemyTank;
    private Timer timer;
    private List<Explosion> explosions;
    private CollisionService collisionService;
    private KeyboardMonitor keyboardMonitor;
    private GameScene gameScene;

    public GameEngine() {
        keyboardMonitor = new KeyboardMonitor();
        keyboardMonitor.AddObserver(this);
        gameScene = new GameScene(keyboardMonitor);
        checkForGameStart();

        blocks = new ArrayList<>();
        explosions = new ArrayList<>();
        initializeObjects();
        addTimer();
    }

    private void addTimer(){
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGameObjectStates();
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
                        blocks.add((EntityWithHealth) factory.createEntity("Border",j*16, i*16));
                        break;
                    case '&':
                        blocks.add((EntityWithHealth) factory.createEntity("Brick",j*16, i*16));
                        break;
                    case '%':
                        blocks.add((EntityWithHealth) factory.createEntity("Leaves",j*16, i*16));
                        break;
                    case '@':
                        playerTank = (PlayerTank) factory.createEntity("PlayerTank",j*16, i*16);
                        break;
                    case '+':
                        blocks.add((EntityWithHealth) factory.createEntity("Steel",j*16, i*16));
                        break;
                    case '*':
                        enemyTank = (AITank) factory.createEntity("AITank",j*16, i*16, 30);
                        break;
                    default:
                        break;
                }
            }
        }
        collisionService = new CollisionService(blocks, explosions, getBlocksAndTanks());
        playerTank.initializeTankMovement(collisionService, enemyTank);
        enemyTank.initializeTankMovement(collisionService, playerTank);

    }

    private void updateGameObjectStates(){
        enemyTank.decideAction();
        for(Bullet b : bullets){
            b.move();
        }
        collisionService.checkCollisionBetweenBulletAndOtherEntity();

        for(Explosion e : explosions){
            e.updateImage();
        }
    }

    private void checkGameOver(){
        if(!playerTank.isVisible() || !enemyTank.isVisible()) {
            System.out.println("Game over");
            timer.stop();
            gameScene.drawGameOver("Player");
        }
    }

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
        List<Entity> allEntities = new ArrayList<>(blocks);
        allEntities.addAll(bullets);
        allEntities.add(playerTank);
        allEntities.add(enemyTank);
        allEntities.addAll(explosions);
        return allEntities;
    }

    private List<EntityWithHealth> getBlocksAndTanks() {
        List<EntityWithHealth> entities = new ArrayList<>(blocks);
        entities.add(playerTank);
        entities.add(enemyTank);
        return entities;
    }
}
