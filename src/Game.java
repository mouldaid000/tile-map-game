import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.net.URL;

/**
 * Created by mouldaid000 on 4/10/2017.
 */
public class Game extends Canvas implements KeyListener {

    private Image sprite;

    private BufferStrategy strategy;

    static boolean left, right, up, down;
    private Map map;
    private Entity player;

    public static void main(String[] args) {
        new Game();
    }

    public Game() {
        try{
            URL url = Thread.currentThread().getContextClassLoader().getResource("res/sprite.png");
            if(url == null){
                System.err.println("Unable to find sprite: res/sprite.png");
                System.exit(0);
            }
            sprite = ImageIO.read(url);
        }catch (IOException e){
            System.err.println("Unable to load sprite: res/sprite.gif");
            System.exit(0);
        }
        Frame frame = new Frame("Tile Map Game");
        frame.setLayout(null);
        setBounds(0, 0, 500, 500);
        frame.add(this);
        frame.setSize(500, 500);
        frame.setResizable(false);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.addKeyListener(this);
        addKeyListener(this);
        frame.setVisible(true);

        createBufferStrategy(2);
        strategy = getBufferStrategy();
        map = new Map();
        player = new Entity(sprite, map, 1.5f, 1.5f);
        loop();
    }
    public void loop(){
        boolean gameRunning = true;
        long last = System.nanoTime();
        while(gameRunning){
            Graphics2D g = (Graphics2D)strategy.getDrawGraphics();
            g.setColor(Color.black);
            g.fillRect(0,0,500,500);
            g.translate(100,100);
            map.paint(g);
            player.paint(g);

            g.dispose();
            strategy.show();
            try{Thread.sleep(4);} catch (Exception e){}

            long delta = (System.nanoTime()-last)/ 1000000;
            last = System.nanoTime();
            for(int i = 0; i < delta / 5; i++){
                logic(5);
            }
            if((delta % 5) != 0){
                logic(delta%5);
            }
        }
    }

    public void logic(long delta){
        float dx = 0;
        float dy = 0;
        if(left){
            dx -=1;
        }
        if(right){
            dx+=1;
        }
        if(up){
            dy-=1;
        }
        if(down){
            dy+=1;
        }
        if((dx!=0) || (dy != 0)){
            player.move(dx*delta*0.003f, dy * delta * 0.003f);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //unused
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            left = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            up = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            right = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            left = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            up = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            right = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            down = false;
        }
    }
}
