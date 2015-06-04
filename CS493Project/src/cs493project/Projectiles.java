package cs493project;

import java.awt.Point;
import java.awt.Shape;
import java.awt.image.BufferedImage;

/**
 *
 * @author sherman
 */
public class Projectiles extends Point implements Constants {
    public int pow;
    public int type;
    public boolean enemyFire;
    public boolean neg;
    public boolean north;
    public Shape shape;
    public BufferedImage image;        
    public int deltaX, deltaY;
    public Point location;
    public double pX, pY, eX, eY, theta, hyp;
    
    public Projectiles (int planeX, int planeY, int enX, int enY, int strength, int var, boolean ff) {
        pX = planeX;
        pY = planeY;
        eX = enX;
        eY = enY;       
        this.pow = strength;
        this.type = var;        
        
        if (!ff) {
            deltaY = -15;
            this.y = planeY;
            this.x = planeX;
        }
        else {
            enemyFire = ff;
            this.y = enY;
            this.x = enX;
            hyp = (int) Math.sqrt((Math.pow(planeX - enX, 2)) + (Math.pow(planeY - enY, 2)));
                       
            if (enY <  planeY) {
                theta = (int) Math.toDegrees(Math.acos(((planeY - enY) / hyp)));
                north = true;
            }
            else {
                theta = (int) Math.toDegrees(Math.asin(((planeX - enX) / hyp)));
                north = false;
            }
           
            if (enX < planeX)
                neg = false;
            else 
                neg = true;
        }             
    }
    
    public boolean update (int planeX, int planeY, int width, int height) {
            switch (this.type) {
                case ENEMYPRO:
                    if (north) {                                                
                        this.deltaY = (int) (EPROSPEED *(Math.cos(Math.toRadians(theta))));
                        this.deltaX = (int) (EPROSPEED * (Math.sin(Math.toRadians(theta))));
                        if (neg)
                            this.deltaX *= -1;
                    }
                    else {
                        this.deltaX = (int) (EPROSPEED * (Math.cos(Math.toRadians(theta))));
                        this.deltaY = (int) (EPROSPEED * (Math.sin(Math.toRadians(theta))));
                        if (neg)
                            this.deltaX *= -1;
                    }
                    
                    this.x += deltaX;
                    this.y += deltaY;
                    
                    if ((this.x >planeX) && (this.x < planeX + width))
                        if ((this.y > planeY) && (this.y < planeY + height)) {
                            return true;
                        }
                    else
                            return false;                            
                    break;
                case LASER: 
                        if (this.y + deltaY <= 0)
                            this.y = 0;
                        else
                            this.y += deltaY;
                        break;
            }        
            return false;
    }      
}