package cs493project;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author sherman
 */
public class CSGame {
    public String title = "Space Ace Vs. The Invaders!";
    public GameWindow window;
    public BufferedImage map;    
    public BufferedImage enemyMap;
    public Plane plane;
    public ArrayList <Enemy> enemies;
    public ArrayList <Projectiles> projectiles;
    public Dimension d;
    public final short fillMax = 250;
    public int fillLevel;
    public short lives;
    public long score;
    public boolean noPaint;
    public Enemy enemyHandler;
    
    public CSGame() {
        plane = new Plane(false);
        window = new GameWindow(this);
        enemies = new ArrayList<Enemy>(10);        
        projectiles = new ArrayList<Projectiles>();
        fillLevel = 0;
        score = 0;
        lives = 3;        
        enemyHandler = new Enemy(this);
        Thread thread = new Thread(this.window);
        thread.start();        
        Thread enemies = new Thread(this.enemyHandler);
        enemies.start();

        try {
            map = ImageIO.read(new File("Pics/map3.jpg"));
            map = map.getSubimage(0,0,this.d.width, this.d.height);
            enemyMap = ImageIO.read(new File("smallestInvaders.jpg"));            
        } catch (IOException ex) {
            Logger.getLogger(CSGame.class.getName()).log(Level.SEVERE, null, ex);
        }                  
    }    
}