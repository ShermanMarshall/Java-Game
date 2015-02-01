/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cs.pkg493.project;

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
    public String title = "title";
    public GameWindow window;
    public BufferedImage map;
    public BufferedImage enemyMap;
    public Plane plane;
    public ArrayList <Enemy> enemies;
    
    public CSGame() {
        
        enemies = new ArrayList<Enemy>(10);
        enemies.add(new Enemy(plane));
        plane = new Plane();
        try {
            map = ImageIO.read(new File("map3.jpg"));
            enemyMap = ImageIO.read(new File("smallestInvaders.jpg"));
            
        } catch (IOException ex) {
            Logger.getLogger(CSGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
