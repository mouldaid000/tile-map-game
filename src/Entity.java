import java.awt.*;

/**
 * Created by mouldaid000 on 4/10/2017.
 */
public class Entity {
    private float x;
    private float y;
    private Image image;
    private Map map;
    private float ang;
    private float size = 0.3f;

    public Entity(Image image, Map map, float x, float y){
        this.image = image;
        this.map = map;
        this.x = x;
        this.y = y;
    }

    public boolean move(float dx, float dy){
        float nx = x + dx;
        float ny = y + dy;
        if(validLocation(nx,ny)){
            x = nx;
            y = ny;
            ang = (float)(Math.atan2(dy,dx)-(Math.PI/2));
            return true;
        }
        return false;
    }
    public boolean validLocation(float nx, float ny){
        if(map.blocked(nx - size,ny - size)){
            return false;
        }
        if(map.blocked(nx + size,ny - size)){
            return false;
        }
        if(map.blocked(nx - size,ny + size)){
            return false;
        }
        if(map.blocked(nx + size,ny + size)){
            return false;
        }

        return true;
    }
    public  void paint(Graphics2D g){

        int xp = (int)(Map.TILE_SIZE * x);
        int yp = (int)(Map.TILE_SIZE * y);
        g.rotate(ang, xp, yp);
        g.drawImage(image,(int)(xp-16), (int)(yp-16),null);
        g.rotate(-ang,xp,yp);
    }
}
