package cs493project;

import java.awt.Point;
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
public class Plane extends Point implements Constants, Runnable {
    Plane plane;
    BufferedImage pic;
    int weaponType;
    int strength;
    int height, width;
    boolean fire, noPaint;
    
    public Plane(boolean paint) {
        this.plane = this;
        this.noPaint = paint;
        try {
            x = 500; 
            y = 700;            
            weaponType = LASER;
            strength = MIN;
            fire = false;
            pic = ImageIO.read(new File("Pics/Plane_mod3.png"));
            height = pic.getHeight();
            width = pic.getWidth();
        } catch (IOException ex) {
            Logger.getLogger(Plane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run () {
        noPaint = true;
        
        try {
            Thread.sleep(1000);           
        } catch (InterruptedException ex) {
            Logger.getLogger(Plane.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.plane = new Plane(false);
    }

    public int getWeap() 
    { return this.weaponType; }
    
    public void setWeap (int type) {
        this.weaponType = type; }
    
    public int getStrength() {
        return this.strength; }
    
    public void setStrength(int ouch) {
        this.strength = ouch; }
}
