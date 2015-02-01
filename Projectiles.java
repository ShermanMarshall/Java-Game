/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cs.pkg493.project;

/**
 *
 * @author sherman
 */
public class Projectiles {
    int x;
    int y;
    int pow;
    int type; 
    
    public Projectiles (int xcoord, int ycoord, int strength, int var) {
        this.x = xcoord;       
        this.y = ycoord;
        this.pow = strength;
        this.type = var;
    }
    
    
}
