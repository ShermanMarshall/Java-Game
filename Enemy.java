/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cs.pkg493.project;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author sherman
 */
public class Enemy implements Runnable {
    Plane plane;
    int x, y;
    int type;
    int picx1, picx2, picy1, picy2;
    public BufferedImage image; 
    public boolean destroyed;
    
    public Enemy (Plane player) {
        this.plane = player;
        this.type = (int) (0 + Math.random() * ((8 - 0) + 1));
        int x = 0;
        int y = 0;
        
        try {
            image = ImageIO.read(new File("smallestInvaders.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch (this.type) {
            case 0: 
                picx1 = 13;
                picx2 = 70;
                picy1 = 25;
                picy2 = 63;               
                break;
            case 1:
                picx1 = 75;
                picx2 = 125;
                picy1 = 22;
                picy2 = 63;
                break;
            case 2:
                picx1 = 138;
                picx2 = 182;
                picy1 = 25;
                picy2 = 60;
                break;
            case 3:
                picx1 = 19;
                picx2 = 66;
                picy1 = 77;
                picy2 = 120;
                break;
            case 4:
                picx1 = 76;
                picx2 = 126;
                picy1 = 78;
                picy2 = 120;
                break;
            case 5:
                picx1 = 138;
                picx2 = 186;
                picy1 = 85;
                picy2 = 115;
                break;
            case 6:
                picx1 = 19;
                picx2 = 66;
                picy1 = 142;
                picy2 = 175;
                break;
            case 7:
                picx1 = 75;
                picx2 = 130;
                picy1 = 140;
                picy2 = 177;
                break;
            case 8:
                picx1 = 141;
                picx2 = 182;
                picy1 = 140;
                picy2 = 178;
                break;
            default:
                picx1 = 0;
                picx2 = 0;                      
                picy1 = 0;
                picy2 = 0;
                break; 
        }
    }  
    
    public void run() {
        while (!destroyed) {
            //do stuff
        }
        
    }   
    
    public void movement (int diff) {
        int xadjust = Xdirection();
        int yadjust = Ydirection();
        
        if (xadjust > 1)
            diff++;
    }
    
    public boolean proximity () {
        if ((plane.x - this.x > 100) | (plane.y - this.y > 100)) 
            return false;
        else 
            return true;
    }
    
    public int Xdirection () {
        if (proximity()) {
            if (plane.x - this.x > 0)
                return 1;            
            else
                return 0;
        }
        else 
            return 2;       
    }
    
    public int Ydirection () {
        if (proximity()) {
            if (plane.y - this.y > 0)
                return 1;
            else
                return 0;
        }
        else 
            return 2;
    }
    
    
}


