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
public class Plane {
    int x, y;
    BufferedImage pic;
    int weaponType;
    int strength;
    boolean fire;
    
    public Plane() {
        try {
            x = y = 0;
            pic = ImageIO.read(new File("Pics/Plane.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(Plane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getX() {
        return this.x; }
    
    public int getY() {
        return this.y; }
    
    public void setX (int xx) {
        this.x = xx; }
    
    public void setY(int yy) {
        this.y = yy; }
    
    public int getWeap() {
        return this.weaponType; }
    
    public void setWeap (int type) {
        this.weaponType = type; }
    
    public int getStrength() {
        return this.strength; }
    
    public void setStrength(int ouch) {
        this.strength = ouch; }
    
    
   
}
