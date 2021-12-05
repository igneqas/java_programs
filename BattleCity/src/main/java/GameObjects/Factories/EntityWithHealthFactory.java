package GameObjects.Factories;

import GameObjects.*;
import GameObjects.TankRelated.AITank;
import GameObjects.TankRelated.PlayerTank;

public class EntityWithHealthFactory extends AbstractEntityFactory {


    @Override
    public Entity createEntity(String entityType, int x, int y) {
        switch (entityType) {
            case "Border":
                return new Border(y, x);
            case "Brick":
                return new Brick(y, x);
            case "Leaves":
                return new Leaves(y, x);
            case "Steel":
                return new Steel(y, x);
            case "PlayerTank":
                return new PlayerTank(y, x);
            default:
                return null;
        }
    }

    @Override
    public Entity createEntity(String entityType, int x, int y, char direction) {
        return null;
    }

    @Override
    public Entity createEntity(String entityType, int x, int y, int directionChangeInterval) {
        return new AITank(y, x, directionChangeInterval);
    }
}
