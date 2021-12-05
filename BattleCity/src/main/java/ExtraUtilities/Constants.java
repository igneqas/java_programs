package ExtraUtilities;

public class Constants {
    public static final int STANDARD_GAME_ENGINE_TIMER_DELAY = 10;
    public static final int STANDARD_BLOCK_WIDTH = 16;
    public static final int BASIC_TANK_DIRECTION_CHANGE_INTERVAL = 30;
    public static final int ESC_KEY_CODE = 27;
    public static final int W_KEY_CODE = 119;
    public static final int A_KEY_CODE = 97;
    public static final int S_KEY_CODE = 115;
    public static final int D_KEY_CODE = 100;
    public static final int SPACE_KEY_CODE = 32;
    public static final int ENTER_KEY_CODE = 10;
    public static final int STANDARD_WINDOW_DIMENSION = 550;
    public static final int STANDARD_FONT_SIZE = 40;
    public static final int SMALL_FONT_SIZE = 20;
    public static final int GAME_OVER_TEXT_X_COORDINATE = 100;
    public static final int GAME_OVER_TEXT_Y_COORDINATE = 300;
    public static final int GAME_START_FIRST_LINE_TEXT_X_COORDINATE = 55;
    public static final int GAME_START_FIRST_LINE_TEXT_Y_COORDINATE = 270;
    public static final int GAME_START_SECOND_LINE_TEXT_X_COORDINATE = 115;
    public static final int GAME_START_SECOND_LINE_TEXT_Y_COORDINATE = 350;
    public static final int GAME_START_THIRD_LINE_TEXT_X_COORDINATE = 80;
    public static final int GAME_START_THIRD_LINE_TEXT_Y_COORDINATE = 500;
    public static final int AI_TANK_FIRST_RANDOMIZER_BOUND = 60;
    public static final int AI_TANK_RANDOM_DIRECTION_FREQUENCY = 31;
    public static final int AI_TANK_SECOND_RANDOMIZER_BOUND = 10;
    public static final int AI_TANK_MOVE_FREQUENCY = 3;
    public static final int TANK_SHOOT_DELAY = 3;
    public static final int BULLET_DAMAGE_TO_BORDER = 1;
    public static final int BULLET_DAMAGE_TO_BRICK = 4;
    public static final int BULLET_DAMAGE_TO_LEAVES = 10;
    public static final int BULLET_DAMAGE_TO_STEEL = 2;
    public static final char DIRECTION_UP = 'u';
    public static final char DIRECTION_DOWN = 'd';
    public static final char DIRECTION_LEFT = 'l';
    public static final char DIRECTION_RIGHT = 'r';
    public static final char TANK_FIRE = 'f';
    public static final int BULLET_COORDINATE_ADJUSTMENT_ONE = 9;
    public static final int BULLET_COORDINATE_ADJUSTMENT_TWO = 29;
    public static final int BULLET_MOVEMENT_UNIT = 3;
    public static final int STANDARD_ENTITY_HEALTH = 10;
    public static final int MINIMUM_ENTITY_HEALTH = 0;
    public static final int SINGLE_COORDINATE_UNIT = 1;
    public static final int NANOSECONDS_IN_SECOND = 1000000000;
    public static final int MICROSECONDS_IN_SECOND = 1000000;
    public static final int BULLET_DELAY_FOR_THIRD_ANIMATION_UPDATE = 750;
    public static final int BULLET_DELAY_FOR_SECOND_ANIMATION_UPDATE = 500;
    public static final int BULLET_DELAY_FOR_FIRST_ANIMATION_UPDATE = 250;

    public static final char[][] MAP = {
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', '%', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#'},
            {'#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', '%', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#'},
            {'#', '#', '%', '%', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', '%', '%', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '#', '#'},
            {'#', '#', '%', '%', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', '%', '%', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '#', '#'},
            {'#', '#', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '#', '#'},
            {'#', '#', ' ', ' ', '&', '&', ' ', ' ', '&', '&', '*', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '#', '#'},
            {'#', '#', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '#', '#'},
            {'#', '#', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', '+', '+', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '#', '#'},
            {'#', '#', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', '+', '+', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '#', '#'},
            {'#', '#', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '#', '#'},
            {'#', '#', ' ', ' ', '&', '&', ' ', ' ', '&', '&', '%', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '&', '&', '%', '%', '&', '&', ' ', ' ', '#', '#'},
            {'#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', '%', ' ', ' ', ' ', ' ', '&', '&', ' ', ' ', ' ', ' ', '%', '%', ' ', ' ', ' ', ' ', '#', '#'},
            {'#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', ' ', ' ', '%', ' ', ' ', ' ', ' ', '#', '#'},
            {'#', '#', '&', '&', ' ', ' ', '&', '&', '&', '&', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '&', '&', '&', '&', ' ', ' ', '&', '&', '#', '#'},
            {'#', '#', '+', '+', ' ', ' ', '&', '&', '&', '&', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '&', '&', '&', '&', ' ', ' ', '+', '+', '#', '#'},
            {'#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#'},
            {'#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '&', '&', '&', '&', '&', '&', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#'},
            {'#', '#', ' ', ' ', '&', '&', '%', '%', '&', '&', ' ', ' ', '&', '&', '&', '&', '&', '&', ' ', ' ', '&', '&', '%', '%', '&', '&', ' ', ' ', '#', '#'},
            {'#', '#', ' ', ' ', '&', '&', '%', '%', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', '%', '%', '&', '&', ' ', ' ', '#', '#'},
            {'#', '#', ' ', ' ', '&', '&', ' ', ' ', '&', '&', '%', '%', '&', '&', ' ', ' ', '&', '&', '%', '%', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '#', '#'},
            {'#', '#', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '#', '#'},
            {'#', '#', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '#', '#'},
            {'#', '#', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', ' ', '&', ' ', ' ', ' ', ' ', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '#', '#'},
            {'#', '#', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', ' ', '&', ' ', ' ', '&', ' ', ' ', ' ', '&', '&', ' ', ' ', '&', '&', ' ', ' ', '#', '#'},
            {'#', '#', '%', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '&', '@', ' ', '&', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', '%', '#', '#'},
            {'#', '#', '%', '%', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '&', ' ', ' ', '&', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '%', '%', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}};
}
