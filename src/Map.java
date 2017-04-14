import java.awt.*;

/**
 * Created by mouldaid000 on 4/10/2017.
 */
public class Map {
    private static final int CLEAR = 0;
    private static final int BLOCKED = 1;
    private static final int MAP_WIDTH = 15;
    private static final int MAP_HEIGHT = 15;
    public static final int TILE_SIZE = 20;
    private int[][] map = new int[MAP_WIDTH][MAP_HEIGHT];

    public Map(){
        for(int c = 0; c < MAP_HEIGHT; c++){
            map[0][c] = BLOCKED;
            map[2][c] = BLOCKED;
            map[7][c] = BLOCKED;
            map[11][c] = BLOCKED;
            map[MAP_WIDTH - 1][c] = BLOCKED;
        }
        for(int r = 0; r < MAP_WIDTH; r++){
            if((r > 0) && (r < MAP_WIDTH-1)){
                map[r][10] = CLEAR;
            }
            if((r > 0) && (r < MAP_WIDTH-1)){
                map[r][10] = CLEAR;
            }
            map[r][0] = BLOCKED;
            map[r][MAP_HEIGHT -1] = BLOCKED;
        }
        map[4][9] = CLEAR;
        map[7][5] = CLEAR;
        map[7][4] = CLEAR;
        map[11][7] = CLEAR;
    }
    public void paint(Graphics2D g){
        for(int x = 0; x < MAP_WIDTH; x++){
            for(int y = 0;y < MAP_HEIGHT; y++){
                g.setColor(Color.DARK_GRAY);
                if(map[x][y] == BLOCKED){
                    g.setColor(Color.gray);
                }
                g.fillRect(x*TILE_SIZE, y*TILE_SIZE, TILE_SIZE, TILE_SIZE);
                g.setColor(g.getColor().darker());
                g.drawRect(x*TILE_SIZE, y*TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    public boolean blocked(float x, float y){
        return map[(int) x][(int)y] == BLOCKED;
    }
}
