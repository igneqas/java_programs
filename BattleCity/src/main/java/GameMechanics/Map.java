package GameMechanics;

public class Map {
    /*public static final int MAP_WIDTH = 16 * 33;
    public static final int MAP_HEIGHT = 16 * 30;
    private static final int ROW_SHIFT = 1;
    private static final int COL_SHIFT = 2;
    private static final int BASE_POS = 14;*/

    public static final char[][] map = {
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