package GameObjects.TankRelated;

import GameMechanics.CollisionService;

import java.awt.*;

public class TankMovement {
    private Tank tank;
    private Tank enemyTank;
    private CollisionService collisionService;

    public TankMovement(Tank tank, Tank enemyTank, CollisionService collisionService) {
        this.tank = tank;
        this.enemyTank = enemyTank;
        this.collisionService = collisionService;
    }

    public void move(){
        switch (tank.direction) {
            case 'u':
                moveUp();
                break;
            case 'd':
                moveDown();
                break;
            case 'l':
                moveLeft();
                break;
            case 'r':
                moveRight();
                break;
            default:
                break;
        }
    }

    private void moveUp() {
        if(!collisionService.checkCollisionBetweenTankAndBlock(new Rectangle(tank.getX(),tank.getY()-1,tank.getWidth(),tank.getHeight())) && !collisionService.checkCollisionBetweenTanks(new Rectangle(tank.getX(),tank.getY()-1,tank.getWidth(),tank.getHeight()),enemyTank)) {
            tank.setY(tank.getY()-1);
        }
    }

    private void moveDown() {
        if(!collisionService.checkCollisionBetweenTankAndBlock(new Rectangle(tank.getX(),tank.getY()+1,tank.getWidth(),tank.getHeight()))&& !collisionService.checkCollisionBetweenTanks(new Rectangle(tank.getX(),tank.getY()+1,tank.getWidth(),tank.getHeight()),enemyTank)){
            tank.setY(tank.getY()+1);
        }
    }

    private void moveLeft() {
        if(!collisionService.checkCollisionBetweenTankAndBlock(new Rectangle(tank.getX()-1,tank.getY(),tank.getWidth(),tank.getHeight())) && !collisionService.checkCollisionBetweenTanks(new Rectangle(tank.getX()-1,tank.getY(),tank.getWidth(),tank.getHeight()),enemyTank)) {
            tank.setX(tank.getX()-1);
        }
    }

    private void moveRight() {
        if(!collisionService.checkCollisionBetweenTankAndBlock(new Rectangle(tank.getX()+1,tank.getY(),tank.getWidth(),tank.getHeight())) && !collisionService.checkCollisionBetweenTanks(new Rectangle(tank.getX()+1,tank.getY(),tank.getWidth(),tank.getHeight()),enemyTank)) {
            tank.setX(tank.getX()+1);
        }
    }
}
