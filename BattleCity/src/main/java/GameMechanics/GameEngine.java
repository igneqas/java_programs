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

import static ExtraUtilities.Constants.*;

public class GameEngine implements Observer, BulletList {

    public List<EntityWithHealth> blocks;
    private boolean startGame;
    private PlayerTank playerTank;
    private AITank enemyTank;
    private Timer timer;
    private List<Explosion> explosions;
    private CollisionUtility collisionUtility;
    private KeyboardMonitor keyboardMonitor;
    private GameScene gameScene;

    public GameEngine() {
        keyboardMonitor = new KeyboardMonitor();
        keyboardMonitor.AddObserver(this);
        gameScene = new GameScene(keyboardMonitor);
        checkForGameStart();

        blocks = new ArrayList<>();
        explosions = new ArrayList<>();
        createGameEntities();
        addTimer();
    }

    private void addTimer() {
        timer = new Timer(STANDARD_GAME_ENGINE_TIMER_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGameEntityStates();
                gameScene.updateScene(getAllEntities());
                checkGameOver();
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
    }

    private void createGameEntities() {
        AbstractEntityFactory factory = EntityFactoryProvider.getFactory("EntityWithHealth");
        assert factory != null;
        for (int i = 0; i < MAP.length; i++) {
            for (int j = 0; j < MAP[0].length; j++) {
                switch (MAP[i][j]) {
                    case '#':
                        blocks.add((EntityWithHealth) factory.createEntity("Border", j * STANDARD_BLOCK_WIDTH, i * STANDARD_BLOCK_WIDTH));
                        break;
                    case '&':
                        blocks.add((EntityWithHealth) factory.createEntity("Brick", j * STANDARD_BLOCK_WIDTH, i * STANDARD_BLOCK_WIDTH));
                        break;
                    case '%':
                        blocks.add((EntityWithHealth) factory.createEntity("Leaves", j * STANDARD_BLOCK_WIDTH, i * STANDARD_BLOCK_WIDTH));
                        break;
                    case '@':
                        playerTank = (PlayerTank) factory.createEntity("PlayerTank", j * STANDARD_BLOCK_WIDTH, i * STANDARD_BLOCK_WIDTH);
                        break;
                    case '+':
                        blocks.add((EntityWithHealth) factory.createEntity("Steel", j * STANDARD_BLOCK_WIDTH, i * STANDARD_BLOCK_WIDTH));
                        break;
                    case '*':
                        enemyTank = (AITank) factory.createEntity("AITank", j * STANDARD_BLOCK_WIDTH, i * STANDARD_BLOCK_WIDTH, BASIC_TANK_DIRECTION_CHANGE_INTERVAL);
                        break;
                    default:
                        break;
                }
            }
        }
        initializeRequiredUtilities();
    }

    private void initializeRequiredUtilities() {
        collisionUtility = new CollisionUtility(blocks, explosions, getBlocksAndTanks());
        playerTank.initializeTankMovement(collisionUtility, enemyTank);
        enemyTank.initializeTankMovement(collisionUtility, playerTank);
    }

    private void updateGameEntityStates() {
        updateEnemyTank();
        updateBullets();
        updateExplosionAnimation();
    }

    private void updateEnemyTank() {
        enemyTank.decideAction();
    }

    private void updateBullets() {
        for (Bullet b : bullets) {
            b.move();
        }
        collisionUtility.checkCollisionBetweenBulletAndOtherEntity();
    }

    private void updateExplosionAnimation() {
        for (Explosion e : explosions) {
            e.updateImage();
        }
    }

    private void checkGameOver() {
        if (!playerTank.isVisible() || !enemyTank.isVisible()) {
            System.out.println("Game over");
            timer.stop();
            determineWinner();
        }
    }

    private void determineWinner() {
        if (!enemyTank.isVisible()) {
            gameScene.drawGameOver("Player");
        } else {
            gameScene.drawGameOver("AI");
        }
    }

    private void checkForGameStart() {
        while (!startGame) {
            System.out.println("Press enter");
        }
    }

    @Override
    public void processKeyInput(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == ESC_KEY_CODE) {
            System.exit(0);
        } else if (keyEvent.getKeyCode() == ENTER_KEY_CODE) {
            startGame = true;
        } else {
            playerTank.processUserCommand(keyEvent);
        }
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
